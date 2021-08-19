package com.ssafy.kiwi;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class HealaBeApplication {
	
    @PostConstruct
    public void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }
	
	public static final String APPLICATION_LOCATIONS = "spring.config.location="+
			"classpath:application.properties,"+
			"classpath:application-aws.properties,"+
			"classpath:application-datasource.properties,"+
			"classpath:application-api-key.properties,"+
			"classpath:application-jwt.properties";

	public static void main(String[] args) {
		new SpringApplicationBuilder(HealaBeApplication.class)
									.properties(APPLICATION_LOCATIONS)
 									.run(args);
	}

}
