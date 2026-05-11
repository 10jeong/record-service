package com.yeoljeong.tripmate.record.infrastructure;

import com.yeoljeong.tripmate.exception.BusinessException;
import com.yeoljeong.tripmate.record.application.client.StorageClient;
import com.yeoljeong.tripmate.record.domain.exception.RecordErrorCode;
import io.awspring.cloud.s3.S3Exception;
import io.awspring.cloud.s3.S3Resource;
import io.awspring.cloud.s3.S3Template;
import java.io.IOException;
import java.io.InputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class AwsS3BucketClient implements StorageClient {

  private final S3Template s3Template;

  @Value("${spring.cloud.aws.s3.bucket}")
  private String bucketName;

  @Override
  public String upload(MultipartFile file, String fileName) {
    if (file==null || file.isEmpty()) {
      throw new BusinessException(RecordErrorCode.IMAGE_EMPTY_ERROR);
    }
    try
        (InputStream is = file.getInputStream()) {
      S3Resource resource = s3Template.upload(bucketName, fileName, is);
      return resource.getURL().toString();
    } catch (IOException | S3Exception e) {
      throw new BusinessException(RecordErrorCode.IMAGE_UPLOAD_ERROR);
    }
  }

  @Override
  public void delete(String fileName) {
    try {
      s3Template.deleteObject(bucketName, fileName);
    } catch (S3Exception e) {
      throw new BusinessException(RecordErrorCode.IMAGE_NOT_FOUND);
    }
  }
}
