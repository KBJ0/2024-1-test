package com.green.gittest.eventbanner;


import com.green.gittest.common.CustomFileUtils;
import com.green.gittest.eventbanner.model.GetEventBannerRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventBannerService {
    private final EventBannerMapper mapper;
    private final CustomFileUtils customFileUtils;

    public int postEventBanner(MultipartFile imageUrl){
        String imageName = imageUrl.getOriginalFilename();
        String path = "eventbanner/" + imageName;
        String target = path + "/" + imageName;
        customFileUtils.makeFolders(path);
        try {
            customFileUtils.transferTo(imageUrl, target);
        } catch (IOException e) {
            log.error("파일 전송 중 오류 발생", e);
            throw new RuntimeException();
        }
        return mapper.postEventBanner(imageName);
    }
    List<GetEventBannerRes> getEventBanner(){
        return mapper.getEventBanner();
    }
    int deleteEventBanner(Long imageId){
        return mapper.deleteEventBanner(imageId);
    }

}
