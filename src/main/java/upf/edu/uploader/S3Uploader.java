package upf.edu.uploader;

import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

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
		
		AWSCredentials credentials = new BasicAWSCredentials(
				"<AWS accesskey>",
				"<AWS secretkey>"
				);
		
		//Bucket name es el que tenim guardat com a _bucket, el path es el nostre _prefix i el File("path") es la ruta completa cap al fitxer q hem de pujar
		AmazonS3 s3client = AmazonS3ClientBuilder
				.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.EU_CENTRAL_1)
				.build();
		
		s3client.putObject(<bucketname>, "path", new File("path"));
		
	}
	
	
}
