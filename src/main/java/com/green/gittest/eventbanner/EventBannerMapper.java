package com.green.gittest.eventbanner;

import com.green.gittest.eventbanner.model.GetEventBannerRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventBannerMapper {
    int postEventBanner(String imageName);
    List<GetEventBannerRes> getEventBanner();
    int deleteEventBanner(Long imageId);
}
