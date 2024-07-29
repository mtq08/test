package com.solidgate.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import com.solidgate.api.dto.orderstatus.StatusDTO;
import com.solidgate.api.dto.paymentpage.CreatePageDTO;
import com.solidgate.api.utils.properties.ApiKeysProperties;
import com.solidgate.api.utils.properties.ApiProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.aeonbits.owner.ConfigFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static com.solidgate.api.Headers.MERCHANT;
import static com.solidgate.api.Headers.SIGNATURE;
import static com.solidgate.api.SolidgateApiEndpoint.CHECK_ORDER_STATUS;
import static com.solidgate.api.SolidgateApiEndpoint.CREATE_PAGE_INIT;

public class SolidgateApiClient {
    private final ApiKeysProperties API_KEYS = ConfigFactory.create(ApiKeysProperties.class);
    private final ApiProperties API_PROPS = ConfigFactory.create(ApiProperties.class);

    public Response createPage(CreatePageDTO createPageDTO) {
        return makePostRequest(createPageDTO, API_PROPS.paymentPageUrl(), CREATE_PAGE_INIT);
    }

    public Response checkOrderStatus(StatusDTO statusDto) {
        return makePostRequest(statusDto, API_PROPS.statusUrl(), CHECK_ORDER_STATUS);
    }

    private Response makePostRequest(Object value, String baseUri, String basePath) {
        String body = serializeToStringJSON(value);
        return RestAssured.given()
                          .baseUri(baseUri)
                          .header(MERCHANT, API_KEYS.getPublicKey())
                          .header(SIGNATURE, generateSignature(body))
                          .contentType(ContentType.JSON)
                          .body(body)
                          .basePath(basePath)
                          .post();
    }

    private String serializeToStringJSON(Object value) {
        try {
            return new ObjectMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateSignature(String body) {
        String text = API_KEYS.getPublicKey() + body + API_KEYS.getPublicKey();
        byte[] hashedBytes = Hashing.hmacSha512(API_KEYS.getSecretKey().getBytes())
                                    .hashString(text, StandardCharsets.UTF_8).toString().getBytes();
        return Base64.getEncoder().encodeToString(hashedBytes);
    }
}
