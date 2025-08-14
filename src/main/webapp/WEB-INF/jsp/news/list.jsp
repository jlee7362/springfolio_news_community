<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../common/head.jspf" %>
<h1 class="text-2xl font-bold mb-4">${date} 뉴스</h1>
<c:choose>
  <c:when test="${empty items}">
    <div class="alert">해당 날짜 뉴스가 없습니다.</div>
  </c:when>
  <c:otherwise>
    <div class="space-y-3">
      <c:forEach items="${items}" var="n">
        <div class="card bg-base-100 shadow">
          <div class="card-body">
            <a class="text-lg font-semibold hover:underline" href="/news/detail/${n.id}">${n.title}</a>
            <div class="text-sm opacity-60">${n.source}</div>
            <p class="text-sm">${n.summary}</p>
            <a class="link" target="_blank" href="${n.link}">원문</a>
          </div>
        </div>
      </c:forEach>
    </div>
  </c:otherwise>
</c:choose>
<%@ include file="../common/foot.jspf" %>
