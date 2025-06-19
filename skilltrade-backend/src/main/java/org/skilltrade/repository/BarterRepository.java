package org.skilltrade.repository;

import org.skilltrade.entity.Barter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BarterRepository extends JpaRepository<Barter, Long> {

    List<Barter> findAllBySenderEmailOrReceiverEmail(String email, String email1);
}