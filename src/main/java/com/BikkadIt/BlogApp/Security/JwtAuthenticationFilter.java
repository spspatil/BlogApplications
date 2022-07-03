package com.BikkadIt.BlogApp.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println(request + "ssssssssssssssssssssssssss");
		
		String requestToken = request.getHeader("Authorization");
		
		//Bearer 23444o39jfjv   Authorization
		
		System.out.println("+++++++++++"+requestToken);
		
		String userName=null;
		
		String token=null;
		
		if (request!=null && requestToken.startsWith("Bearer")) {
			
			token=requestToken.substring(7);
			
			try {
			userName = this.jwtTokenHelper.getUsernameFromToken(token);
			}
			catch (IllegalArgumentException e) {
				
				
				System.out.println("unable to get jwt token");
			}
			catch (ExpiredJwtException e) {
				
				System.out.println("jwt token has expired");
				
			}
			catch (MalformedJwtException e) {
				// TODO: handle exception
				
				System.out.println("invalid jwt");
			}
			
			
		}else {
			System.out.println("Jwt token does not begin with Bearer");
		}
		
		
		//once getting token now validating
		
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			
			if(this.jwtTokenHelper.validateToken(token, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
				=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				
				System.out.println("invalid jwt token");
			}
			
		}else {
			
			System.out.println("username is null or context is not null");
		}
		
		
		
		filterChain.doFilter(request, response);
	}

}
