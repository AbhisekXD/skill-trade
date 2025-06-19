package org.skilltrade.dto;

import lombok.Data;

@Data
public class BarterRequestDTO {

    private Long receiverUserId;       // whom you are sending barter request to

    private String taskDescription;    // what do you want done
    private String offeredSkill;       // your skill offered
    private String requestedSkill;     // skill you want in return

    private int senderEstimatedHours;      // your task effort
    private int receiverEstimatedHours;    //
}