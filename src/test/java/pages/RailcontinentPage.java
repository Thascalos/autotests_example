package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.SelenideElementIterator;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class RailcontinentPage {
    SelenideElement htmlBody = $("body"),
            modalBody = $(".modal-inner-content"),
            menuItemConditions = $(byText("Условия перевозок")),
            subMenuItemDocuments =  $(byText("Документы")),
            cargoListExcel = $(byText("Список режимных грузов")),
            menuItemConditionsCompany =  $(byText("Компания")),
            menuItemConditionsTariffs =  $(byText("Тарифы")),
            subMenuItemDocumentsAboutUs =  $(byText("О нас")),
            subMenuItemDocumentsActions =  $(byText("Акции")),
            subMenuItemDocumentsOurClients =  $(byText("Наши клиенты")),
            subMenuItemDocumentsAdditional =  $(byText("Дополнительные услуги")),
            menuItemInsuranceTerms =  $(byText("Обязательное страхование")),
            menuItemInsuranceTariffs =  $(byText("добровольного страхования")),
            cityFrom = $(byName("city-from")),
            cityTo = $(byName("city-to")),
            weightValue = $(byName("weight")),
            volumeValue = $(byName("volume")),
            inputOrder = $(byName("order")),
            inputOrderPin = $(byName("pin"));


    ElementsCollection clientsLogo = $$(".client-logo"),
            linksInFooter = $$("a[class='item']");

//            subMenuItemDocuments =  $(byText("Документы")),
//            subMenuItemDocuments =  $(byText("Документы")),
//            subMenuItemDocuments =  $(byText("Документы")),
//            subMenuItemDocuments =  $(byText("Документы")),
//            subMenuItemDocuments =  $(byText("Документы"));


    @Step("Переходим в раздел \"Документы\"")
    public void openDocumentsPage() {
        menuItemConditions.hover();
        subMenuItemDocuments.click();
    }

    @Step("Переходим в раздел \"О нас\"")
    public void openAboutUsPage() {
        menuItemConditionsCompany.hover();
        subMenuItemDocumentsAboutUs.click();
    }

    @Step("Переходим в раздел \"Акции\"")
    public void openActionsPage() {
        menuItemConditionsCompany.hover();
        subMenuItemDocumentsActions.click();
    }

    @Step("Переходим в раздел \"Наши клиенты\"")
    public void openOurClientsPage() {
        menuItemConditionsCompany.hover();
        subMenuItemDocumentsOurClients.click();
    }

    @Step("Переходим в раздел \"Страхование\"")
    public void openInsurancePage() {
        linksInFooter.filterBy(text("Обязательное страхование")).get(0).click();
        menuItemInsuranceTariffs.click();
    }

    @Step("Проверяем наличие нужной фразы \"{text}\" на странице")
    public void checkTextExistance(String text) {
        htmlBody.shouldHave(text(text));
    }

    @Step("Проверяем наличие нужной фразы \"{text}\" в модальном окне")
    public void checkTextExistanceInModal(String text) {
        modalBody.shouldHave(text(text));
    }

    @Step("Проверяем наличие кол-ва логотипов (\"{num}\") на странице \"Наши клиенты\"")
    public void checkQuantityOfClientsLogo(int num) {
        clientsLogo.shouldHave(size(num));
    }

    @Step("Вводим информацию в калькульятор: перевозка из \"{from}\" в \"{to}\", вес \"{weight}\" кг, объём \"{volume}\" куба")
    public void calculateFreightTransportation(String from, String to, String weight, String volume) {
        cityFrom.setValue(from);
        cityTo.setValue(to);
        weightValue.setValue(weight);
        volumeValue.setValue(volume).pressEnter();
    }

    @Step("Вводим информацию в форму проверки статуса накладной: номер накладной \"{invoice}\", пин-код \"{pin}\"")
    public void checkOrderStatus(String order, String pin) {
        inputOrder.setValue(order).pressEnter();
        inputOrderPin.setValue(pin).pressEnter();
    }

    @Step("Скачиваем файл \"Список режимных грузов\"")
    public File downloadCargoXls() throws FileNotFoundException {
        return cargoListExcel.download();
    }

    @Step("Проверяем наличие слова \"{text}\" в XLS файле \"Список режимных грузов\"")
    public void findTextInXls(String text) throws FileNotFoundException, NullPointerException {
        XLS xls = new XLS(downloadCargoXls());

        assertThat(xls, XLS.containsText(text));
    }
}