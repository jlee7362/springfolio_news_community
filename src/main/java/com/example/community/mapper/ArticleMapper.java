package com.example.community.mapper;
import com.example.community.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ArticleMapper {
  void insert(Article a);
  Article findById(@Param("id") Long id);
  List<Article> findAll(@Param("offset") int offset, @Param("limit") int limit);
  int countAll();
  void update(Article a);
  void addHit(@Param("id") Long id);
}
