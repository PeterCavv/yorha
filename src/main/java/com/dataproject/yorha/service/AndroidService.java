package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.entity.*;
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

    public Android createAndroid(AndroidDTO androidDTO){

        Android android = new Android();
        android.setName(androidDTO.getName());
        android.setShort_name(androidDTO.getShort_name());
        android.setDesc(android.getDesc());
        android.setModel(android.getModel());
        android.setType(android.getType());
        android.setType_number(android.getType_number());
        android.setState(android.getState());
        android.setAppearance(android.getAppearance());

        return androidRepository.save(android);
    }

}
