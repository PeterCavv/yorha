package com.dataproject.yorha.DTO;

import com.dataproject.yorha.model.Appearance;
import com.dataproject.yorha.model.Model;
import com.dataproject.yorha.model.State;
import com.dataproject.yorha.model.Type;
import com.dataproject.yorha.repository.AppearanceRepository;
import com.dataproject.yorha.repository.ModelRepository;
import com.dataproject.yorha.repository.StateRepository;
import com.dataproject.yorha.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AndroidDTO {

    @Autowired
    private AppearanceRepository appearanceRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TypeRepository typeRepository;

    public Appearance stringToAppearance(String appearanceName){
        List<Appearance> appearanceList = appearanceRepository.findAll();
        for ( Appearance appearance : appearanceList ){
            if(appearance.getName().equals(appearanceName)){
                return appearance;
            }
        }

        return null;
    }

    public Model stringToModel(String modelName){
        List<Model> modelList = modelRepository.findAll();
        for ( Model model : modelList) {
            if(model.getName().equals(modelName)){
                return model;
            }
        }

        return null;
    }

    public State stringToState(String stateName){
        List<State> stateList = stateRepository.findAll();
        for ( State state : stateList ) {
            if(state.getName().equals(stateName)){
                return state;
            }
        }

        return null;
    }

    public Type stringToType(String typeName){
        List<Type> typeList = typeRepository.findAll();
        for ( Type type : typeList ){
            if(type.getName().equals(typeName)){
                return type;
            }
        }

        return null;
    }
}
