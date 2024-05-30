package com.green.gittest.mainbanner;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.mainbanner.model.GetMainBannerRes;
import com.green.gittest.mainbanner.model.PostMainBannerReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/mainBanner")
@Tag(name = "MainBanner", description = "EventBanner : CRD")
public class MainBannerController {
    private final MainBannerService service;

    @PostMapping
    public ResultDto<Long> postMainBanner(@RequestBody PostMainBannerReq p){
        service.postMainBanner(p);
        return ResultDto.resultDto(HttpStatus.OK,"메인 배너 등록 완료",p.getImageId());
    }
    @GetMapping
    public ResultDto<List<GetMainBannerRes>> getMainBanner(@RequestParam Object p){
        return ResultDto.resultDto(HttpStatus.OK,"메인 배너 불러오기 완료",service.getMainBanner(p));
    }
    @DeleteMapping
    public ResultDto<Integer> deleteMainBanner(@RequestParam Long imageId){
        return ResultDto.resultDto(HttpStatus.OK,"메인 배너 삭제 완료",service.deleteMainBanner(imageId));
    }
}
