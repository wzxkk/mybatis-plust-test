package org.wzx.test.mybatisplustest.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.wzx.test.mybatisplustest.entity.CurrencyInfo;
import org.wzx.test.mybatisplustest.service.impl.CurrencyInfoServiceImpl;
import org.wzx.test.mybatisplustest.controller.base.BaseController;

/**
 * @author: 鱼头
 * @description: 币种信息API接口
 * @since: 2021-03-09
 */
@RestController
@RequestMapping("/currencyInfo" )
@Api(tags = "币种信息API接口" )
@ApiResponses({
        @ApiResponse(code = 204, message = "请求币种信息成功，但内容返回" ),
        @ApiResponse(code = 400, message = "请求币种信息的参数有误" ),
        @ApiResponse(code = 401, message = "请求币种信息需要通过 HTTP 认证" ),
        @ApiResponse(code = 403, message = "服务器拒绝访问币种信息" ),
        @ApiResponse(code = 404, message = "请求币种信息的路径路径不对" ),
        @ApiResponse(code = 500, message = "请求币种信息时服务器出现错误" )
})
public class CurrencyInfoController extends BaseController<CurrencyInfoServiceImpl, CurrencyInfo>{}