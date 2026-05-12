package com.studyspace.user.controller;
import com.studyspace.common.result.ApiResult;
import com.studyspace.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 通过id获取用户信息
    @GetMapping("/getUserById")
    public ApiResult getUserById(@RequestParam long id) {
        return userService.getUserById(id);
    }

    // 通过用户名获取用户信息
    @GetMapping("/getUserByUsername")
    public ApiResult getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    // 用户登录
    @PostMapping("/login")
    public ApiResult login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    // 用户注册
    @PostMapping("/register")
    public ApiResult register(@RequestParam String username, @RequestParam String password,
                              @RequestParam String email) {
        // 创建时间应在服务层自动生成，不应从前端接收
        return userService.register(username, password, email, null);
    }

    // 删除用户
    @DeleteMapping("/deleteUser")
    public ApiResult deleteUser(@RequestParam long id) {
        return userService.deleteUser(id);
    }

    // 获取所有用户
    @GetMapping("/getAllUsers")
    public ApiResult getAllUsers() {
        return userService.getAllUsers();
    }

    // 插入用户
    @PostMapping("/insertUser")
    public ApiResult insertUser(@RequestParam String username, @RequestParam String password,
                                @RequestParam String email, @RequestParam String created_at) {
        return userService.insertUser(username, password, email, created_at);
    }
    // 获取当前登录用户信息
    @GetMapping("/getCurrentUser")
    public ApiResult getCurrentUser() {
        return userService.getCurrentUser();
    }

}
