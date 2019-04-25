package JsonCollector;

public class JsonDataObject {

    public String name;
    public String handle_name;
    public String follower_count;
    public String following_count;

   public JsonDataObject(String name,String handle_name ,String follower_count,String following_count){
       this.name=name;
       this.handle_name=handle_name;
       this.follower_count=follower_count;
       this.following_count=following_count;
   }

}