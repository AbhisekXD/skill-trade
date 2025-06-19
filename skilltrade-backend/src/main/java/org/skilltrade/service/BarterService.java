package org.skilltrade.service;

import org.skilltrade.dto.BarterRequestDTO;
import org.skilltrade.entity.Barter;
import org.skilltrade.entity.User;
import org.skilltrade.repository.BarterRepository;
import org.skilltrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class BarterService {

    @Autowired
    private BarterRepository barterRepo;
    @Autowired
    private UserRepository userRepo;

    @Async
    public CompletableFuture<Void> logBarterMatch(Barter barter) {
        System.out.println("Logged barter asynchronously: " + barter.getId());
        return CompletableFuture.completedFuture(null);
    }

    public void createBarterRequest(String senderEmail, BarterRequestDTO dto) {
        User sender = userRepo.findByEmail(senderEmail)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepo.findById(dto.getReceiverUserId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        Barter barter = new Barter();
        barter.setSender(sender);
        barter.setReceiver(receiver);
        barter.setDescription(dto.getTaskDescription());
        barter.setOfferedSkill(dto.getOfferedSkill());
        barter.setRequestedSkill(dto.getRequestedSkill());
        barter.setSenderEstimatedHours(dto.getSenderEstimatedHours());
        barter.setReceiverEstimatedHours(dto.getReceiverEstimatedHours());
        barter.setStatus("PENDING");
        barter.setCreatedAt(new Date());

        barterRepo.save(barter);

        logBarterMatch(barter); // async
    }

    public List<Barter> getAllBartersForUser(String email) {
        return barterRepo.findAllBySenderEmailOrReceiverEmail(email, email);
    }

    public void markBarterAsCompleted(Long barterId) {
        Barter barter = barterRepo.findById(barterId)
                .orElseThrow(() -> new RuntimeException("Barter not found"));

        barter.setStatus("COMPLETED");
        barter.setCompletedAt(new Date());
        barterRepo.save(barter);
    }
}
