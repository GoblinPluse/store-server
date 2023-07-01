package com.ki.service;

import com.ki.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarven
 * @since 2023-06-29
 */
public interface IProductService extends IService<Product> {

    void addProduct(Product product);
    Product queryProduct(Integer id);
    void updateProduct(Product product);
    void deleteProduct(Integer id);
    void sellProduct(Product product);
}
