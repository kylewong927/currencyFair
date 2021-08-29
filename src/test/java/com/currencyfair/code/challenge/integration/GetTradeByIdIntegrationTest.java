package com.currencyfair.code.challenge.integration;

import com.currencyfair.code.challenge.repository.entity.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import static com.currencyfair.code.challenge.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GetTradeByIdIntegrationTest extends IntegrationBase {

    @BeforeEach
    private void setup() throws ParseException {
        tradeRepository.deleteAll();
        tradeRepository.save(mockTrade(id, mockTradeRequest()));
    }

    @Test
    public void whenGetTradeById_thenReturnTradeRecord() throws Exception {
        List<Trade> tradeList = tradeRepository.findAll();
        String id = tradeList.get(0).getId().toString();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/trade/" + id.toString())).andReturn();

        Trade mockedTrade = tradeList.get(0);
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        Trade resultTrade = gson.fromJson(result.getResponse().getContentAsString(), Trade.class);
        assertNotNull(resultTrade);
        assertEquals(id, resultTrade.getId().toString());
        assertEquals(mockedTrade.getAmountBuy(), resultTrade.getAmountBuy());
        assertEquals(mockedTrade.getAmountSell(), resultTrade.getAmountSell());
        assertEquals(mockedTrade.getCurrencyFrom(), resultTrade.getCurrencyFrom());
        assertEquals(mockedTrade.getCurrencyTo(), resultTrade.getCurrencyTo());
        assertEquals(mockedTrade.getOriginatingCountry(), resultTrade.getOriginatingCountry());
        assertEquals(mockedTrade.getTimePlaced().toInstant(), resultTrade.getTimePlaced().toInstant());
        assertEquals(mockedTrade.getRate(), resultTrade.getRate());
    }

    @Test
    public void whenGetTradeByFakeId_thenReturnNoTradeRecord() throws Exception {
        UUID fakeId = UUID.randomUUID();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/trade/" + fakeId.toString())).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.NO_CONTENT.value(), status, "Incorrect Response Status");

    }
}
