package Listener;

        import org.testng.IRetryAnalyzer;
        import org.testng.ITestResult;

        import java.util.logging.Logger;

public class Retry implements IRetryAnalyzer {
    private static final Logger log = Logger.getLogger(Retry.class.getSimpleName());

    private int retrycount = 0;
    private static final int MAX_RETRY_COUNT = 2;

    //Logic for retrying the test with a particular result status.
    public boolean retry(ITestResult result) {
        if (retrycount < MAX_RETRY_COUNT) {
            log.info("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retrycount + 1) + " time(s).");
            retrycount++;
            return true;
        }
        return false;
    }

    private String getResultStatusName(int status) {
        String resultName = "";
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }


}
