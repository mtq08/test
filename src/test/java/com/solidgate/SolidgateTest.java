package com.solidgate;

import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.solidgate.api.SolidgateApiClient;
import com.solidgate.api.dto.orderstatus.OrderStatusDTO;
import com.solidgate.api.dto.orderstatus.StatusDTO;
import com.solidgate.api.dto.orderstatus.TransactionsDTO;
import com.solidgate.api.dto.paymentpage.CreatePageDTO;
import com.solidgate.api.dto.paymentpage.CreatePageResponseDTO;
import com.solidgate.api.dto.paymentpage.OrderDTO;
import com.solidgate.api.dto.paymentpage.PageCustomizationDTO;
import com.solidgate.web.pageobjects.PaymentPage;
import com.solidgate.web.pageobjects.PaymentSuccessfulPage;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Currency;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.solidgate.api.PaymentStatuses.SUCCESS;

public class SolidgateTest {

    private final OrderDTO orderDTO = new OrderDTO().setRandomOrderId();
    private Currency paymentPageCurrency;
    private String paymentPageUrl = "";
    private int paymentPageAmount = 0;

    @Test(priority = 1)
    public void initializePaymentPage() {
        CreatePageDTO createPageDTO = new CreatePageDTO()
                .setOrder(orderDTO)
                .setPageCustomization(new PageCustomizationDTO());

        paymentPageUrl = new SolidgateApiClient()
                .createPage(createPageDTO)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(CreatePageResponseDTO.class)
                .getUrl();
    }

    @Test(priority = 2)
    public void paymentProcessingThroughUI() {
        open(paymentPageUrl);

        PaymentPage paymentPage = new PaymentPage();

        paymentPageAmount = paymentPage.getPriceInSmallestCurrencyUnit();
        paymentPageCurrency = paymentPage.getCurrencyType();

        paymentPage
                .setCardNumber("4067429974719265")
                .setCardExpiryDate("1229")
                .setCardCvv("123")
                .setCardHolderFullName(Faker.instance().name().fullName())
                .clickSubmit();

        new PaymentSuccessfulPage()
                .checkStatusTitle("Payment successful!");

        Selenide.closeWebDriver();
    }

    @Test(priority = 3)
    public void verifyOrderStatus() {
        Response response = new SolidgateApiClient()
                .checkOrderStatus(new StatusDTO().setOrderId(orderDTO.getOrderId()));

        ResponseBodyExtractionOptions body = response
                .then()
                .statusCode(200)
                .extract()
                .body();

        OrderStatusDTO order = body
                .jsonPath()
                .getObject("order", OrderStatusDTO.class);

        Map<String, Object> transactions = body
                .jsonPath()
                .getMap("transactions");

        transactions.forEach((key, value) -> {
            TransactionsDTO transaction = new ObjectMapper().convertValue(value, TransactionsDTO.class);
            Assert.assertEquals(transaction.getStatus(), SUCCESS, "Transaction status is not SUCCESS for transaction ID: " + key);
        });

        Assert.assertEquals(order.getAmount(), paymentPageAmount, "Order amount does not match the payment page amount.");
        Assert.assertEquals(order.getCurrency(), paymentPageCurrency.toString(), "Order currency does not match the payment page currency.");
    }
}