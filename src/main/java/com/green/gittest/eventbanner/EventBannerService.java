package com.green.gittest.eventbanner;


import com.green.gittest.eventbanner.model.GetEventBannerReq;
import com.green.gittest.eventbanner.model.GetEventBannerRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventBannerService {
    private final EventBannerMapper mapper;
    // 1
//    public int postEventBanner(MultipartFile imageUrl){
//        return mapper.postEventBanner(imageUrl);
//    }
    List<GetEventBannerRes> getEventBanner(GetEventBannerReq p){
        return mapper.getEventBanner(p);
    }
    int deleteEventBanner(Long imageId){
        return mapper.deleteEventBanner(imageId);
    }

}
