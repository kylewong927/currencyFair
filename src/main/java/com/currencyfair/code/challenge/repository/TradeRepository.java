package com.currencyfair.code.challenge.repository;

import com.currencyfair.code.challenge.repository.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TradeRepository extends JpaRepository<Trade, UUID> {

    List<Trade> findByUserId(String userId);
}
