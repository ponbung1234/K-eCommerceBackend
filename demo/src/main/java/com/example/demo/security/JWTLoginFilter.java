package com.example.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.example.demo.dao.UsersRepository;
import com.example.demo.model.CustomUserDetails;
import com.example.service.CustomUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableJpaRepositories(basePackageClasses = UsersRepository.class)
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private CustomUserDetailsService userService;

	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {

		AccountCredentials creds = new ObjectMapper().readValue(req.getReader(), AccountCredentials.class);
		if(userService == null) {
			ServletContext servletContext = req.getServletContext();
            WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
            userService = webApplicationContext.getBean(CustomUserDetailsService.class);
		}
		String username = creds.getUsername();
		String password = creds.getPassword();
		
		if(userService == null)
			System.out.println("userService is null");
		
		CustomUserDetails user = userService.loadUserByUsername(username);

		if (user == null) {
			System.out.println("user is null");
			throw new BadCredentialsException("1000");
		}
		System.out.println(user.getId());
		if (!user.isEnabled()) {
			System.out.println("user is disabled");
			throw new DisabledException("1001");
		}
		if (!password.equals(user.getPassword())) {
			System.out.println("wrong password");
			throw new BadCredentialsException("1000");
		}
		Authentication auth = new UsernamePasswordAuthenticationToken(username, password,
				 user.getAuthorities());
		return auth;
		

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		TokenAuthenticationService.addAuthentication(res, auth.getName());
		System.out.println("name : "+auth.getName());
		System.out.println("credentials : "+auth.getCredentials());

	}
	
}