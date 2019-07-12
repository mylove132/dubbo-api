package com.dubbo.api;

import com.dubbo.api.common.util.ZKPropertiesLoader;
import com.dubbo.api.task.ExecJmeterScript;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@Slf4j
@SpringBootApplication
@EnableScheduling
@MapperScan(basePackages = "com.dubbo.api.dao")
public class QuartzDemoApplication implements CommandLineRunner,WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

	@Value("${server.port}")
	private String serverPort;

	public static void main(String[] args) {
		try {
			ZKPropertiesLoader.load("/xdfapp/jmeter-web");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		SpringApplication.run(QuartzDemoApplication.class, args);
	}

	@Bean
	public ExecJmeterScript execJmeterScript(){
		return new ExecJmeterScript();
	}

	@Override
	public void run(String... args) throws Exception {

	}

	@Override
	public void customize(ConfigurableServletWebServerFactory factory) {
		factory.setPort(Integer.parseInt(serverPort));
	}
}
