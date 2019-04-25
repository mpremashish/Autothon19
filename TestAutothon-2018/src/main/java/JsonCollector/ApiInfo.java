package JsonCollector;

import Base.Device;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class ApiInfo {
    int top_retweet_count;
    int top_like_count;
    ArrayList<String> top_10_hashtag;
    ArrayList<JsonDataObject> biographies;


    public String toString(String type) {
        String assertv="pass";
        if (type.contains("tweet"))
        return "Test Mode" + type + ",##Value=" + this.top_retweet_count +",##assertresult"+assertv+ ",## device=" +System.getProperty("threadsName");
        else if (type.contains("like")){
            return "Test Mode" + type + ",##Value=" + this.top_like_count +",##assertresult"+assertv+ ",## device=" + System.getProperty("threadsName");
        }
        else if (type.contains("hash")){
            return "Test Mode" + type + ",##Value=" + top_10_hashtag +",##assertresult"+assertv+ ",## device=" + System.getProperty("threadsName");
        }
        else if (type.contains("ui")){
            return "Test Mode" + type + ",##Value=" + this.biographies +",##assertresult"+assertv+ ",## device=" + System.getProperty("threadsName");
        }
        else {
            return "Test Mode" + type + ",##Value=" + "null" +",##assertresult"+"fail"+ ",## device=" + System.getProperty("threadsName");
        }
    }


}