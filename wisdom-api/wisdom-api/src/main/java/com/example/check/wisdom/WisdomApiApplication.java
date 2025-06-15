// border wisdom brown

package com.example.check.wisdom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;


@SpringBootApplication
public class WisdomApiApplication {

	public static void main(String[] args) {
		System.out.println("DB_PORT: " + System.getenv("DB_PORT"));
		System.out.println("DB_HOST: " + System.getenv("DB_HOST"));
		System.out.println("DB_NAME: " + System.getenv("DB_NAME"));
		SpringApplication.run(WisdomApiApplication.class, args);
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(@Value("${spring.application.name}")String appName){
		System.out.println("In Meter Registry" + appName);
		return registry -> registry.config().commonTags("app", appName);
	}

}
