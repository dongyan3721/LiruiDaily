package com.liruisecond.liruisecond.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserOperateMapper {

    public String getPasswordByUsername(String username);

    public int createNewUser(@Param("username") String username, @Param("password") String password);
}
