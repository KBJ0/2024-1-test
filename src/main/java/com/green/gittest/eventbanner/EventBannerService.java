package com.green.gittest.eventbanner;


import com.green.gittest.eventbanner.model.GetEventBannerReq;
import com.green.gittest.eventbanner.model.GetEventBannerRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventBannerService {
    private final EventBannerMapper mapper;

    public long postEventBanner(PostEventBannerReq p){
        mapper.postEventBanner(p);
        return p.getImageId();

    }
    List<GetEventBannerRes> getEventBanner(GetEventBannerReq p){
        return mapper.getEventBanner(p);
    }
    int deleteEventBanner(Long imageId){
        return mapper.deleteEventBanner(imageId);
    }

}
