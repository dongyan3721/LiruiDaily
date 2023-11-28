package com.liruisecond.liruisecond.service;

import com.liruisecond.liruisecond.mapper.UserOperateMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UserService {
    private final SqlSession sqlSession;
    public UserService() {
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        this.sqlSession = build.openSession();
    }

    public int insertIntoUsers(String username, String password){
        int ret  = this.sqlSession.getMapper(UserOperateMapper.class).createNewUser(username, password);
        this.sqlSession.commit();
        return ret;
    }

    public String selectPasswordByUsername(String username){
        return this.sqlSession.getMapper(UserOperateMapper.class).getPasswordByUsername(username);
    }

    public void closeConn(){
        this.sqlSession.close();
    }
}
