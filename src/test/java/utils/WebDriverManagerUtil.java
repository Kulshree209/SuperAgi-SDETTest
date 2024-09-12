package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverManagerUtil {
    public static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }
}
