<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_Ru"/>


<!DOCTYPE>
<html>
	<head>
		<meta charset="utf-8">
		<title>Близкие по духу мысли и цитаты.</title>	
		<link type="text/css" rel="stylesheet" href="/static/css/style.css" />	
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="/static/js/aphorism.js"></script>
		<script src="/static/js/utils.js" ></script>
	<body>			
		<c:forEach var="aphorism" items="${aphorismList}">
		<c:set var="wasLiked" value="${aphorism.wasLiked ? 'liked' : ''}"/>
		<div class="aphorism_block">
			<a href="javascript:void(0)" class="like_link ${wasLiked}" onclick="likeClick(this, ${aphorism.id})">
				<span class="like_icon"></span>				
				<span class="like_count">${aphorism.likeCount}</span>				
			</a>
			<div class="time_block">										
				<fmt:formatDate value="${aphorism.createdTime}" pattern="dd MMM yyyy в HH:mm:ss"/>											
			</div>
			<div class="text_block">
				${aphorism.text}					
			</div>
			<div class="author_block">
				${aphorism.author}
			</div>
		</div>
		</c:forEach>
	</body>
</html>	
	
