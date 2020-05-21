package tests;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.instagramLogin;
import static helpers.Environment.instagramUrl;
import static helpers.Environment.instagramPassword;

@Tag("instagram")
public class InstagramTests extends TestBase {

    @Test
    @Description("Log in into account and change website address in user profile page")
    void changeWebsiteInfoInInstagramProfile() {

        String gen = "https://" + RandomStringUtils.randomAlphanumeric(10).toLowerCase() + ".ru/"; //generating random url

        open(instagramUrl);
        $(byName("username")).setValue(instagramLogin);
        $(byName("password")).setValue(instagramPassword).pressEnter();

        $(byName("username")).waitUntil(disappear,10000); //waits unlil we log in, username input should dissapear

        if ($(byText("Не сейчас")).exists()) {
            $(byText("Не сейчас")).click(); // if remember password pop-up is presented, then click
        }

        $("a[style='width: 22px; height: 22px;']").click();
        $("[href='/accounts/edit/'] button").click();
        $("#pepWebsite").setValue(gen);
        $(byText("Отправить")).click(); //$x("//button[text()=\"Отправить\"]").waitUntil(enabled, 2000).click();

        $("html").shouldHave(text("Профиль сохранен."));
        $("#pepWebsite").shouldHave(value(gen));
    }
}
