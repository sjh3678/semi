<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="text-center">
	<ul class="pagination pagination-sm">

	<%-- 첫 페이지로 이동 --%>
	<c:if test="${paging.curPage ne 1 }">
		<button><a href="/product/display">&larr; 처음</a></button>	
	</c:if>
	
	<%-- 이전 페이징 리스트로 이동 --%>
	<c:choose>
	<c:when test="${paging.startPage ne 1 }">
		<button><a href="/product/display?curPage=${paging.startPage - paging.pageCount }">&laquo;</a></button>
	</c:when>
	<c:when test="${paging.startPage eq 1 }">
		<button class="disabled"><a>&laquo;</a></button>
	</c:when>
	</c:choose>
	
	<%-- 이전 페이지로 가기 --%>
	<c:if test="${paging.curPage > 1 }">
		<button><a href="/product/display?curPage=${paging.curPage - 1 }">&lt;</a></button>
	</c:if>
	
	
	
	
	<%-- 페이징 리스트 --%>
	<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="i">
	<c:if test="${paging.curPage eq i }">
		<button class="active"><a href="/product/display?curPage=${i }">${i }</a></button>
	</c:if>
	<c:if test="${paging.curPage ne i }">
		<button><a href="/product/display?curPage=${i }">${i }</a></button>
	</c:if>
	</c:forEach>
	

	
	
	<%-- 다음 페이지로 가기 --%>
	<c:if test="${paging.curPage < paging.totalPage }">
		<button><a href="/product/display?curPage=${paging.curPage + 1 }">&gt;</a></button>
	</c:if>
	
	<%-- 다음 페이징 리스트로 이동 --%>
	<c:choose>
	<c:when test="${paging.endPage ne paging.totalPage }">
		<button><a href="/product/display?curPage=${paging.startPage + paging.pageCount }">&raquo;</a></button>
	</c:when>
	<c:when test="${paging.endPage eq paging.totalPage }">
		<button class="disabled"><a>&raquo;</a></button>
	</c:when>
	</c:choose>

	<%-- 끝 페이지로 이동 --%>
	<c:if test="${paging.curPage ne paging.totalPage }">
		<button><a href="/product/display?curPage=${paging.totalPage }">끝 &rarr;</a></button>	
	</c:if>
	
	</ul>
</div>
















