package com.fastcampus.projectboard.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingFields.class)
@MappedSuperclass
public class AuditingFields {
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdAt;  // 생성일시

  @CreatedBy
  @Column(nullable = false, length = 100)
  String createdBy; // 생성자

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  @LastModifiedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime modifiedAt; // 수정일

  @LastModifiedBy
  @Column(nullable = false, length = 100)
  String modifiedBy; // 수정자
}
