package com.example.jwt;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.customer.CustomerUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private final CustomerUserDetailsService customerUserDetailsService;

  public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomerUserDetailsService customerUserDetailsService) {
    this.jwtUtil = jwtUtil;
    this.customerUserDetailsService = customerUserDetailsService;
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {
    String authorizationHeader = request.getHeader(AUTHORIZATION);
    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
      // Reject Request and continue with Filter Chain
      filterChain.doFilter(request, response);
      return;
    }
    // Substring at Position 7 because `Bearer ` is ignored for JWT
    String jwt = authorizationHeader.substring(7);
    String subject = jwtUtil.getSubject(jwt);
    // SecurityContextHolder contains Information about the Principal who is
    // currently authenticated
    // When SecurityContextHolder is null, it means that, the User is not
    // authenticated
    if (subject != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = customerUserDetailsService.loadUserByUsername(subject);
      if (jwtUtil.isJwtValid(jwt, userDetails.getUsername())) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,
            null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        ;
      }
    }
    // Continue with Filter Chain
    filterChain.doFilter(request, response);
  }
}
