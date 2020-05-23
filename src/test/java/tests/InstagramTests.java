package tests;

import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.InstagramProfilePage;

import static com.codeborne.selenide.Selenide.*;
import static helpers.Environment.instagramLogin;
import static helpers.Environment.instagramUrl;
import static helpers.Environment.instagramPassword;

@Tag("instagram")
public class InstagramTests extends TestBase {

    @Test
    @Description("Changes personal website in instagram profile")
    void changeWebsiteInfoInInstagramProfile() {

        InstagramProfilePage instagramProfilePage = new InstagramProfilePage();
        String site = "https://" + RandomStringUtils.randomAlphanumeric(10).toLowerCase() + ".ru/"; //generating random url

        open(instagramUrl);

        instagramProfilePage.authWithoutOpenID(instagramLogin, instagramPassword);
        instagramProfilePage.clickOnOwnUserProfile();
        instagramProfilePage.clickOnEditProfile();
        instagramProfilePage.editPersonalWebsite(site);

        instagramProfilePage.checkPersonalWebsite(site);
    }
}
