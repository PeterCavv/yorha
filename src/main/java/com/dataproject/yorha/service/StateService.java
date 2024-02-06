package com.dataproject.yorha.service;

import com.dataproject.yorha.entity.State;
import com.dataproject.yorha.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateService {
    @Autowired
    private StateRepository stateRepository;

    public List<State> getAllState(){ return stateRepository.findAll(); }
}
