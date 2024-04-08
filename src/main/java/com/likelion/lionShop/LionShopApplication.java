package com.likelion.lionShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LionShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LionShopApplication.class, args);
	}

}
