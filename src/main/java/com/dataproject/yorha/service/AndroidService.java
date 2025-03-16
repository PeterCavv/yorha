package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.android.CreateAndroidDTO;
import com.dataproject.yorha.DTO.android.GetAndroidDTO;
import com.dataproject.yorha.DTO.operator.GetOperatorDTO;
import com.dataproject.yorha.model.*;
import com.dataproject.yorha.exception.DuplicatedObjectException;
import com.dataproject.yorha.exception.ObjectAssignedException;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Service
public class AndroidService extends CreateAndroidDTO {

    @Autowired
    private AndroidRepository androidRepository;

    @Autowired
    private OperatorService operatorService;
    @Autowired
    private ExecutionerService executionerService;
    @Autowired
    private StateService stateService;
    @Autowired
    private ModelService modelService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private HistoryService historyService;


    /**
     * Get all Androids.
     * @return
     */
    public List<GetAndroidDTO> findAll() {
        return androidRepository.findAll()
                .stream().map(GetAndroidDTO::new).toList();
    }

    /**
     * Get only available Androids.
     * @return
     */
    public List<GetAndroidDTO> findAllAvailable(){
        return androidRepository.findAll().stream()
        .filter(android -> android.getAssigned_operator() == null
                        && !android.getState().getName().equals("Out of service")
                        && android.getModel().getName().equals("YoRHa"))
                .map(GetAndroidDTO::new)
                .toList();
    }

    public Optional<Android> findById(String id) {
        return androidRepository.findById(id);
    }

    /**
     * Method to create an Android
     * @param createAndroidDTO android object validation
     * @return
     */
    public Android createAndroid(CreateAndroidDTO createAndroidDTO) {

        Android android = new Android();

        android.setDesc(createAndroidDTO.getDesc().isBlank() ? "" : createAndroidDTO.getDesc().trim());

        android.setModel(new Model( createAndroidDTO.getModelId() ));
        android.setType(new Type( createAndroidDTO.getTypeId() ));
        android.setAppearance(new Appearance( createAndroidDTO.getAppearanceId() ));
        android.setAssigned_operator(null);

        createAndroidName(android, createAndroidDTO);

        android.setState( stateService.getAllState().stream()
                .filter( state -> state.getName().equals("Operational") ).findFirst().orElseThrow(
                        () -> new ObjectNotFoundException("Not found an State called 'Operational'")
                ) );

        Android newAndroid = androidRepository.save(android);
        checkType(newAndroid, createAndroidDTO);

        return newAndroid;
    }

    /**
     * Assign an Android to an Operator. The Android will be added to the Operator's android list,
     * and the android will be assigned to an Operator.
     * @param idAndroid Android's ID
     * @param idOperator Operator's ID
     */
    public Optional<Android> addAssignedAndroid(String idAndroid, String idOperator){

        Android android = validateAndroidId( idAndroid );
        Operator operator = validateOperatorId( idOperator );

        List<Android> androidList = operator.getAndroids();

        checkIfDuplicate( android, androidList, operator );

        operator.getAndroids().add(android);
        android.setAssigned_operator(operator);

        operatorService.saveOperator(operator);
        androidRepository.save(android);

        return Optional.of(android);
    }

    /**
     * Remove the Android assigned to the Operator. The Operator assigned
     * to the Android will be removed as well.
     * @param idAndroid Android's ID.
     * @param idOperator Operator's ID.
     * @return Return an Android.
     */
    public Optional<Android> deleteAssignedAndroid( String idAndroid, String idOperator ){

        Android android = validateAndroidId( idAndroid );
        Operator operator = validateOperatorId( idOperator );

        checkIfNotAssigned(android, operator);

        operator.getAndroids().remove(android);
        operatorService.saveOperator(operator);

        android.setAssigned_operator(null);
        androidRepository.save(android);

        return Optional.of(android);
    }

    /**
     * Change the State of an Android to "Out of service" state.
     * @param idAndroid Android's ID.
     * @param idExecutioner Executioner's ID.
     * @return Return an Android
     */
    public Optional<Android> executeAndroid( String idAndroid, String idExecutioner ){

        Android android = validateAndroidId( idAndroid );

        Executioner executioner = validateExecutionerId( idExecutioner );

        checkIfAssigned(android);
        checkAndroidBeforeExecute(android, executioner);

        executioner.getHistory().add(new History(android, executioner, LocalDate.now()));
        executionerService.saveExecutioner(executioner);

        android.setState(stateService.getAllState().stream()
                .filter(state -> state.getName().equals("Out Of Service"))
                .findFirst().orElseThrow(
                        () -> new ObjectNotFoundException("Not found 'Out of Service' Status.")
                )
        );

        androidRepository.save(android);

        return Optional.of(android);
    }

    //FUNCTIONAL METHODS

