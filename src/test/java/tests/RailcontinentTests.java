package tests;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Tag("railcontinent")
public class RailcontinentTests extends TestBase {

    @Test
    @DisplayName("Проверка соответствия текста  в xls файле")
    void checkContentInRegimeCargoList() throws FileNotFoundException {
        open("https://www.railcontinent.ru/");
        $(byText("Условия перевозок")).hover();
        $(byText("Документы")).click();

        File cargoListExcel = $(byText("Список режимных грузов")).download();

        XLS xls = new XLS(cargoListExcel);
        assertThat(xls, XLS.containsText("З И М А"));
        assertThat(xls, XLS.containsText("Наименование груза"));
        assertThat(xls, XLS.containsText("Гарантийное письмо"));
        assertThat(xls, XLS.containsText("Вода"));
    }
}