package org.gesart.gesart.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.security.NUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author :  <A>BRAHIMA TRAORE</A>
 * @version : 1.0
 * Copyright (c) 2024, All rights reserved.
 * @since : 2024/12/15 à 00:16
 */

@Slf4j
@SuppressWarnings("ALL")
public class AuthTokenFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private NUserDetailsService userDetailsService;

    /**
     * Parse token.
     *
     * @param request
     * @return token parsed
     */
    private String parseJwt(final HttpServletRequest request) {
        String headerAuth = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            String token = headerAuth.substring(7);
            log.debug("==== JWT token: {}", token);
            return token;
        }
        return null;
    }

    /**
     * Le filter du token des requetes http.
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = resolveToken(request);

            if (StringUtils.hasText(jwt)) {
                String username = tokenProvider.extractUsername(jwt);
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (tokenProvider.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }


            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            log.info("Security exception for user {} - {}",
                    e.getClaims().getSubject(), e.getMessage());

            log.trace(String.valueOf(e));
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }


    private String resolveToken(final HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return "";
    }

}
