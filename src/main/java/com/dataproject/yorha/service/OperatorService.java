package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.android.GetAndroidDTO;
import com.dataproject.yorha.DTO.operator.GetOperatorDTO;
import com.dataproject.yorha.model.Android;
import com.dataproject.yorha.model.Operator;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;

    public List<GetOperatorDTO> allOperator() {
        return operatorRepository.findAll().stream().map(GetOperatorDTO::new).toList();
    }

    /**
     * Return an Optional Operator.
     * @param id operator's ID.
     * @return
     */
    public Optional<Operator> findById(String id) {
        return operatorRepository.findById( id );
    }

    public void createOperator(Operator operator){

        List<Android> androidsList = new ArrayList<>();
        operator.setAndroids( androidsList );

        saveOperator( operator );

    }

    //METHODS

    /**
     * Save an Operator into DB. This function is called from another service.
     * @param operator1
     */
    public void saveOperator(Operator operator1) {
        operatorRepository.save( operator1 );
    }
}