package com.fastcampus.projectboard.dto;

import java.time.LocalDateTime;

public record ArticleCommentDto(
    LocalDateTime createdAt,
    String createdBy,
    String title,
    String content,
    String hashtag
) {
  public static ArticleCommentDto of(LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
    return new ArticleCommentDto(createdAt,createdBy,title,content,hashtag);
  }
}
