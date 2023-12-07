package com.dataproject.yorha.DTO;

import com.dataproject.yorha.model.State;
import com.dataproject.yorha.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StateDTO {

    @Autowired
    private StateRepository stateRepository;

    public State stringToState(String stateName){
        List<State> stateList = stateRepository.findAll();
        for ( State state : stateList ) {
            if(state.getName().equals(stateName)){
                return state;
            }
        }

        return null;
    }
}
