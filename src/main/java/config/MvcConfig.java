package config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	Logger logger = LoggerFactory.getLogger(MvcConfig.class);
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/static/",	
		"classpath:/resources/",	
		"classpath:/images/"	
	};
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
	}
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		logger.info("configureViewResolvers 호출 성공");
		registry.jsp("/WEB-INF/jsp/",".jsp");
	}
}









