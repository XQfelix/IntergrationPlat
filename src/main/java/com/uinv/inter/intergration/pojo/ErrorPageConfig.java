package com.uinv.inter.intergration.pojo;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;


/**
 * @Title 页面错误路由配置
 * @author GuoQ.yin
 * */
@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar{
	 @Override
	    public void registerErrorPages(ErrorPageRegistry registry) {
	        //错误类型为404 ...，重定向到index
	        ErrorPage e404 = new ErrorPage(HttpStatus.NOT_FOUND, "/code/index.html");
	        registry.addErrorPages(e404);
	    }
}
