package com.green.gittest.eventbanner;

import com.green.gittest.common.model.ResultDto;
import com.green.gittest.eventbanner.model.GetEventBannerReq;
import com.green.gittest.eventbanner.model.GetEventBannerRes;
import com.green.gittest.eventbanner.model.PostEventBannerReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/eventBanner")
@Tag(name = "EventBanner", description = "EventBanner : CRD")
public class EventBannerController {
    private final EventBannerService service;

    @PostMapping
    public ResultDto<Long> postEventBanner(@RequestBody PostEventBannerReq p){
        service.postEventBanner(p);
        return ResultDto.resultDto(HttpStatus.OK,"이벤트 배너 등록 완료",p.getImageId());
    }
    @GetMapping
    public ResultDto<List<GetEventBannerRes>> getEventBanner(@ParameterObject @ModelAttribute GetEventBannerReq p){
        return ResultDto.resultDto(HttpStatus.OK,"이벤트 배너 불러오기 완료",service.getEventBanner(p));
    }
    @DeleteMapping
    public ResultDto<Integer> deleteEventBanner(@RequestParam(name = "imageId") Long imageId){
        return ResultDto.resultDto(HttpStatus.OK,"이벤트 배너 삭제 완료",service.deleteEventBanner(imageId));
    }
}
