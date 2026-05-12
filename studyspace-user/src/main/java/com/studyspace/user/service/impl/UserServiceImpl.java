package com.studyspace.user.service.impl;


import com.studyspace.common.result.ApiResult;
import com.studyspace.user.domain.User;
import com.studyspace.user.mapper.UserMapper;
import com.studyspace.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    // 判断当前用户是否为管理员
    private boolean isAdmin() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) return false;
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession(false);
        if (session == null) return false;
        User user = (User) session.getAttribute("loginUser");
        return user != null && "admin".equals(user.getRole());
    }

    public ApiResult getUserById(long id) {
        if (!isAdmin()) return ApiResult.error("无权限操作");
        ApiResult r = ApiResult.error("获取用户失败");
        User user = userMapper.getUserById(id);
        if (user != null) {
            r = ApiResult.ok(user);
        } else {
            r = ApiResult.error("用户不存在");
        }
        return r;
    }

    public ApiResult getUserByUsername(String username) {
        if (!isAdmin()) return ApiResult.error("无权限操作");
        ApiResult r = ApiResult.error("获取用户失败");
        User user = userMapper.getUserByUsername(username);
        if (user != null) {
            r = ApiResult.ok(user);
        } else {
            r = ApiResult.error("用户不存在");
        }
        return r;
    }

    public ApiResult login(String username, String password) {
        ApiResult r = ApiResult.error("登录失败");
        User user = userMapper.login(username, password);
        if (user != null) {
            // 获取当前请求的HttpServletRequest
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                // 获取session并存储用户信息
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", user);
            }
            r = ApiResult.loginSuccess(user);
        } else {
            r = ApiResult.error("用户名或密码错误");
        }
        return r;
    }

    public ApiResult register(String username, String password, String email, Date created_at) {
        ApiResult r = ApiResult.error("注册失败");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreated_at(created_at);
        int result = userMapper.register(user);
        if (result > 0) {
            r = ApiResult.ok("注册成功");
        } else {
            r = ApiResult.error("注册失败");
        }
        return r;
    }

    public ApiResult deleteUser(long id) {
        if (!isAdmin()) return ApiResult.error("无权限操作");
        ApiResult r = ApiResult.error("删除用户失败");
        int result = userMapper.deleteUser(id);
        if (result > 0) {
            r = ApiResult.ok("删除用户成功");
        } else {
            r = ApiResult.error("删除用户失败");
        }
        return r;
    }

    public ApiResult getAllUsers() {
        if (!isAdmin()) return ApiResult.error("无权限操作");
        ApiResult r = ApiResult.error("获取所有用户失败");
        List<User> users = userMapper.getAllUsers();
        if (users != null && !users.isEmpty()) {
            r = ApiResult.ok(users);
        } else {
            r = ApiResult.error("没有用户信息");
        }
        return r;
    }

    public ApiResult insertUser(String username, String password, String email, String created_at) {
        if (!isAdmin()) return ApiResult.error("无权限操作");
        ApiResult r = ApiResult.error("插入用户失败");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setCreated_at(new Date());
        int result = userMapper.insertUser(username, password, email, created_at);
        if (result > 0) {
            r = ApiResult.ok("插入用户成功");
        } else {
            r = ApiResult.error("插入用户失败");
        }
        return r;
    }
    public ApiResult getCurrentUser() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            HttpSession session = request.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("loginUser");
                if (user != null) {
                    return ApiResult.ok(user);
                }
            }
        }
        return ApiResult.noauth("未登录");
    }

}