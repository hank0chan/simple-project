package cn.hankchan.thymeleaf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig {

	private static final String VIEWS_PREFIX = "/WEB-INF/templates/";
	private static final String VIEWS_SUFFIX = ".html";
	
	/*
	@Bean
	public ServletContextTemplateResolver templateResolverBeforeImpl() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(null);
		templateResolver.setPrefix(VIEWS_PREFIX);
		templateResolver.setSuffix(VIEWS_SUFFIX);
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		return templateResolver;
	}*/
	
	@Bean
	public ITemplateResolver templateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setPrefix(VIEWS_PREFIX);
		resolver.setSuffix(VIEWS_SUFFIX);
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
//		templateEngine.addDialect(new SpringSecurityDialect());  // 暂不用
//		templateEngine.addDialect(new Java8TimeDialect());  // 暂不用
		return templateEngine;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine(templateEngine());
		thymeleafViewResolver.setCharacterEncoding("UTF-8");
		return thymeleafViewResolver;
	}
}
