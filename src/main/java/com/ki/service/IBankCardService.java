package com.ki.service;

import com.ki.entity.BankCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jarven
 * @since 2023-06-29
 */
public interface IBankCardService extends IService<BankCard> {
    /**
     * 创建银行卡
     * @param bankCard
     */
    void addBankCard(BankCard bankCard);
}
