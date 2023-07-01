package com.ki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ki.commom.ResultException;
import com.ki.entity.Product;
import com.ki.mapper.ProductMapper;
import com.ki.service.IBankCardService;
import com.ki.service.IMoneyAccountService;
import com.ki.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jarven
 * @since 2023-06-29
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    IMoneyAccountService moneyAccountService;
    @Autowired
    IBankCardService bankCardService;
    /**
     * 添加商品信息
     * @param product
     */
    @Override
    public void addProduct(Product product) {
        LambdaQueryWrapper<Product> queryWrapper = Wrappers.lambdaQuery();//条件构造器 <>中填写的为表名 表名中“_字母”用驼峰代替
        queryWrapper.eq(Product::getName,product.getName()); //填参数
        Product saveProduct = this.getOne(queryWrapper);//查询

        if(saveProduct != null){
            throw new ResultException(1006, "商品名称重复");
        }

        product.setCreateTime(LocalDateTime.now());
        this.save(product);//插入新数据
    }

    /**
     * 查询商品信息
     * @param id
     * @return
     */
    @Override
    public Product queryProduct(Integer id) {
        Product product = this.getById(id);
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        this.updateById(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        this.removeById(id);
    }

    @Override
    public void sellProduct(Product product) {
        /**
         * 登录验证
         */

        /**
         * 库存减少
         */

        /**
         * 订单增加
         */

        /**
         * 钱包扣款
         */

        /**
         * 银行卡扣款
         */

        /**
         * 混合扣款
         */

        /**
         * 余额更新
         */

    }


}
