package com.green.gittest.mainbanner;

import com.green.gittest.mainbanner.model.GetMainBannerRes;
import com.green.gittest.mainbanner.model.PostMainBannerReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainBannerMapper {
    int postMainBanner(PostMainBannerReq p);
    List<GetMainBannerRes> getMainBanner(Object p);
    int deleteMainBanner(Long imageId);
}
