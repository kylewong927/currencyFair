package com.currencyfair.code.challenge.controller;

import com.currencyfair.code.challenge.repository.entity.Trade;
import com.currencyfair.code.challenge.repository.model.TradeRequest;
import com.currencyfair.code.challenge.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Api(tags="TransactionController")
@RestController
public class TransactionController {

    @Autowired
    private TradeService tradeService;

    @ApiOperation(value="trade endpoint")
    @PostMapping("/trade")
    public ResponseEntity<UUID> trade(@Valid @RequestBody TradeRequest tradeRequest) throws ParseException, Exception {
        return new ResponseEntity<UUID>(tradeService.saveTrade(tradeRequest), HttpStatus.OK);
    }

    @ApiOperation(value="Get Trade by UUID")
    @GetMapping("/trade/{id}")
    public ResponseEntity<Trade> getTrade(@PathVariable UUID id) throws NoSuchElementException, Exception {
        return new ResponseEntity<Trade>(tradeService.getTradeById(id), HttpStatus.OK);
    }
}
