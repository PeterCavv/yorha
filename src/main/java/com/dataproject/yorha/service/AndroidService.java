package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.model.*;
import com.dataproject.yorha.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AndroidService extends AndroidDTO{

    @Autowired
    private AndroidRepository androidRepository;

    @Autowired
    private AppearanceRepository appearanceRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Android> allAndroids() {
        return androidRepository.findAll();
    }

    public Optional<Android> oneAndroid(ObjectId id) {
        return androidRepository.findById(id);
    }

    public Android createAndroid(Android android){

        //Appearance newAppearance = stringToAppearance(appearance);
        //Model newModel = stringToModel(model);
        //State newState = stringToState(state);
        //Type newType = stringToType(type);

        //int typeNumberInt = Integer.parseInt(typeNumber);

        //Android android = new Android(name, shortName, newModel, newType, typeNumberInt, newAppearance, newState, desc);

        androidRepository.insert(android);

        if(android.getType().getName().equals("Operator")){
            Operator operator = new Operator(android);
            operatorService.createOperator(operator);
        }

        return android;
    }

}
