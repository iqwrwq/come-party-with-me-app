package de.shopitech.compa.data.entity;

import de.shopitech.compa.data.model.EventRole;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ParticipantList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID participantListId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @Enumerated(EnumType.STRING)
    private EventRole eventRole;
}
