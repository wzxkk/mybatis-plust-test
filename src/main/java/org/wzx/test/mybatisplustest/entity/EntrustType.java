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
 * @description: 委托类型
 * @since 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("entrust_type")
@ApiModel(value="EntrustType对象", description="委托类型")
public class EntrustType extends CommonEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "交易所")
    @TableField("bourse")
    private String bourse;

    @ApiModelProperty(value = "交易类型")
    @TableField("trade")
    private String trade;

    @ApiModelProperty(value = "委托类型代号")
    @TableField("entrust_type_code")
    private String entrustTypeCode;

    @ApiModelProperty(value = "委托类型名称")
    @TableField("entrust_type_name")
    private String entrustTypeName;

}
