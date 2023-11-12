package com.liruisecond.liruisecond.mapper;

import org.apache.ibatis.annotations.Param;

public interface CaptchaMapper {

    public String getCaptchaValueByUUID(@Param("id") String uuid);

    public int insertIntoCaptchaUUIDAndValue(@Param("uuid") String uuid, @Param("value") String value);
}
