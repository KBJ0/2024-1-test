package com.green.gittest.eventbanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PostEventBannerReq {
    @JsonIgnore
    private long imageId;
    private String imageUrl;
}
