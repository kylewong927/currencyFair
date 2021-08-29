package com.currencyfair.code.challenge;

import com.currencyfair.code.challenge.repository.entity.Trade;
import com.currencyfair.code.challenge.repository.model.TradeRequest;
import com.google.gson.Gson;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtil {

    public static Gson gson = new Gson();

    public static TradeRequest mockTradeRequest() {
        return new TradeRequest(
                "12345", "HKD", "USD", BigDecimal.valueOf(123), BigDecimal.valueOf(123), BigDecimal.valueOf(0.123), "24-Jan-18 10:27:44", "HK"
        );
    }

    public static Trade mockTrade(UUID id, TradeRequest tradeRequest) throws ParseException {
        Trade trade = new Trade(tradeRequest);
        trade.setId(id);
        return trade;
    }

    public static List<Trade> mockTradeList() throws ParseException{
        List<Trade> tradeList = new ArrayList<Trade>();
        tradeList.add(mockTrade(UUID.randomUUID(), mockTradeRequest()));
        return tradeList;
    }
}
