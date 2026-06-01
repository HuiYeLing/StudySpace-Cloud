package com.studyspace.product.service.impl;

import com.studyspace.common.result.ApiResult;
import com.studyspace.product.domain.Order;
import com.studyspace.product.domain.Product;
import com.studyspace.product.feign.UserFeignClient;
import com.studyspace.product.mapper.OrderMapper;
import com.studyspace.product.mapper.ProductMapper;
import com.studyspace.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserFeignClient userFeignClient;

    // ========== 商品 CRUD ==========

    @Override
    public ApiResult getProductById(Long id) {
        Product product = productMapper.getProductById(id);
        return product != null ? ApiResult.ok(product) : ApiResult.error("未找到该商品");
    }

    @Override
    public ApiResult getAllProducts() {
        List<Product> list = productMapper.getAllProducts();
        return ApiResult.ok(list);
    }

    @Override
    public ApiResult addProduct(Product product) {
        int res = productMapper.insertProduct(product);
        return res > 0 ? ApiResult.ok("添加成功") : ApiResult.error("添加失败");
    }

    @Override
    public ApiResult updateProduct(Product product) {
        int res = productMapper.updateProduct(product);
        return res > 0 ? ApiResult.ok("更新成功") : ApiResult.error("更新失败");
    }

    @Override
    public ApiResult deleteProduct(Long id) {
        int res = productMapper.deleteProduct(id);
        return res > 0 ? ApiResult.ok("删除成功") : ApiResult.error("删除失败");
    }

    // ========== 购买（Feign 调 user 服务 + 本地事务扣库存 + 写订单）==========

    @Override
    @Transactional
    public ApiResult buy(Long userId, Long productId) {
        // 1. Feign 调 user 服务验证用户
        ApiResult userResult = userFeignClient.getCurrentUser(userId);
        if (!userResult.isSuccess()) {
            return ApiResult.error("用户不存在");
        }
        Map<String, Object> userData = (Map<String, Object>) userResult.getData();
        String username = (String) userData.get("username");

        // 2. 查商品
        Product product = productMapper.getProductById(productId);
        if (product == null) {
            return ApiResult.error("商品不存在");
        }
        if (product.getStock() == null || product.getStock() < 1) {
            return ApiResult.error("库存不足");
        }

        // 3. 扣库存
        int updateCount = productMapper.decreaseStock(productId, 1);
        if (updateCount <= 0) {
            return ApiResult.error("扣减库存失败");
        }

        // 4. 创建订单
        Order order = new Order();
        order.setUserId(userId);
        order.setUsername(username);
        order.setProductId(productId);
        order.setProductName(product.getName());
        order.setTotalPrice(BigDecimal.valueOf(product.getPrice()));
        order.setCreateTime(new Date());
        order.setStatus(0);

        int res = orderMapper.insertOrder(order);
        return res > 0 ? ApiResult.ok("购买成功") : ApiResult.error("订单创建失败");
    }

    // ========== 订单管理 ==========

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
    public ApiResult getAllOrders() {
        List<Order> orders = orderMapper.selectAllOrders();
        return ApiResult.ok(orders);
    }

    @Override
    public ApiResult deleteOrder(Long id) {
        int result = orderMapper.deleteOrderById(id);
        return result > 0 ? ApiResult.ok("订单删除成功") : ApiResult.error("订单删除失败");
    }

    @Override
    public ApiResult deliverOrder(Long orderId) {
        int result = orderMapper.updateOrderStatus(orderId, 1);
        return result > 0 ? ApiResult.ok("已送达") : ApiResult.error("更新失败");
    }
}