// controller/HomeController.java
package com.example.community.controller;

import com.example.community.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
  private final NewsService newsService;

  @GetMapping("/")
  public String home(Model model){
    // 최근 7일 날짜별 카운트 + 최신 12건
    model.addAttribute("byDate", newsService.countByRecentDays(7));
    model.addAttribute("latest", newsService.latest(12));
    return "home";
  }
}
