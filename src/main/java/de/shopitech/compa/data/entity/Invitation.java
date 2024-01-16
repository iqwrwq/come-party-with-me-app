package de.shopitech.compa.data.entity;

import de.shopitech.compa.data.model.InvitationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID invitationId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "inviter_user_id")
    private AppUser inviter;

    @ManyToOne
    @JoinColumn(name = "invited_user_id")
    private AppUser invited;

    private Date invitationDate;
    private InvitationStatus status;

    // Constructors, getters, setters, and other methods...
}
