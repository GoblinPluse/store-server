package com.ki.controller;


import com.ki.service.IBankCardService;
import com.ki.commom.ResponseResult;
import com.ki.entity.BankCard;
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
@RequestMapping("/bankCard")
public class BankCardController {
    @Autowired
    private IBankCardService bankCardService;
    @PostMapping("/addBankCard")
    public ResponseResult<BankCard> addBankCard(BankCard bankCard){
        log.info("绑定银行卡:{}", bankCard);
        bankCardService.addBankCard(bankCard);
        return ResponseResult.SUCCESS();
    }

}

