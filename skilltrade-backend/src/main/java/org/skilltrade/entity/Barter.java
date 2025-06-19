package org.skilltrade.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
public class Barter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    private String description;
    private String offeredSkill;
    private String requestedSkill;

    private int senderEstimatedHours;
    private int receiverEstimatedHours;

    private String status = "PENDING"; // PENDING, AGREED, COMPLETED, DISPUTED

    private Date createdAt = new Date();
    private Date completedAt;

}