package com.currencyfair.code.challenge.integration;

import com.currencyfair.code.challenge.repository.entity.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.util.List;

import static com.currencyfair.code.challenge.TestUtil.*;
import static com.currencyfair.code.challenge.controller.UserControllerTests.getObjectList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTradeListByUserIdIntegrationTest extends IntegrationBase {
    @BeforeEach
    private void setup() throws ParseException {
        tradeRepository.deleteAll();
        tradeRepository.save(mockTrade(id, mockTradeRequest()));
        tradeRepository.save(mockTrade(id, mockTradeRequest()));
    }

    @Test
    public void whenGetAllTradeByUserId_thenReturnAllTradeRecord() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/" + userId + "/trade/")).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        List<Trade> resultTradeList = getObjectList(result.getResponse().getContentAsString(), Trade.class);
        assertEquals(2, resultTradeList.size());
        assertEquals(userId, resultTradeList.get(0).getUserId());
        assertEquals(userId, resultTradeList.get(1).getUserId());
    }

    @Test
    public void whenGetAllTradeByFakeUserId_thenReturnNoTradeRecord() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/12221/trade/")).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.NO_CONTENT.value(), status, "Incorrect Response Status");
    }
}
