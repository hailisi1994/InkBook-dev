package com.hls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 1.http://localhost:8088/swagger-ui.html
 * 2.http://localhost:8088/doc.html
 * @author liangyehao
 * @version 1.0
 * @date 2020/2/27 22:22
 * @content swagger2配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2 {



    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hls.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title("api文档")
                .description("hls")
                .termsOfServiceUrl("localhost:8080/")
                .contact(new Contact("hls","",""))
                .version("1.0.1")
                .build();
    }


}
