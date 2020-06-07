package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RailcontinentPage;
import java.io.FileNotFoundException;

import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.railcontinentUrl;

@Tag("railcontinent")
public class RailcontinentTests extends TestBase {

    @Test
    @DisplayName("Проверка наличия текста в xls файле")
    void checkContentInRegimeCargoList() throws FileNotFoundException {
        RailcontinentPage railcontinentPage = new RailcontinentPage();

        open(railcontinentUrl);

        railcontinentPage.clickMenuItemConditions();
        railcontinentPage.clickOnSubMenuItemDocuments();

        railcontinentPage.checksIfTextFoundInFile("З И М А");
    }
}