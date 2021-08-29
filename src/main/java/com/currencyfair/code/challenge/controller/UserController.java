package com.currencyfair.code.challenge.controller;

import com.currencyfair.code.challenge.repository.entity.Trade;
import com.currencyfair.code.challenge.service.TradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="UserController")
@RestController
public class UserController {

    @Autowired
    private TradeService tradeService;

    @ApiOperation(value="Get all trade record by userId")
    @GetMapping("/user/{userId}/trade")
    public ResponseEntity<List<Trade>> getAllTrade(@PathVariable String userId) {
        List<Trade> tradeList = tradeService.getAllTradeByUserId(userId);
        if(tradeList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(tradeList, HttpStatus.OK);
    }
}
