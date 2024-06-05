package com.green.gittest.email;

import com.green.gittest.email.model.UserEntityEmail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MailMapper {
    UserEntityEmail getUserByEmail(String email);
}
