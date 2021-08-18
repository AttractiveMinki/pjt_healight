package com.ssafy.kiwi.model.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import lombok.NoArgsConstructor;


@Service
@NoArgsConstructor
public class ImageServer {
	
	private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey; // 엑세스 키

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey; // 보안 엑세스 키

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;
	
    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }
    
    public String registerImageIntoServer(MultipartFile file) throws IOException {
  
    	String fileName = makeFileName();
    	s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
    			.withCannedAcl(CannedAccessControlList.PublicRead));
    	return fileName;
	}
    
    public String makeFileName() {
    	SimpleDateFormat format = new SimpleDateFormat ("yyyyMMdd/HHmmss");
    	Date currentTime = new Date();
    	String time = format.format(currentTime);
    	double dValue = Math.random();
    	return time+dValue;
    }

	public String registerKiwiImageIntoServer(MultipartFile file) throws IOException {
		
		String name = file.getOriginalFilename();
		int point = name.indexOf(".");
		String newName = "admin/" + name.substring(0, point);
		
    	s3Client.putObject(new PutObjectRequest(bucket, newName, file.getInputStream(), null)
    			.withCannedAcl(CannedAccessControlList.PublicRead));
    	return newName;
	}
    
    
}