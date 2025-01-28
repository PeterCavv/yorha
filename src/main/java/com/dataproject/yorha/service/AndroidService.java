package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.entity.*;
import com.dataproject.yorha.exception.DuplicatedObjectException;
import com.dataproject.yorha.exception.ObjectAssignedException;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AndroidService extends AndroidDTO{

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


    public List<Android> findAll() {
        return androidRepository.findAll();
    }

    public Optional<Android> findById(String id) {
        return androidRepository.findById(id);
    }

    /**
     * Method to create an Android
     * @param androidDTO Object obtained from the http post petition.
     * @return
     */
    public Android createAndroid(AndroidDTO androidDTO) {

        Android android = new Android();

        validateIdAttributes(androidDTO);

        android.setDesc(androidDTO.getDesc().isBlank() ? "" : androidDTO.getDesc().trim());

        Model model = new Model();
        model.setId( androidDTO.getModelId() );
        android.setModel(model);

        Type type = new Type();
        type.setId( androidDTO.getTypeId() );
        android.setType(type);

        Appearance appearance = new Appearance();
        appearance.setId( androidDTO.getAppearanceId() );
        android.setAppearance( appearance );

        android.setType_number( androidDTO.getType_number() );

        android.setAssigned_operator(null);

        createAndroidName(android, androidDTO);

        android.setState( stateService.getAllState().stream()
                .filter( state -> state.getName().equals("Operational") )
                .toList().get(0) );

        Android newAndroid = androidRepository.save(android);

        checkType(newAndroid, androidDTO);

        return newAndroid;
    }

    /**
     * Assing an Android to an Operator. The Android will be added to the Operator's android list,
     * and the android will be assigned to an Operator.
     * @param idAndroid
     * @param idOperator
     * @return
     */
    public Optional<Android> addAssignedAndroid(String idAndroid, String idOperator){

        validateIdAndroid(idAndroid);
        operatorService.validateIdOperator(idOperator);

        Optional<Android> android = androidRepository.findById(idAndroid);
        Optional<Operator> operator = operatorService.findById(idOperator);

        operator.ifPresent(operator1 -> {
                List<Android> listAndroids = operator1.getAndroids();

                android.ifPresent(android1 -> {

                    checkIfDuplicate(android1, listAndroids, idOperator);

                    listAndroids.add(android1);

                    operator1.setAndroids(listAndroids);
                    android1.setAssigned_operator(operator1);

                    operatorService.saveOperator(operator1);
                    androidRepository.save(android1);
                });
        });

        return android;
    }

    public Optional<Android> deleteAssignedAndroid( String idAndroid, String idOperator ){
        validateIdAndroid( idAndroid );
        operatorService.validateIdOperator( idOperator );

        Optional<Android> android = androidRepository.findById(idAndroid);
        Optional<Operator> operator = operatorService.findById(idOperator);

        operator.ifPresent(operator1 -> {
            List<Android> listAndroids = operator1.getAndroids().stream()
                    .filter( android1 -> !android1.getId().equals( idAndroid ) ).toList();

            android.ifPresent(android1 -> {
                android1.setAssigned_operator(null);
                operator1.setAndroids( listAndroids );

                operatorService.saveOperator( operator1 );
                androidRepository.save( android1 );
            });
        });

        return android;
    }

    public Optional<Android> executeAndroid( String idAndroid, String idExecutioner ){
        validateIdAndroid(idAndroid);
        executionerService.validateIdExecutioner(idExecutioner);

        Optional<Android> android = androidRepository.findById(idAndroid);
        Optional<Executioner> executioner = executionerService.findById(idExecutioner);

        android.ifPresent(android1 -> {
            if(android1.getAssigned_operator() != null){
                throw new ObjectAssignedException(
                        "Android with ID " + android1.getId() + " have an Operator assigned."
                );
            }

            executioner.ifPresent(executioner1 -> {
                List<History> history = executioner1.getHistory();
                History element = new History();

                element.setAndroid(android1);
                element.setExecutioner(executioner1);

                History newElement = historyService.saveHistory(element);
                history.add(newElement);

                executioner1.setHistory(history);

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
     * @param idAndroid
     */
    private void validateIdAndroid(String idAndroid){
        if( !androidRepository.existsById(idAndroid) ){
            throw new ObjectNotFoundException(
                    "Android not found with the ID: " + idAndroid );
        }
    }

    /**
     * Method to validate the IDs of the attributes from the Android.
     * @param androidDTO Android to create obtained from the http petition.
     */
    private void validateIdAttributes(AndroidDTO androidDTO){
        typeService.validateType( androidDTO.getTypeId() );

        modelService.validateModel( androidDTO.getModelId() );

        appearanceService.validateAppearance( androidDTO.getAppearanceId() );
    }

    /**
     * Create the name that will be used by the android.
     * @param android
     * @param androidDTO
     */
    private void createAndroidName(Android android, AndroidDTO androidDTO){
        if( androidDTO.getName().isBlank() ) {
            char letterType = typeService.allTypes().stream()
                    .filter( type1 -> type1.getId().equals(android.getType().getId()) )
                    .toList().get(0).getName().charAt(0);

            android.setName( "YoRHa No." + android.getType_number()
                    + " Type " + letterType );
            android.setShort_name( String.valueOf( android.getType_number() ) + letterType );
        } else {
            android.setName( androidDTO.getName().trim() );
        }
    }

    /**
     * Checks if the android is an operator. If it is, an Operator will be created.
     * @param android Android created.
     * @param androidDTO
     */
    private void checkType(Android android, AndroidDTO androidDTO){
        if( androidDTO.isOperator() ){
            prepareOperator(android);
        } else if( androidDTO.isExecutioner() ){
            prepareExecutioner(android);
        }
    }

    /**
     * Check if the Android have
     * @param android
     * @param operatorAndroids
     * @param idOperator
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
