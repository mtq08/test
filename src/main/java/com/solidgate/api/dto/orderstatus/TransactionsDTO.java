package com.solidgate.api.dto.orderstatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TransactionsDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("status")
    private String status;

    @JsonProperty("descriptor")
    private String descriptor;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("marketing_amount")
    private int marketingAmount;

    @JsonProperty("marketing_currency")
    private String marketingCurrency;

    @JsonProperty("card")
    private Card card;

    @JsonProperty("card_token")
    private CardToken cardToken;

    @Data
    public static class Card {
        @JsonProperty("bin")
        private String bin;

        @JsonProperty("brand")
        private String brand;

        @JsonProperty("number")
        private String number;

        @JsonProperty("card_holder")
        private String cardHolder;

        @JsonProperty("bank")
        private String bank;

        @JsonProperty("country")
        private String country;

        @JsonProperty("card_exp_month")
        private String cardExpMonth;

        @JsonProperty("card_exp_year")
        private int cardExpYear;

        @JsonProperty("card_type")
        private String cardType;

        @JsonProperty("card_token")
        private CardToken cardToken;

        @JsonProperty("card_id")
        private String cardId;
    }

    @Data
    public static class CardToken {
        @JsonProperty("token")
        private String token;
    }
}
