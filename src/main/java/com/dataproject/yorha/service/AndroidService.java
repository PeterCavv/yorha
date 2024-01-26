package com.dataproject.yorha.service;

import com.dataproject.yorha.DTO.AndroidDTO;
import com.dataproject.yorha.entity.*;
import com.dataproject.yorha.exception.ObjectNotFoundException;
import com.dataproject.yorha.repository.*;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    /**
     * Se crea un Android mediante un objeto JSON obtenido por una petición http POST
     * @param androidDTO Objeto obtenido desde la petición
     * @return
     */
    public Android createAndroid(AndroidDTO androidDTO){

        Android android = new Android();
        android.setName(androidDTO.getName());
        android.setShort_name(androidDTO.getShort_name());
        android.setDesc(androidDTO.getDesc().isEmpty() ? "" : androidDTO.getDesc());
        android.setType_number(androidDTO.getType_number());

        //Se crean las clases correspondientes que tendrán los IDs obtenidos desde la petición HTTP
        Type type = new Type();
        try{
            type = typeRepository.findAll().stream().filter(typeR -> typeR.getId() == androidDTO.getTypeId()).toList().get(0);
            android.setType(type);
        } catch (IndexOutOfBoundsException obj){
            throw new RuntimeException("Type not found with ID: " + androidDTO.getTypeId());
        }


        Appearance appearance = new Appearance();
        appearance.setId(androidDTO.getAppearanceId());
        android.setAppearance(appearance);

        State state = new State();
        state.setId(androidDTO.getStateId());
        android.setState(state);

        Model model = new Model();
        model.setId(androidDTO.getModelId());
        android.setModel(model);

        return androidRepository.save(android);
    }

}
