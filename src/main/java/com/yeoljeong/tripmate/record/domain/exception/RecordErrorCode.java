package com.yeoljeong.tripmate.record.domain.exception;

import com.yeoljeong.tripmate.exception.constants.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum RecordErrorCode implements ErrorCode {
  IMAGE_EMPTY_ERROR(HttpStatus.BAD_REQUEST, "이미지 파일이 비어 있습니다."),
  IMAGE_UPLOAD_ERROR(HttpStatus.CONFLICT, "이미지 업로드 중 에러가 발생하였습니다."),
  IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지 파일을 찾을 수 없습니다."),
  FEED_NOT_FOUND(HttpStatus.NOT_FOUND, "피드를 찾을 수 없습니다."),
  FEED_NOT_ACCESSIBLE(HttpStatus.FORBIDDEN, "피드에 접근할 수 있는 권한이 없습니다.");
  private final HttpStatus status;
  private final String message;

  @Override
  public int getCode() {
    return this.status.value();
  }
}
