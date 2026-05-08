package com.studyspace.auth.service;
import com.studyspace.common.result.ApiResult;

public interface AuthService {
    ApiResult login(String username, String password);
    ApiResult register(String username, String password, String email);
}
