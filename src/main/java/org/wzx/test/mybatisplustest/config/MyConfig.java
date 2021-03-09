package org.wzx.test.mybatisplustest.config;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDateTime;

/**
 * @author: 鱼头
 * @description: 数据操作基础配置
 * @since: 2020/5/19 16:46
 */
@Configuration
@MapperScan("org.wzx.test.mybatisplustest.mapper")
@EnableSwagger2
public class MyConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("uuid", IdUtil.simpleUUID(), metaObject);
        LocalDateTime t = LocalDateTimeUtil.now();
        this.setFieldValByName("createTime", t, metaObject);
        this.setFieldValByName("updateTime", t, metaObject);
        this.setFieldValByName("status", "normal", metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", LocalDateTimeUtil.now(), metaObject);
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("交易所API接口文档")
                        .description("文档随时有变动，请多多关注")
                        .contact(new Contact("鱼头", "https://github.com/wzxkk/mybatis-plust-test", "377322994@qq.com"))
                        .version("0.0.1")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.wzx.test.mybatisplustest"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}