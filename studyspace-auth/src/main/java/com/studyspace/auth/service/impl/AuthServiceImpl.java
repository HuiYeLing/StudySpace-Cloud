package com.studyspace.auth.service.impl;


import com.studyspace.auth.domain.User;
import com.studyspace.auth.mapper.AuthMapper;

import com.studyspace.auth.service.AuthService;
import com.studyspace.common.result.ApiResult;
import com.studyspace.common.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

    @Override
    public ApiResult login(String username, String password) {
        User user = authMapper.login(username, password);
        if (user == null) {
            return ApiResult.error("用户名或密码错误");
        }

        // 签发 JWT
        String token = JwtUtil.generate(user.getId(), user.getUsername(), user.getRole());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("role", user.getRole());
        data.put("email", user.getEmail());

        return ApiResult.ok(data, "登录成功");
    }

    @Override
    public ApiResult register(String username, String password, String email) {
        User exist = authMapper.getUserByUsername(username);  // ← 方法名一致
        if (exist != null) {
            return ApiResult.error("用户名已存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreated_at(new Date());
        user.setRole("user");

        int result = authMapper.register(user);
        if (result > 0) {
            return ApiResult.ok("注册成功");
        }
        return ApiResult.error("注册失败");
    }

}