package Listener;

import JsonCollector.ApiInfo;
import Reporter.HtmlReporter;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class HtmlListener implements ITestListener {
    private static Logger log = Logger.getLogger(Class.class.getName());
    ApiInfo x = new ApiInfo();
    static HtmlReporter reporter;

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getMethod().getMethodName());

        if (result.getMethod().getMethodName().toLowerCase().contains("api")){
            reporter.addStepRow("" + "Error-twitter" + "##Value=" + "null" +"##"+"fail"+ "## device=" + System.getProperty("threadsName"),false);
        }
        if (result.getMethod().getMethodName().toLowerCase().contains("ui")){
            reporter.addStepRow("" + "Error-twitter-UI" + "##Value=" + "null" +"##"+"fail"+ "## device=" + System.getProperty("threadsName"),false);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    public void setreport(HtmlReporter report){
     reporter=report;
    }
}
