package Pages;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.Duration;

public class Information extends BasePage {

    private WebDriver driver;
    private static ExtentReports extent= new ExtentReports();
    private static ExtentTest test = extent.createTest("Information Page", "Order Details");

    public Information()
    {this.driver = DriverSingleton.getDriverInstance() ;
    }

    public void detailsInformation(){
        toWhomTheGift();
        receiverName();
        chooseEvent();
        enterBlessing();
        uploadPicture();
        assertReceiverName();
        pressContinue();
        whenToSendGift();
        pickSMS();
        enterSenderName();
        assertSenderName();
        go2Payment();

    }

    public void toWhomTheGift() { //chose send a gift to some one else
        clickElement((By.cssSelector("div[gtm='למישהו אחר']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }
    private void receiverName(){ // fill in receiver's name
        sendKeysToElement(By.xpath("//input[@data-parsley-required-message='מי הזוכה המאושר? יש להשלים את שם המקבל/ת']"), "Daniel");
        test.log(Status.INFO, "Receiver name");
    }
    public void chooseEvent(){ // chose the event
        clickElement((By.cssSelector("span[title='לאיזה אירוע?']")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        clickElement((By.xpath("//span[text()='תודה']")));
        test.log(Status.INFO, "Choose event");
    }
    private void enterBlessing(){ // add a blessing
        sendKeysToElement(By.xpath("//textarea[@placeholder='מזל טוב, תודה רבה או פשוט מלא אהבה? כאן כותבים מילים טובות ואיחולים שמחים']"), " Congrats ");
        test.log(Status.INFO, "Type blessing");
    }
    private void uploadPicture(){ //uploade a picture (random screenshot i toke of the website
        sendKeysToElement(By.xpath("//*[@accept='image/png,image/jpeg,video/quicktime,video/mp4,.mov,.qt']"),"Z:\\QA Course\\intelliJ Projects\\BuyMeProject2\\buyme.png");
        test.log(Status.INFO, "Upload picture");
    }

    public void assertReceiverName(){ // checking that the email and username match our sent input
        WebElement textBar = driver.findElement(By.xpath("//input[@data-parsley-required-message='מי הזוכה המאושר? יש להשלים את שם המקבל/ת']"));
        String textReceiver = textBar.getAttribute("value");
        Assert.assertEquals(textReceiver,"Daniel");
        test.log(Status.INFO, "verified receiver name entered correctly");
    }
    private void pressContinue(){ //go to step2
        clickElement(By.cssSelector("button[gtm='המשך']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        test.log(Status.INFO, "Press continue");
    }

    public void whenToSendGift(){ // chose when to send the gift " we chose now"
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        clickElement((By.cssSelector("div[gtm='עכשיו']")));
    }
    private void pickSMS(){ // chose the sms option
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        clickElement(By.cssSelector("div[class=circle-area]"));
        sendKeysToElement(By.id("sms"), "0501234567");
        test.log(Status.INFO, "Choose sms");
    }
    private void enterSenderName(){ //fill in senders info
        driver.findElement(By.xpath("//input[@placeholder='שם שולח המתנה']")).clear(); // apparently the website decided tonight ot start automatically fill sender name based on your name
        sendKeysToElement(By.xpath("//input[@placeholder='שם שולח המתנה']"),"Raneem");
        sendKeysToElement(By.xpath("//input[@placeholder='מספר נייד']"),"0501234567");
        test.log(Status.INFO, "Sender info entered");
    }

    public void assertSenderName(){ // checking that the email and username match our sent input
        WebElement textBar2 = driver.findElement(By.xpath("//input[@placeholder='שם שולח המתנה']"));
        String textSender = textBar2.getAttribute("value");
        Assert.assertEquals(textSender,"Raneem");
        test.log(Status.INFO, "verified sender name entered correctly");
    }
    private void go2Payment(){ //go to final payment page (step3)
        clickElement(By.xpath("//span[text()='המשך לתשלום']"));
        test.log(Status.INFO, "press pay");
    }

}
