// config/WebMvcConfig.java
package com.example.community.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  // 필요 시 인터셉터 등록 (로그인 체크 등)
  // @Override
  // public void addInterceptors(InterceptorRegistry registry) {
  //   registry.addInterceptor(new BeforeActionInterceptor())
  //           .addPathPatterns("/**");
  // }
}
