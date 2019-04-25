package Base;

import java.io.FileInputStream;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import JsonCollector.*;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public abstract class TestCase extends TestBaseManager {

    private static Logger log = Logger.getLogger(TestCase.class.getName());
    protected static Stack<JsonDataObject> jsonStack = new Stack<>();
    private static ConcurrentMap<String, String> userMap = new ConcurrentHashMap<>();
    public Twitter twitter;
    public ApiInfo apiInfo=new ApiInfo();

    @Factory(dataProvider = "instanceFactory")
    public TestCase(String thread) throws SecurityException, IllegalArgumentException {
        super(Device.fromString(thread));
    }

    @DataProvider(name = "instanceFactory", parallel = true)
    public static Iterator<Object[]> testFactory(ITestContext testContext) {
        if (testContext != null)
            System.getProperties().putAll(testContext.getCurrentXmlTest().getAllParameters());
        List<Object[]> data = new ArrayList<>();
        Arrays.stream(System.getProperty("threadsName").split(",")).forEach(i -> {
            data.add(new Object[]{i});
        });
        return data.iterator();
    }


    public TestCase(Device thread) {
        super(thread);
    }

//    protected void setCredentials() {
//        try {
//            Properties prop = new Properties();
//            prop.load(new FileInputStream(System.getProperty("user.dir") + "//logindata//credentials.properties"));
//            userMap.putAll(prop.entrySet().stream().collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString())));
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }

//    public static synchronized String popUserMap() {
//        Iterator<Map.Entry<String, String>> itr = userMap.entrySet().iterator();
//        Map.Entry<String, String> nxtEntry;
//        String pair;
//        if (itr.hasNext()) {
//            nxtEntry = itr.next();
//            pair = nxtEntry.getKey() + ":" + nxtEntry.getValue();
//            userMap.remove(nxtEntry.getKey());
//        } else {
//            throw new InvalidParameterException("user details is empty..");
//        }
//        return pair;
//    }

    public void CalminRetweetminLikeHash(String screename) throws TwitterException {
        int retweethighest=0;
        int Likehighest=0;
        HashMap<String,Integer> hashtag = new HashMap<>();
        ArrayList<String> hashtagl = new ArrayList<>();
        Paging paging = new Paging(1, 50);
        List<Status> statuses = twitter.getUserTimeline(
                screename, paging);
        for (Status status : statuses) {
            if (retweethighest<status.getRetweetCount()){
                retweethighest=status.getRetweetCount();
            }
            if (Likehighest<status.getFavoriteCount()){
                Likehighest=status.getFavoriteCount();
            }
            for (HashtagEntity h:status.getHashtagEntities()){
                if (hashtag.containsKey(h.getText())){
                    int value =hashtag.get(h.getText())+1;
                    hashtag.put(h.getText(),value);

                }
                else if(h.getText().toLowerCase().matches("[a-zA-Z0-9_]*$")&&h.getText().toLowerCase().matches(".*\\S.*")){
                    hashtag.put(h.getText(),1);
                }
            }

        }
        Set<Map.Entry<String, Integer>> set = hashtag.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        int Counter=0;
        for(Map.Entry<String, Integer> entry:list){
            if(Counter<10){
                hashtagl.add(entry.getKey());
            }
            Counter=Counter+1;
        }

        apiInfo.setLike_Count(Likehighest);
        apiInfo.setRetweet_count(retweethighest);
        apiInfo.setHashtag_Count(hashtagl);
    }

    public void setTimeout(long timeout) {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    @BeforeSuite
    public void TestExecutionSetUp() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("3QHsalYXMeQchpOhwZWc2HfrC");
        cb.setOAuthConsumerSecret("sDE8sk6kj0aegjy2fLIZXV5oZRZY5AOH6XcX1LcyYP0tjjNqXT");
        cb.setOAuthAccessToken("1121272893663584256-RoLkgcGZs7caL56l4YrCu9QZBVbmfD");
        cb.setOAuthAccessTokenSecret("7eKt0SbuW0pVzD2F9TTXyrYVDD9bFH9RsUHKIg8yRGemO");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        log.info("Running TestExecutionSetUp (before suite)");
        jsonStack = JsonCollector.findJsonObjects();
    }


    @AfterSuite
    public void afterSuite() {
        driver.quit();
    }

}
