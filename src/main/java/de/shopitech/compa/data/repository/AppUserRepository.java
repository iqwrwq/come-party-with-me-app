package de.shopitech.compa.data.repository;

import de.shopitech.compa.data.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID>, JpaSpecificationExecutor<AppUser> {

    AppUser findAppUserByEmail(String email);

    void deleteAllByEmailNot(String email);
}
