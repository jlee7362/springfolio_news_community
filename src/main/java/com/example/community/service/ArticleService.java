// service/ArticleService.java
package com.example.community.service;

import com.example.community.domain.Article;
import com.example.community.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
  private final ArticleMapper mapper;
  public void write(Article a){ mapper.insert(a); }
  public Article get(Long id){ return mapper.findById(id); }
  public List<Article> list(int page, int size){
    return mapper.findAll((page-1)*size, size);
  }
  public int count(){ return mapper.countAll(); }
  public void modify(Article a){ mapper.update(a); }
  public void addHit(Long id){ mapper.addHit(id); }
}
