package com.green.gittest.mainbanner;

import com.green.gittest.mainbanner.model.GetMainBannerRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface MainBannerMapper {
    // 11
    int postMainBanner(MultipartFile imageUrl);
    List<GetMainBannerRes> getMainBanner(Object p);
    int deleteMainBanner(Long imageId);
}
