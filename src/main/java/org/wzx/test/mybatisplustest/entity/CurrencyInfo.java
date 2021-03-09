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
 * @description: 币种信息
 * @since 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("currency_info")
@ApiModel(value="CurrencyInfo对象", description="币种信息")
public class CurrencyInfo extends CommonEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "币种代号")
    @TableField("currency_code")
    private String currencyCode;

    @ApiModelProperty(value = "交易所")
    @TableField("bourse")
    private String bourse;

    @ApiModelProperty(value = "交易所币种代号")
    @TableField("bourse_code")
    private String bourseCode;

    @ApiModelProperty(value = "交易所币种名称")
    @TableField("bourse_name")
    private String bourseName;

    @ApiModelProperty(value = "英文名")
    @TableField("en_name")
    private String enName;

    @ApiModelProperty(value = "中文名")
    @TableField("cn_name")
    private String cnName;

    @ApiModelProperty(value = "备注")
    @TableField("mark")
    private String mark;

}
