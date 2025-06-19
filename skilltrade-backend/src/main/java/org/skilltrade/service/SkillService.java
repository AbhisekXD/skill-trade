package org.skilltrade.service;

import org.skilltrade.dto.SkillDTO;
import org.skilltrade.entity.Skill;
import org.skilltrade.entity.User;
import org.skilltrade.repository.SkillRepository;
import org.skilltrade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SkillService {

    @Autowired private SkillRepository skillRepo;
    @Autowired private UserRepository userRepo;

    private final Map<String, List<Skill>> skillCache = new ConcurrentHashMap<>();

    public void addSkill(String userEmail, SkillDTO dto) {
        User user = userRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Skill skill = new Skill();
        skill.setName(dto.getName());
        skill.setProficiency(dto.getProficiency());
        skill.setUser(user);

        skillRepo.save(skill);
        skillCache.remove(dto.getProficiency()); // Clear cache for updated proficiency
    }

    public List<Skill> getSkillsByProficiency(String level) {
        return skillCache.computeIfAbsent(level, k -> skillRepo.findByProficiency(k));
    }

    public List<Skill> getUserSkills(String email) {
        return skillRepo.findByUserEmail(email);
    }
}
