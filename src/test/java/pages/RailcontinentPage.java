package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.xlstest.XLS;
import io.qameta.allure.Step;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static helpers.Environment.selenoid_url;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.FileUtils.*;

public class RailcontinentPage {
    SelenideElement menuItemConditions = $(byText("Условия перевозок")),
            subMenuItemDocuments =  $(byText("Документы")),
            cargoListExcel = $(byText("Список режимных грузов"));


    @Step("Переходим в раздел \"Документы\"")
    public void clickOnDocuments() {
        menuItemConditions.hover();
        subMenuItemDocuments.click();
    }

    @Step("Скачиваем файл \"Список режимных грузов\"")
    public File cargoListXlsDownload() throws FileNotFoundException {
        return cargoListExcel.download();
    }

    @Step("Скачиваем файл \"Список режимных грузов\" в selenoid")
    public void downloadFileFromPageToSelenoid() {
        cargoListExcel.click();
    }

    @Step("Скачиваем файл \"Список режимных грузов\" из selenoid")
    public String downloadFileFromPageFromSelenoid() {
        String url = cargoListExcel.getAttribute("href");
        assert url != null;

        String filename = getFileNameFromUrl(url);

        getFileFromContainer(filename);

        return filename;
    }

    @Step("Проверяем наличие слова \"{text}\" в XLS файле \"Список режимных грузов\"")
    public void checksIfTextFoundInFile(String text) throws FileNotFoundException, NullPointerException {
        if (selenoid_url != null) {
            downloadFileFromPageToSelenoid();
            String filename = downloadFileFromPageFromSelenoid();

            XLS xls = new XLS(readBytesFromFile("build/downloads/" + filename));

            assertThat(xls, XLS.containsText(text));
        } else {
            XLS xls = new XLS(cargoListXlsDownload());

            assertThat(xls, XLS.containsText(text));
        }
    }
}