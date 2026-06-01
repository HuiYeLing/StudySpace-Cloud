package com.studyspace.product.service;

import com.studyspace.common.result.ApiResult;
import com.studyspace.product.domain.Product;

public interface ProductService {
    ApiResult getProductById(Long id);
    ApiResult getAllProducts();
    ApiResult addProduct(Product product);
    ApiResult updateProduct(Product product);
    ApiResult deleteProduct(Long id);
    ApiResult buy(Long userId, Long productId);

    // 订单相关
    ApiResult getOrderById(Long id);
    ApiResult getOrdersByUserId(Long userId);
    ApiResult getAllOrders();
    ApiResult deleteOrder(Long id);
    ApiResult deliverOrder(Long orderId);
}
