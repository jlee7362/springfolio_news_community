package com.example.community.mapper;
import com.example.community.domain.NewsItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface NewsMapper {
  void insert(NewsItem n);
  NewsItem findByLink(@Param("link") String link);
  List<NewsItem> findByDateRange(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
  List<Map<String,Object>> countGroupByDate(@Param("days") int days);
  List<NewsItem> latest(@Param("limit") int limit);
  NewsItem findById(@Param("id") Long id);
}
