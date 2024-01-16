package de.shopitech.compa.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Gift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID giftId;

    private String name;
    private String description;
    private double price;

    public Gift(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
