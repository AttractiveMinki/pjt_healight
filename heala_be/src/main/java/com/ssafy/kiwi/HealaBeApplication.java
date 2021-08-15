package com.ssafy.kiwi;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HealaBeApplication {
	
	public static final String APPLICATION_LOCATIONS = "spring.config.location="+
			"classpath:application.properties,"+
			"classpath:aws.properties,"+
			"classpath:datasource.properties,"+
			"classpath:api.properties";

	public static void main(String[] args) {
		new SpringApplicationBuilder(HealaBeApplication.class)
									.properties(APPLICATION_LOCATIONS)
									.run(args);
	}

}
