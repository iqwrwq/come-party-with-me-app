package de.shopitech.compa.data.entity;

import de.shopitech.compa.data.model.SuggestionType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ProductSuggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID suggestionId;

    @Enumerated(EnumType.STRING)
    private SuggestionType suggestionType;

    private String productName;
    private String productDescription;
    private String productLink;

    // Constructors, getters, setters, and other methods...
}
