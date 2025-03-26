package org.gesart.gesart.service.admin;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.Role;
import org.gesart.gesart.domain.enums.TypeStatut;
import org.gesart.gesart.dto.admin.AccountDto;
import org.gesart.gesart.dto.admin.CurrentUserDto;
import org.gesart.gesart.repository.admin.RoleRepository;
import org.gesart.gesart.repository.admin.UserRepository;
import org.gesart.gesart.security.NUserDetailsService;
import org.gesart.gesart.security.jwt.AuthTokenFilter;
import org.gesart.gesart.security.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author : <a href="siguizana08@gmail.com"> BRAHIMA TRAORE </a>.
 * @version : 1.0
 * @since : 07/12/2022 10:50:38mn
 **/
@SuppressWarnings("ALL")
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository authorityRepository;
    private final NUserDetailsService userDetailsService;


    /**
     * Service de connexion au système.
     *
     * @param accountDto les credentials de l'utilisateur
     * @return l'utilisateur connecté
     */
    public ResponseEntity<CurrentUserDto> authorize(final AccountDto accountDto) {
        if (!userRepository.existsByStatutAndLogin(TypeStatut.ACTIF, accountDto.getLogin())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Vos informations de connextion sont incorrectes !");
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(accountDto.getLogin(), accountDto.getPassword());

        log.debug("Auth token : {}", authenticationToken.getCredentials());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(accountDto.getLogin());
        String jwt = tokenProvider.generateJwtToken(userDetails);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(AuthTokenFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        CurrentUserDto currentUserDto = new CurrentUserDto(jwt, "bearer");
        return new ResponseEntity<>(currentUserDto, httpHeaders, HttpStatus.OK);
    }

    /**
     * Recuperation de la liste des roles.
     *
     * @return list des rôles
     */
    public List<Role> getAllAuthority() {
        return authorityRepository.findAll();
    }

    /**
     * creation du role.
     *
     * @param objet
     * @return Authority
     */
    public Role createAuthority(final Role objet) {
        return authorityRepository.save(objet);
    }

    /**
     * suppresion d'un role.
     *
     * @param name
     * @return Boolean
     */
    public Boolean suppressionAuthority(final @NotNull @Size(max = 50) String name) {
        authorityRepository.deleteById(name);
        return Boolean.TRUE;
    }
}
