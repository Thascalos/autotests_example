package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.*;

@Tag("yandexmarket")
public class YandexMarketTests extends TestBase {

    @Test
    @Description("Checking first element of finded results")
    void checkFirstElementOnCatalog() {
        open(yandexMarketUrl);
        $("html").shouldHave(text("Яндекс.Маркет"));
        $(byId("header-search")).val(yandexMarketItemName);
        $x("//button[div[text()=\"Найти\"]]").click();
        $x("//h3/a[1]").click();
        switchTo().window(1);
        $(byTagName("h1")).shouldHave(text(yandexMarketItemName));
    }

}
