package org.vdt.productmanagementservice.services;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.HashMap;

public interface IJwtService {
    
    String generateRefreshToken(String username);
    
    String generateAccessToken(HashMap<String, Object> claims, String username, Collection<GrantedAuthority> role);
    
    String getUserIdFromToken(String token);
    
    Collection<GrantedAuthority> getAuthoritiesFromToken(String token);
    
    boolean isTokenExpiration(String token);
}
