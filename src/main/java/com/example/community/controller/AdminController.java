// controller/AdminController.java
package com.example.community.controller;

import com.example.community.service.CrawlerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final CrawlerService crawlerService;

    @GetMapping("/admin/crawl-now")
    @ResponseBody
    public String crawlNow() {
        crawlerService.crawl();
        return "OK: crawl triggered";
    }
}
