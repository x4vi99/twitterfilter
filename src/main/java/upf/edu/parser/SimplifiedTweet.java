package upf.edu.parser;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Serializable;
import java.util.Optional;

public class SimplifiedTweet {

  private final long tweetId;			  // the id of the tweet ('id')
  private final String text;  		      // the content of the tweet ('text')
  private final long userId;			  // the user id ('user->id')
  private final String userName;		  // the user name ('user'->'name')
  private final String language;          // the language of a tweet ('lang')
  private final long timestampMs;		  // seconds from epoch ('timestamp_ms')
  

  public SimplifiedTweet(long tweetId, String text, long userId, String userName,
                         String language, long timestampMs) {
	  
	this.tweetId=tweetId;
	this.text=text;
	this.userId=userId;
	this.userName=userName;
	this.language=language;
	this.timestampMs=timestampMs;
	
  }

  /**
   * Returns a {@link SimplifiedTweet} from a JSON String.
   * If parsing fails, for any reason, return an {@link Optional#empty()}
   *
   * @param jsonStr
   * @return an {@link Optional} of a {@link SimplifiedTweet}
   */
  public static Optional<SimplifiedTweet> fromJson(String jsonStr) {
	  
	  
	  JsonElement je= JsonParser.parseString(jsonStr);
	  JsonObject jo= je.getAsJsonObject();
	  
	  Long tweetId = null;			  
	  String text = null;  		  
	  Long userId = null;			  
	  String userName = null;		  
	  String language = null;          
	  Long timestampMs = null;
	  
	  
	  Optional <SimplifiedTweet> tweetopt = Optional.empty();
	  
	  if(jo.has("id") && jo.has("text") && jo.has("user")  && jo.has("lang") && jo.has("timestamp_ms")){
		  JsonObject userObject = jo.get("user").getAsJsonObject();
		  
		  if (userObject.has("userName") && userObject.has("id")) {
			  userName = userObject.get("name").getAsString();
			  userId = userObject.get("id").getAsLong();
		  }
			tweetId = jo.get("id").getAsLong();
	        text = jo.get("text").getAsString();
	        language = jo.get("lang").getAsString();
	        timestampMs = jo.get("timestamp_ms").getAsLong();
	        SimplifiedTweet tweet = new SimplifiedTweet(tweetId,text,userId,userName,language,timestampMs);
	        tweetopt = Optional.of(tweet);
	  }
	
	  return tweetopt;
	  

	

  }
}
