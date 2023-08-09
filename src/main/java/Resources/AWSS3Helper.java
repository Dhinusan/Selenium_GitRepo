package Resources;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CopyObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;


public class AWSS3Helper {

	private static final String SUFFIX = "/";
	 
	public AmazonS3 getS3Client(String awsRegion) {
		
		/*AWSCredentials credentials = new BasicAWSCredentials(
				accessKey, 
				secretKey
				);*/
		
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  //.withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(awsRegion)
				  .build();
	
		return s3client;
	}
	
	public void createBucket(String bucketName, AmazonS3 client) {
		
		client.createBucket(bucketName);
		
	}
	
	
	public void uploadToS3(String bucketName, String folderName, String fileName, File fileToUpload, AmazonS3 client) {

		client.putObject
		(
				  bucketName, 
				  folderName +  SUFFIX + fileName, 
				  fileToUpload
				);
	}
		public void createFolder(String bucketName, String folderName, AmazonS3 client) {
	    // create meta-data for your folder and set content-length to 0
	    ObjectMetadata metadata = new ObjectMetadata();
	    metadata.setContentLength(0);

	    // create empty content
	    InputStream emptyContent = new ByteArrayInputStream(new byte[0]);

	    // create a PutObjectRequest passing the folder name suffixed by /
	    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
	                folderName + SUFFIX, emptyContent, metadata);

	    // send request to S3 to create folder
	    client.putObject(putObjectRequest);
	}

	public void createRegionFolder(String bucketName, String folderName, AmazonS3 client) {
	    // create meta-data for your folder and set content-length to 0
	    ObjectMetadata metadata = new ObjectMetadata();
	    metadata.setContentLength(0);

	    // create empty content
	    InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        //need to create one more folder for region now for the region specific -- 2681-new region onboard
	    // create a PutObjectRequest passing the folder name suffixed by /
	    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
	                folderName + SUFFIX, emptyContent, metadata);

	    // send request to S3 to create folder
	    client.putObject(putObjectRequest);
	}
	/*public void createRegionProjectFolder(String bucketName, String folderName, AmazonS3 client,String regionName) {
	    // create meta-data for your folder and set content-length to 0
	    ObjectMetadata metadata = new ObjectMetadata();
	    metadata.setContentLength(0);

	    // create empty content
	    InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        //need to create one more folder for region now for the region specific -- 2681-new region onboard
	    // create a PutObjectRequest passing the folder name suffixed by /
	    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
	    		regionName + SUFFIX+folderName + SUFFIX, emptyContent, metadata);

	    // send request to S3 to create folder
	    client.putObject(putObjectRequest);
	}*/
	
	public void deleteBucket(String bucketName, AmazonS3 client) {
		System.out.println(" - removing objects from bucket");
		ObjectListing object_listing = client.listObjects(bucketName);
		while (true) {
		    for (Iterator<?> iterator =
		         object_listing.getObjectSummaries().iterator();
		         iterator.hasNext(); ) {
		        S3ObjectSummary summary = (S3ObjectSummary) iterator.next();
		        client.deleteObject(bucketName, summary.getKey());
		    }

		    // more object_listing to retrieve?
		    if (object_listing.isTruncated()) {
		        object_listing = client.listNextBatchOfObjects(object_listing);
		    } else {
		        break;
		    }
		}
		
		client.deleteBucket(bucketName);

	}
	
	
	public void moveFileFromOneBucketToAnotherBucket(final String sourceBucket,
			final String destinationBucket, AmazonS3 client) {
		
		copyFileFromOneBucketToAnotherBucket(sourceBucket,
				destinationBucket, client);
		
		ObjectListing objectListing = client.listObjects(sourceBucket);
		for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
			deleteFileFromS3Bucket(sourceBucket, summary.getKey(), client);
		}
	}
	
	public void copyFileFromOneBucketToAnotherBucket(final String sourceBucket,
			final String destinationBucket, AmazonS3 client) {

			ObjectListing objectListing = client.listObjects(sourceBucket);
			for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
				String destinationFileName = summary.getKey();
				CopyObjectRequest copyObjectRequest = new CopyObjectRequest(sourceBucket, summary.getKey(),
						destinationBucket, destinationFileName.toString());
				client.copyObject(copyObjectRequest);
			}
	}
	
	
	public void deleteFileFromS3Bucket(final String bucketName, final String fileKeyName,
			AmazonS3 client) {

			for (S3ObjectSummary file : client.listObjects(bucketName, fileKeyName).getObjectSummaries()) {
				client.deleteObject(bucketName, file.getKey());
			}
	}
	
	
	public List<String> getListOfFileInS3Bucket(final String bucketName, final String keyName, AmazonS3 client) {
		List<String> contentList = new ArrayList<String>();
		String fileName = null;

		
			if(keyName == null || keyName.isEmpty()) {
				for (S3ObjectSummary objectSummary : client.listObjects(bucketName).getObjectSummaries()){
					fileName = objectSummary.getKey();
					contentList.add(fileName);
				}
			}else {				
				for (S3ObjectSummary objectSummary : client.listObjects(bucketName, keyName).getObjectSummaries()){
					fileName = objectSummary.getKey();
					contentList.add(fileName);
				}
			}			
		return contentList;
	}
}
