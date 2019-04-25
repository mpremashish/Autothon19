package TestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import JsonCollector.JsonDataObject;
import Listener.Retry;
import Reporter.HtmlReporter;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.Device;
import Base.ObjectUtils;
import Base.TestCase;
import JsonCollector.*;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import twitter4j.HashtagEntity;
import twitter4j.Paging;
import twitter4j.Status;


public class Autothon19 extends TestCase {
    public static HtmlReporter report = null;
    private static Logger log = Logger.getLogger(Class.class.getName());

    public Autothon19(Device thread) {
        super(thread);
    }

    @BeforeClass
    public void beforesuit(ITestContext context){
        report = new HtmlReporter(context.getCurrentXmlTest().getName());
        report.setTestSuiteHeader("Test Suite: " + context.getCurrentXmlTest().getName());
        report.addStepRow("TestCase##Test Mode##Value##assertresult##Device", "warn", false);
    }

    @Test
    public void TestApi(){
        try {
        CalminRetweetminLikeHash("stepin_forum");
        log.info(apiInfo.getLike_Count());
        log.info(apiInfo.getRetweet_count());
        log.info(apiInfo.getHashtag_Count());
        setTimeout(5);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestUI(){
        setDriver();
        setTimeout(10);

        driver.get("https://twitter.com/stepin_forum");
        setTimeout(100);
        String name_first = driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[1]/strong")).getText();

        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[1]")).click();

        String handle_name_first = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
        String following_count_first = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
        String followers_count_first = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();


        System.out.println("Name of the first people to follow:" + name_first);
        System.out.println("Handle Name of the first people to follow:" + handle_name_first);
        System.out.println("Following count of the first people:" + following_count_first);
        System.out.println("Followers of the first people:" + followers_count_first);

        driver.navigate().back();
        //To fetch Second profile details
        String name_second = driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[2]/strong")).getText();

        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[2]")).click();

        String handle_name_second = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
        String following_count_second = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
        String followers_count_second = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();

        System.out.println("Name of the first second to follow:" + name_second);
        System.out.println("Handle Name of the second people to follow:" + handle_name_second);
        System.out.println("Following count of the second people:" + following_count_second);
        System.out.println("Followers of the second people:" + followers_count_second);

        driver.navigate().back();
        //To fetch third profile details
        String name_third = driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[3]")).getText();

        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[3]")).click();

        String handle_name_third = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
        String following_count_third = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
        String followers_count_third = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();

        System.out.println("Name of the first second to follow:" + name_third);
        System.out.println("Handle Name of the second people to follow:" + handle_name_third);
        System.out.println("Following count of the second people:" + following_count_third);
        System.out.println("Followers of the second people:" + followers_count_third);
        driver.close();


    }

    @AfterClass
    public void TearDown(ITestContext context){
        this.report.finalizeSuiteResult(context);
    }
//    private static Logger log = Logger.getLogger(Autothon19.class.getName());


}
