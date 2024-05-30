package com.green.gittest.eventbanner;

import com.green.gittest.eventbanner.model.GetEventBannerReq;
import com.green.gittest.eventbanner.model.GetEventBannerRes;
import com.green.gittest.eventbanner.model.PostEventBannerReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventBannerMapper {
    int postEventBanner(PostEventBannerReq p);
    List<GetEventBannerRes> getEventBanner(GetEventBannerReq p);
    int deleteEventBanner(Long imageId);
}
