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


    @Step("Переходим в раздел \"Документы\"")
    public void openDocumentsPage() {
        menuItemConditions.hover();
        subMenuItemDocuments.click();
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