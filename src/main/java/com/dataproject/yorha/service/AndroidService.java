package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.entity.*;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AndroidService extends AndroidDTO{

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private AndroidRepository androidRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Autowired
    private OperatorService operatorService;

    public List<Android> allAndroids() {
        return androidRepository.findAll();
    }

    public Optional<Android> oneAndroid(ObjectId id) {
        return androidRepository.findById(id);
    }

    /**
     * Se crea un Android mediante un objeto JSON obtenido por una petición http POST
     * @param androidDTO Objeto obtenido desde la petición
     * @return
     */
    public Android createAndroid(AndroidDTO androidDTO){

        Android android = new Android();
        android.setDesc(androidDTO.getDesc().isEmpty() ? "" : androidDTO.getDesc());
        android.setType_number(androidDTO.getType_number());

        Model model = new Model();
        try {
            model = modelRepository.findAll().stream().filter(modelR -> modelR.getId()
                    .equals(androidDTO.getModelId())).toList().get(0);
            android.setModel(model);
        } catch (IndexOutOfBoundsException obj) {
            throw new RuntimeException("Model not found with ID: " + androidDTO.getModelId());
        }

        Type type = new Type();
        try {
            type = typeRepository.findAll().stream().filter(typeR -> typeR.getId()
                    .equals(androidDTO.getTypeId())).toList().get(0);
            android.setType(type);
        } catch (IndexOutOfBoundsException obj) {
            throw new RuntimeException("Type not found with ID: " + androidDTO.getTypeId());
        }

        Appearance appearance = new Appearance();
        appearance.setId(androidDTO.getAppearanceId());
        android.setAppearance(appearance);

        //Si el modelo del androide es YoRHa, se creará su nombre en valor a sus parámetros.
        //En caso contrario, debería haberse obtenido su nombre en el front.
        if(android.getModel().getName().equals("YoRHa")) {
            android.setName(android.getModel().getName() + " No. " + android.getType_number()
                    + " Type " + android.getType().getName().charAt(0));
            android.setShort_name(String.valueOf(android.getType_number()) + android.getType().getName().charAt(0));
        } else {
            android.setName(androidDTO.getName());
        }

        android.setState(stateRepository.findAll().stream().filter(state -> state.getName()
                .equals("Operational")).toList().get(0));

        return androidRepository.save(android);
    }

}
