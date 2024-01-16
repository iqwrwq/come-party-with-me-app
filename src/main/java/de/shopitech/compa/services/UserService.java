package de.shopitech.compa.services;

import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.data.repository.AppUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final AppUserRepository appUserRepository;

    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public Optional<AppUser> get(UUID id) {
        return appUserRepository.findById(id);
    }

    public AppUser update(AppUser entity) {
        return appUserRepository.save(entity);
    }

    public void delete(UUID id) {
        appUserRepository.deleteById(id);
    }

    public Page<AppUser> list(Pageable pageable) {
        return appUserRepository.findAll(pageable);
    }

    public Page<AppUser> list(Pageable pageable, Specification<AppUser> filter) {
        return appUserRepository.findAll(filter, pageable);
    }

    public int count() {
        return (int) appUserRepository.count();
    }

}
