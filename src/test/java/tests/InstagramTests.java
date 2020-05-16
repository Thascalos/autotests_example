package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.instagramLogin;
import static helpers.Environment.instagramUrl;
import static helpers.Environment.instagramPassword;

@Tag("instagram")
public class InstagramTests extends TestBase {

    @Test
    @Description("Log in into account and change profile info")
    void changeInfoInInstagramProfile() {

        open(instagramUrl);
        $("html").shouldHave(text("INSTAGRAM ОТ FACEBOOK"));
        $x("//input[@aria-label='Номер телефона, имя пользователя или эл. адрес']").setValue(instagramLogin);
        $x("//input[@aria-label='Пароль']").setValue(instagramPassword);
        $x("//div[text()='Войти']").waitUntil(visible, 1000).click();
        $x("//button[text()=\"Не сейчас\"]").waitUntil(visible, 10000).click();
        open(instagramUrl + "accounts/edit/");
        sleep(2000);
        $("#pepWebsite").setValue("");
        $("#pepWebsite").setValue(instagramUrl + (int)(Math.random()));
        $x("//button[text()=\"Отправить\"]").waitUntil(visible, 10000).click();
    }

}
