package Pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.time.Duration;


public class HomeScreen extends BasePage {
    private WebDriver driver;
    private static ExtentReports extent= new ExtentReports();
    private static ExtentTest test = extent.createTest("Home Page", "BuyMe Home Page");
    public HomeScreen() {
        this.driver = DriverSingleton.getDriverInstance();
    }


    public void searchGift(){//chose options out of the dropdowns
        pricePoint();
        pickRegion();
        pickCategory();
        findGift();
    }
    public void pricePoint(){
        clickElement(By.cssSelector("span[title=סכום]"));
        clickElement(By.xpath("//*[@id='ember1076']"));
        test.log(Status.INFO, "picked a price");

    }
    public void pickRegion(){
        clickElement(By.cssSelector("span[title=אזור]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        clickElement(By.xpath("//*[@id='ember1114']"));
        test.log(Status.INFO, "picked a region");
    }
    public void pickCategory(){
        clickElement(By.cssSelector("span[title=קטגוריה]"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        clickElement(By.xpath("//*[@id='ember1173']"));
        test.log(Status.INFO, "picked a Category");

    }
    public void findGift(){ // couldnt click for somereason so decided to get the element and copy the url that is generated once options are picked and loaded that page instead as a click
        WebElement button = driver.findElement(By.id("ember1199"));
        String href = button.getAttribute("href");
        driver.get(href);
        test.log(Status.INFO, "found gift");
    }

    }

