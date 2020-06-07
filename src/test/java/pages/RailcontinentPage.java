package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static org.hamcrest.MatcherAssert.assertThat;

public class RailcontinentPage {
    SelenideElement menuItemConditions = $(byText("Условия перевозок")),
            subMenuItemDocuments =  $(byText("Документы")),
            cargoListExcel = $(byText("Список режимных грузов"));

    @Step("Наводим мышкой на элемент в главном меню 'Условия перевозок'")
    public void clickMenuItemConditions() {
        menuItemConditions.hover();
    }

    @Step("Нажимаем на ссылку раздела документы")
    public void clickOnSubMenuItemDocuments() {
        subMenuItemDocuments.click();
    }

    @Step("Скачиваем файл \"Список режимных грузов\"")
    public File cargoListXlsDownload() throws FileNotFoundException {
        return cargoListExcel.download();
    }

    @Step("Проверяем наличие слова \"{text}\" в XLS файле \"Список режимных грузов\"")
    public void checksIfTextFoundInFile(String text) throws FileNotFoundException {
        XLS xls = new XLS(cargoListXlsDownload());
        assertThat(xls, XLS.containsText(text));
    }
}