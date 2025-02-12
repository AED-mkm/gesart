package org.gesart.gesart.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.domain.enums.TypeStatut;
import org.gesart.gesart.repository.admin.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author : <a href="siguizana08@gmail.com"> BRAHIMA TRAORE </a>.
 * @version : 1.0
 * @since : 07/12/2022 10:50:38mn
 **/

@Service
@Slf4j
@Data
@RequiredArgsConstructor
@SuppressWarnings("ALL")
public class NUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    /**
     * Get user by username.
     *
     * @param keyword
     * @return user {@link User}
     * @throws ResponseStatusException
     */
    @Override
    public UserDetails loadUserByUsername(final String keyword) throws UsernameNotFoundException {
        if (new EmailValidator().isValid(keyword, null)) {
            return userRepository.findOneByStatutAndEmailIgnoreCase(TypeStatut.ACTIF, keyword)
                    .map(this::createSpringSecurityUser)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Un utilisateur avec le login: "
                                    + keyword + " n'exite pas dans la base de donnée"));
        }

        return userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF, keyword)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Un utilisateur avec le login: "
                                + keyword.toLowerCase(Locale.FRENCH)
                                + " n'exite pas dans la base de donnée"));
    }

    /**
     * Get spring security user.
     *
     * @param user
     * @return spring security user {@link org.springframework.security.core.userdetails.User}
     */
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(final User user) {
        List<GrantedAuthority> grantedAuthorities = user.getProfil().getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getLogin(),
                user.getPassword(),
                grantedAuthorities);
    }
}
