package com.ki.controller;


import com.ki.entity.MoneyAccount;
import com.ki.commom.ResponseResult;
import com.ki.service.IMoneyAccountService;
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
@RequestMapping("/moneyAccount")
public class MoneyAccountController {
    @Autowired
    IMoneyAccountService moneyAccountService;
    @PostMapping("/recharge")
    public ResponseResult<MoneyAccount> recharge(MoneyAccount moneyAccount){
        log.info("钱包充值:{}", moneyAccount);
        moneyAccountService.recharge(moneyAccount);
        return ResponseResult.SUCCESS();
    }

    @PostMapping("/cashout")
    public ResponseResult<MoneyAccount> cashout(MoneyAccount moneyAccount){
        log.info("钱包提现:{}", moneyAccount);
        moneyAccountService.cashout(moneyAccount);
        return ResponseResult.SUCCESS();
    }
}

