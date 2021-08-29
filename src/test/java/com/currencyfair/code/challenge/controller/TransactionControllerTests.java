package com.currencyfair.code.challenge.controller;

import com.currencyfair.code.challenge.repository.entity.Trade;
import com.currencyfair.code.challenge.repository.model.TradeRequest;
import com.currencyfair.code.challenge.service.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import springfox.documentation.spring.web.json.Json;

import java.util.NoSuchElementException;
import java.util.UUID;


import static com.currencyfair.code.challenge.TestUtil.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    @Test
    public void whenPostWithTradeRequest_thenIdReturned() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(tradeService.saveTrade(any(TradeRequest.class))).thenReturn(id);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/trade/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(gson.toJson(mockTradeRequest()))).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");
        verify(tradeService).saveTrade(any(TradeRequest.class));

        UUID resultId = gson.fromJson(result.getResponse().getContentAsString(), UUID.class);
        assertNotNull(resultId);
        assertEquals(id, resultId);
    }

    @Test
    public void whenGetTradeByExistingId_thenTradeRecordReturned() throws Exception {
        UUID id = UUID.randomUUID();
        Trade mockedTrade = mockTrade(id, mockTradeRequest());
        Mockito.when(tradeService.getTradeById(id)).thenReturn(mockedTrade);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/trade/" + id.toString())).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");
        verify(tradeService).getTradeById(any(UUID.class));

        Trade resultTrade = gson.fromJson(result.getResponse().getContentAsString(), Trade.class);
        assertNotNull(resultTrade);
        assertEquals(mockedTrade.getId(), resultTrade.getId());
        assertEquals(mockedTrade.getAmountBuy(), resultTrade.getAmountBuy());
        assertEquals(mockedTrade.getAmountSell(), resultTrade.getAmountSell());
        assertEquals(mockedTrade.getCurrencyFrom(), resultTrade.getCurrencyFrom());
        assertEquals(mockedTrade.getCurrencyTo(), resultTrade.getCurrencyTo());
        assertEquals(mockedTrade.getOriginatingCountry(), resultTrade.getOriginatingCountry());
        assertEquals(mockedTrade.getTimePlaced(), resultTrade.getTimePlaced());
        assertEquals(mockedTrade.getRate(), resultTrade.getRate());
    }

    @Test
    public void whenGetTradeByFakeId_thenNoContentReturned() throws Exception {
        UUID id = UUID.randomUUID();
        Mockito.when(tradeService.getTradeById(id)).thenThrow(new NoSuchElementException());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/trade/" + id.toString())).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.NO_CONTENT.value(), status, "Incorrect Response Status");
        verify(tradeService).getTradeById(any(UUID.class));
    }

    @Test
    public void whenTradeRequestWithNullCurrencyFromAndTo_thenErrorMessageReturned() throws Exception {
        TradeRequest tradeRequest = mockTradeRequest();
        tradeRequest.setCurrencyFrom(null);
        tradeRequest.setCurrencyTo(null);

        String json = gson.toJson(tradeRequest);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/trade/").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status, "Incorrect Response Status");

        String resultJson = result.getResponse().getContentAsString();
        assertTrue(resultJson.contains("CurrencyTo is mandatory"));
        assertTrue(resultJson.contains("CurrencyFrom is mandatory"));
    }
}
