package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class InstagramProfilePage {
    SelenideElement loginInput = $(byName("username")),
            passwordInput =  $(byName("password")),
            passwordPopUp = $(byText("Не сейчас")),
            userProfileLink = $("a[style='width: 22px; height: 22px;']"),
            userProfileEditLink = $("[href='/accounts/edit/'] button"),
            personalWebsite = $("#pepWebsite"),
            saveChanges = $(byText("Отправить")),
            htmlBody = $("html");

    @Step("Авторизация по обычному логину и паролю")
    public void authWithoutOpenID(String username, String password) {
        loginInput.setValue(username);
        passwordInput.setValue(password).pressEnter();

        loginInput.waitUntil(disappear,10000); //waits unlil we log in, username input should dissapear

        if (passwordPopUp.exists()) {
            passwordPopUp.click(); // if remember password pop-up is presented, then click
        }
    }

    @Step("Переход в свой профиль")
    public void clickOnOwnUserProfile() {
        userProfileLink.click();
    }

    @Step("Нажатие на кнопку редактирования профиля")
    public void clickOnEditProfile() {
        userProfileEditLink.click();
    }

    @Step("Изменение поля персонального сайта и его сохранение")
    public void editPersonalWebsite(String site) {
        personalWebsite.setValue(site);
        saveChanges.click();
        htmlBody.shouldHave(text("Профиль сохранен."));
    }

    @Step("Проверка что сохраненный сайт есть в профиле")
    public void checkPersonalWebsite(String site) {
        personalWebsite.shouldHave(value(site));
    }
}
