package de.shopitech.compa.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID eventId;

    private String title;
    private String description;
    private Date dateAndTime;
    private String location;
    @ManyToOne
    @JoinColumn(name = "organizer_app_user_id")
    private AppUser organizer;
}