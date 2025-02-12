package org.gesart.gesart.security.jwt;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author : <A>BRAHIMA TRAORE</A>
 * @version : 1.0
 * Copyright (c) 2024.
 * @since : 2024/12/15 à 00:16
 */

@Component
@Slf4j
@SuppressWarnings("ALL")
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    /**
     * Check access resources using user authentication.
     *
     * @param request
     * @param response
     * @param e
     * @throws IOException
     */
    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException e) throws IOException, ServletException {
        log.error("Unauthorized error: {}", e.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                e.getLocalizedMessage());
    }
}
