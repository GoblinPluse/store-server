package com.ki.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jarven
 * @since 2023-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MoneyAccount implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Double money;

    /**
     * 用户id
     */
    private Integer userid;

    private LocalDateTime createTime;

    @TableField(exist = false)
    private String token;

    //充值的金额
    @TableField(exist = false)
    private Double addMonry;
    @TableField(exist = false)
    private Double minusMonry;

}
