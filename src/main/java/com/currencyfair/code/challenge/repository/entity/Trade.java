package com.currencyfair.code.challenge.repository.entity;

import com.currencyfair.code.challenge.repository.model.TradeRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "trade")
@ApiModel(description = "Trade Response Body(Entity)")
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, length = 36)
    @ApiModelProperty(notes = "Auto generated UUID used for querying single trade record")
    private UUID id;

    @ApiModelProperty(notes = "User Id")
    @Column(name = "user_id", unique = false, nullable = false, length = 50)
    private String userId;

    @Column(name = "currency_from", unique = false, nullable = false, length = 3)
    @ApiModelProperty(notes = "Currency converted From")
    private String currencyFrom;

    @Column(name = "currency_to", unique = false, nullable = false, length = 3)
    @ApiModelProperty(notes = "Currency converted to")
    private String currencyTo;

    @Column(name = "amount_sell", unique = false, nullable = false)
    @ApiModelProperty(notes = "Amount selling")
    private BigDecimal amountSell;

    @Column(name = "amount_buy", unique = false, nullable = false)
    @ApiModelProperty(notes = "Amount buying")
    private BigDecimal amountBuy;

    @Column(name = "rate", unique = false, nullable = false)
    @ApiModelProperty(notes = "fx rate")
    private BigDecimal rate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "time_placed", unique = false, nullable = false)
    @ApiModelProperty(notes = "Trade order placed time")
    private Date timePlaced;

    @Column(name = "originating_country", unique = false, nullable = false, length = 2)
    @ApiModelProperty(notes = "Order placed country")
    private String originatingCountry;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private Date modifiedAt;

    public Trade() {}

    public Trade(TradeRequest tradeRequest) throws ParseException {
        this.userId = tradeRequest.getUserId();
        this.currencyFrom = tradeRequest.getCurrencyFrom();
        this.currencyTo = tradeRequest.getCurrencyTo();
        this.amountBuy = tradeRequest.getAmountBuy();
        this.amountSell = tradeRequest.getAmountSell();
        this.rate = tradeRequest.getRate();
        this.timePlaced = new SimpleDateFormat("dd-MMM-yy hh:mm:ss").parse(tradeRequest.getTimePlaced());
        this.originatingCountry = tradeRequest.getOriginatingCountry();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public Date getTimePlaced() {
        return timePlaced;
    }

    public void setTimePlaced(Date timePlaced) {
        this.timePlaced = timePlaced;
    }

    public String getOriginatingCountry() {
        return originatingCountry;
    }

    public void setOriginatingCountry(String originatingCountry) {
        this.originatingCountry = originatingCountry;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", currencyFrom='" + currencyFrom + '\'' +
                ", currencyTo='" + currencyTo + '\'' +
                ", amountSell=" + amountSell +
                ", amountBuy=" + amountBuy +
                ", rate=" + rate +
                ", timePlaced=" + timePlaced +
                ", originatingCountry='" + originatingCountry + '\'' +
                '}';
    }
}