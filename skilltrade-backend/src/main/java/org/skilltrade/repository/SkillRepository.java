package org.skilltrade.repository;

import org.skilltrade.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByProficiency(String k);

    List<Skill> findByUserEmail(String email);
}