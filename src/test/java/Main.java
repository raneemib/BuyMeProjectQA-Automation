import Configs.DriverSingleton;
import Pages.*;
import Configs.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class Main extends BasePage {

    private static WebDriver driver;
    private static ExtentReports extent= new ExtentReports();
    private static ExtentTest test = extent.createTest("BuyMe Report", "The BuyMe project report");

    @BeforeClass
    public static void beforeClass() throws Exception {
        String type = getdata("browserType");
        if (type.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", Constants.CHROMEDRIVER_PATH);
            driver = DriverSingleton.getDriverInstance();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        } else if (type.equals("FF")) {
            System.setProperty("webdriver.firefox.driver", Constants.FFDRIVER_PATH);
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        }

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("Z:\\QA Course\\intelliJ Projects\\BuyMeProject2\\Report.html");
        extent.attachReporter(htmlReporter);
        test.log(Status.INFO, "before test method");

    }

    @Test
    public void test01_openWebsite() throws Exception {
        driver.get(getdata("URL"));
    }

    @Test
    public void test02_register(){
        try {
            Thread.sleep(500); // sadly driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); didnt do the job
            RegistrationScreen registrationScreen = new RegistrationScreen();
            registrationScreen.login();
            test.log(Status.INFO, "login test pass successfully");
        }
        catch (Exception e){
            e.printStackTrace();
            test.log(Status.INFO, "login test pass fail");
            // screenshot
            test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "ScreenShoot"+System.currentTimeMillis())).build());
        }
    }
    @Test
    public void test03_homeScreen(){
        try {
            Thread.sleep(1000); // sadly driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); didnt do the job
            HomeScreen homeScreen = new HomeScreen();
            homeScreen.searchGift();
            test.log(Status.INFO, "Home Screen test pass successfully");
        }
        catch (Exception e){
            e.printStackTrace();
            test.log(Status.INFO, "Home Screen test fail"+e.getMessage());
            // screenshot
            test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "ScreenShoot"+System.currentTimeMillis())).build());
        }
    }

    @Test
    public void test04_pickBusiness() {
        try {
            Thread.sleep(1000); // sadly driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); didnt do the job
            PickBusiness pickBusiness = new PickBusiness();
            pickBusiness.business();
            test.log(Status.INFO, "businessPage test pass successfully");
        }
        catch (Exception e){
            e.printStackTrace();
            test.log(Status.INFO, "businessPage test fail" +e.getMessage());
            // screenshot
            test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "ScreenShoot"+System.currentTimeMillis())).build());
        }
    }

    @Test
    public void test05_sNrInformation(){
        try {
            Thread.sleep(500); // sadly driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); didnt do the job
            Information information = new Information();
            information.detailsInformation();
            test.log(Status.INFO, "informationPage test pass successfully");
        }
        catch (Exception e){
            e.printStackTrace();
            test.log(Status.INFO, "informationPage test fail" +e.getMessage());
            // screenshot
            test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot(driver, "ScreenShoot"+System.currentTimeMillis())).build());
        }
    }
    @AfterClass

    public static void afterClass() {
        extent.flush();
        driver.quit();
    }




}