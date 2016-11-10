package edu.almabridge.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@ImportResource(value = {"classpath:ApplicationContextConfig.xml"})
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "edu.almabridge")
public class AlmaBridgeConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        System.out.println("in configureViewResolvers method of AlmaBridgeConfiguration class");
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	 System.out.println("in addResourceHandlers method of AlmaBridgeConfiguration class");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

	

}
