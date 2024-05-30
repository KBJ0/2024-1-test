package com.green.gittest.pet.model;

import lombok.Data;

@Data
public class UpdatePetReq {
    private long petId;
    private long userId;
    private String petName;
    private String petCategory;
    private String petImage;
    private String petIcon;
    private String petBackColor;
}
