package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selenide.*;

public class YandexMarketPage {
    SelenideElement searchInput = $("#header-search"),
                    itemHeaderText =  $(byTagName("h1"));

    ElementsCollection productElements = $$x("//h3/a[1]");

    @Step("Ввод текста в поле поиска")
    public void typeSearch(String text) {
        searchInput.val(text).pressEnter();
    }

    @Step("Переходим по ссылке на порядковый номер нужного товара")
    public void clickOnProductIndex(int number) {
        productElements.get(number).click();
        switchTo().window(1);
    }

    @Step("Проверка - действительно ли на странице есть заголовок названия товара")
    public void verifyItemHeaderName(String text) {
        itemHeaderText.shouldHave(text(text));
    }

}
