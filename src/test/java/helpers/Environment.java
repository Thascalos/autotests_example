package helpers;


public class Environment {
    public final static String isHeadless = System.getProperty("isHeadless", "true");
    public final static String yandexMarketUrl = System.getProperty("yandexMarketUrl", "https://market.yandex.ru/");
    public final static String yandexMarketItemName = System.getProperty("yandexMarketItem", "iphone");
    public final static String instagramUrl = System.getProperty("instagramUrl", "https://www.instagram.com/");
    public final static String instagramLogin = System.getProperty("login");
    public final static String instagramPassword = System.getProperty("pass");
}
