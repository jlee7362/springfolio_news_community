// service/CrawlerService.java
package com.example.community.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.community.domain.NewsItem;
import com.example.community.mapper.NewsMapper;
import com.example.community.util.TextSummarizer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrawlerService {

  private final NewsMapper newsMapper;
  private final com.example.community.config.AppRssProperties rssProps; // 주입

  private static final ZoneId KST = ZoneId.of("Asia/Seoul");

//10초 후 첫 실행, 이후 2시간마다
@Scheduled(initialDelay = 10_000, fixedDelay = 7_200_000)
public void crawl() {
    final java.util.List<String> feeds = rssProps.getFeeds();
    System.out.println("feeds = " + feeds); // 진단용

    if (feeds == null || feeds.isEmpty()) {
      System.out.println("⚠ RSS feeds가 비어있음. application.yml 확인 필요.");
      return;
    }

    for (String url : feeds) {
      try {
        Document rss = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(12000).get();
        for (org.jsoup.nodes.Element item : rss.select("item")) {
          String title = item.selectFirst("title") != null ? item.selectFirst("title").text() : "";
          String link  = item.selectFirst("link")  != null ? item.selectFirst("link").text()  : "";
          String pub   = item.selectFirst("pubDate") != null ? item.selectFirst("pubDate").text() : null;

          if (title.isBlank() || link.isBlank()) continue;
          if (newsMapper.findByLink(link) != null) continue;

          String rawText = fetchMainText(link);
          String summary = TextSummarizer.summarize(
              (rawText == null || rawText.trim().isEmpty()) ? title : rawText, 3);

          LocalDateTime publishedAt = LocalDateTime.now(KST);
          try {
            DateTimeFormatter fmt = DateTimeFormatter.RFC_1123_DATE_TIME;
            ZonedDateTime zdt = ZonedDateTime.parse(pub, fmt);
            publishedAt = zdt.withZoneSameInstant(KST).toLocalDateTime();
          } catch (Exception ignored) {}

          Date publishedAtDate = Date.from(publishedAt.atZone(KST).toInstant());
          
          NewsItem n = new NewsItem();
          n.setSource(hostFrom(link));
          n.setTitle(title);
          n.setLink(link);
          n.setPublishedAt(publishedAtDate);
          n.setRawText(rawText);
          n.setSummary((summary == null || summary.isEmpty()) ? title : summary);

          newsMapper.insert(n);
        }
      } catch (Exception e) {
        // TODO: 로깅
      }
    }
  }

  private String hostFrom(String link) {
    try {
      java.net.URI u = new java.net.URI(link);
      String host = u.getHost();
      return host != null ? host.replace("www.", "") : "unknown";
    } catch (Exception e) { return "unknown"; }
  }

  private String fetchMainText(String link) {
    try {
      Document doc = Jsoup.connect(link).userAgent("Mozilla/5.0").timeout(12000).get();
      Elements candidates = doc.select("article, #article, .article-body, .story-content, .news, .post-content, .article__content");
      String text = candidates.isEmpty() ? doc.body().text() : candidates.text();
      if (text.length() > 4000) text = text.substring(0, 4000);
      return text;
    } catch (Exception e) { return ""; }
  }
}