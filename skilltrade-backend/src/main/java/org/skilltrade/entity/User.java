package org.skilltrade.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Generated;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    private String email;
    private String password;
    private boolean isEduEmail;

    private int karmaPoints = 0;  // reputation
    private double trustScore = 0.0;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    private List<Barter> sentBarters = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Barter> receivedBarters = new ArrayList<>();
}
