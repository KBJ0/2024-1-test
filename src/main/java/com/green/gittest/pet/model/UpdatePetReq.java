package com.green.gittest.pet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdatePetReq {
    private long petId;
    private String petName;
    private String petCategory;
    @JsonIgnore private String petImage;
    private int petIcon;
    private String petBackColor;
}
