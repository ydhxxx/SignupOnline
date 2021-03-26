package com.example.signuponline.handle;


import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


/**
 * @author yudh
 *  页面不存在时处理
 * @date 2020-12-03 15:06:02
 */
@Configuration
public class ErrorConfigurar implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[2];
        errorPages[0] = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        errorPages[1] = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

        registry.addErrorPages(errorPages);
    }

}
