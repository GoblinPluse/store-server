package com.ki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ki.commom.ResultException;
import com.ki.entity.MoneyAccount;
import com.ki.entity.UserInfo;
import com.ki.utils.SessionUtil;
import com.ki.entity.BankCard;
import com.ki.mapper.MoneyAccountMapper;
import com.ki.service.IBankCardService;
import com.ki.service.IMoneyAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jarven
 * @since 2023-06-29
 */
@Service
public class MoneyAccountServiceImpl extends ServiceImpl<MoneyAccountMapper, MoneyAccount> implements IMoneyAccountService {
    @Autowired
    private IMoneyAccountService moneyAccountService;
    @Autowired
    private IBankCardService bankCardService;

    @Override
    public void recharge(MoneyAccount moneyAccount) {
        /**
         * 校验用户是否登入
         */
        UserInfo userInfo = SessionUtil.getUserInfo(moneyAccount.getToken());
        if(userInfo == null){
            throw new ResultException(1007,"请先登录");
        }
        /**
         * 银行卡扣款
         */
        LambdaQueryWrapper<BankCard> queryWrapper = Wrappers.lambdaQuery();//条件构造器 <>中填写的为表名 表名中“_字母”用驼峰代替
        queryWrapper.eq(BankCard::getUserid,userInfo.getId()); //填参数
        BankCard bankCard = bankCardService.getOne(queryWrapper);//查询
        if(bankCard == null){
            throw new ResultException(1010, "请先绑定银行卡");
        }

        //判断银行卡余额是否足够
        Double addMonry = moneyAccount.getAddMonry(); //将充值的钱
        Double money = bankCard.getMoney(); //银行卡余额
        if(money < addMonry){
            throw  new ResultException(1011, "银行卡余额不足");
        }
        //银行卡剩余下的钱
        Double  residueMoneyBankCard = money - addMonry;
        /**
         * 钱包账户充值
         */
        LambdaQueryWrapper<MoneyAccount> queryWrapperMoneyAccount = Wrappers.lambdaQuery();//条件构造器 <>中填写的为表名 表名中“_字母”用驼峰代替
        queryWrapperMoneyAccount.eq(MoneyAccount::getUserid,userInfo.getId()); //填参数
        MoneyAccount account = moneyAccountService.getOne(queryWrapperMoneyAccount);//查询

        if(account == null){
            throw new ResultException(1012, "钱包不存在");
        }
        //钱包充值结算后的余额
        Double residueMoneyAccount = addMonry + account.getMoney();

        /**
         * 更新银行卡和钱包余额到数据库
         */
        //更新银行卡余额
        bankCard.setMoney(residueMoneyBankCard);
        bankCardService.updateById(bankCard);
        //更新钱包余额
        account.setMoney(residueMoneyAccount);
        moneyAccountService.updateById(account);
    }

    @Override
    public void cashout(MoneyAccount moneyAccount) {
        /**
         * 校验用户是否登入
         */
        UserInfo userInfo = SessionUtil.getUserInfo(moneyAccount.getToken());
        if(userInfo == null){
            throw new ResultException(1007,"请先登录");
        }

        /**
         * 钱包账户扣款
         */
        LambdaQueryWrapper<MoneyAccount> queryWrapperMoneyAccount = Wrappers.lambdaQuery();//条件构造器 <>中填写的为表名 表名中“_字母”用驼峰代替
        queryWrapperMoneyAccount.eq(MoneyAccount::getUserid,userInfo.getId()); //填参数
        MoneyAccount account = moneyAccountService.getOne(queryWrapperMoneyAccount);//查询

        if(account == null){
            throw new ResultException(1012, "钱包不存在");
        }
        //判断钱包余额是否足够
        Double minusMonry = moneyAccount.getMinusMonry(); //将提现的金额
        Double money = account.getMoney(); //钱包余额
        if(money < minusMonry){ //钱包余额 < 提现金额
            throw  new ResultException(1013, "钱包余额不足");
        }
        //钱包提现结算后的余额
        Double residueMoneyAccount = account.getMoney() - minusMonry; //钱包原余额 - 提现金额

        /**
         * 银行卡提现
         */
        LambdaQueryWrapper<BankCard> queryWrapper = Wrappers.lambdaQuery();//条件构造器 <>中填写的为表名 表名中“_字母”用驼峰代替
        queryWrapper.eq(BankCard::getUserid,userInfo.getId()); //填参数
        BankCard bankCard = bankCardService.getOne(queryWrapper);//查询
        if(bankCard == null){
            throw new ResultException(1010, "请先绑定银行卡");
        }

        //银行卡提现后的余额
        Double  residueMoneyBankCard = bankCard.getMoney() + minusMonry; //银行卡原余额 + 提现金额
        /**
         * 更新银行卡和钱包余额到数据库
         */
        //更新银行卡余额
        bankCard.setMoney(residueMoneyBankCard);
        bankCardService.updateById(bankCard);
        //更新钱包余额
        account.setMoney(residueMoneyAccount);
        moneyAccountService.updateById(account);
    }
}
