package com.dataproject.yorha.service;

import com.dataproject.yorha.model.History;
import com.dataproject.yorha.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public List<History> findAll(){ return historyRepository.findAll(); }

    public Optional<History> findById(String idHistory){ return historyRepository.findById(idHistory); }

    public History saveHistory(History history){
        return historyRepository.save(history);
    }
}
