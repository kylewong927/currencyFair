package com.currencyfair.code.challenge.controller;

import com.currencyfair.code.challenge.repository.entity.Trade;
import com.currencyfair.code.challenge.service.TradeService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.currencyfair.code.challenge.TestUtil.*;
import static com.google.gson.JsonParser.parseString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TradeService tradeService;

    private final String userId = "12345";

    @Test
    public void whenGetAllTradeByUserIdWithExistingUserId_thenTradeListReturned() throws Exception {

        Mockito.when(tradeService.getAllTradeByUserId(any(String.class))).thenReturn(mockTradeList());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/" + userId + "/trade/")).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");
        verify(tradeService).getAllTradeByUserId(any(String.class));

        List<Trade> resultTradeList = getObjectList(result.getResponse().getContentAsString(), Trade.class);
        assertEquals(1, resultTradeList.size());
        Trade resultTrade = resultTradeList.get(0);
        assertEquals(userId, resultTrade.getUserId());
    }

    @Test
    public void whenGetAllTradeByUserIdWithFakeUserId_thenNoTradeListReturned() throws Exception {

        Mockito.when(tradeService.getAllTradeByUserId("12345")).thenReturn(mockTradeList());

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/12221/trade/")).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.NO_CONTENT.value(), status, "Incorrect Response Status");
        verify(tradeService).getAllTradeByUserId(any(String.class));

        List<Trade> resultTradeList = getObjectList(result.getResponse().getContentAsString(), Trade.class);
        assertEquals(0, resultTradeList.size());
    }

    public static <T> List<T> getObjectList(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            JsonArray arry = parseString(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
