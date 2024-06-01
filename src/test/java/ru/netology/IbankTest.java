package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static ru.netology.DataGenerede.Registration.getRegistredUser;

public class IbankTest {

    @BeforeEach
    void setup(){
       open("http://localhost:9999/");
    }
    @Test
    @DisplayName("should Return egistration User")
    void shouldReturnRegistrationUser(){
        var registredUser = getRegistredUser("active");
        $("[data-test-id='login'] input").setValue(registredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registredUser.getPassword());
        $(".button").click();
        $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
    }
}
