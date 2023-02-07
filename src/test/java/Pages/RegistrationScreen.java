package Pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;

public class RegistrationScreen extends BasePage {

    private WebDriver driver;

    public RegistrationScreen() {this.driver = DriverSingleton.getDriverInstance() ; }
    private static ExtentReports extent= new ExtentReports();
    private static ExtentTest test = extent.createTest("Login Page", "Login BuyMe Website");

    private String email = "Raneem"+System.currentTimeMillis()+"@gmail.com";

    public void login(){
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        goToLogin();
        goToSignup();
        enterCredentials();
        assertCredentials();
        pressSignup();
    }

    private void goToLogin(){//go to login page
        clickElement(By.linkText("כניסה / הרשמה"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        test.log(Status.INFO, "inside login page");
    }

    private void goToSignup(){//move to the signup page
        clickElement(By.xpath("//span[@class='text-link theme']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        test.log(Status.INFO, "inside signup page");
    }
    private void enterCredentials(){ // fill in the fields
        sendKeysToElement(By.cssSelector("input[placeholder='שם פרטי']"),"Raneem");
        sendKeysToElement(By.cssSelector("input[placeholder='מייל']"), email);
        sendKeysToElement(By.id("valPass"), "Rr123456!");
        sendKeysToElement(By.cssSelector("input[placeholder='אימות סיסמה']"), "Rr123456!");
        clickElement(By.className("circle"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        test.log(Status.INFO, "login details");


    }

    public void assertCredentials(){ // checking that the email and username match our sent input
        WebElement textBar = driver.findElement(By.cssSelector("input[placeholder='שם פרטי']"));
        String textName = textBar.getAttribute("value");
        WebElement textBar2 = driver.findElement(By.cssSelector("input[placeholder='מייל']"));
        String textEmail = textBar2.getAttribute("value");
        Assert.assertEquals(textName,"Raneem");
        test.log(Status.INFO, "verified the name has ben correctly entered");
        Assert.assertEquals(textEmail,email);
        test.log(Status.INFO, "verified the email has ben correctly entered");
    }
    private void pressSignup() { // press on the signup button
        clickElement(By.cssSelector("button[gtm='הרשמה ל-BUYME']"));// /html/body/div[3]/div/div[1]/div/div/div[3]/div[2]/div[3]/form/button/span
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        test.log(Status.INFO, "test login ended");

    }


}



