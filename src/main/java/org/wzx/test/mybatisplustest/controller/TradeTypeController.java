package org.wzx.test.mybatisplustest.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.wzx.test.mybatisplustest.entity.TradeType;
import org.wzx.test.mybatisplustest.service.impl.TradeTypeServiceImpl;
import org.wzx.test.mybatisplustest.controller.base.BaseController;

/**
 * @author: 鱼头
 * @description: 交易类型信息API接口
 * @since: 2021-03-09
 */
@RestController
@RequestMapping("/tradeType" )
@Api(tags = "交易类型信息API接口" )
@ApiResponses({
        @ApiResponse(code = 204, message = "请求交易类型信息成功，但内容返回" ),
        @ApiResponse(code = 400, message = "请求交易类型信息的参数有误" ),
        @ApiResponse(code = 401, message = "请求交易类型信息需要通过 HTTP 认证" ),
        @ApiResponse(code = 403, message = "服务器拒绝访问交易类型信息" ),
        @ApiResponse(code = 404, message = "请求交易类型信息的路径路径不对" ),
        @ApiResponse(code = 500, message = "请求交易类型信息时服务器出现错误" )
})
public class TradeTypeController extends BaseController<TradeTypeServiceImpl, TradeType>{}