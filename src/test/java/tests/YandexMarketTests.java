package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.YandexMarketPage;

import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.*;

@Tag("yandexmarket")
public class YandexMarketTests extends TestBase {

    @Test
    @DisplayName("Проверка поиска товара в яндекс маркете")
    @Description("Checks if item can be found")
    void checkItemCanBeFound() {
        YandexMarketPage yandexMarketPage = new YandexMarketPage();

        open(yandexMarketUrl);

        yandexMarketPage.typeSearch(yandexMarketItemName);
        yandexMarketPage.clickOnProductIndex(0);

        yandexMarketPage.verifyItemHeaderName(yandexMarketItemName);
    }
}
