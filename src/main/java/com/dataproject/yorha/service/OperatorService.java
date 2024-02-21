package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.Android;
import com.dataproject.yorha.entity.Operator;
import com.dataproject.yorha.repository.OperatorRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;

    public List<Operator> allOperator() {
        return operatorRepository.findAll();
    }

    public Optional<Operator> oneOperator(String id) {
        return operatorRepository.findById(id);
    }

    public void createOperator(Operator operator){
        operatorRepository.save(operator);

    }
}