package com.ki.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ki.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarven
 * @since 2023-06-28
 */

public interface IUserInfoService extends IService<UserInfo> {
    void saveUserInfo(UserInfo userInfo);
    UserInfo queryUserInfo(UserInfo userInfo);
    UserInfo login(String account , String password);
    IPage<UserInfo> pageUserInfo(Long current, Long pageSize);
    void updateUserInfo(UserInfo userInfo);
}
