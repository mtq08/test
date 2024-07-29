package com.solidgate.web.pageobjects;

import java.util.Currency;

import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    public PaymentPage setCardNumber(String cardNumber) {
        $("[data-testid=\"cardNumber\"]").setValue(cardNumber);
        return this;
    }

    public PaymentPage setCardExpiryDate(String expiryDate) {
        $("[data-testid=\"cardExpiryDate\"]").setValue(expiryDate);
        return this;
    }

    public PaymentPage setCardCvv(String cardCvv) {
        $("[data-testid=\"cardCvv\"]").setValue(cardCvv);
        return this;
    }

    public PaymentPage setCardHolderFullName(String fullName) {
        $("[data-testid=\"cardHolder\"]").setValue(fullName);
        return this;
    }

    public String getPrice() {
        return $("[data-testid=\"price_major\"]").getText();
    }

    public int getPriceInSmallestCurrencyUnit() {
        // regexp: €10.20 -> 1020
        return Integer.parseInt(getPrice().replaceAll("\\D+", ""));
    }

    public Currency getCurrencyType() {
        // regexp: €10.20 -> €
        String currency = getPrice().replaceAll("[0-9.,]+", "").trim();
        return Currency.getAvailableCurrencies()
                       .stream()
                       .filter(c -> c.getSymbol().equals(currency))
                       .findFirst()
                       .orElseThrow(() -> new IllegalArgumentException("Currency not found: " + currency));
    }

    public PaymentPage clickSubmit() {
        $("[data-testid=\"submit\"]").click();
        return this;
    }
}
