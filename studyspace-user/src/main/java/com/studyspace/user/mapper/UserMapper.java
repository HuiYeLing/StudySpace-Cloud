package com.studyspace.user.mapper;


import com.studyspace.user.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    // 根据ID查询用户
    @Select("SELECT * FROM users WHERE id = #{id}")
    User getUserById(@Param("id") long id);
    // 根据用户名查询用户（用于登录）
    @Select("SELECT * FROM users WHERE username = #{username}")
    User getUserByUsername(@Param("username") String username);
    // 登录验证
    @Select("SELECT * FROM users WHERE username = #{username} AND password = #{password}")
    User login(@Param("username") String username, @Param("password") String password);
    // 注册用户
    @Insert("INSERT INTO users (username, password, email, created_at) VALUES (#{username}, #{password}, #{email}, #{created_at})")
    int register(User user);
    // 删除用户
    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteUser(@Param("id") long id);
    // 查询所有用户
    @Select("SELECT * FROM users")
    List<User> getAllUsers();
    @Insert("INSERT INTO users (username, password, email, created_at) VALUES (#{username}, #{password}, #{email}, #{created_at})")
    int insertUser(@Param("username") String username, @Param("password") String password, @Param("email") String email, @Param("created_at") String created_at);
    @Select("SELECT * FROM users WHERE id = #{id}")
    User getCurrentUser(@Param("id") long id); // 获取当前登录用户信息
    @Update("UPDATE user SET avatar_url = #{avatarUrl} WHERE id = #{userId}")
    void updateAvatarUrl(@Param("userId") long userId, @Param("avatarUrl") String avatarUrl);
}