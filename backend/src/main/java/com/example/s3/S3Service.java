package com.example.s3;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.exception.ResourceNotFoundException;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

  private final S3Client s3Client;

  public S3Service(S3Client s3Client) {
    this.s3Client = s3Client;
  }

  public byte[] getObject(String bucketName, String key) {
    GetObjectRequest objectRequest = GetObjectRequest.builder()
        .bucket(bucketName)
        .key(key)
        .build();
    ResponseInputStream<GetObjectResponse> responseInputStream = s3Client.getObject(objectRequest);
    try {
      return responseInputStream.readAllBytes();
    } catch (IOException exception) {
      throw new ResourceNotFoundException("Object with Key %s not found".formatted(key));
    }
  }

  public void putObject(String bucketName, String key, byte[] file) {
    PutObjectRequest objectRequest = PutObjectRequest.builder()
        .bucket(bucketName)
        .key(key)
        .build();
    s3Client.putObject(objectRequest, RequestBody.fromBytes(file));
  }
}
