package com.green.gittest.eventbanner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.green.gittest.common.GlobalConst;
import com.green.gittest.common.model.Paging;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.BindParam;

@Getter
@Setter
public class GetEventBannerReq extends Paging {
    public GetEventBannerReq(Integer page, Integer size) {
        super(page, size == null ? GlobalConst.SIZE_NULL_NUM : size);
    }
}
