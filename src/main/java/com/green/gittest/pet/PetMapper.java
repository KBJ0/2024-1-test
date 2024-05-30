package com.green.gittest.pet;

import com.green.gittest.pet.model.GetPetRes;
import com.green.gittest.pet.model.PostPetReq;
import com.green.gittest.pet.model.UpdatePetReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    int postPet(PostPetReq p);
    int updatePet(UpdatePetReq p);

    List<GetPetRes> getPetForUserId(Long userId);

    int deletePet(Long petId);
}
