package com.green.gittest.eventbanner;

import com.green.gittest.eventbanner.model.GetEventBannerReq;
import com.green.gittest.eventbanner.model.GetEventBannerRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface EventBannerMapper {
    int postEventBanner(String imageName);
    List<GetEventBannerRes> getEventBanner(GetEventBannerReq p);
    int deleteEventBanner(Long imageId);
}
