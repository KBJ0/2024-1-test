package com.green.gittest.pet;

import com.green.gittest.common.CustomFileUtils;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.pet.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class PetService {
    private final PetMapper mapper;
//    private final CheckMapper checkMapper;
    private final CustomFileUtils customFileUtils;

    @Transactional
    public PostPetRes postPet(MultipartFile petImage, PostPetReq p){


        String saveFileName = customFileUtils.makeRandomFileName(petImage);
        p.setPetImage(saveFileName);

        int result = mapper.postPet(p);

        String path = String.format("pet/%d", p.getPetId());
        customFileUtils.makeFolders(path);

        String target = String.format("%s/%s", path, saveFileName);
        try {
            customFileUtils.transferTo(petImage, target); // 사진 이름 지정 후 파일에 저장
        } catch (IOException e) {
            log.error("파일 전송 중 오류 발생", e);
            throw new RuntimeException();
        }

        return PostPetRes.builder().petId(p.getPetId()).petImage(saveFileName).build();
        // 프론트가 없는 정보만 가져다 주기.
    }

    public List<GetPetRes> getPet(Long userId) {
        return mapper.getPetForUserId(userId);
    }

    @Transactional
    public UpdatePetRes updatePet(MultipartFile petImage, UpdatePetReq p) {
        String path = String.format("pet/%d", p.getPetId());
        String saveFileName = customFileUtils.makeRandomFileName(petImage);
        String target = String.format("%s/%s", path, saveFileName);

        try {
            customFileUtils.deleteFolder(path);
            customFileUtils.makeFolders(path);
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
        customFileUtils.deleteFolder("pet/" + petId);
        return mapper.deletePet(petId);
    }
}
