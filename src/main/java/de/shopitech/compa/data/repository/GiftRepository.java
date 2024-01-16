package de.shopitech.compa.data.repository;

import de.shopitech.compa.data.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GiftRepository extends JpaRepository<Gift,UUID> {
}
