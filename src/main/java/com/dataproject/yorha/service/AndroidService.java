package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.android.AndroidCreateDTO;
import com.dataproject.yorha.DTO.android.AndroidGetDTO;
import com.dataproject.yorha.model.*;
import com.dataproject.yorha.exception.DuplicatedObjectException;
import com.dataproject.yorha.exception.ObjectAssignedException;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AndroidService extends AndroidCreateDTO {

    @Autowired
    private AndroidRepository androidRepository;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private AppearanceService appearanceService;

    @Autowired
    private ExecutionerService executionerService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private StateService stateService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private HistoryService historyService;


    public List<AndroidGetDTO> findAll() {
        return androidRepository.findAll().stream().map(AndroidGetDTO::new).toList();
    }

    public List<Android> findAllAvailable(){
        return androidRepository.findAll().stream()
                .filter(android -> android.getAssigned_operator() == null
                        && !android.getState().getName().equals("Out of service")
                        && android.getModel().getName().equals("YoRHa"))
                .toList();
    }

    public Optional<Android> findById(String id) {
        return androidRepository.findById(id);
    }

    /**
     * Method to create an Android
     * @param androidCreateDTO Object obtained from the http post petition.
     */
    public Android createAndroid(AndroidCreateDTO androidCreateDTO) {

        Android android = new Android();

        android.setDesc(androidCreateDTO.getDesc().isBlank() ? "" : androidCreateDTO.getDesc().trim());

        Model model = new Model();
        model.setId( androidCreateDTO.getModelId() );
        android.setModel(model);

        Type type = new Type();
        type.setId( androidCreateDTO.getTypeId() );
        android.setType(type);

        Appearance appearance = new Appearance();
        appearance.setId( androidCreateDTO.getAppearanceId() );
        android.setAppearance( appearance );

        android.setAssigned_operator(null);

        createAndroidName(android, androidCreateDTO);

        android.setState( stateService.getAllState().stream()
                .filter( state -> state.getName().equals("Operational") )
                .toList().get(0) );

        Android newAndroid = androidRepository.save(android);

        checkType(newAndroid, androidCreateDTO);

        return newAndroid;
    }

    /**
     * Assign an Android to an Operator. The Android will be added to the Operator's android list,
     * and the android will be assigned to an Operator.
     * @param idAndroid Android's ID
     * @param idOperator Operator's ID
     */
    public Optional<Android> addAssignedAndroid(String idAndroid, String idOperator){

        validateIdAndroid(idAndroid);
        operatorService.validateIdOperator(idOperator);

        Optional<Android> android = androidRepository.findById(idAndroid);
        Optional<Operator> operator = operatorService.findById(idOperator);

        operator.ifPresent(operator1 -> {
                List<Android> listAndroids = operator1.getAndroids();

                android.ifPresent(android1 -> {

                    checkIfDuplicate( android1, listAndroids, idOperator );

                    listAndroids.add(android1);

                    operator1.setAndroids(listAndroids);
                    android1.setAssigned_operator(operator1);

                    operatorService.saveOperator(operator1);
                    androidRepository.save(android1);
                });
        });

        return android;
    }

    /**
     * Remove the Android assigned to the Operator. The Operator assigned
     * to the Android will be removed as well.
     * @param idAndroid Android's ID.
     * @param idOperator Operator's ID.
     * @return Return an Android.
     */
    public Optional<Android> deleteAssignedAndroid( String idAndroid, String idOperator ){
        validateIdAndroid( idAndroid );
        operatorService.validateIdOperator( idOperator );

        Optional<Android> android = androidRepository.findById(idAndroid);
        Optional<Operator> operator = operatorService.findById(idOperator);

        operator.ifPresent(operator1 -> {
            List<Android> listAndroids = operator1.getAndroids().stream()
                    .filter( android1 -> !android1.getId().equals(idAndroid) ).toList();

            android.ifPresent(android1 -> {
                checkIfNotAssigned(android1, operator1);

                android1.setAssigned_operator(null);
                operator1.setAndroids( listAndroids );

                operatorService.saveOperator( operator1 );
                androidRepository.save( android1 );
            });
        });

        return android;
    }

    /**
     * Change the State of an Android to "Out of service" state.
     * @param idAndroid Android's ID.
     * @param idExecutioner Executioner's ID.
     * @return Return an Android
     */
    public Optional<Android> executeAndroid( String idAndroid, String idExecutioner ){
        validateIdAndroid(idAndroid);
        executionerService.validateIdExecutioner(idExecutioner);

        Optional<Android> android = androidRepository.findById(idAndroid);
        Optional<Executioner> executioner = executionerService.findById(idExecutioner);

        android.ifPresent(android1 -> {
            checkIfAssigned(android1);

            executioner.ifPresent(executioner1 -> {
                checkAndroidBeforeExecute(android1, executioner1);

                List<History> historyList = executioner1.getHistory();
                History element = new History();

                element.setAndroid(android1);
                element.setExecutioner(executioner1);

                element = historyService.saveHistory(element);
                historyList.add(element);

                executioner1.setHistory(historyList);

                android1.setState( stateService.getAllState().stream()
                        .filter( state -> state.getName().equals("Out of service") )
                        .toList().get(0) );

                executionerService.saveExecutioner( executioner1 );
                androidRepository.save( android1 );
            });
        });

        return android;
    }

    //FUNCTIONAL METHODS

    /**
     * Validate if the android ID exist.
     * @param idAndroid Android's ID
     */
    private void validateIdAndroid(String idAndroid){
        if( !androidRepository.existsById(idAndroid) ){
            throw new ObjectNotFoundException(
                    "Android not found with the ID: " + idAndroid );
        }
    }


    /**
     * Create the name that will be used by the android.
     * @param android Android to set the name.
     * @param androidCreateDTO Object to get the necessary data.
     */
    private void createAndroidName(Android android, AndroidCreateDTO androidCreateDTO){
        if( androidCreateDTO.getName().isBlank() ) {
            char letterType = typeService.allTypes().stream()
                    .filter( type1 -> type1.getId().equals(android.getType().getId()) )
                    .toList().get(0).getName().charAt(0);

            android.setName( "YoRHa No." + android.getType_number()
                    + " Type " + letterType );
            android.setShort_name( String.valueOf( android.getType_number() ) + letterType );
        } else {
            android.setName( androidCreateDTO.getName().trim() );
        }
    }

    /**
     * Checks if the android is an operator. If it is, an Operator will be created.
     * @param android Android created.
     * @param androidCreateDTO Object to get the necessary data.
     */
    private void checkType(Android android, AndroidCreateDTO androidCreateDTO){
        if( androidCreateDTO.isOperator() ){
            prepareOperator(android);
        } else if( androidCreateDTO.isExecutioner() ){
            prepareExecutioner(android);
        }
    }

    /**
     * Check if the Android have already an Operator or if the Operator have that Android assigned.
     * @param android Android to check.
     * @param operatorAndroids List of the Androids assigned to the Operator.
     * @param idOperator Operator's ID.
     */
    private void checkIfDuplicate(Android android, List<Android> operatorAndroids, String idOperator){
        if( android.getAssigned_operator() != null ){
            if( operatorAndroids.contains(android) || android.getAssigned_operator().getId().equals(idOperator) ){
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
            Operator operator = operatorService.allOperator().stream()
                    .filter(operator1 -> operator1.getName().getName().equals(android1.getName()))
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
