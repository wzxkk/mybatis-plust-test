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
 * @description: 用户key信息
 * @since 2021-03-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user_key")
@ApiModel(value="UserKey对象", description="用户key信息")
public class UserKey extends CommonEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "交易所")
    @TableField("bourse")
    private String bourse;

    @ApiModelProperty(value = "用户账号")
    @TableField("username")
    private String username;

    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty(value = "账号id")
    @TableField("account_id")
    private String accountId;

    @ApiModelProperty(value = "访问密钥")
    @TableField("api_key")
    private String apiKey;

    @ApiModelProperty(value = "签名认证加密所使用的密钥")
    @TableField("secret_key")
    private String secretKey;

    @ApiModelProperty(value = "备注")
    @TableField("mark")
    private String mark;

    @ApiModelProperty(value = "账号状态")
    @TableField("account_state")
    private String accountState;

}
