package com.currencyfair.code.challenge.integration;

import com.currencyfair.code.challenge.repository.entity.Trade;
import com.currencyfair.code.challenge.repository.model.TradeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.util.UUID;

import static com.currencyfair.code.challenge.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateTradeRecordIntegrationTest extends IntegrationBase {

    @BeforeEach
    private void setup() throws ParseException {
        tradeRepository.deleteAll();
    }

    @Test
    public void whenCreateTradeWithPost_thenReturnOk() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/trade/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(gson.toJson(mockTradeRequest()))).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        UUID resultId = gson.fromJson(result.getResponse().getContentAsString(), UUID.class);
        assertNotNull(resultId);
        long size = tradeRepository.count();
        assertEquals(1L, size);
    }

    @Test
    public void whenCreateTradeWithWrongParam_thenReturnBadRequest() throws Exception {
        TradeRequest tradeRequest = mockTradeRequest();
        tradeRequest.setTimePlaced("erwerfsd");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/trade/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(gson.toJson(tradeRequest))).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status, "Incorrect Response Status");
        long size = tradeRepository.count();
        assertEquals(0L, size);
    }
}
