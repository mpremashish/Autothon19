//package JsonCollector;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import cucumber.api.java.it.Ma;
//import twitter4j.*;
//import twitter4j.conf.*;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;
//
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.*;
//
//public class JsonCollectorJava {
//    public static void main(String[] args) {
//        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setOAuthConsumerKey("3QHsalYXMeQchpOhwZWc2HfrC");
//        cb.setOAuthConsumerSecret("sDE8sk6kj0aegjy2fLIZXV5oZRZY5AOH6XcX1LcyYP0tjjNqXT");
//        cb.setOAuthAccessToken("1121272893663584256-RoLkgcGZs7caL56l4YrCu9QZBVbmfD");
//        cb.setOAuthAccessTokenSecret("7eKt0SbuW0pVzD2F9TTXyrYVDD9bFH9RsUHKIg8yRGemO");
//        TwitterFactory tf = new TwitterFactory(cb.build());
//        Twitter twitter = tf.getInstance();
//        ObjectMapper mapper=new ObjectMapper();
//
//        Paging paging = new Paging(1, 50);
//        int retweethighest=0;
//            ArrayList<Integer> hash = new ArrayList<>();
//            ArrayList<String> hashtagl = new ArrayList<>();
//        String retweet = "";
//        HashMap<String,Integer> hashtag = new HashMap<>();
//        int Likehighest=0;
//        try {
//            List<Status> statuses = twitter.getUserTimeline(
//                    "stepin_forum", paging);
//            int count=0;
//            for (Status status : statuses) {
//                count=count+1;
//                if (retweethighest<status.getRetweetCount()){
//                    retweethighest=status.getRetweetCount();
////                    retweet=status.getText();
//                }
//                if (Likehighest<status.getFavoriteCount()){
//                    Likehighest=status.getFavoriteCount();
//                }
//                for (HashtagEntity h:status.getHashtagEntities()){
//                    if (hashtag.containsKey(h.getText())){
//                        int value =hashtag.get(h.getText())+1;
//                        hashtag.put(h.getText(),value);
//                        System.out.println(h.getText()+hashtag.get(h.getText()));
//                    }
//                    else if(h.getText().toLowerCase().matches("[a-zA-Z0-9_]*$")&&h.getText().toLowerCase().matches(".*\\S.*")){
//                        System.out.println(h.getText());
//                        hashtag.put(h.getText(),1);
//                    }
//                }
//
//            }
//            System.out.println("Highest retweet---->"+retweethighest);
//            System.out.println("Highest Likes----->"+Likehighest);
//            Set<Map.Entry<String, Integer>> set = hashtag.entrySet();
//            List<Map.Entry<String, Integer>> list = new ArrayList<>(set);
//            Collections.sort( list, new Comparator<Map.Entry<String, Integer>>() {
//            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
//            {
//                return (o2.getValue()).compareTo( o1.getValue() );
//            }
//            } );
//            int Counter=0;
//            for(Map.Entry<String, Integer> entry:list){
//                if(Counter<10){
//                    hashtagl.add(entry.getKey());
//                    System.out.println(entry.getKey()+"----------->"+entry.getValue());
//                }
//                Counter=Counter+1;
//            }
//                    // Sort the list
//        } catch (TwitterException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//
//        }
//        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://twitter.com/stepin_forum");
//
//        //To fetch First profile details
//        String name_first = driver.findElement(By.xpath("`(//span[contains(@class,'account-group-inner')])[3]`")).getText();
//
//        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[1]")).click();
//
//        String handle_name_first = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
//        String following_count_first = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
//        String followers_count_first = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();
//
//
//        System.out.println("Name of the first people to follow:" + name_first);
//        System.out.println("Handle Name of the first people to follow:" + handle_name_first);
//        System.out.println("Following count of the first people:" + following_count_first);
//        System.out.println("Followers of the first people:" + followers_count_first);
//
//        driver.navigate().back();
//        //To fetch Second profile details
//        String name_second = driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[2]/strong")).getText();
//
//        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[2]")).click();
//
//        String handle_name_second = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
//        String following_count_second = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
//        String followers_count_second = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();
//
//        System.out.println("Name of the first second to follow:" + name_second);
//        System.out.println("Handle Name of the second people to follow:" + handle_name_second);
//        System.out.println("Following count of the second people:" + following_count_second);
//        System.out.println("Followers of the second people:" + followers_count_second);
//
//        driver.navigate().back();
//        //To fetch third profile details
////        String name_third = driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[3]")).getText();
////
////        driver.findElement(By.xpath("(//span[contains(@class,'account-group-inner')])[3]")).click();
////
////        String handle_name_third = driver.findElement(By.xpath("//h2[contains(@class,'ProfileHeaderCard-screenname u-inlineBlock')]/a")).getText();
////        String following_count_third = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[2]/a/span[3]")).getText();
////        String followers_count_third = driver.findElement(By.xpath("//ul[contains(@class,'ProfileNav-list')]/li[3]/a/span[3]")).getText();
////
////        System.out.println("Name of the first second to follow:" + name_third);
////        System.out.println("Handle Name of the second people to follow:" + handle_name_third);
////        System.out.println("Following count of the second people:" + following_count_third);
////        System.out.println("Followers of the second people:" + followers_count_third);
//        driver.close();
//
//        JSONObject employeeDetails = new JSONObject();
//        employeeDetails.put("Name",name_first );
//        employeeDetails.put("Handle_name",handle_name_first);
//
//
//        JSONObject employeeObject = new JSONObject();
//        employeeObject.put("employee", employeeDetails);
//
//        //Second Employee
//        JSONObject employeeDetails2 = new JSONObject();
//        employeeDetails2.put("firstName", "Brian");
//        employeeDetails2.put("lastName", "Schultz");
//        employeeDetails2.put("website", "example.com");
//
//        JSONObject employeeObject2 = new JSONObject();
//        employeeObject2.put("employee", employeeDetails2);
//
//        //Add employees to list
////        JSONArray employeeList = new JSONArray();
////        employeeList.add(employeeObject);
////        employeeList.add(employeeObject2);
//
////        //Write JSON file
////        try (FileWriter file = new FileWriter("employees.json")) {
////
////            file.write(employeeList.toJSONString());
////            file.flush();
////
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//    }
//}
