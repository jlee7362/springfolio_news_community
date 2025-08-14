package com.example.community.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.rss")
public class AppRssProperties {
    // application.yml의 app.rss.feeds가 여기에 바인딩됨
    private List<String> feeds = new ArrayList<>();

    public List<String> getFeeds() { return feeds; }
    public void setFeeds(List<String> feeds) { this.feeds = feeds; }
}