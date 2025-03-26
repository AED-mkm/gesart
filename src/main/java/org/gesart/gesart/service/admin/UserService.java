package org.gesart.gesart.service.admin;

import com.github.dozermapper.core.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.Permission;
import org.gesart.gesart.domain.admin.Profil;
import org.gesart.gesart.domain.admin.Role;
import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.domain.enums.TypeStatut;
import org.gesart.gesart.domain.parametrage.Magasin;
import org.gesart.gesart.dto.admin.KeyAndPasswordVM;
import org.gesart.gesart.dto.admin.PasswordChangedDto;
import org.gesart.gesart.dto.admin.UserDto;
import org.gesart.gesart.repository.admin.PermissionRepository;
import org.gesart.gesart.repository.admin.ProfilRepository;
import org.gesart.gesart.repository.admin.RoleRepository;
import org.gesart.gesart.repository.admin.UserRepository;
import org.gesart.gesart.repository.parametrage.MagasinRepository;
import org.gesart.gesart.security.AuthoritiesConstants;
import org.gesart.gesart.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import tech.jhipster.security.RandomUtil;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
@SuppressWarnings("ALL")
public class UserService {
    private final Mapper mapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProfilRepository profilRepository;
    private final MagasinRepository magasinRepository;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final MailService mailService;

    /**
     * Activation d'un compte utilisateur.
     *
     * @param login
     * @return {@link User}
     */
    public Optional<User> activateRegistration(final String login) {
        return userRepository.findByStatutAndActivatedFalseAndLogin(TypeStatut.ACTIF, login)
                .map(user -> {
                    user.setActivated(true);
                    return userRepository.save(user);
                });
    }

    @Transactional
    public User creerUtilisateur(final UserDto userDto) {
	    String generatePassword = RandomUtil.generatePassword();
	    // Vérifier si le login ou l'email existent déjà
	    userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF, userDto.getLogin().toLowerCase())
			    .ifPresent(existingUser -> {
				    boolean removed = removeNonActivatedUser(existingUser);
				    if (!removed) {
					    throw new ResponseStatusException(HttpStatus.CONFLICT, "Le nom d'utilisateur est déjà utilisé");
				    }
			    });
	    userRepository.findOneByStatutAndEmailIgnoreCase(TypeStatut.ACTIF, userDto.getEmail())
			    .ifPresent(existingUser -> {
				    boolean removed = removeNonActivatedUser(existingUser);
				    if (!removed) {
					    throw new ResponseStatusException(HttpStatus.CONFLICT, "L'adresse email est déjà utilisée");
				    }
			    });

	    // Création de l'utilisateur
	    User newUser = mapper.map(userDto, User.class);
	    newUser.setLogin(userDto.getLogin().toLowerCase());
	    newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
	    newUser.setActivationKey(RandomUtil.generateActivationKey());
	    newUser.setStatut(TypeStatut.ACTIF);

	    // Vérifier et assigner les rôles
	    Set<Role> authorities = null;
	    if (userDto.getRoleName() != null && !userDto.getRoleName().isEmpty()) {
		    authorities = roleRepository.findByNameIn(Collections.singleton(userDto.getRoleName()));
		    if (authorities.isEmpty()) {
			    throw new IllegalArgumentException("Aucun rôle valide trouvé dans : " + userDto.getRoleName());
		    }
		    newUser.setAuthoritySet(authorities);
	    }

	    log.info("AAAAAAAAAAAAAAAAAaa:" + authorities);


