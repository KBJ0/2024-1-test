package com.green.gittest.pet;

import com.green.gittest.common.CustomFileUtils;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.pet.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PetService {
    private final PetMapper mapper;
    private final CustomFileUtils customFileUtils;

    public PostPetRes postPet(MultipartFile petImage, PostPetReq p){

        if (petImage == null || petImage.isEmpty()) {
            mapper.postPet(p);
            return PostPetRes.builder().petId(p.getPetId()).build();
        }
        String path = String.format("pet/%d", p.getPetId());
        customFileUtils.makeFolders(path);

        String saveFileName = customFileUtils.makeRandomFileName(petImage);
        String target = String.format("%s/%s", path, saveFileName);
        try {
            customFileUtils.transferTo(petImage, target); // 사진 이름 지정 후 파일에 저장
        } catch (IOException e) {
            log.error("파일 전송 중 오류 발생", e);
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
        p.setPetImage(saveFileName);

        mapper.postPet(p);
        return PostPetRes.builder().petId(p.getPetId()).petImage(saveFileName).build();
        // 프론트가 없는 정보만 가져다 주기.
    }

    public List<GetPetRes> getPet(Long userId) {
        List<GetPetRes> list = mapper.getPetForUserId(userId);
        if(list == null || list.isEmpty()) {return null;} //없는 유저일 경우 값 반환
        return list;
    }

    public UpdatePetRes updatePet(MultipartFile petImage, UpdatePetReq p) {
        if (petImage == null || petImage.isEmpty()) {
            mapper.updatePet(p);
            return null;
        }
        String path = String.format("pet/%d", p.getPetId());
        customFileUtils.deleteFolder(path);

        customFileUtils.makeFolders(path);

        String saveFileName = customFileUtils.makeRandomFileName(petImage);
        String target = String.format("%s/%s", path, saveFileName);
        try {
            customFileUtils.transferTo(petImage, target); // 사진 이름 지정 후 파일에 저장
        } catch (IOException e) {
            log.error("파일 전송 중 오류 발생", e);
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
        p.setPetImage(saveFileName);
        mapper.updatePet(p);
        return UpdatePetRes.builder().petImage(saveFileName).build();
    }

    public int deletePet(Long petId) {
        customFileUtils.deleteFolder("pet/"+petId);
        return mapper.deletePet(petId);
    }
}
