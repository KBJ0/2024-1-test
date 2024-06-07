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
        return service.postPet(petImage, p);
    }

    @GetMapping
    @Operation(summary = "반려동물 불러오기" , description = "반려동물 선택창에 있는 이름만 불러오기\n" +
                                                            "userId는 로그인한 유저 pk")
    public ResultDto<List<GetPetRes>> getPet(@RequestParam(name = "user_id") Long userId) {
        return service.getPet(userId);
    }

    @PatchMapping
    @Operation(summary = "반려동물 정보 수정" , description = "반려동물 상세페이지 수정, \n" +
                                                            "로그인한 유저 pk 도 필요함")
    public ResultDto<UpdatePetRes> updatePet(@RequestPart(required = false) MultipartFile petImage
            , @RequestPart UpdatePetReq p) {
        return service.updatePet(petImage, p);
    }

    @DeleteMapping
    @Operation(summary = "반려동물 삭제" , description = "등록되어 있는 반려동물 삭제\n")
    public ResultDto<Integer> deletePet(@RequestParam(name ="pet_id") Long petId){
        return service.deletePet(petId);
    }

}
