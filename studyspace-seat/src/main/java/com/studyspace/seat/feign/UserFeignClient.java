package com.studyspace.seat.feign;

import com.studyspace.common.result.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "studyspace-user")
public interface UserFeignClient {

    @GetMapping("/api/user/internal/currentUser")
    ApiResult getCurrentUser(@RequestParam("userId") Long userId);
}