package com.green.gittest.pet;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.pet.model.*;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "반려동물 등록" , description = "반려동물 등록" )
    public ResultDto<PostPetRes> postPet(@RequestPart MultipartFile petImage
            , @RequestPart PostPetReq p) {
        log.info("petImage: {}", petImage);
        log.info("p: {}", p);
        PostPetRes result = service.postPet(petImage, p);
        return ResultDto.resultDto(HttpStatus.OK,"SU","반려동물 등록이 정상적으로 완료되었습니다.", result);
    }

    @GetMapping
    @Operation(summary = "반려동물 불러오기" , description = "반려동물 선택창에 있는 이름만 불러오기\n" +
                                                            "userId는 로그인한 유저 pk")
    public ResultDto<List<GetPetRes>> getPet(@RequestParam(name = "userId") Long userId) {
        log.info("userId: {}", userId);
        if (userId == null) throw new NullPointerException("유저 정보가 누락 되었습니다.");

        List<GetPetRes> result = service.getPet(userId);
        return ResultDto.resultDto(HttpStatus.OK, "정보 불러 오기", result);
    }

    @PatchMapping
    @Operation(summary = "반려동물 정보 수정" , description = "반려동물 상세페이지 수정, \n" +
                                                            "로그인한 유저 pk 도 필요함")
    public ResultDto<UpdatePetRes> updatePet(@RequestPart(required = false) MultipartFile petImage
            , @RequestPart UpdatePetReq p) {
        log.info("petImage: {}", petImage);
        log.info("p: {}", p);
        if (p == null) throw new NullPointerException();  

        UpdatePetRes result = service.updatePet(petImage, p);
        return ResultDto.resultDto(HttpStatus.OK, "정보 수정 완료.", result);
    }

    @DeleteMapping
    @Operation(summary = "반려동물 삭제" , description = "등록되어 있는 반려동물 삭제\n")
    public ResultDto<Integer> deletePet(@RequestParam(name ="petId") Long petId){
        log.info("petId: {}", petId);
        if(petId == null) throw new NullPointerException();
        int result = service.deletePet(petId);
        if (result == 0) return ResultDto.resultDto(HttpStatus.OK, "입력하신 펫 정보가 존재하지 않습니다.", result);
        return ResultDto.resultDto(HttpStatus.OK, "삭제 완료", result);
    }

}
