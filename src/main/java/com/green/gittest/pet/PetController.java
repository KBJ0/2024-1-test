package com.green.gittest.pet;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.pet.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/pet")
@Tag(name = "pet", description = "pet CRUD")
public class PetController {
    private final PetService service;

    @PostMapping
    public ResultDto<PostPetRes> postPet(@RequestPart MultipartFile petImage
            , @RequestPart PostPetReq p) {
        log.info("petImage: {}", petImage);
        log.info("p: {}", p);
        if (petImage == null || p == null) throw new NullPointerException();


        PostPetRes result = service.postPet(petImage, p);
        return ResultDto.resultDto(HttpStatus.OK, "정보 등록 완료", result);
    }

    @GetMapping
    public ResultDto<List<GetPetRes>> getPet(@RequestParam(name = "userId") Long userId) {
        log.info("userId: {}", userId);
        if (userId == null) throw new NullPointerException("유저 정보가 누락 되었습니다.");

        List<GetPetRes> result = service.getPet(userId);
        return ResultDto.resultDto(HttpStatus.OK, "정보 불러 오기.", result);
    }

    @PatchMapping
    public ResultDto<UpdatePetRes> updatePet(@RequestPart(required = false) MultipartFile petImage
            , @RequestPart UpdatePetReq p) {
        log.info("petImage: {}", petImage);
        log.info("p: {}", p);
        if (p == null) throw new NullPointerException();

        UpdatePetRes result = service.updatePet(petImage, p);
        return ResultDto.resultDto(HttpStatus.OK, "정보 수정 완료.", result);
    }

    @DeleteMapping
    public ResultDto<Integer> deletePet(@RequestParam(name ="petId") Long petId){
        log.info("petId: {}", petId);
        if(petId == null) throw new NullPointerException();
        int result = service.deletePet(petId);
        if (result == 0) return ResultDto.resultDto(HttpStatus.OK, "존재하지 않습니다.", result);
        return ResultDto.resultDto(HttpStatus.OK, "삭제 완료", result);
    }

}
