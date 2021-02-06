package upf.edu.uploader;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Uploader implements Uploader{
	
	private String bucketName;
	private String prefix;
	private String credentials_profile_name;
	
	public S3Uploader(String _bucketName, String _prefix, String _credentials_profile_name) {
		this.bucketName = _bucketName;
		this.prefix = _prefix;
		this.credentials_profile_name = _credentials_profile_name;
	}
	

	public void upload(List<String> files) {
		
		/*try {
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
					.withRegion(~~~)
					.build();
			
			
		}
		*/
		
	}
	
	
}
