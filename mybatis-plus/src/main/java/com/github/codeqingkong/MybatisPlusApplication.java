package com.github.codeqingkong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @author: QingKong
 * @date: 2023/2/27
 */
@SpringBootApplication
@MapperScan("com.github.codeqingkong.mapper")
public class MybatisPlusApplication {
	public static void main(String[] args) {
		SpringApplication.run(MybatisPlusApplication.class, args);
	}

}
