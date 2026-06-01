package com.studyspace.product.service.impl;

import com.studyspace.common.result.ApiResult;
import com.studyspace.product.domain.Order;
import com.studyspace.product.mapper.OrderMapper;
import com.studyspace.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ApiResult createOrder(Order order) {
        int result = orderMapper.insertOrder(order);
        return result > 0 ? ApiResult.ok("订单创建成功") : ApiResult.error("订单创建失败");
    }

    @Override
    public ApiResult getOrderById(Long id) {
        Order order = orderMapper.selectOrderById(id);
        return order != null ? ApiResult.ok(order) : ApiResult.error("订单不存在");
    }

    @Override
    public ApiResult getOrdersByUserId(Long userId) {
        List<Order> orders = orderMapper.selectOrdersByUserId(userId);
        return ApiResult.ok(orders);
    }

    @Override
    public ApiResult deleteOrder(Long id) {
        int result = orderMapper.deleteOrderById(id);
        return result > 0 ? ApiResult.ok("订单删除成功") : ApiResult.error("订单删除失败");
    }
    @Override
    public ApiResult getAllOrders() {
        List<Order> orders = orderMapper.selectAllOrders();
        return ApiResult.ok(orders);
    }

    @Override
    public ApiResult updateOrderStatus(Long orderId, Integer status) {
        int result = orderMapper.updateOrderStatus(orderId, status);
        return result > 0 ? ApiResult.ok("订单状态更新成功") : ApiResult.error("订单状态更新失败");
    }
}
