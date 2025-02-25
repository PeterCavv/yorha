package com.dataproject.yorha.DTO.android;

import com.dataproject.yorha.DTO.common.AppearanceDTO;
import com.dataproject.yorha.DTO.common.ModelDTO;
import com.dataproject.yorha.DTO.common.StateDTO;
import com.dataproject.yorha.DTO.common.TypeDTO;
import com.dataproject.yorha.model.Android;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class GetAndroidDTO implements Serializable {

    private String id;
    private String name;
    private String shortName;
    private int typeNumber;
    private ModelDTO model;
    private TypeDTO type;
    private AppearanceDTO appearance;
    private StateDTO state;
    private String desc;

    public GetAndroidDTO(Android android){
        this.id = android.getId();
        this.name = android.getName();
        if( android.getType() != null ) {
            this.type = new TypeDTO(android.getType());
        }
        if( android.getModel() != null ){
            this.model = new ModelDTO(android.getModel());
        }
        if( android.getAppearance() != null ){
            this.appearance = new AppearanceDTO(android.getAppearance());
        }
        if( android.getState() != null ){
            this.state = new StateDTO(android.getState());
        }
        this.desc = android.getDesc();
        this.shortName = android.getShort_name();
        this.typeNumber = android.getType_number();
    }

}
