package com.insider.steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.gauge.Step;
import io.restassured.http.ContentType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static io.restassured.RestAssured.given;


public class PetSteps extends BaseSteps {

    @Step("Get pets by status : <status>")
    public void getPetsStatus(String status) {
        response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .log()
                .all()
                .when()
                .request("GET", "/v2/pet/findByStatus?status=" + status)
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    @Step("Add a new pet to the store")
    public void addNewPet() {
        var petJson = readJsonFromFile("pet.json").toString();

        response = given()
                .contentType("application/json").accept(ContentType.JSON)
                .body(petJson)
                .log()
                .all()
                .when()
                .request("POST", "/v2/pet")
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    @Step("Update pet")
    public void updatePet() {
        var petUpdateJson = readJsonFromFile("petUpdate.json").toString();

        response = given()
                .contentType("application/json").accept(ContentType.JSON)
                .body(petUpdateJson)
                .log()
                .all()
                .when()
                .request("PUT", "/v2/pet")
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    @Step("Get pets by id : <id>")
    public void getPetsId(String id) {
        response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .log()
                .all()
                .when()
                .request("GET", "/v2/pet/" + id)
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    @Step("Delete pet : <id>")
    public void deletePet(String id) {
        response = given()
                .contentType("application/json")
                .accept(ContentType.JSON)
                .log()
                .all()
                .when()
                .request("DELETE", "/v2/pet/" + id)
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    @Step("Update pet with form data")
    public void updatePetWithFormData() {
        response = given()
                .contentType("application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .formParams("name", "fish 3", "status", "sold")
                .log()
                .all()
                .when()
                .request("POST", "/v2/pet/111")
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    @Step("Update pet image with file")
    public void updatePetImageWithFile() {
        var file = new File("src/test/resources/pet.json");
        response = given()
                .basePath("/v2/pet/111/uploadImage")
                .contentType(ContentType.MULTIPART)
                .multiPart("file", file)
                .multiPart("additionalMetadata", "sevgi")
                .log()
                .all()
                .when()
                .post()
                .prettyPeek()
                .then()
                .extract()
                .response();
    }

    private JsonObject readJsonFromFile(String filename) {
        try {
            return JsonParser.parseReader(new FileReader("src/test/resources/" + filename)).getAsJsonObject();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
            return new JsonObject();
        }
    }
}
