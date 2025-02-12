package org.gesart.gesart.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author : <a href="siguizana08@gmail.com"> BRAHIMA TRAORE </a>.
 * @version : 1.0
 **/
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    /**
     * Get current auditor.
     *
     * @return username
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof String) {
                return Optional.of((String) authentication.getPrincipal());
            }
            return Optional.of(((User) authentication.getPrincipal()).getUsername());
        } else {
            return Optional.of("anonymous");
        }
    }
}
