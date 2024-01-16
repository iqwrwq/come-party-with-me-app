package de.shopitech.compa.data.entity;

import de.shopitech.compa.data.model.ReservationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class GiftReservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID reservationId;

    @ManyToOne
    @JoinColumn(name = "gift_list_id")
    private GiftList giftList;

    @ManyToOne
    @JoinColumn(name = "reserver_user_id")
    private AppUser reserver;

    private Date reservationDate;
    private ReservationStatus status;

    // Constructors, getters, setters, and other methods...
}
