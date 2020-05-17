package tests;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.clearBrowserCache;
import static helpers.Environment.instagramLogin;
import static helpers.Environment.instagramUrl;
import static helpers.Environment.instagramPassword;

@Tag("instagram")
public class InstagramTests extends TestBase {

    @Test
    @Description("Log in into account and change profile info")
    void changeInfoInInstagramProfile() {

        String gen = "https://" + RandomStringUtils.randomAlphanumeric(10).toUpperCase() + ".ru/"; //generating random url

        open(instagramUrl);

        $("html").shouldHave(text("INSTAGRAM ОТ FACEBOOK"));
        $x("//input[@aria-label='Номер телефона, имя пользователя или эл. адрес']").setValue(instagramLogin);
        $x("//input[@aria-label='Пароль']").setValue(instagramPassword);
        $x("//div[text()='Войти']").waitUntil(visible, 1000).click();
        sleep(2000); //waiting for auth, yep, nasty.
        $("html").shouldHave(text("INSTAGRAM ОТ FACEBOOK")); //wait until new page fully loaded, if not - we will logout
        open(instagramUrl + "accounts/edit/");
        $("html").shouldHave(text("INSTAGRAM ОТ FACEBOOK")); //wait until new page fully loaded, if not - we will logout
        $("#pepWebsite").setValue(gen);
        $x("//button[text()=\"Отправить\"]").waitUntil(visible, 10000).click();
        clearBrowserCache(); //Instagram has a 'bug?' with preserving older value until page refreshes, this is workaround
        $("#pepWebsite").shouldHave(value(gen));
    }

}
