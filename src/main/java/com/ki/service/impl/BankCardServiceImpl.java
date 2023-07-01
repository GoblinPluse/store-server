package com.ki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ki.commom.ResultException;
import com.ki.entity.UserInfo;
import com.ki.service.IBankCardService;
import com.ki.utils.SessionUtil;
import com.ki.entity.BankCard;
import com.ki.mapper.BankCardMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class BankCardServiceImpl extends ServiceImpl<BankCardMapper, BankCard> implements IBankCardService {

    @Override
    public void addBankCard(BankCard bankCard) {
        /**
         * 检查用户是否登录
         */
        UserInfo userInfo = SessionUtil.getUserInfo(bankCard.getToken());
        if(userInfo == null){
            throw  new ResultException(1007 ,"请先登录");
        }
        /**
         * 判断卡号是否已绑定
         */
        LambdaQueryWrapper<BankCard> queryWrapper = Wrappers.lambdaQuery();//条件构造器 <>中填写的为表名 表名中“_字母”用驼峰代替
        queryWrapper.eq(BankCard::getNumber, bankCard.getNumber()); //填参数
        BankCard queryBankCard = this.getOne(queryWrapper);//查询

        if(queryBankCard != null){
            throw new ResultException(1009, "银行卡已绑定");
        }
        /**
         * 初始化 属性
         */
        bankCard.setCreateTime(LocalDateTime.now());
        bankCard.setMoney(10000.00); //初始化时银行卡中的钱
//        bankCard.setMoney(bankCard.getAddMoney());
//        UserInfo userInfo = SessionUtil.getUserInfo(bankCard.getToken());
        bankCard.setUserid(userInfo.getId());

        this.save(bankCard);//插入新数据
    }
}
