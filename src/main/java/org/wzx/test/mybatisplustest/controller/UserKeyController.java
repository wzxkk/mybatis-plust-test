package org.wzx.test.mybatisplustest.controller;

import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.wzx.test.mybatisplustest.entity.UserKey;
import org.wzx.test.mybatisplustest.service.impl.UserKeyServiceImpl;
import org.wzx.test.mybatisplustest.controller.base.BaseController;

/**
 * @author: 鱼头
 * @description: 用户key信息API接口
 * @since: 2021-03-09
 */
@RestController
@RequestMapping("/userKey" )
@Api(tags = "用户key信息API接口" )
@ApiResponses({
        @ApiResponse(code = 204, message = "请求用户key信息成功，但内容返回" ),
        @ApiResponse(code = 400, message = "请求用户key信息的参数有误" ),
        @ApiResponse(code = 401, message = "请求用户key信息需要通过 HTTP 认证" ),
        @ApiResponse(code = 403, message = "服务器拒绝访问用户key信息" ),
        @ApiResponse(code = 404, message = "请求用户key信息的路径路径不对" ),
        @ApiResponse(code = 500, message = "请求用户key信息时服务器出现错误" )
})
public class UserKeyController extends BaseController<UserKeyServiceImpl, UserKey>{}