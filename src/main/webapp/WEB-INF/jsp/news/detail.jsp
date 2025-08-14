<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../common/head.jspf" %>
<h1 class="text-2xl font-bold mb-2">${n.title}</h1>
<div class="opacity-60 mb-4">
  ${n.source} · <fmt:formatDate value="${n.publishedAt}" pattern="yyyy-MM-dd HH:mm"/>
</div>
<p class="mb-4">${n.summary}</p>
<pre class="whitespace-pre-wrap bg-base-100 p-4 rounded">${n.rawText}</pre>
<a class="btn btn-primary mt-4" href="${n.link}" target="_blank">원문 보기</a>
<%@ include file="../common/foot.jspf" %>
