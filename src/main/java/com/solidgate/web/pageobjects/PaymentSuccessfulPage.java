package com.solidgate.web.pageobjects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PaymentSuccessfulPage {

    public PaymentSuccessfulPage checkStatusTitle(String statusTitle) {
        $("[data-testid=\"status-title\"]").shouldHave(text(statusTitle));
        return this;
    }
}
