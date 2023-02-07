package Pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class PickBusiness extends BasePage {
    private WebDriver driver;
    private static ExtentReports extent= new ExtentReports();
    private static ExtentTest test = extent.createTest("PickBusiness Page", "Business Selection Page");

    public PickBusiness(){
        this.driver = DriverSingleton.getDriverInstance() ;
    }

    public void business(){
        assertURL();
        pickBusiness();
        choosePrice();
        pressChose();
    }

    public void assertURL(){ //check that we are in the right search
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        String URL = DriverSingleton.getDriverInstance().getCurrentUrl();
        Assert.assertEquals(URL, "https://buyme.co.il/search?budget=2&category=22&region=14");
        test.log(Status.INFO, "URL matches, your in the right search page");
    }
    public void pickBusiness (){ //pick the desried business
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        clickElement(By.linkText("עזריאלי גיפט קארד"));
        test.log(Status.INFO, "pick a business");
    }

    public void choosePrice (){ //fill in the desired price
        sendKeysToElement(By.cssSelector("input[placeholder='הכנס סכום']"),"100");
        test.log(Status.INFO, "pick a price ");
    }
   public void pressChose(){ //accept and go to payment page
       clickElement(By.cssSelector("div[class='mx-12 money-btn']"));
       test.log(Status.INFO, "pickBusiness test end");
   }
}
