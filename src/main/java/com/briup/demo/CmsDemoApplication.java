package com.briup.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@MapperScan({"com.briup.demo.dao"})
@SpringBootApplication
public class CmsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsDemoApplication.class, args);
	}

}
