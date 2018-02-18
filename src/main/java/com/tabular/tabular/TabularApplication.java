package com.tabular.tabular;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "config/spring/dao.xml", "config/spring/service.xml" })
@MapperScan(basePackages = {"com.tabular.tabular.dao"})
public class TabularApplication {

	public static void main(String[] args) {
		SpringApplication.run(TabularApplication.class, args);
	}
}
