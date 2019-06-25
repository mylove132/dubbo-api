package com.dubbo.api;

import com.dubbo.api.task.ExecJmeterScript;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuartzDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzDemoApplication.class, args);
	}

	@Bean
	public ExecJmeterScript execJmeterScript(){
		return new ExecJmeterScript();
	}
}
