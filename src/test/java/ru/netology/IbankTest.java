package ru.netology;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static ru.netology.DataGenerede.Registration.getRegistredUser;
import static ru.netology.DataGenerede.Registration.getUser;


public class IbankTest {

    @BeforeEach
    void setup(){
       open("http://localhost:9999/");
    }
    @Test

    void shouldReturnRegistrationUser(){
        var registredUser = getRegistredUser("active");
        $("[data-test-id='login'] input").setValue(registredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registredUser.getPassword());
        $(".button").click();
        $("h2").shouldHave(Condition.exactText("Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    void shouldReturnBlockedUser(){
        var registredUser = getRegistredUser("blocked");
        $("[data-test-id='login'] input").setValue(registredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registredUser.getPassword());
        $(".button").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldReturnInvalidLogin(){
        var registredUser = getRegistredUser("active");
        $("[data-test-id='login'] input").setValue("qqq");
        $("[data-test-id='password'] input").setValue(registredUser.getPassword());
        $(".button").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldReturnInvalidPassword(){
        var registredUser = getRegistredUser("active");
        $("[data-test-id='login'] input").setValue(registredUser.getLogin());
        $("[data-test-id='password'] input").setValue("q");
        $(".button").click();
        $("[data-test-id=error-notification] .notification__content")
                .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
                .shouldBe(Condition.visible);
    }
@Test
    void shouldGetReturnRegistrationUser(){
        var registredUser = getUser("active");
        $("[data-test-id='login'] input").setValue(registredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registredUser.getPassword());
        $(".button").click();
    $("[data-test-id=error-notification] .notification__content")
            .shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль"))
            .shouldBe(Condition.visible);
    }

}
