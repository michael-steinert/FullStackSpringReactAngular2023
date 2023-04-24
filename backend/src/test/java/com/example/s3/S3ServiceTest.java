package com.example.s3;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

// Manage the Mocking (i.e. The Opening and Closing of Mocks)
@ExtendWith(MockitoExtension.class)
public class S3ServiceTest {

  private S3Service underTest;
  @Mock
  private S3Client s3Client;

  @BeforeEach
  void setUp() {
    underTest = new S3Service(s3Client);
  }

  @Test
  void canGetObject() throws IOException {
    // Given
    String bucketName = "customer";
    String key = "my-key";
    byte[] file = "Bruno".getBytes();
    // When
    underTest.putObject(bucketName, key, file);
    // Then
    ArgumentCaptor<PutObjectRequest> putObjectRequestArgumentCaptor = ArgumentCaptor.forClass(PutObjectRequest.class);
    ArgumentCaptor<RequestBody> requestBodyArgumentCaptor = ArgumentCaptor.forClass(RequestBody.class);
    verify(s3Client).putObject(putObjectRequestArgumentCaptor.capture(), requestBodyArgumentCaptor.capture());
    PutObjectRequest actualPutObjectRequest = putObjectRequestArgumentCaptor.getValue();
    assertThat(actualPutObjectRequest.bucket()).isEqualTo(bucketName);
    assertThat(actualPutObjectRequest.key()).isEqualTo(key);
    RequestBody actualRequestBody = requestBodyArgumentCaptor.getValue();
    assertThat(actualRequestBody.contentStreamProvider().newStream().readAllBytes())
        .isEqualTo(RequestBody.fromBytes(file).contentStreamProvider().newStream().readAllBytes());
  }

  @Test
  void canPutObject() throws IOException {
    // Given
    String bucketName = "customer";
    String key = "my-key";
    byte[] file = "Bruno".getBytes();
    GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(key).build();
    ResponseInputStream<GetObjectResponse> responseInputStream = mock(ResponseInputStream.class);
    when(responseInputStream.readAllBytes()).thenReturn(file);
    when(s3Client.getObject(eq(getObjectRequest))).thenReturn(responseInputStream);
    // When
    byte[] actualFile = underTest.getObject(bucketName, key);
    // Then
    assertThat(actualFile).isEqualTo(file);
  }

  @Test
  void willThrowWhenGetObject() throws IOException {
    // Given
    String bucketName = "customer";
    String key = "my-key";
    GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(key).build();
    ResponseInputStream<GetObjectResponse> responseInputStream = mock(ResponseInputStream.class);
    when(responseInputStream.readAllBytes()).thenThrow(new IOException("Object with Key %s not found".formatted(key)));
    when(s3Client.getObject(eq(getObjectRequest))).thenReturn(responseInputStream);
    // When
    // Then
    assertThatThrownBy(() -> underTest.getObject(bucketName, key))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("Object with Key %s not found".formatted(key));
  }
}
