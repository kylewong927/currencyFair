package com.currencyfair.code.challenge.integration;

import com.currencyfair.code.challenge.Application;
import com.currencyfair.code.challenge.configuration.H2TestProfileJPAConfig;
import com.currencyfair.code.challenge.repository.TradeRepository;
import com.currencyfair.code.challenge.service.TradeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {
        Application.class,
        H2TestProfileJPAConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class IntegrationBase {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected TradeRepository tradeRepository;

    @Autowired
    protected TradeService tradeService;

    protected final UUID id = UUID.randomUUID();

    protected final String userId = "12345";
}
