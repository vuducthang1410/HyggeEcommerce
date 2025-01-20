package org.vdt.productmanagementservice.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.vdt.productmanagementservice.services.IJwtService;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtFilterConfig extends OncePerRequestFilter {
    private final RedisTemplate<String, Object> redisTemplate;
    private final IJwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = authorizationHeader.substring(7);
            String userId = jwtService.getUserIdFromToken(token);
            String tokenCacheRedis = (String) redisTemplate.opsForValue().get(userId);
            if (token.equalsIgnoreCase(tokenCacheRedis)) {
                authenticateTokenAndSetSecurityContext(request, userId);
            } else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.getWriter().write("TOKEN_NOT_VALID");
                return;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("TOKEN_EXPIRATION");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateTokenAndSetSecurityContext(HttpServletRequest request, String userId) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userId, userId, null);
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
    }
}
