package TestCases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import JsonCollector.JsonDataObject;
import Listener.HtmlListener;
import Listener.Retry;
import Reporter.HtmlReporter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
    ObjectUtils uiobj;
    HtmlListener lis = new HtmlListener();

    public Autothon19(Device thread) {
        super(thread);
    }

    @BeforeSuite
    public void beforesuit(ITestContext context){
        report = new HtmlReporter(context.getCurrentXmlTest().getName());
        report.setTestSuiteHeader("Test Suite: " + context.getCurrentXmlTest().getName());
        report.addStepRow("Test Mode##Value##assertresult##Device", "warn", false);
        lis.setreport(report);
    }

    @Test
    public void TestApi(){
        try {
        CalminRetweetminLikeHash("stepin_forum",report);
        log.info(apiInfo.getTop_like_count());
        log.info(apiInfo.getTop_retweet_count());
        log.info(apiInfo.getTop_10_hashtag());
        Assert.assertTrue(apiInfo.getTop_retweet_count()>0&&apiInfo.getTop_like_count()>0&&apiInfo.getTop_10_hashtag().size()>10);
        report.addStepRow(apiInfo.toString("api-tweet"),false);
        report.addStepRow(apiInfo.toString("api-like"),false);
        report.addStepRow(apiInfo.toString("api-hash"),false);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void TestGUI(){
        setDriver();
        setTimeout(10);
        ArrayList<JsonDataObject> jslist=new ArrayList<JsonDataObject>();
        uiobj = new ObjectUtils(driver,"uiobjects");
        driver.get("https://twitter.com/stepin_forum");
        setTimeout(100);
        uiobj.getElement("NameFirst");
        String name_first = uiobj.getElement("NameFirst").getText();
        uiobj.getElement("FirstUser").click();

        String handle_name_first = uiobj.getElement("HandleNameFirst").getText();
        String following_count_first = uiobj.getElement("FollowingCountFirst").getText();
        String followers_count_first = uiobj.getElement("FollowersCountFirst").getText();

        jslist.add(new JsonDataObject(name_first,handle_name_first,followers_count_first,following_count_first));

        log.info(name_first);
        log.info(handle_name_first);
        log.info(following_count_first);
        log.info(followers_count_first);

        driver.navigate().back();
        //To fetch Second profile details
        String name_second = uiobj.getElement("NameSecond").getText();

       uiobj.getElement("SecondUser").click();

        String handle_name_second = uiobj.getElement("HandleNameSecond").getText();
        String following_count_second = uiobj.getElement("FollowingCountSecond").getText();
        String followers_count_second = uiobj.getElement("FollowersCountSecond").getText();

        jslist.add(new JsonDataObject(name_second,handle_name_second,followers_count_second,following_count_second));

        log.info(name_second);
        log.info(handle_name_second);
        log.info(following_count_second);
        log.info(followers_count_second);


        driver.navigate().back();
        //To fetch third profile details
        String name_third = uiobj.getElement("NameThird").getText();

        uiobj.getElement("ThirdUser").click();

        String handle_name_third = uiobj.getElement("HandleNameThird").getText();
        String following_count_third = uiobj.getElement("FollowingCountThird").getText();
        String followers_count_third = uiobj.getElement("FollowersCountThird").getText();

        jslist.add(new JsonDataObject(name_third,handle_name_third,followers_count_third,following_count_third));

        log.info(name_third);
        log.info(handle_name_third);
        log.info(following_count_third);
        log.info(followers_count_third);

        apiInfo.setBiographies(jslist);
        report.addStepRow(apiInfo.toString("ui"),true);
        driver.close();

    }

    @Test
    public void TestPost() throws IOException, InterruptedException {
        setDriver();
        uiobj = new ObjectUtils(driver,"uiobjects");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/api.json"),apiInfo );
        String baseUrl = "https://cgi-lib.berkeley.edu/ex/fup.html";
        driver.get(baseUrl);
        WebElement uploadElement = uiobj.getElement("uploadurl");
        // enter the file path onto the file-selection input field
        uploadElement.sendKeys(Paths.get("./target/api.json").toAbsolutePath().toString());
        // click the "Press" button
        uiobj.getElement("uploadbtn").click();
        driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        report.addStepRow(apiInfo.toString("ui"),true);
        driver.close();
    }

    @AfterClass
    public void TearDown(ITestContext context) throws IOException {
        this.report.finalizeSuiteResult(context);
    }
//    private static Logger log = Logger.getLogger(Autothon19.class.getName());


}
