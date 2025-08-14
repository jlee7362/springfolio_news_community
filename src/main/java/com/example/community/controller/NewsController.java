// controller/NewsController.java
package com.example.community.controller;

import com.example.community.domain.NewsItem;
import com.example.community.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/news")
public class NewsController {
  private final NewsService newsService;

  @GetMapping("/list")
  public String list(@RequestParam(required=false)
                     @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date,
                     Model model){
    LocalDate target = (date==null)? LocalDate.now() : date;
    List<NewsItem> items = newsService.getByDate(target);
    model.addAttribute("date", target);
    model.addAttribute("items", items);
    return "news/list";
  }

  @GetMapping("/detail/{id}")
  public String detail(@PathVariable Long id, Model model){
    model.addAttribute("n", newsService.findById(id));
    return "news/detail";
  }
}
