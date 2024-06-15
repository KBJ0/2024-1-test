package com.green.gittest.mainbanner;


import com.green.gittest.common.CustomFileUtils;
import com.green.gittest.mainbanner.model.GetMainBannerRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class MainBannerService {
    private final MainBannerMapper mapper;
    private final CustomFileUtils customFileUtils;

    public int postMainBanner(MultipartFile imageUrl){
        String imageName = imageUrl.getOriginalFilename();
        String path = "mainbanner/" + imageName;
        String target = path + "/" + imageName;
        customFileUtils.makeFolders(path);
        try {
            customFileUtils.transferTo(imageUrl, target);
        } catch (IOException e) {
            log.error("파일 전송 중 오류 발생", e);
            throw new RuntimeException();
        }

        return mapper.postMainBanner(imageName);
    }
    List<GetMainBannerRes> getMainBanner(){
        return mapper.getMainBanner();
    }
    int deleteMainBanner(Long imageId){
        return mapper.deleteMainBanner(imageId);
    }

}
