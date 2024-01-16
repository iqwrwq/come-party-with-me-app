package de.shopitech.compa.data.repository;

import de.shopitech.compa.data.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event,UUID> {
}
