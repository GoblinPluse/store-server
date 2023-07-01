package com.ki.controller;


import com.ki.commom.ResultException;
import com.ki.commom.ResponseResult;
import com.ki.entity.Product;
import com.ki.entity.UserInfo;
import com.ki.service.IProductService;
import com.ki.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jarven
 * @since 2023-06-29
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @PostMapping("/addProduct")
    public ResponseResult<Product> addProduct(Product product){
        log.info("添加商品信息:{}", product);
        productService.addProduct(product);
        return ResponseResult.SUCCESS();
    }

    @PostMapping("/queryProduct")
    public ResponseResult<Product> queryProduct(Integer id , String token){
        UserInfo userInfo = SessionUtil.getUserInfo(token);
        if(userInfo == null){
            throw new ResultException(1007,"请先登录");
        }
        log.info("查询商品信息:{},{}",id,token);
        Product queryProduct = productService.queryProduct(id);
        return ResponseResult.SUCCESS(queryProduct);
    }

    @PostMapping("/updateProduct")
    public ResponseResult<Product>  updateProduct(Product product){
        log.info("更新商品信息:{}",product);
        productService.updateProduct(product);
        return ResponseResult.SUCCESS(product);
    }

    @PostMapping("/deleteProduct")
    public ResponseResult<Product> deleteProduct(Integer id){
        log.info("删除商品信息:{}",id);
        productService.deleteProduct(id);
        return ResponseResult.SUCCESS();
    }

    @PostMapping("/sellProduct")
    public ResponseResult<Product> sellProduct(Product product){
        log.info("购买商品:{}",product);
        productService.sellProduct(product);
        return ResponseResult.SUCCESS();
    }
}

