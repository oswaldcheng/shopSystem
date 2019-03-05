package com.shibeijie.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  //允许缓存
public class ShibeijieApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShibeijieApplication.class, args);
	}

}
