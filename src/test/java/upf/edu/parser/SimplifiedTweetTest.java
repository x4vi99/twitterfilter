package upf.edu.parser;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import org.junit.Test;

import upf.edu.filter.FileLanguageFilter;
import upf.edu.filter.LanguageFilter;

public class SimplifiedTweetTest {
	
	private String inputFile;
	private String outputFile;


	private FileLanguageFilter filter;
	
	public void setUp() throws Exception{
		filter = new FileLanguageFilter (inputFile,outputFile);
	}
	
	
	@Test
	public void invalidlanguage() {
		//assertEquals(error,filter.filterLanguage(""),"This should fail");
	}
	
	public void invalidJson() {
		//asserEquals
	}


}
