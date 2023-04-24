package com.example.s3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public class FakeS3 implements S3Client {

  private static final String PATH = System.getProperty("user.home") + "\\FakeS3";

  @Override
  public String serviceName() {
    return "FakeS3";
  }

  @Override
  public void close() {
    throw new UnsupportedOperationException("Unimplemented Method 'close()'");
  }

  @Override
  public ResponseInputStream<GetObjectResponse> getObject(GetObjectRequest getObjectRequest)
      throws AwsServiceException, SdkClientException {
    try (FileInputStream fileInputStream = new FileInputStream(
        String.format("%s/%s/%s", PATH, getObjectRequest.bucket(), getObjectRequest.key()))) {
      return new ResponseInputStream<GetObjectResponse>(GetObjectResponse.builder().build(), fileInputStream);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public PutObjectResponse putObject(PutObjectRequest putObjectRequest, RequestBody requestBody)
      throws AwsServiceException, SdkClientException {
    InputStream inputStream = requestBody.contentStreamProvider().newStream();
    try {
      byte[] file = IOUtils.toByteArray(inputStream);
      FileUtils.writeByteArrayToFile(
          new File(String.format("%s/%s/%s", PATH, putObjectRequest.bucket(), putObjectRequest.key())), file);
      return PutObjectResponse.builder().build();
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }
}
