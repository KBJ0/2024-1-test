package com.green.gittest.mainbanner;


import com.green.gittest.mainbanner.model.GetMainBannerRes;
import com.green.gittest.mainbanner.model.PostMainBannerReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MainBannerService {
    private final MainBannerMapper mapper;

    public long postMainBanner(PostMainBannerReq p){
        mapper.postMainBanner(p);
        return p.getImageId();
    }
    List<GetMainBannerRes> getMainBanner(Object p){
        return mapper.getMainBanner(p);
    }
    int deleteMainBanner(Long imageId){
        return mapper.deleteMainBanner(imageId);
    }

}
