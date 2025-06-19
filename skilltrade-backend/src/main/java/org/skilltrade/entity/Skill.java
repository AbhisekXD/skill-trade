package org.skilltrade.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String name;
    private String proficiency; // Beginner, Intermediate, Expert

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}