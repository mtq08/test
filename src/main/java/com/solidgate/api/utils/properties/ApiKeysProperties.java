package com.solidgate.api.utils.properties;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

@Sources({"file:src/main/resources/keys.properties"})
public interface ApiKeysProperties extends Config {

    @Key("publicKey")
    String getPublicKey();

    @Key("secretKey")
    String getSecretKey();
}
