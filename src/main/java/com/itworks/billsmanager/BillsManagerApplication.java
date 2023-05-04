package com.itworks.billsmanager;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class BillsManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillsManagerApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("en", "CA"));
	}
	
	@Configuration
	public static class MvcConfig implements WebMvcConfigurer {
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addRedirectViewController("/", "/bills");
		}
	}
}

