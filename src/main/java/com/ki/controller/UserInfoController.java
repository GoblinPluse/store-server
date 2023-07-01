package com.ki.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ki.entity.UserInfo;
import com.ki.commom.ResponseResult;
import com.ki.service.IUserInfoService;
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
 * @since 2023-06-28
 */
@Slf4j //日志
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;
    @PostMapping("/saveUserInfo")
    public ResponseResult<UserInfo> saveUserInfo(UserInfo userInfo){
        log.info("添加用户信息:{}", userInfo);
        userInfoService.saveUserInfo(userInfo);

        return ResponseResult.SUCCESS();
    }

    @PostMapping("/queryUserInfo")
    public ResponseResult<UserInfo> queryUserInfo(UserInfo userInfo){
        log.info("查询用户信息:{}", userInfo);
        UserInfo queryUserInfo = userInfoService.queryUserInfo(userInfo);

        return ResponseResult.SUCCESS(queryUserInfo);
    }

    @PostMapping("/login")
    public ResponseResult<UserInfo> login(String account , String password){
        log.info("用户登入{},{}", account ,password);
        UserInfo userInfo = userInfoService.login(account, password);

        return ResponseResult.SUCCESS(userInfo);
    }
    @PostMapping("/pageUserInfo")
    public ResponseResult<IPage<UserInfo> > pageUserInfo(Long current, Long pageSize){
        log.info("分页查询用户信息:{},{}" ,current ,pageSize);
        IPage<UserInfo> userInfoIPage = userInfoService.pageUserInfo(current , pageSize);

        return ResponseResult.SUCCESS(userInfoIPage);
    }

    @PostMapping("/updateUserInfo")
    public ResponseResult<UserInfo> updateUserInfo(UserInfo userInfo){
        log.info("更新用户信息:{}",userInfo);
        userInfoService.updateUserInfo(userInfo);
        return ResponseResult.SUCCESS(userInfo);
    }
}

