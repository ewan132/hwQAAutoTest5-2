package ru.netology;

import com.github.javafaker.Faker;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class DataGenerede {

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static final Faker faker = new Faker(new Locale("en"));

    private   DataGenerede(){

    }

    private static RegistrationDto sendRequest(RegistrationDto user){
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
        return user;

    }




    public static String getLogin(){
        return faker.name().username();
    }

    public static String getPassword(){
        return faker.internet().password();
    }


    public static class  Registration {
        private Registration(){
             }
             public static RegistrationDto getUser(String status){
            return new RegistrationDto(getLogin(), getPassword(), status);
             }
         public static RegistrationDto getRegistredUser (String status){
            return sendRequest(getUser(status));
         }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
}
