package tests;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
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

        $(byName("username")).setValue(instagramLogin);
        $(byName("password")).setValue(instagramPassword).pressEnter(); 

        sleep(2000); //waiting for auth, yep, nasty.

        $(byText("Не сейчас")).click();

        $("a[style='width: 22px; height: 22px;']").click();
        $("[href='/accounts/edit/'] button").click();
        $("#pepWebsite").setValue(gen);
        $(byText("Отправить")).click(); //$x("//button[text()=\"Отправить\"]").waitUntil(enabled, 2000).click();
        $("html").shouldHave(text("Профиль сохранен."));
        $("#pepWebsite").shouldHave(value(gen));
    }


}
