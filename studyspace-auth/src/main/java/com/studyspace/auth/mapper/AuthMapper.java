package com.studyspace.auth.mapper;

import com.studyspace.auth.domain.User;
import org.apache.ibatis.annotations.*;
@Mapper
public interface AuthMapper {

    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO users (username, password, email, created_at, role) " +
            "VALUES (#{username}, #{password}, #{email}, #{created_at}, #{role})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int register(User user);
}