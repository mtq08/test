package com.solidgate.api.dto.paymentpage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    @JsonProperty("order_id")
    private String orderId = "923bb4e6-1111-41ec-81fb-28eb8a152e55";

    @JsonProperty("amount")
    private int amount = 1020;

    @JsonProperty("currency")
    private String currency = "EUR";

    @JsonProperty("order_description")
    private String orderDescription = "Premium package";

    @JsonProperty("order_items")
    private String orderItems = "item 1 x 10, item 2 x 30";

    @JsonProperty("order_date")
    private String orderDate = "2015-12-21 11:21:30";

    @JsonProperty("order_number")
    private int orderNumber = 9;

    @JsonProperty("type")
    private String type = "auth";

    @JsonProperty("settle_interval")
    private int settleInterval = 0;

    @JsonProperty("retry_attempt")
    private int retryAttempt = 3;

    @JsonProperty("force3ds")
    private boolean force3ds = false;

    @JsonProperty("google_pay_allowed_auth_methods")
    private List<String> googlePayAllowedAuthMethods = List.of("PAN_ONLY");

    @JsonProperty("customer_date_of_birth")
    private String customerDateOfBirth = "1988-11-21";

    @JsonProperty("customer_email")
    private String customerEmail = "example@example.com";

    @JsonProperty("customer_first_name")
    private String customerFirstName = "Nikola";

    @JsonProperty("customer_last_name")
    private String customerLastName = "Tesla";

    @JsonProperty("customer_phone")
    private String customerPhone = "+10111111111";

    @JsonProperty("traffic_source")
    private String trafficSource = "facebook";

    @JsonProperty("transaction_source")
    private String transactionSource = "main_menu";

    @JsonProperty("purchase_country")
    private String purchaseCountry = "USA";

    @JsonProperty("geo_country")
    private String geoCountry = "USA";

    @JsonProperty("geo_city")
    private String geoCity = "New Castle";

    @JsonProperty("language")
    private String language = "en";

    @JsonProperty("website")
    private String website = "https://solidgate.com";

    @JsonProperty("order_metadata")
    private Map<String, String> orderMetadata = Map.of(
            "coupon_code", "NY2018",
            "partner_id", "123989"
    );

    @JsonProperty("success_url")
    private String successUrl = "";

    @JsonProperty("fail_url")
    private String failUrl = "";

    public OrderDTO setRandomOrderId() {
        setOrderId(new Faker().letterify("????????-????-????-????-????????????"));
        return this;
    }
}