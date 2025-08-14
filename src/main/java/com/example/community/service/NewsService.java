// service/NewsService.java
package com.example.community.service;

import com.example.community.domain.NewsItem;
import com.example.community.mapper.NewsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NewsService {
  private final NewsMapper newsMapper;

  public List<Map<String,Object>> countByRecentDays(int days) {
    return newsMapper.countGroupByDate(days);
  }
  public List<NewsItem> getByDate(LocalDate date) {
    LocalDateTime start = date.atStartOfDay();
    LocalDateTime end = date.atTime(LocalTime.MAX);
    return newsMapper.findByDateRange(start, end);
  }
  public List<NewsItem> latest(int limit) { return newsMapper.latest(limit); }
  public NewsItem findById(Long id) { return newsMapper.findById(id); }
}
