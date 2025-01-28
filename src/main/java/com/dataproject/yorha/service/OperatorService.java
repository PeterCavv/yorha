package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.Android;
import com.dataproject.yorha.entity.Operator;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.OperatorRepository;
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

        List<Android> androidsList = new ArrayList<>();
        operator.setAndroids( androidsList );

        saveOperator( operator );

    }

    //METHODS

    /**
     * Validate if the operator ID exist.
     * @param idOperator operator's ID
     */
    public void validateIdOperator(String idOperator){
        if( !operatorRepository.existsById(idOperator) ){
            throw new ObjectNotFoundException(
                    "Operator not found with the ID: " + idOperator );
        }
    }

    /**
     * Return an Optional Operator.
     * @param idOperator operator's ID.
     * @return
     */
    public Optional<Operator> findById(String idOperator) {
        return operatorRepository.findById( idOperator );
    }

    /**
     *
     * @param operator1
     */
    public void saveOperator(Operator operator1) {
        operatorRepository.save( operator1 );
    }
}