package com.studyspace.product.mapper;

import com.studyspace.product.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product getProductById(@Param("id") Long id);
    @Select("SELECT * FROM product")
    List<Product> getAllProducts();
    @Insert("INSERT INTO product (name, price, stock, description) VALUES (#{name}, #{price}, #{stock}, #{description})")
    int insertProduct(Product product);
    @Update("UPDATE product SET name=#{name}, price=#{price}, stock=#{stock}, description=#{description} WHERE id=#{id}")
    int updateProduct(Product product);
    @Delete("DELETE FROM product WHERE id = #{id}")
    int deleteProduct(@Param("id") Long id);
    @Update("UPDATE product SET stock = stock - #{count} WHERE id = #{productId} AND stock >= #{count}")
    int decreaseStock(@Param("productId") Long productId, @Param("count") Integer count);

}
