package com.currencyfair.code.challenge.service;

import com.currencyfair.code.challenge.repository.TradeRepository;
import com.currencyfair.code.challenge.repository.entity.Trade;
import com.currencyfair.code.challenge.repository.model.TradeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class TradeService {

    @Autowired
    public TradeRepository tradeRepository;

    public UUID saveTrade(TradeRequest tradeRequest) throws ParseException {
        Trade trade = new Trade(tradeRequest);
        Trade savedTrade = tradeRepository.save(trade);
        return savedTrade.getId();
    }

    public Trade getTradeById(UUID id) throws NoSuchElementException, Exception {
        return tradeRepository.findById(id).orElseThrow();
    }

    public List<Trade> getAllTradeByUserId(String userId) {
        List<Trade> tradeList = tradeRepository.findByUserId(userId);
        return tradeList;
    }
}
