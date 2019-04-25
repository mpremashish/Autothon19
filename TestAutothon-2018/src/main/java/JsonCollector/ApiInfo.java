package JsonCollector;

import Base.Device;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;

@Data
public class ApiInfo {
    String assertv="pass";
    int retweet_count;
    int like_Count;
    ArrayList<String> hashtag_Count;
    Device device;


    public String toString(String type) {
        if (type.contains("tweet"))
        return "##Test Mode" + type + ",##Value=" + this.retweet_count +",##assertresult"+this.assertv+ ",## device=" + this.getDevice();
        else if (type.contains("like")){
            return "##Test Mode" + type + ",##Value=" + this.like_Count +",##assertresult"+this.assertv+ ",## device=" + this.getDevice();
        }
        else if (type.contains("ui")){
            return "##Test Mode" + type + ",##Value=" + "UI" +",##assertresult"+this.assertv+ ",## device=" + this.getDevice();
        }
        else {
            return "##Test Mode" + type + ",##Value=" + "null" +",##assertresult"+"fail"+ ",## device=" + this.getDevice();
        }
    }


}