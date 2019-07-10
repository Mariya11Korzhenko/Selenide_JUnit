package utils;

import com.codeborne.selenide.Configuration;
import pages.ItemsStartPage;

import static com.codeborne.selenide.Selenide.open;

public class DriverSettings {

    public static ItemsStartPage setUpDriver(){


        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
//        Configuration.browser = "firefox";
        Configuration.timeout = 10000;


        open("http://todomvc.com/examples/react/#/");
        return new ItemsStartPage();
    }
}

