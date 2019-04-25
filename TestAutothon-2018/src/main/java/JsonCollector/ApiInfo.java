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
        return "" + type.toUpperCase() + "##Value=" + this.top_retweet_count +"##"+assertv+ "## device=" +System.getProperty("threadsName");
        else if (type.contains("like")){
            return "" + type + "##Value=" + this.top_like_count +"##"+assertv+ "## device=" + System.getProperty("threadsName");
        }
        else if (type.contains("hash")){
            return "" + type.toUpperCase() + "##Value=" + top_10_hashtag +"##"+assertv+ "## device=" + System.getProperty("threadsName");
        }
        else if (type.contains("ui")){
            return "" + type.toUpperCase() + "##Value=" + this.biographies +"##"+assertv+ "## device=" + System.getProperty("threadsName");
        }
        else {
            return "" + type.toUpperCase() + "##Value=" + "null" +"##"+"fail"+ "## device=" + System.getProperty("threadsName");
        }
    }


}