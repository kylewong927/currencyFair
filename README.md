# CurrencyFair Code Challenge 
by Kyle Wong

----

# How to start the application?

### Required
- Java 11
- Docker

### Run script

`./script/bootRun`


# How to try? 
Prefer using Swagger to call the endpoints

You can also use crul command
- TradeUsingPOST

    ```curl -X POST "http://localhost:8080/trade" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"amountBuy\": 40, \"amountSell\": 1000, \"currencyFrom\": \"EUR\", \"currencyTo\": \"GBP\", \"originatingCountry\": \"HK\", \"rate\": 0.123, \"timePlaced\": \"24-Jan-18 10:27:44\", \"userId\": \"123456\"}"```
  
- GetTradeUsingGET
  
    ```curl -X GET "http://localhost:8080/trade/cd4d2108-2c22-4f0b-8948-f4d45125bf91" -H "accept: */*"```

- GetAllTradeUsingGET
  
    ```curl -X GET "http://localhost:8080/user/1234/trade" -H "accept: */*"```

### Useful link
[Swagger**](http://localhost:8080/swagger-ui/index.html)

[API docs in json](http://localhost:8080/v2/api-docs)

### Leakage
- Cannot solve the connection refused between app and postgres in Docker
- No redundant check for the incoming request
- No Frontend for the app
- No complex logic to check the request body
