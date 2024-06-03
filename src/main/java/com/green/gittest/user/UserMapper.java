package com.green.gittest.user;

import com.green.gittest.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int postUser(SignUpPostReq p);
    User getUserByEmail(String p);
}
