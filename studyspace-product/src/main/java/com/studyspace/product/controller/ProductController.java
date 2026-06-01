package com.studyspace.product.controller;

import com.studyspace.common.result.ApiResult;
import com.studyspace.product.domain.Product;
import com.studyspace.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // ===== 商品 =====
    @GetMapping("/getById")
    public ApiResult getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/getAll")
    public ApiResult getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/add")
    public ApiResult addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update")
    public ApiResult updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete")
    public ApiResult deleteProduct(@RequestParam Long id) {
        return productService.deleteProduct(id);
    }

    @PostMapping("/buy")
    public ApiResult buy(@RequestParam Long userId,
                         @RequestParam Long productId) {
        return productService.buy(userId, productId);
    }

    // ===== 订单 =====
    @GetMapping("/order/getById")
    public ApiResult getOrderById(@RequestParam Long id) {
        return productService.getOrderById(id);
    }

    @GetMapping("/order/getByUserId")
    public ApiResult getOrdersByUserId(@RequestParam Long userId) {
        return productService.getOrdersByUserId(userId);
    }

    @GetMapping("/order/getAll")
    public ApiResult getAllOrders() {
        return productService.getAllOrders();
    }

    @DeleteMapping("/order/delete")
    public ApiResult deleteOrder(@RequestParam Long id) {
        return productService.deleteOrder(id);
    }

    @PostMapping("/order/deliver")
    public ApiResult deliverOrder(@RequestParam Long orderId) {
        return productService.deliverOrder(orderId);
    }
}