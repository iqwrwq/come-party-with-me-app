package de.shopitech.compa.security;

import com.vaadin.flow.spring.security.AuthenticationContext;
import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.data.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Component
public class AuthenticatedUser {

    private final AppUserRepository appUserRepository;
    private final AuthenticationContext authenticationContext;

    public AuthenticatedUser(AuthenticationContext authenticationContext, AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
        this.authenticationContext = authenticationContext;
    }

    @Transactional
    public Optional<AppUser> get() {
        return authenticationContext.getAuthenticatedUser(UserDetails.class)
                .map(userDetails -> appUserRepository.findAppUserByEmail(userDetails.getUsername()));
    }

    public void logout() {
        authenticationContext.logout();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
