package com.green.gittest.pet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostPetReq {
    @JsonIgnore private long petId;
    private long userId;
    private String petName;
    private String petCategory;
    @JsonIgnore private String petImage;
    private String petIcon;
    private String petBackColor;
}
