package com.fastcampus.projectboard.service;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.type.SearchType;
import com.fastcampus.projectboard.dto.ArticleDto;
import com.fastcampus.projectboard.dto.ArticleUpdateDto;
import com.fastcampus.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
  @InjectMocks
  private ArticleService sut;

  @Mock
  private ArticleRepository articleRepository;

  @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
  @Test
  void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList(){

    Page<ArticleDto> articles=sut.searchArticles(SearchType.TITLE,"search keyword");

    assertThat(articles).isNotNull();
  }

  @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
  @Test
  void givenId_whenSearchingArticle_thenReturnsArticle(){

    ArticleDto article=sut.searchArticle(1L);

    assertThat(article).isNotNull();
  }

  @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다")
  @Test
  void givenArticleInfo_whenSavingArticle_thenSavesArticle(){
    given(articleRepository.save(any(Article.class))).willReturn(null);

    sut.saveArticle(ArticleDto.of(LocalDateTime.now(),"Uno","title","content","#java"));

    then(articleRepository).should().save(any(Article.class));
  }

  @DisplayName("게시글의 ID와 수정 정보를 입력하면, 게시글을 수정한다")
  @Test
  void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle(){
    given(articleRepository.save(any(Article.class))).willReturn(null);

    sut.updateArticle(1L, ArticleUpdateDto.of("title","content","#java"));

    then(articleRepository).should().save(any(Article.class));
  }

  @DisplayName("게시글의 ID를 입력하면, 게시글을 삭제한다")
  @Test
  void givenArticleId_whenDeletingArticle_thenDeleteArticle(){
    willDoNothing().given(articleRepository).delete(any(Article.class));

    sut.deleteArticle(1L);

    then(articleRepository).should().delete(any(Article.class));
  }
}