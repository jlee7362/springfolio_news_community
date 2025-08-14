<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../common/head.jspf" %>
<h1 class="text-2xl font-bold mb-4">게시판</h1>
<a class="btn btn-primary mb-3" href="/article/write">글쓰기</a>
<table class="table">
  <thead><tr><th>ID</th><th>제목</th><th>작성일</th></tr></thead>
  <tbody>
    <c:forEach items="${list}" var="a">
      <tr>
        <td>${a.id}</td>
        <td><a class="link" href="/article/detail/${a.id}">${a.title}</a></td>
        <td>${a.regDate}</td>
      </tr>
    </c:forEach>
  </tbody>
</table>
<%@ include file="../common/foot.jspf" %>
