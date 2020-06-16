package helpers;

import static java.lang.Boolean.parseBoolean;

public class Environment {

    public final static String
            selenoidUrl = System.getProperty("selenoid_url", ""),
            selenoidUsername = System.getProperty("selenoid_username", ""),
            selenoidPassword = System.getProperty("selenoid_password", ""),
            yandexMarketUrl = System.getProperty("yandexMarketUrl", "https://market.yandex.ru/"),
            yandexMarketItemName = System.getProperty("yandexMarketItem", "iphone"),
            instagramUrl = System.getProperty("instagramUrl", "https://www.instagram.com/"),
            instagramLogin = System.getProperty("instagramLogin"),
            instagramPassword = System.getProperty("instagramPass"),
            tinkoffUrl = System.getProperty("tinkoffUrl", "https://tinkoff.ru/"),
            railcontinentUrl = System.getProperty("railcontinentUrl", "https://www.railcontinent.ru/");

    public final static boolean
            isHeadless = parseBoolean(System.getProperty("isHeadless", "false")),
            isSelenoid = selenoidUrl != "";
}
