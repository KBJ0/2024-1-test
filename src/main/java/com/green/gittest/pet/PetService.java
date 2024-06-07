package com.green.gittest.pet;

import com.green.gittest.common.CustomFileUtils;
import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.pet.model.*;
import jakarta.validation.constraints.Null;
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
    private final CustomFileUtils customFileUtils;


    @Transactional
    public ResultDto<PostPetRes> postPet(MultipartFile petImage, PostPetReq p){

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

        return ResultDto.resultDto("SU","반려동물 등록이 정상적으로 완료되었습니다."
                        , PostPetRes.builder()
                        .petId(p.getPetId())
                        .petImage(saveFileName)
                        .build());
    }

    public ResultDto<List<GetPetRes>> getPet(Long userId) {
        List<GetPetRes> list = mapper.getPetForUserId(userId);
        return ResultDto.resultDto("SU", "반려동물 정보를 정상적으로 불러왔습니다.", list);
    }

    @Transactional
    public ResultDto<UpdatePetRes> updatePet(MultipartFile petImage, UpdatePetReq p) {
        if (p == null) throw new NullPointerException();

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

        return ResultDto.resultDto("SU","반려동물 정보 수정이 정상적으로 완료되었습니다."
                , UpdatePetRes.builder().petImage(saveFileName).build());
    }

    public ResultDto<Integer> deletePet(Long petId) {
        if(petId == null) throw new NullPointerException();
        customFileUtils.deleteFolder("pet/" + petId);
        mapper.deletePet(petId);

        return ResultDto.resultDto("SU","반려동물 삭제가 정상적으로 완료되었습니다.");
    }
}
