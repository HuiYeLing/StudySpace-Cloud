package com.studyspace.product.controller;

import com.studyspace.common.result.ApiResult;
import com.studyspace.product.domain.Order;
import com.studyspace.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单
    @PostMapping("/create")
    public ApiResult createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // 根据订单ID查询
    @GetMapping("/getById")
    public ApiResult getOrderById(@RequestParam Long id) {
        return orderService.getOrderById(id);
    }

    // 根据用户ID查询订单列表
    @GetMapping("/getByUserId")
    public ApiResult getOrdersByUserId(@RequestParam Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    // 删除订单
    @DeleteMapping("/delete")
    public ApiResult deleteOrder(@RequestParam Long id) {
        return orderService.deleteOrder(id);
    }
    // 获取所有订单
    @GetMapping("/getAll")
    public ApiResult getAllOrders() {
        return orderService.getAllOrders();
    }
    @PostMapping("/deliver")
    public ApiResult deliverOrder(@RequestParam Long orderId) {
        return orderService.updateOrderStatus(orderId, 1);
    }

}