package com.green.gittest.mainbanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostMainBannerReq {
    @JsonIgnore
    private long imageId;
    private String imageUrl;
}
