package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selenide.*;

@Tag("tinkoff")
public class TinkoffTests extends TestBase {

    @Test
    @Description("Checks if link of deposit service page in header is working from main page")
    void clickDepositUrlInHeader() {
        open("https://tinkoff.ru");

        $$(byAttribute("data-qa-type", "uikit/tabsWithDroplist.item")).findBy(text("Вклады")).click();

        $("h1").shouldHave(text("Откройте вклад"));
    }

    @Test
    @Description("Checks if link of deposit service page in footer is working from main page")
    void clickDepositUrlInFooter() {
        open("https://tinkoff.ru");

        $$("a[class^='footer__']").findBy(text("Вклады")).click();

        $("h1").shouldHave(text("Откройте вклад"));
    }
}
