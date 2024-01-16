package de.shopitech.compa.data.repository;

import de.shopitech.compa.data.entity.GiftList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GiftListRepository extends JpaRepository<GiftList, UUID> {
}