    /**
     * Create the name that will be used by the android.
     * @param android Android to set the name.
     * @param createAndroidDTO Object to get the necessary data.
     */
    private void createAndroidName(Android android, CreateAndroidDTO createAndroidDTO){
        Model modelId = modelService.getAllModels().stream()
                .filter(model -> model.getName().equals("YoRHa"))
                .findFirst().orElseThrow(
                        () -> new ObjectNotFoundException("Not found 'YoRHa' Model.")
                );

        if( createAndroidDTO.getModelId().equals( modelId.getId() ) ) {
            char letterType = typeService.allTypes().stream()
                    .filter( type1 -> type1.getId().equals(android.getType().getId()) )
                    .toList().get(0).getName().charAt(0);

            int typeNumber = (int) androidRepository.countByType( android.getType() ) + 1;
            android.setType_number(typeNumber);
            android.setName( "YoRHa No." + android.getType_number()
                    + " Type " + letterType );
            android.setShort_name( String.valueOf( android.getType_number() ) + letterType );

        } else if( !createAndroidDTO.getName().isBlank() ){
            android.setName( createAndroidDTO.getName().trim() );

        } else {
            throw new IllegalArgumentException("Special Androids needs a name ");
        }
    }

    /**
     * Checks if the android is an operator. If it is, an Operator will be created.
     * @param android Android created.
     * @param createAndroidDTO Object to get the necessary data.
     */
    private void checkType(Android android, CreateAndroidDTO createAndroidDTO){
        if( createAndroidDTO.isOperator() ){
            prepareOperator(android);
        } else if( createAndroidDTO.isExecutioner() ){
            prepareExecutioner(android);
        }
    }

    /**
     * Check if the Android exists by its ID.
     * @param id Android's ID
     * @return Return the Android if found
     */
    private Android validateAndroidId(String id){
       return androidRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Android not found with the ID: " + id));
    }

    /**
     * Check if the Executioner exists by its ID.
     * @param id Executioner's ID
     * @return Return the Executioner if found
     */
    private Executioner validateExecutionerId(String id){
        return executionerService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Executioner not found with the ID: " + id));
    }

    /**
     * Check if the Operator exists by its ID.
     * @param id Operator's ID
     * @return Return the Operator if found
     */
    private Operator validateOperatorId(String id){
        return operatorService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Operator not found with the ID: " + id));
    }

    /**
     * Check if the Android have already an Operator or if the Operator have that Android assigned.
     * @param android Android to check.
     * @param operatorAndroids List of the Androids assigned to the Operator.
     * @param operator Operator's ID.
     */
    private void checkIfDuplicate(Android android, List<Android> operatorAndroids, Operator operator){
        if( android.getAssigned_operator() != null ){

            if( operatorAndroids.contains(android) ||
                    android.getAssigned_operator().getId().equals(operator.getName().getId()) ){

                throw new DuplicatedObjectException(
                        "Operator already have this Android assigned.");
            }

            throw new DuplicatedObjectException(
                    "Android is already assigned to an Operator");
        }


    }

    /**
     * Check if the Android and the Executioner are the same Android.
     * @param android Android's ID.
     * @param executioner Executioner.
     */
    private void checkAndroidBeforeExecute(Android android, Executioner executioner) {
        if( executioner.getName().getState().getName().equals("Out of service")){
            throw new IllegalStateException(
                    "The Android cannot be executed by an Executioner that is out of service."
            );
        }
        if( android.getState().getName().equals("Out of service") ){
            throw new IllegalStateException(
                    "The Android cannot be executed because it is out of service."
            );
        }
        if( executioner.getName().getId().equals(android.getId()) ){
            throw new UnsupportedOperationException("The Android cannot be executed by itself");
        }
    }

    /**
     * Check if the Android have an Operator assigned.
     * @param android1 Android to check.
     */
    private void checkIfAssigned(Android android1) {
        if(android1.getAssigned_operator() != null){
            throw new ObjectAssignedException(
                    "Android with ID " + android1.getId() + " have an Operator assigned."
            );
        }

        if( android1.getType().getName().equals("Operator") ){
            GetOperatorDTO operator = operatorService.allOperator().stream()
                    .filter(operator1 -> operator1.getName().equals(android1.getName()))
                    .toList().get(0);

            if( operator.getAndroids() != null ){
                throw new ObjectAssignedException(
                        "Operator with ID " + operator.getId() + " have androids assigned."
                );
            }
        }
    }

    /**
     * Check if the android doesn't have an Operator assigned.
     * @param android1 Android to check.
     */
    private void checkIfNotAssigned(Android android1, Operator operator) {
        if( android1.getAssigned_operator() == null ){
            throw new NullPointerException(
                    "The Android doesn't have an Operator to remove."
            );
        }
        if( !android1.getAssigned_operator().getId().equals(operator.getId()) ){
            throw new UnsupportedOperationException(
                    "The Android to remove have another different Operator " +
                    "Assigned"
            );
        }
    }

    /**
     * Prepare the Android to be created into Operators
     * @param android Android created
     */
    private void prepareOperator(Android android){
        Operator newOperator = new Operator();

        newOperator.setName(android);

        operatorService.createOperator( newOperator );
    }

    /**
     * Prepare the Android to be created into Executioners
     * @param android Android created
     */
    private void prepareExecutioner(Android android){
        Executioner newExecutioner = new Executioner();

        newExecutioner.setName(android);

        executionerService.createExecutioner( newExecutioner );
    }

    //END OF METHODS

}
