package upf.edu.filter;

import upf.edu.parser.SimplifiedTweet;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class FileLanguageFilter implements LanguageFilter {
	private final String inputFile;
	private final String outputFile;

	public FileLanguageFilter(String inputFile, String outputFile) {
		this.inputFile = inputFile;
		this.outputFile = outputFile;
	}

	public void filterLanguage(String language) throws IOException {
		Optional<SimplifiedTweet> opt;
		SimplifiedTweet tweet;

		/* Create new Output File */
		try {
			File output = new File(this.outputFile);
			output.createNewFile();

		} catch (IOException e) {
			e.printStackTrace();
		}

		/* Read Input File */
		try {
			File input = new File(this.inputFile);
			Scanner reader = new Scanner(input);
			BufferedWriter writer = new BufferedWriter(new FileWriter(this.outputFile, true));

			while (reader.hasNextLine()) {
				String line = reader.nextLine();
				/* Create SimplifiedTweet object from line */
				opt = SimplifiedTweet.fromJson(line);

				if (opt.isPresent()) {
					tweet = opt.get();
					if (tweet.getLanguage().equals(language)) {
						/*Write Output File*/
						writer.write("\n##########################################################");
						writer.newLine();
						writer.write("Tweet Id: ");
						writer.append(String.valueOf(tweet.getId()));
						
						writer.newLine();
						writer.write("User Id: ");
						writer.append(String.valueOf(tweet.getUserId()));
						
						writer.newLine();
						writer.write("User Name: ");
						writer.append(String.valueOf(tweet.getUserName()));
						
						writer.newLine();
						writer.write("Text: ");
						writer.append(String.valueOf(tweet.getText()));
						
						writer.newLine();
						writer.write("Time Stamp MS: ");
						writer.append(String.valueOf(tweet.getTimeStampMs()));
						writer.newLine();
						writer.write("##########################################################");
						writer.newLine();
						writer.newLine();
					}
				}

			}
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
