package de.shopitech.compa.data.entity;

import de.shopitech.compa.data.model.AppUserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID appUserId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private int eventsPlanned = 0;
    private int follower = 0;
    private int followed = 0;
    private String bio = "This is my awesome profile, check it out!";
    private LocalDate birthDate;
    private LocalDate creationDate = LocalDate.now();
    private String profilePicture;
    private Set<AppUserRole> appUserRole;
    private String avatarUrl;
    private String bannerUrl;

    public Set<AppUserRole> getAppUserRole() {
        return appUserRole != null ? appUserRole : Collections.emptySet();
    }

    public void setRoles(Set<AppUserRole> appUserRoles) {
        this.appUserRole = appUserRoles;
    }
}
