package com.pyb.app.zuul_api_proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApiProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApiProxyApplication.class, args);
	}

}
