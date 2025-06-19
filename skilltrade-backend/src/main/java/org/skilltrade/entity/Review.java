package org.skilltrade.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rating; // 1–5 stars
    private String comment;

    @ManyToOne
    private User reviewer;

    @ManyToOne
    private User reviewed;

    @OneToOne
    private Barter barter;
}
