package com.currencyfair.code.challenge.service;

import com.currencyfair.code.challenge.repository.TradeRepository;
import com.currencyfair.code.challenge.repository.entity.Trade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.currencyfair.code.challenge.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TradeService.class)
public class TradeServiceTests {

    private final UUID id = UUID.randomUUID();

    private final String userId = "12345";

    @Autowired
    private TradeService tradeService;

    @MockBean
    private TradeRepository tradeRepository;

    @BeforeEach
    public void setup() {
        tradeRepository.deleteAll();
    }

    @Test
    public void whenSaveTradeRecordSucceed_thenReturnUUID() throws Exception {

        Mockito.when(tradeRepository.save(any(Trade.class))).thenReturn(mockTrade(id, mockTradeRequest()));

        UUID result = tradeService.saveTrade(mockTradeRequest());
        verify(tradeRepository).save(any(Trade.class));
        assertEquals(id, result);
    }

    @Test
    public void whenFindByExistingId_thenReturnTradeRecord() throws Exception {

        Mockito.when(tradeRepository.findById(any(UUID.class))).thenReturn(Optional.of(mockTrade(id, mockTradeRequest())));

        Trade result = tradeService.getTradeById(id);
        verify(tradeRepository).findById(any(UUID.class));
        assertEquals(id, result.getId());
    }

    @Test
    public void whenFindByExistingUserId_thenReturnListOfTradeRecord() throws Exception {

        Mockito.when(tradeRepository.findByUserId(any(String.class))).thenReturn(mockTradeList());

        List<Trade> result = tradeService.getAllTradeByUserId(userId);
        verify(tradeRepository).findByUserId(any(String.class));
        assertEquals(1, result.size());
        assertEquals(userId, result.get(0).getUserId());
    }
}
