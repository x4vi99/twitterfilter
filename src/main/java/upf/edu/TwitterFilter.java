package upf.edu;


import java.io.File;
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
		String dir = argsList.get(3);

		int num_tweets = 0;
		
		System.out
				.println("Language: " + language + ". Output file: " + outputFile + ". Destination bucket: " + bucket);
		
		//For each file containing eurovision tweets we filter the tweets on those files
		File folder = new File(dir);
		for (File fileEntry: folder.listFiles()) {
			System.out.println("Processing: " + fileEntry.getName());
			String s = dir+"/"+fileEntry.getName();
			final FileLanguageFilter filter = new FileLanguageFilter(s, outputFile);
			num_tweets += filter.filterLanguage(language);
		}
		System.out.println("There is a total of: " + num_tweets + " tweets.");
		
		//Upload the tweets to AWS
		 final S3Uploader uploader = new S3Uploader(bucket, language, "default");
		 uploader.upload(Arrays.asList(outputFile));
		
		 long endTime = System.currentTimeMillis();
		 long totalTime = endTime - startTime;
		 System.out.println("Execution Time: " + totalTime + " ms.");

	}
}
