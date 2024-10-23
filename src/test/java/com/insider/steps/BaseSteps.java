package com.insider.steps;

import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

public class BaseSteps {

    protected static Response response;
    private static final Logger LOGGER = LogManager.getLogger();

    @Step("Check status code <code>")
    public void checkStatusCode(String code) {
        LOGGER.info("checked " + code + " equals " + response.getStatusCode());
        Assertions.assertEquals(code, String.valueOf(response.getStatusCode()));
    }

    @Step("Check status code is not <code>")
    public void checkStatusCodeNegative(String code) {
        LOGGER.info("checked " + code + " equals " + response.getStatusCode());
        Assertions.assertNotEquals(code, String.valueOf(response.getStatusCode()));
    }

    @Step("Check response <type>: <key>,<value>")
    public void checkResponseMethod(String type, String key, String value) {
        if (type.equals("body")) {
            Assertions.assertEquals(value, response.jsonPath().getString(key), value + " not same " + response.jsonPath().getString(key));
        }
    }
}
