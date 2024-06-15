package com.green.gittest.mainbanner;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.mainbanner.model.GetMainBannerRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/mainbanner")
@Tag(name = "MainBanner", description = "MainBanner CRUD")
public class MainBannerController {
    private final MainBannerService service;

    @PostMapping
    @Operation(summary = "MainBanner 등록" , description = "MainBanner에 추가할 사진을 등록합니다. \nMultipartFile 타입으로 주세요")
    public ResultDto<Integer> postMainBanner(@RequestPart MultipartFile imageUrl){
        service.postMainBanner(imageUrl);
        return ResultDto.resultDto("SU","메인 배너 사진을 등록 하였습니다");
    }
    @GetMapping
    @Operation(summary = "MainBanner 불러오기" , description = "MainBanner에 추가한 사진을 불러옵니다")
    public ResultDto<List<GetMainBannerRes>> getMainBanner(@RequestParam Object p){
        return ResultDto.resultDto("SU","메인 배너 불러오기 완료",service.getMainBanner(p));
    }
    @DeleteMapping
    @Operation(summary = "MainBanner 삭제" , description = "MainBanner에 추가한 사진을 삭제합니다")
    public ResultDto<Integer> deleteMainBanner(@RequestParam Long imageId){
        return ResultDto.resultDto("SU","메인 배너 삭제 완료",service.deleteMainBanner(imageId));
    }
}
