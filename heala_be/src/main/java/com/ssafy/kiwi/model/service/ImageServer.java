package com.ssafy.kiwi.model.service;

import java.io.IOException;

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
    	s3Client.putObject(new PutObjectRequest(bucket, file.getOriginalFilename(), file.getInputStream(), null)
    			.withCannedAcl(CannedAccessControlList.PublicRead));
    	return s3Client.getUrl(bucket, file.getOriginalFilename()).toString();
	}
    
    
    
    
}