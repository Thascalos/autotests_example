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
        railcontinentPage.openDocumentsPage();

        railcontinentPage.findTextInXls("З И М А");
    }

    @Test
    @DisplayName("Проверка существования нужной фразы на странице \"О нас\"")
    void checkAboutUsPage() {
        RailcontinentPage railcontinentPage = new RailcontinentPage();

        open(railcontinentUrl);
        railcontinentPage.openAboutUsPage();

        railcontinentPage.checkTextExistance("комфортные и выгодные грузоперевозки по всей стране");
    }

    @Test
    @DisplayName("Проверка актуальности акции при заказе через интернет")
    void checkActionsPage() {
        RailcontinentPage railcontinentPage = new RailcontinentPage();

        open(railcontinentUrl);
        railcontinentPage.openActionsPage();

        railcontinentPage.checkTextExistance("Скидка 10%");
    }

    @Test
    @DisplayName("Проверка страницы услуги \"Страхование\"")
    void checkInsurancePage() {
        RailcontinentPage railcontinentPage = new RailcontinentPage();

        open(railcontinentUrl);
        railcontinentPage.openInsurancePage();

        railcontinentPage.checkTextExistance("Стоимость добровольного страхования грузов");
    }

    @Test
    @DisplayName("Проверка наличия логотипов наших клиентов")
    void checkOurClientsPage() {
        RailcontinentPage railcontinentPage = new RailcontinentPage();

        open(railcontinentUrl);
        railcontinentPage.openOurClientsPage();

        railcontinentPage.checkQuantityOfClientsLogo(13);
    }

    @Test
    @DisplayName("Проверка расчёта стоимости перевозки")
    void checkQuantityOfClientsLogo() {
        RailcontinentPage railcontinentPage = new RailcontinentPage();

        open(railcontinentUrl);

        railcontinentPage.calculateFreightTransportation("Москва", "Санкт-Петербург", "1", "1");

        railcontinentPage.checkTextExistance("2 035");
    }

    @Test
    @DisplayName("Проверка статуса накладной Т1658691")
    void checkInvoice() {
        RailcontinentPage railcontinentPage = new RailcontinentPage();

        open(railcontinentUrl);

        railcontinentPage.checkOrderStatus("Т1658691", "3696");

        railcontinentPage.checkTextExistanceInModal("Выдан клиенту на терминале ВОЛГОГРАД");
    }
}
