package helpers;


public class Environment {
    public final static String
            yandexMarketUrl = System.getProperty("yandexMarketUrl", "https://market.yandex.ru/"),
            yandexMarketItemName = System.getProperty("yandexMarketItem", "iphone"),
            instagramUrl = System.getProperty("instagramUrl", "https://www.instagram.com/"),
            instagramLogin = System.getProperty("login"),
            instagramPassword = System.getProperty("pass");
}
