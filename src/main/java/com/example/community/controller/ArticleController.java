// controller/ArticleController.java
package com.example.community.controller;

import com.example.community.domain.Article;
import com.example.community.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
public class ArticleController {
  private final ArticleService service;

  @GetMapping("/list")
  public String list(@RequestParam(defaultValue="1") int page,
                     @RequestParam(defaultValue="10") int size, Model model){
    model.addAttribute("list", service.list(page, size));
    model.addAttribute("page", page);
    model.addAttribute("size", size);
    model.addAttribute("total", service.count());
    return "article/list";
  }

  @GetMapping("/detail/{id}")
  public String detail(@PathVariable Long id, Model model){
    service.addHit(id);
    model.addAttribute("a", service.get(id));
    return "article/detail";
  }

  @GetMapping("/write")
  public String writeForm(){ return "article/write"; }

  @PostMapping("/write")
  public String write(@RequestParam Long memberId,
                      @RequestParam String title,
                      @RequestParam String body){
    Article a = new Article();
    a.setMemberId(memberId);
    a.setTitle(title);
    a.setBody(body);
    service.write(a);
    return "redirect:/article/list";
  }
}
