package edu.almabridge.config;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("In getRootConfigClasses method of WebAppInitializer class");
		return new Class[] { AlmaBridgeConfiguration.class};
		
	}
	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("In getServletConfigClasses method of WebAppInitializer class");
		
		return null;
	}
	@Override
	protected String[] getServletMappings() {
		System.out.println("In getServletMappings method of WebAppInitializer class");
		 return new String[] { "/" };
	}
	
	protected ClassPathXmlApplicationContext factory = new ClassPathXmlApplicationContext("ApplicationContextConfig.xml");
}
