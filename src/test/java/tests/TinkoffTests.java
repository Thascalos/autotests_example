package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.tinkoffUrl;
import static io.qameta.allure.Allure.step;

@Tag("tinkoff")
public class TinkoffTests extends TestBase {

    @Test
    @DisplayName("Проверка ссылки на страницу Вклады в шапке сайта")
    void clickDepositUrlInHeader() {
        step("Открываем сайт", () -> open(tinkoffUrl));

        step ("Нажимаем на ссылку в шапке сайта страницы услуги Вклады", () ->
                $$(byAttribute("data-qa-type", "uikit/tabsWithDroplist.item")).findBy(text("Вклады")).click()
        );

        step("Проверяем наличие заголовка Откройте вклад", () ->
                $("h1").shouldHave(text("Откройте вклад"))
        );
    }

    @Test
    @DisplayName("Проверка ссылки на страницу Вклады в подвале сайта")
    void clickDepositUrlInFooter() {
        step("Открываем сайт", () -> open(tinkoffUrl));

        step ("Нажимаем на ссылку в подвале сайта страницы услуги Вклады", () ->
                $$("a[class^='footer__']").findBy(text("Вклады")).click());

        step("Проверяем наличие заголовка Откройте вклад", () ->
                $("h1").shouldHave(text("Откройте вклад"))
        );
    }
}
