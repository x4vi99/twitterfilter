package upf.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import org.junit.Test;

import upf.edu.filter.FileLanguageFilter;
import upf.edu.parser.SimplifiedTweet;

/**
 * Unit test for simple App.
 */
public class TwitterFilterTest
{
    /**
     * Rigorous Test :-)
     */
	
    @Test

    public void parseTweet(){
    	SimplifiedTweet tweet;
    	Optional<SimplifiedTweet> opt;
    	
    	File input = new File("test/Eurovision3.json");
		Scanner reader = null;
		try {
			reader = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line = reader.nextLine();
		/* Create SimplifiedTweet object from line */	
		opt = SimplifiedTweet.fromJson(line);
		tweet = opt.get();

		assertEquals("995332494974210048",String.valueOf(tweet.getId()));
		assertEquals("492271155",String.valueOf(tweet.getUserId()));
		assertEquals("alba aguirre",String.valueOf(tweet.getUserName()));
		assertEquals("RT @carloscarmo98: -Manel, algo que decir sobre tu actuación en Eurovision?\n-Kikiriketediga https://t.co/yXGYtKmJoM",String.valueOf(tweet.getText()));
		assertEquals("1526140733842",String.valueOf(tweet.getTimeStampMs()));	
    }
    
    @Test
    public void invalidJson() {
    	SimplifiedTweet tweet;
    	Optional<SimplifiedTweet> opt;   	
    	File input = new File("test/invalid.json");
		Scanner reader = null;
		try {
			reader = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		tweet=null;
		try {
		String line = reader.nextLine();
		opt = SimplifiedTweet.fromJson(line);
		tweet = opt.get();
		}catch(Exception e){
			System.out.println("Invalid Json");
		}
		
    }
    @Test
    public void missingfield() {
    	SimplifiedTweet tweet;
    	Optional<SimplifiedTweet> opt;   	
    	File input = new File("test/missing.json");
		Scanner reader = null;
		try {
			reader = new Scanner(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String line = reader.nextLine();
		opt = SimplifiedTweet.fromJson(line);
		
		assertEquals(Optional.empty(),opt);
		
    }
    
}
