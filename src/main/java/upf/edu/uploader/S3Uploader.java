package upf.edu.uploader;

import java.io.File;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;

public class S3Uploader implements Uploader{
	
	private String bucketName;
	private String prefix;
	private String credentials_profile_name;
	
	public S3Uploader(String _bucketName, String _prefix, String _credentials_profile_name) {
		
		this.bucketName = _bucketName;
		this.prefix = _prefix;
		//Guardar les _credentials com a tipus AWSCredentialsi no com a String
		this.credentials_profile_name = _credentials_profile_name;
	}
	

	public void upload(List<String> files) {
		
		/*
		 * Creating client connection 
		 */
		/*
		AWSCredentials credentials = new BasicAWSCredentials(
				"<AWS accesskey>",
				"<AWS secretkey>"
				);
		
		*/
		String filename = files.get(0);
		String filekey = this.prefix+"-"+filename;
		
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withRegion(Regions.US_EAST_1)
				.build();
	
		if(!s3Client.doesBucketExistV2(this.bucketName)) {	
			 Bucket bucket = s3Client.createBucket(this.bucketName);
		}
		try {
			PutObjectRequest request = new PutObjectRequest(this.bucketName,filekey, new File(filename));
			ObjectMetadata metadata = new ObjectMetadata();
	        metadata.setContentType("plain/text");
	        request.setMetadata(metadata);
			s3Client.putObject(request);
		} catch (AmazonS3Exception e) {
			System.err.println(e.getErrorMessage());
		}
	
		

	}
	
	
}
