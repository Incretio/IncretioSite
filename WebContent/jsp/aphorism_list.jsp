<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_Ru"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Жизнь в цитатах</title>	
		<link rel="shortcut icon" href="/static/img/favicon.png"/>
		<link type="text/css" rel="stylesheet" href="/static/css/style.css" />	
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script src="/static/js/jquery.cookie.js"></script>
		<script src="https://vk.com/js/api/openapi.js?152" type="text/javascript"></script>
		<script src="/static/js/aphorism.js"></script>
		<script src="/static/js/utils.js" ></script>
		<script src="/static/js/vk_api_help.js"></script>
	</head>	
	<body>	
		<div class="layout">
			<div class="auth_block">
				<h3>Авторизация:</h3>
				<div class="vk_auth_link vk_icon link"></div>
			</div>							
			<c:forEach var="aphorism" items="${aphorismList}">
				<c:set var="wasLiked" value="${aphorism.wasLiked ? 'liked' : ''}"/>
				<div class="aphorism_block">
					<span id="${aphorism.id}" class="like_link link ${wasLiked}">
						<span class="like_icon"></span>				
						<span class="like_count">${aphorism.likeCount}</span>				
					</span>
					<div class="time_block">										
						<fmt:formatDate value="${aphorism.createdTime}" pattern="dd MMM yyyy в HH:mm:ss"/>											
					</div>
					<c:if test="${aphorism.video != null && !aphorism.video.isEmpty()}">
						<iframe src="https://www.youtube.com/embed/${aphorism.video}?rel=0&amp;showinfo=0" 
							frameborder="0" allow="autoplay; encrypted-media" allowfullscreen>
						</iframe>
					</c:if>
					<c:if test="${aphorism.image != null && !aphorism.image.isEmpty()}">
						<img class="image" src="${aphorism.image}"></img>
					</c:if>
					<div class="text_block">
						${aphorism.text}					
					</div>
					<div class="author_block">
						${aphorism.author}
					</div>
				</div>
			</c:forEach>
			
			</div>
		<script>
			vkInit();
			$(".like_link").click(function() {
				likeClick(this);
			});
			$(".vk_auth_link").click(function() {
				login();
			});
		</script>
	</body>
</html>	
	
