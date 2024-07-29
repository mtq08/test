package com.solidgate.api.dto.paymentpage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageCustomizationDTO {

    @JsonProperty("public_name")
    private String publicName = "Public Name";

    @JsonProperty("order_title")
    private String orderTitle = "Order Title";

    @JsonProperty("order_description")
    private String orderDescription = "Premium package";

    @JsonProperty("payment_methods")
    private List<String> paymentMethods = List.of("paypal");

    @JsonProperty("button_font_color")
    private String buttonFontColor = "#FFFFFF";

    @JsonProperty("button_color")
    private String buttonColor = "#00816A";

    @JsonProperty("font_name")
    private String fontName = "Open Sans";

    @JsonProperty("is_cardholder_visible")
    private boolean isCardholderVisible = true;

    @JsonProperty("terms_url")
    private String termsUrl = "https://solidgate.com/terms";

    @JsonProperty("back_url")
    private String backUrl = "https://solidgate.com";
}