package com.tuwindi.erp.erpservice.security.jwt;

import com.tuwindi.erp.erpservice.exception.CustomException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, Authorization, X-Requested-With, remember-me");
        response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, enctype, Content-Type, Access-Control-Request-Method, " + "Access-Control-Request-Headers, , X-Requested-With, Authorization");
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // System.out.println("request.getMethod() : "+request.getMethod());
            response.setStatus(HttpServletResponse.SC_OK);
        }

        String token = jwtTokenProvider.resolveToken(request);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                System.out.println("INVALIDE TOKEN : " + token);
            }
        } catch (CustomException e) {
            // ex.printStackTrace(System.err);
            // this is very important, since it guarantees the user is not authenticated at all
            SecurityContextHolder.clearContext();
            response.sendError(e.getHttpStatus().value(), e.getMessage());
            return;
        }

        filterChain.doFilter(request, response);

    }
}
