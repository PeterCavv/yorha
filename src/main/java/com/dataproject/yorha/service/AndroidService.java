package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.entity.*;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AndroidService extends AndroidDTO{

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private AndroidRepository androidRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private AppearanceRepository appearanceRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private OperatorService operatorService;

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

        //Check if the attributes related with data from other documents of the BDD exists in it.
        validateIdAttributes(androidDTO);

        //Check if the description of the android is blank or not.
        android.setDesc(androidDTO.getDesc().isBlank() ? "" : androidDTO.getDesc().trim());

        Model model = new Model();
        model.setId(androidDTO.getModelId());
        android.setModel(model);

        Type type = new Type();
        type.setId(androidDTO.getTypeId());
        android.setType(type);

        Appearance appearance = new Appearance();
        appearance.setId( androidDTO.getAppearanceId() );
        android.setAppearance( appearance );

        android.setType_number( androidDTO.getType_number() );

        //If there is no name for the Android, that means the Android provided is a YoRHa model,
        //then the name will be created here.
        if( androidDTO.getName().isBlank() ) {
            char letterType = typeRepository.findAll().stream()
                    .filter( type1 -> type1.getId().equals(android.getType().getId()) )
                    .toList().get(0).getName().charAt(0);

            android.setName( "YoRHa No." + android.getType_number()
                    + " Type " + letterType );
            android.setShort_name( String.valueOf( android.getType_number() ) + letterType );
        } else {
            android.setName( androidDTO.getName().trim() );
        }

        //This is to set the state 'Operational' to the Android.
        android.setState( stateRepository.findAll().stream()
                .filter( state -> state.getName().equals("Operational") )
                .toList().get(0) );

        //Save the Android and put the return value to a new variable because,
        //if we need to create an Operator, we need the ID of it.
        Android newAndroid = androidRepository.save(android);

        //If the Android created is an Operator type, then it will be created too, but at the
        //Operators document of the BDD.
        if(androidDTO.isOperator()){
            Operator newOperator = new Operator();

            newOperator.setName(newAndroid);

            //Create an empty ArrayList to the androids of the Operator.
            List<Android> androidsList = new ArrayList<>();
            newOperator.setAndroids( androidsList );

            operatorService.createOperator( newOperator );

        }

        //Return the Android created.
        return newAndroid;
    }

    /**
     * Method to validate the IDs of the attributes from the Android.
     * @param androidDTO Android to create obtained from the http petition.
     */
    private void validateIdAttributes(AndroidDTO androidDTO){
        if( !typeRepository.existsById( androidDTO.getTypeId() ) ){
            throw new ObjectNotFoundException( "Type not found with the ID: " + androidDTO.getTypeId() );
        }

        if( !modelRepository.existsById( androidDTO.getModelId() ) ){
            throw new ObjectNotFoundException( "Model not found with the ID: " + androidDTO.getTypeId() );
        }

        if( !appearanceRepository.existsById( androidDTO.getAppearanceId() ) ){
            throw new ObjectNotFoundException( "Appearance not found with the ID: " + androidDTO.getTypeId() );
        }
    }

}
