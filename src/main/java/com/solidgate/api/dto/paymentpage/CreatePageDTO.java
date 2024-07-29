package com.solidgate.api.dto.paymentpage;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePageDTO {

    @JsonProperty("order")
    private OrderDTO order;

    @JsonProperty("page_customization")
    private PageCustomizationDTO pageCustomization;
}
