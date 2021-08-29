package com.currencyfair.code.challenge.repository.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@ApiModel(description = "Trade Request Body")
public class TradeRequest {

    @ApiModelProperty(notes = "User Id")
    @Size(min = 5, max = 50, message = "Should between 5-50")
    @NotBlank(message = "UserId is mandatory")
    private String userId;

    @ApiModelProperty(notes = "Currency converted From")
    @Size(min = 3, max = 3, message = "Should under three-letter ISO currency code")
    @NotBlank(message = "CurrencyFrom is mandatory")
    private String currencyFrom;

    @ApiModelProperty(notes = "Currency converted to")
    @Size(min = 3, max = 3, message = "Should under three-letter ISO currency code")
    @NotBlank(message = "CurrencyTo is mandatory")
    private String currencyTo;

    @ApiModelProperty(notes = "Amount selling")
    @Positive(message = "AmountSell should be positive or zero")
    private BigDecimal amountSell;

    @ApiModelProperty(notes = "Amount buying")
    @Positive(message = "AmountBuy should be positive or zero")
    private BigDecimal amountBuy;

    @ApiModelProperty(notes = "fx rate")
    @Positive(message = "Rate should be positive or zero")
    private BigDecimal rate;

    @ApiModelProperty(notes = "Trade order placed time, format: dd-MMM-yy hh:mm:ss")
    @NotBlank(message = "TimePlaced is mandatory")
    private String timePlaced;

    @ApiModelProperty(notes = "Order placed country")
    @Size(min = 2, max = 2, message = "Should under two digits country code")
    @NotBlank(message = "OriginatingCountry is mandatory")
    private String originatingCountry;

    public TradeRequest() {}

    public TradeRequest(String userId, String currencyFrom, String currencyTo, BigDecimal amountBuy,
                        BigDecimal amountSell, BigDecimal rate, String timePlaced, String originatingCountry) {
        this.userId = userId;
        this.currencyFrom = currencyFrom;
        this.currencyTo = currencyTo;
        this.amountSell = amountSell;
        this.amountBuy = amountBuy;
        this.rate = rate;
        this.timePlaced = timePlaced;
        this.originatingCountry = originatingCountry;

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(String currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public String getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(String currencyTo) {
        this.currencyTo = currencyTo;
    }

    public BigDecimal getAmountSell() {
        return amountSell;
    }

    public void setAmountSell(BigDecimal amountSell) {
        this.amountSell = amountSell;
    }

    public BigDecimal getAmountBuy() {
        return amountBuy;
    }

    public void setAmountBuy(BigDecimal amountBuy) {
        this.amountBuy = amountBuy;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(String timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }
}
