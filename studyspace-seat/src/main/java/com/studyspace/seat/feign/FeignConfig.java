// 新建 feign/FeignConfig.java
package com.studyspace.seat.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    // 空实现，不自动传递 caller 的 header
}