	    // Vérifier et assigner un magasin si c'est un admin de magasin
	    if (AuthoritiesConstants.MAGASIN_ADMIN.equals(userDto.getRoleName()) &&
			    userDto.getMagasin().getId() != null) {

		    Magasin magasin = magasinRepository.findById(userDto.getMagasin().getId())
				    .orElseThrow(() -> new EntityNotFoundException("Magasin non trouvé"));
		    newUser.setMagasin(magasin);
	    }
	    // Sauvegarde de l'utilisateur
	    return userRepository.save(newUser);
    }



    /**
     * Suppression d'un utilisateur dont le compte est désactivé.
     *
     * @param existingUser
     * @return True or Fasle
     */
    private boolean removeNonActivatedUser(final User existingUser) {
        if (existingUser.isActivated()) {
            return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }

    /**
     * Update all information for a specific user, and return the modified user.
     *
     * @param userDTO user to update
     */
    public void updateUser(final UserDto userDTO) {
        Optional.of(userRepository
                .findById(userDTO.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(user -> {
                    String encodedPass = user.getPassword();

                    if (userDTO.getCurrentPassword() != null) {
                        if (!passwordEncoder.matches(userDTO.getCurrentPassword(), user.getPassword())) {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "Le mot de passe est invalide");
                        } else {
                            encodedPass = passwordEncoder.encode(userDTO.getPassword());
                        }
                    }
                    User userNew = mapper.map(userDTO, User.class);
                    if (userDTO.getResetPassword()) {
                        userNew.setPassword(encodedPass);
                        userNew.setActivated(userDTO.isActivated());
                    } else {
                        userNew.setPassword(user.getPassword());
                    }

                    userRepository.save(userNew);
                    return null;
                });
    }

    /**
     * Delete user from his login.
     *
     * @param login
     */
    public void deleteUser(final String login) {
        User user = userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF, login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Aucun compte trouvé avec ce login: " + login));
        /*user.setStatut(TypeStatut.INACTIF);*/
        user.setActivated(Boolean.FALSE);
        userRepository.save(user);
    }

    /**
     * Changement de mot de passe.
     *
     * @param password
     */
    public void changePassword(final PasswordChangedDto password) {
        SecurityUtils.getCurrentUserLogin()
                .flatMap(login -> userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF, login))
                .ifPresent(user -> {
                    if (!passwordEncoder.matches(password.getCurrentPassword(), user.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Le mot de passe est invalide");
                    }

                    if (password.getPhoto() != null) {
                        user.setPhoto(password.getPhoto());
                        user.setHasPhoto(true);
                    }
                    user.setPassword(passwordEncoder.encode(password.getPassword()));
                    userRepository.save(user);
                });
    }

    /**
     * Recuperation de la liste des utilisateurs.
     *
     * @return {@link List<UserDto>}
     */
    @Transactional(readOnly = true)
    public List<UserDto> getAllManagedUsers() {
        return userRepository.findAllByStatut(TypeStatut.ACTIF).stream()
                .map(sfdUser -> mapper.map(sfdUser, UserDto.class)).collect(Collectors.toList());
    }

    /**
     * Recuperer un utilisateur avec ses roles a partir de son login.
     *
     * @param login
     * @return {@link UserDto}
     */
    @Transactional(readOnly = true)
    public UserDto getUserWithRolesByLogin(final String login) {
        return userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF, login)
                .map(user -> mapper.map(user, UserDto.class)
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Le nom d'utilisateur n'existe pas"));
    }

    /**
     * Recuperer l'utilisateur courant avec ses roles.
     *
     * @return {@link Optional <UserDto>}
     */
    @Transactional(readOnly = true)
    public UserDto getUserWithRoles() {
        return SecurityUtils.getCurrentUserLogin()
                .flatMap(login -> userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF, login))
                .map(user -> {
                    UserDto userDto = mapper.map(user, UserDto.class);
                    // userDto.setAbonnementOk(abonnementService.checkAbonnement(userDto.getDefaultBoutiqueId()));
                    return userDto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Vous devez être connecté pour accéder à la ressource demandée !"));
    }

    /**
     * @return a list of all profils
     */
    public List<Profil> getProfils() {
        return profilRepository.findAll();
    }

    /**
     * Not activated users should be automatically deleted after 3 days.
     * <p>
     * This is scheduled to get fired everyday, at 01:00 (am).
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        userRepository.deleteAll(userRepository
                .findAllByStatutAndActivatedIsFalseAndCreatedDateBefore(
                        TypeStatut.ACTIF, Instant.now()
                                .minus(30, ChronoUnit.DAYS)));
    }

    /**
     * Get photo user from his login.
     *
     * @param login
     * @return photo
     */
    public byte[] getPhoto(final String login) {
        User user = userRepository.findOneByStatutAndLogin(TypeStatut.ACTIF, login)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Aucun compte trouvé avec ce login: " + login));
        return user.getPhoto();
    }


    /**
     * Pour envoyer le mail pour le reinitialisation du mail.
     *
     * @param email
     * @return Boolean
     */
    @Transactional
    public Boolean sendMailToResetPassword(final String email) {
        log.debug("Reset password: " + email);
        Optional<User> user = userRepository.findOneByStatutAndEmailIgnoreCase(TypeStatut.ACTIF, email);
        if (user.isPresent()) {
            user.get().setResetKey(RandomUtil.generateResetKey());
            user.get().setResetDate(Instant.now());
           // mailService.sendPasswordResetMail(user.get());
            userRepository.save(user.get());
            return Boolean.TRUE;
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Votre mail n'a pas puis être envoyé");
        }
    }

    /**
     * reinitialisation de mot de passe.
     *
     * @param keyAndPassword
     * @return Boolean
     */
    @Transactional
    public Boolean finishPasswordReset(final KeyAndPasswordVM keyAndPassword) {
        Optional<User> user = userRepository.findByStatutAndResetKey(TypeStatut.ACTIF, keyAndPassword.getKey());
        if (user.isPresent()) {
            if (user.get().getResetDate().isAfter(Instant.now().minusSeconds(86400))) {
                user.get().setPassword(passwordEncoder.encode(keyAndPassword.getNewPassword()));
                user.get().setResetDate(null);
                user.get().setResetKey(null);
                userRepository.save(user.get());
                return Boolean.TRUE;
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Clé invalide, veuillez resayer après 24 heures");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Aucun utilisateur trouvé avec cette clé");
        }
    }

    /**
     * Pour l'activation d'un utilsateur.
     *
     * @param keyAndPassword
     * @return Boolean
     */
    public Boolean activateUser(final KeyAndPasswordVM keyAndPassword) {
        Optional<User> user = userRepository
                .findByStatutAndActivationKey(TypeStatut.ACTIF, keyAndPassword.getKey());
        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(keyAndPassword.getNewPassword()));
            user.get().setActivationKey(null);
            user.get().setActivated(Boolean.TRUE);
            userRepository.save(user.get());
            return Boolean.TRUE;
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Aucun utilisateur trouvé avec cette clé d'activation");
        }
    }

    /**
     * User par id.
     *
     * @param id
     * @return UserDto
     */
    public UserDto findUserById(final Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return mapper.map(optionalUser.get(), UserDto.class);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Utilisateur introuvable");
        }
    }

    /**
     * Recuperation des de caissier par boutique.
     *
     * @param boutiqueId
     * @return {@link List<UserDto>}
     */
    @Transactional(readOnly = true)
    public List<UserDto> getAllCaissierByBoutique(final Long boutiqueId) {
        return null;
    }

    /**
     * Donner une permission directement à un utilisateur
     * @param userId
     * @param permissionId
     */

    public void ajouterPermissionAUtilisateur(Long userId, Long permissionId) {
        log.debug("Adding Permission with ID {} to User with ID {}", permissionId, userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        Permission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new EntityNotFoundException("Permission not found with ID: " + permissionId));

        if (!user.getPermissions().contains(permission)) {
            user.getPermissions().add(permission);
            userRepository.save(user);
            log.info("Permission with ID {} added to User with ID {}", permissionId, userId);
        } else {
            log.warn("Permission with ID {} is already associated with User with ID {}", permissionId, userId);
        }
    }
    @Transactional
    public void ajouterRoleAUtilisateur(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new EntityNotFoundException("Rôle non trouvé"));

        user.getAuthoritySet().add(role);
        userRepository.save(user);
    }


}
