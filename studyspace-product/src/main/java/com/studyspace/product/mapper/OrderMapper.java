package com.studyspace.product.mapper;


import com.studyspace.product.domain.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO `order` (user_id, username, product_id, product_name, total_price, create_time) " +
            "VALUES (#{userId}, #{username}, #{productId}, #{productName}, #{totalPrice}, #{createTime})")
    int insertOrder(Order order);

    @Results(id = "orderResultMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "productName", column = "product_name"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "createTime", column = "create_time")
    })
    @Select("SELECT * FROM `order` WHERE `id` = #{id}")
    Order selectOrderById(@Param("id") Long id);

    @ResultMap("orderResultMap")
    @Select("SELECT * FROM `order` WHERE `user_id` = #{userId}")
    List<Order> selectOrdersByUserId(@Param("userId") Long userId);

    @ResultMap("orderResultMap")
    @Select("SELECT * FROM `order`")
    List<Order> selectAllOrders();

    @Update("UPDATE `order` SET user_id=#{userId}, username=#{username}, product_id=#{productId}, product_name=#{productName}, total_price=#{totalPrice}, create_time=#{createTime} WHERE id=#{id}")
    int updateOrder(Order order);

    @Delete("DELETE FROM `order` WHERE `id` = #{id}")
    int deleteOrderById(@Param("id") Long id);

    @Update("UPDATE `order` SET status = #{status} WHERE id = #{orderId}")
    int updateOrderStatus(@Param("orderId") Long orderId, @Param("status") Integer status);
}
