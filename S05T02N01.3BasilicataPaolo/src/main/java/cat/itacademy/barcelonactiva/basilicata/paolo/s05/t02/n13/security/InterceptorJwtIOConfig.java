package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class InterceptorJwtIOConfig implements WebMvcConfigurer {
	@Value("${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.security.enabled:false}")
	private boolean securityEnabled;
	
	@Autowired
	private InterceptorJwtIO interceptorJwtIO;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.addInterceptors(registry);
		
		if(securityEnabled) {
			registry.addInterceptor(interceptorJwtIO);
		}
	}
	
	
	
}
