package com.studyspace.product.service;

import com.studyspace.common.result.ApiResult;
import com.studyspace.product.domain.Order;

public interface OrderService {
    ApiResult createOrder(Order order);
    ApiResult getOrderById(Long id);
    ApiResult getOrdersByUserId(Long userId);
    ApiResult deleteOrder(Long id);
    ApiResult getAllOrders();
    ApiResult updateOrderStatus(Long orderId, Integer status);
}
