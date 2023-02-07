package Pages;

import Configs.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BasePage {
    private static ChromeDriver driver;

    DriverSingleton DriverSingleton;


        public void clickElement(By locator) {
            getWebElement(locator).click();
        }
        public void sendKeysToElement(By locator, String text) {
            getWebElement(locator).sendKeys(text);
        }

        protected static String takeScreenShot(WebDriver driver, String ImagesPath) { // screen shoots
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".png");
        try {
            FileUtils.copyFile(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return ImagesPath + ".png";
        }

    private WebElement getWebElement(By locator) {
        return DriverSingleton.getDriverInstance().findElement(locator);
    }

    public static String getdata (String keyName) throws Exception{ //get info from xml (url and driver)
        File fXmlFile = new File("Z:\\QA Course\\intelliJ Projects\\BuyMeProject2\\data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }

    }
