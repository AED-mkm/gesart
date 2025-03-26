package org.gesart.gesart.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gesart.gesart.domain.admin.User;
import org.gesart.gesart.repository.admin.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Data
@RequiredArgsConstructor
public class SecurityService {

	@Autowired
	private UserRepository userRepository;

	public boolean hasAccessToMagasin(Long idMagasin) {
		Optional<String> login = SecurityUtils.getCurrentUserLogin();
		if (login.isEmpty()) {
			return false; // Aucun utilisateur connecté
		}
		User user = userRepository.findByLogin(login.get())
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
		// Vérifie si l'utilisateur est bien associé au magasin demandé
		return user.getMagasin() != null && user.getMagasin().getId().equals(idMagasin);
	}
}
