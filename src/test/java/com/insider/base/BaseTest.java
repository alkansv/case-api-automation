package com.insider.base;

import com.thoughtworks.gauge.BeforeScenario;
import io.restassured.RestAssured;

public class BaseTest {

    @BeforeScenario
    public void setBaseUrl() {
        RestAssured.baseURI = "https://petstore.swagger.io";
    }

}
