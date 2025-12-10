package org.example.blackjack.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "decks")
public class Deck {
    @Id
    private String id;
    private int remaining;

    @OneToMany(mappedBy = "deck")
    List<Card> drawnCards;

    public Deck(String id, int remaining){
        this.id = id;
        this.remaining = remaining;
    }
}