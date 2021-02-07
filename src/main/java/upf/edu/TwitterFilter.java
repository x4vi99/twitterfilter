package upf.edu;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import upf.edu.filter.FileLanguageFilter;
import upf.edu.uploader.S3Uploader;




public class TwitterFilter {
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		
		List<String> argsList = Arrays.asList(args);
		String language = argsList.get(0);
		String outputFile = argsList.get(1);
		String bucket = argsList.get(2);

		int num_tweets = 0;
		
		System.out
				.println("Language: " + language + ". Output file: " + outputFile + ". Destination bucket: " + bucket);
		for (String inputFile : argsList.subList(3, argsList.size())) {
			System.out.println("Processing: " + inputFile);
			final FileLanguageFilter filter = new FileLanguageFilter(inputFile, outputFile);
			num_tweets += filter.filterLanguage(language);
		}
		System.out.println("There is a total of: " + num_tweets + " tweets.");
		 final S3Uploader uploader = new S3Uploader(bucket, language, "default");
		 uploader.upload(Arrays.asList(outputFile));
		
		 long endTime = System.currentTimeMillis();
		 long totalTime = endTime - startTime;
		 System.out.println("Execution Time: " + totalTime + " ms.");

	}
}
