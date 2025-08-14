// domain/NewsItem.java
package com.example.community.domain;
import java.util.Date;

import lombok.Data;
@Data
public class NewsItem {
  private Long id;
  private Date regDate;
  private String source;
  private String title;
  private String link;
  private Date publishedAt;
  private String summary;
  private String rawText;
}
