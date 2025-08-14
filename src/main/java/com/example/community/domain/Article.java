package com.example.community.domain;
import java.util.Date;

import lombok.Data;

@Data
public class Article {
  private Long id;
  private Date regDate;
  private Date updateDate;
  private Long memberId;
  private String title;
  private String body;
  private Integer hit;
}