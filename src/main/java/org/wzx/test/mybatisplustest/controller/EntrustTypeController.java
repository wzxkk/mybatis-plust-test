package org.wzx.test.mybatisplustest.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.wzx.test.mybatisplustest.entity.EntrustType;
import org.wzx.test.mybatisplustest.service.impl.EntrustTypeServiceImpl;
import org.wzx.test.mybatisplustest.controller.base.BaseController;

/**
 * @author: 鱼头
 * @description: 委托类型API接口
 * @since: 2021-03-09
 */
@RestController
@RequestMapping("/entrustType" )
@Api(tags = "委托类型API接口" )
@ApiResponses({
        @ApiResponse(code = 204, message = "请求委托类型成功，但内容返回" ),
        @ApiResponse(code = 400, message = "请求委托类型的参数有误" ),
        @ApiResponse(code = 401, message = "请求委托类型需要通过 HTTP 认证" ),
        @ApiResponse(code = 403, message = "服务器拒绝访问委托类型" ),
        @ApiResponse(code = 404, message = "请求委托类型的路径路径不对" ),
        @ApiResponse(code = 500, message = "请求委托类型时服务器出现错误" )
})
public class EntrustTypeController extends BaseController<EntrustTypeServiceImpl, EntrustType>{}