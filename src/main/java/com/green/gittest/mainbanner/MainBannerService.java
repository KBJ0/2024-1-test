package com.green.gittest.mainbanner;


import com.green.gittest.mainbanner.model.GetMainBannerRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MainBannerService {
    private final MainBannerMapper mapper;
    // 11
//    public int postMainBanner(MultipartFile imageUrl){
//        return mapper.postMainBanner(imageUrl);
//    }
    List<GetMainBannerRes> getMainBanner(Object p){
        return mapper.getMainBanner(p);
    }
    int deleteMainBanner(Long imageId){
        return mapper.deleteMainBanner(imageId);
    }

}
