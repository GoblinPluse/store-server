package com.ki.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ki.commom.ResultException;
import com.ki.entity.UserInfo;
import com.ki.mapper.UserInfoMapper;
import com.ki.utils.SessionUtil;
import com.ki.entity.MoneyAccount;
import com.ki.service.IMoneyAccountService;
import com.ki.service.IUserInfoService;
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
 * @since 2023-06-28
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Autowired
    private IMoneyAccountService moneyAccountService;
    @Override
    public void saveUserInfo(UserInfo userInfo) {

            LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery();//条件构造器 <>中填写的为表名 表名中“_字母”用驼峰代替
            queryWrapper.eq(UserInfo::getAccount ,userInfo.getAccount()); //填参数
            UserInfo queryuserInfo = this.getOne(queryWrapper);//查询

            if(queryuserInfo != null){
                throw new ResultException(1003, "账号已存在");
            }
            userInfo.setCreateTime(LocalDateTime.now());
            this.save(userInfo);//插入新数据 结束后userInfo取到了数据库中刚添加的的一整条完整数据（包括ID）
    /**
             * 生成钱包账户
             */
            MoneyAccount moneyAccount = new MoneyAccount();
            moneyAccount.setMoney(10.00);
            moneyAccount.setUserid(userInfo.getId());
            moneyAccount.setCreateTime(LocalDateTime.now());
            moneyAccountService.save(moneyAccount);
    }

    @Override
    public UserInfo queryUserInfo(UserInfo userInfo) {

        LambdaQueryWrapper<UserInfo> queryWrapper = Wrappers.lambdaQuery();
        if(userInfo.getId() != null)
            queryWrapper.eq(UserInfo::getId, userInfo.getId());
        else {
            queryWrapper.eq(UserInfo::getAccount, userInfo.getAccount());
        }
        UserInfo info = this.getOne(queryWrapper);
        return info;
    }

    @Override
    public UserInfo login(String account, String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(account);
        userInfo.setPassword(password);

        UserInfo queryUserInfo = queryUserInfo(userInfo);
        if(queryUserInfo == null){
            throw new ResultException(1004, "账号不存在");
        } else if(!queryUserInfo.getPassword().equals(password)){
            throw new ResultException(1005, "密码错误");
        }

        String token = SessionUtil.createToken();
        queryUserInfo.setToken(token);
        SessionUtil.addUserInfo(token, queryUserInfo);
        return queryUserInfo;
    }

    @Override
    public IPage<UserInfo> pageUserInfo(Long current, Long pageSize) {
        IPage<UserInfo> page = new Page<>(current ,pageSize);
        LambdaQueryWrapper<UserInfo> queryWrappe = Wrappers.lambdaQuery();

        IPage<UserInfo> iPage = this.page(page ,queryWrappe);
        return iPage;
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        this.updateById(userInfo);
    }
}
