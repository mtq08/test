package com.solidgate.api.utils.properties;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/main/resources/api.properties"})
public interface ApiProperties extends Config {

    @Key("payment_page_url")
    String paymentPageUrl();

    @Key("status_url")
    String statusUrl();
}
