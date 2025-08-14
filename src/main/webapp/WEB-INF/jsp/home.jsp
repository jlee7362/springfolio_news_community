<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="common/head.jspf" %>
<fmt:setTimeZone value="Asia/Seoul" />
<h1 class="text-2xl font-bold mb-4">오늘의 뉴스 요약 & 최근 아카이브</h1>

<div class="grid md:grid-cols-3 gap-4">
  <div class="md:col-span-2">
    <h2 class="text-xl font-semibold mb-2">최신 뉴스 12</h2>
    <c:if test="${not empty latest }">
    <div class="grid md:grid-cols-2 gap-3">
      <c:forEach items="${latest}" var="n">
        <a class="card bg-base-100 shadow hover:shadow-md transition" href="/news/detail/${n.id}">
          <div class="card-body">
           <div class="text-sm opacity-60">
              ${n.source}·<fmt:formatDate value="${n.publishedAt}" pattern="yyyy-MM-dd HH:mm"/>
            </div>
            <div class="font-semibold">${n.title}</div>
            <div class="text-sm line-clamp-3">${n.summary}</div>
          </div>
        </a>
      </c:forEach>
    </div>
    </c:if>
  </div>

  <div>
    <h2 class="text-xl font-semibold mb-2">날짜별 개수(최근 7일)</h2>
    <ul class="menu bg-base-100 rounded-box">
      <c:forEach items="${byDate}" var="day">
        <li>
          <a href="/news/list?date=${day['d']}">
            <span>${day['d']}</span>
            <span class="badge">${day['cnt']}</span>
          </a>
        </li>
      </c:forEach>
    </ul>
  </div>
</div>

<%@ include file="common/foot.jspf" %>