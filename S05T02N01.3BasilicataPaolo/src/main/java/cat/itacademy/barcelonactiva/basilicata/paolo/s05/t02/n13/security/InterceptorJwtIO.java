package cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class InterceptorJwtIO implements HandlerInterceptor {
	
	@Value("${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.token.auth.path}")
	private String AUTH_PATH;
	@Value("#{'${cat.itacademy.barcelonactiva.basilicata.paolo.s05.t02.n13.excluded.path}'.split(',')}")
	private List<String> excluded;
	
	
	@Autowired
	private JwtIO jwtIO;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		//return HandlerInterceptor.super.preHandle(request, response, handler);
		
		
		for (String string : excluded) {
			//System.out.println(string);
		}
		
		
		System.out.println(AUTH_PATH);
		
		boolean validate = false;
		String uri = request.getRequestURI();
		System.out.println(uri);
		if(uri.equals(AUTH_PATH) || excluded(uri)) {
			validate = true;
		}
		
		if(!validate && request.getHeader("Authorization") != null && !request.getHeader("Authorization").isEmpty()) {
			String token = request.getHeader("Authorization").replace("Bearer", "");
			
			validate = !jwtIO.validateToken(token);
		}
		
		
		if(!validate) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		return validate;
	}
	
	
	public boolean excluded(String path) {
		boolean result = false;
		
		for(String exc:excluded) {
			System.out.println(exc);
			if(!exc.equals("#") && exc.equals(path)) {
				result = true;
			}
		}
		
		return result;
	}
	
	
}