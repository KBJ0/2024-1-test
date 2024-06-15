package com.green.gittest.mainbanner;

import com.green.gittest.mainbanner.model.GetMainBannerRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Mapper
public interface MainBannerMapper {
    int postMainBanner(String imageName);
    List<GetMainBannerRes> getMainBanner();
    int deleteMainBanner(Long imageId);
}
