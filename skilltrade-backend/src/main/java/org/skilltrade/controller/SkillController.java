package org.skilltrade.controller;

import org.skilltrade.dto.SkillDTO;
import org.skilltrade.entity.Skill;
import org.skilltrade.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    @Autowired private SkillService skillService;

    @PostMapping("/add")
    public void addSkill(@RequestBody SkillDTO dto, Principal principal) {
        skillService.addSkill(principal.getName(), dto);
    }

    @GetMapping("/my")
    public List<Skill> mySkills(Principal principal) {

        return skillService.getUserSkills(principal.getName());
    }

    @GetMapping("/by-level/{level}")
    public List<Skill> byProficiency(@PathVariable String level) {

        return skillService.getSkillsByProficiency(level);
    }
}
