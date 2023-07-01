package com.ki.service;

import com.ki.entity.MoneyAccount;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarven
 * @since 2023-06-29
 */
public interface IMoneyAccountService extends IService<MoneyAccount> {
    /**
     * 银行卡余额充值到钱包
     * @param moneyAccount
     */
    void recharge(MoneyAccount moneyAccount);
    void cashout(MoneyAccount moneyAccount);
}
