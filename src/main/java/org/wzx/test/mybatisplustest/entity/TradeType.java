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
 * @description: 交易类型信息
 * @since 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("trade_type")
@ApiModel(value="TradeType对象", description="交易类型信息")
public class TradeType extends CommonEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "交易类型代号")
    @TableField("trade_code")
    private String tradeCode;

    @ApiModelProperty(value = "交易类型名称")
    @TableField("trade_name")
    private String tradeName;

    @ApiModelProperty(value = "交易所")
    @TableField("bourse")
    private String bourse;

    @ApiModelProperty(value = "交易所交易类型代号")
    @TableField("bourse_trade_code")
    private String bourseTradeCode;

    @ApiModelProperty(value = "交易所交易类型名称")
    @TableField("bourse_trade_name")
    private String bourseTradeName;

}
