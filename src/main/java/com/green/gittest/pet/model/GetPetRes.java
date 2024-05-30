package com.green.gittest.pet.model;

import lombok.Data;

@Data
public class GetPetRes {
    private long petId;
    private String petName;
    private String petCategory;
    private String petImage;
    private String petIcon;
    private String petBackColor;
    private String createdAt;
    private String updatedAt;
}
