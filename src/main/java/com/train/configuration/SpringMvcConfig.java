package com.train.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@Configuration
@ComponentScan("com.train.controller")
public class SpringMvcConfig implements WebMvcConfigurer
{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");

		registry
				.addResourceHandler("/webjars/**")
				.addResourceLocations("/webjars/");

	}

	@Bean
	public InternalResourceViewResolver viewResolver()
	{
		InternalResourceViewResolver viewResolver =
				new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JavaMailSenderImpl mailSender()
	{
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setProtocol("smtp");
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("alena.buglack@gmail.com");
		javaMailSender.setPassword("oxyireytskwghicv");
		javaMailSender.setDefaultEncoding("UTF-8");

		return javaMailSender;
	}
}
