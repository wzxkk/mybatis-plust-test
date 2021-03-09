package org.wzx.test.mybatisplustest.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import org.wzx.test.mybatisplustest.entity.base.CommonEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 鱼头
 * @description: 交易所所拥有的交易类型
 * @since 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("bourse_trade")
@ApiModel(value="BourseTrade对象", description="交易所所拥有的交易类型")
public class BourseTrade extends CommonEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "交易所代号")
    @TableField("bourse")
    private String bourse;

    @ApiModelProperty(value = "交易所代号")
    @TableField("trade")
    private String trade;

}
