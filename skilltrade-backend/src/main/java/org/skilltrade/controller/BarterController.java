package org.skilltrade.controller;

import org.skilltrade.dto.BarterRequestDTO;
import org.skilltrade.entity.Barter;
import org.skilltrade.service.BarterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/barter")
public class BarterController {

    @Autowired
    private BarterService barterService;

    @PostMapping("/request")
    public void sendBarterRequest(@RequestBody BarterRequestDTO dto, Principal principal) {
        barterService.createBarterRequest(principal.getName(), dto);
    }

    @GetMapping("/my")
    public List<Barter> myBarters(Principal principal) {
        return barterService.getAllBartersForUser(principal.getName());
    }

    @PostMapping("/complete/{id}")
    public void complete(@PathVariable Long id) {
        barterService.markBarterAsCompleted(id);
    }
}
