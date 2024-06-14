package com.green.gittest.eventbanner;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.eventbanner.model.GetEventBannerReq;
import com.green.gittest.eventbanner.model.GetEventBannerRes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/eventBanner")
@Tag(name = "EventBanner", description = "EventBanner CRUD")
public class EventBannerController {
    private final EventBannerService service;

//    @PostMapping
//    @Operation(summary = "EventBanner 등록" , description = "EventBanner에 추가할 사진을 등록합니다. \nMultipartFile 타입으로 주세요")
//    public ResultDto<Integer> postEventBanner(@RequestPart MultipartFile imageUrl){
//        service.postEventBanner(imageUrl);
//        return ResultDto.resultDto("SU", "이벤트 배너 사진을 등록 하였습니다.");
//    }
    @GetMapping
    @Operation(summary = "EventBanner 불러오기" , description = "EventBanner에 추가한 사진을 불러옵니다")
    public ResultDto<List<GetEventBannerRes>> getEventBanner(@ParameterObject @ModelAttribute GetEventBannerReq p){
        return ResultDto.resultDto("SU","이벤트 배너 불러오기 완료",service.getEventBanner(p));
    }
    @DeleteMapping
    @Operation(summary = "EventBanner 삭제" , description = "EventBanner에 추가한 사진을 삭제합니다")
    public ResultDto<Integer> deleteEventBanner(@RequestParam(name = "imageId") Long imageId){
        return ResultDto.resultDto("SU","이벤트 배너 삭제 완료",service.deleteEventBanner(imageId));
    }
}
