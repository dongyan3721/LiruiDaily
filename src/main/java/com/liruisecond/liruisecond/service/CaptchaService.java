package com.liruisecond.liruisecond.service;

import com.liruisecond.liruisecond.mapper.CaptchaMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class CaptchaService {
    private SqlSession sqlSession;
    public CaptchaService() {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        this.sqlSession = build.openSession();
    }

    public int insertIntoCaptchaUUIDAndValue(String uuid, String value){
        int ret =  this.sqlSession.getMapper(CaptchaMapper.class).insertIntoCaptchaUUIDAndValue(uuid, value);
        this.sqlSession.commit();
        return ret;
    }

    public String getCaptchaValueByUUID(String UUID){
        return this.sqlSession.getMapper(CaptchaMapper.class).getCaptchaValueByUUID(UUID);
    }

    public void closeConn(){
        this.sqlSession.close();
    }
}
