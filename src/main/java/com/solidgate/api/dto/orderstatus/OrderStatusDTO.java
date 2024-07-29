package com.solidgate.api.dto.orderstatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderStatusDTO {

    @JsonProperty("order_id")
    private String orderId;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("mid")
    private String mid;

    @JsonProperty("descriptor")
    private String descriptor;

    @JsonProperty("fraudulent")
    private boolean fraudulent;

    @JsonProperty("marketing_amount")
    private int marketingAmount;

    @JsonProperty("marketing_currency")
    private String marketingCurrency;

    @JsonProperty("processing_amount")
    private int processingAmount;

    @JsonProperty("processing_currency")
    private String processingCurrency;

    @JsonProperty("status")
    private String status;

    @JsonProperty("refunded_amount")
    private int refundedAmount;

    @JsonProperty("order_description")
    private String orderDescription;

    @JsonProperty("traffic_source")
    private String trafficSource;

    @JsonProperty("customer_email")
    private String customerEmail;
}