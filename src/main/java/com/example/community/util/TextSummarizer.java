package com.example.community.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class TextSummarizer {
  private static final Pattern SENTENCE_SPLIT = Pattern.compile("(?<=[.!?])\\s+");
  private static final Pattern TOKEN = Pattern.compile("[^a-zA-Z가-힣0-9]+");

  public static String summarize(String text, int maxSentences) {
    if (text == null || text.isEmpty()) return "";
    String[] sentences = SENTENCE_SPLIT.split(text.trim());
    if (sentences.length <= maxSentences) return text;

    Map<String,Integer> freq = new HashMap<>();
    for (String s : sentences) {
      for (String w : TOKEN.matcher(s.toLowerCase()).replaceAll(" ").split("\\s+")) {
        if (w.length() < 2) continue;
        freq.put(w, freq.getOrDefault(w, 0) + 1);
      }
    }
    // 점수 = 단어 빈도 합
    double[] score = new double[sentences.length];
    for (int i=0;i<sentences.length;i++) {
      double sc = 0;
      for (String w : TOKEN.matcher(sentences[i].toLowerCase()).replaceAll(" ").split("\\s+")) {
        sc += freq.getOrDefault(w, 0);
      }
      // 위치 가중(앞문장 소폭 가산)
      score[i] = sc + Math.max(0, sentences.length - i) * 0.05;
    }
    // 상위 k문장 원래 순서 유지
    Integer[] idx = new Integer[sentences.length];
    for (int i=0;i<idx.length;i++) idx[i]=i;
    Arrays.sort(idx, (a,b)->Double.compare(score[b], score[a]));

    Set<Integer> pick = new HashSet<>();
    for (int i=0;i<Math.min(maxSentences, idx.length); i++) pick.add(idx[i]);

    StringBuilder sb = new StringBuilder();
    for (int i=0;i<sentences.length;i++) if (pick.contains(i)) sb.append(sentences[i]).append(" ");
    return sb.toString().trim();
  }
}