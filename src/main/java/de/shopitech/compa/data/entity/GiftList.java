package de.shopitech.compa.data.entity;

import de.shopitech.compa.data.model.Visibility;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class GiftList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID giftListId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "creator_user_id")
    private AppUser creator;

    private String title;
    private double budget;
    private String occasion;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;
}
