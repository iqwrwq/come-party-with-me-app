package de.shopitech.compa.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.data.model.AppUserRole;
import de.shopitech.compa.data.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;

@Service
public class DefaultAdminService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${unsplash.accesskey}")
    public String UNSPLASH_ACCESS_KEY;

    @Value("${unsplash.secret}")
    public String UNSPLASH_SECRET_KEY;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    public DefaultAdminService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createDefaultAdminUser() {
        AppUser existingAdmin = appUserRepository.findAppUserByEmail(adminUsername);

        if (existingAdmin == null) {
            AppUser adminUser = new AppUser();
            adminUser.setEmail(adminUsername);
            adminUser.setPassword(passwordEncoder.encode(adminPassword));
            adminUser.setFirstName("Arthur");
            adminUser.setLastName("ADMIN");
            adminUser.setUsername("the_nice_admin25");
            adminUser.setFollower(420);
            adminUser.setEventsPlanned(29);
            adminUser.setFollowed(2);
            adminUser.setBannerUrl(getRandomUnsplashImageUrl());
            adminUser.setAppUserRole(Collections.singleton(AppUserRole.ADMIN));

            appUserRepository.save(adminUser);

            System.out.println("Default admin user created successfully.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }

    private String getRandomUnsplashImageUrl() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.unsplash.com/photos/random?client_id=" + UNSPLASH_ACCESS_KEY))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());

            return jsonNode.get("urls").get("regular").asText();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
