<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="ru_Ru"/>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<!DOCTYPE>
<html>
	<head>
		<meta charset="utf-8">
		<title>Близкие по духу мысли и цитаты.</title>	
		<link type="text/css" rel="stylesheet" href="<c:url value="/static/css/style.css" />" />	
	<body>		
		<div class="full_page">	
			<c:forEach var="aphorism" items="${aphorismList}">
			<div class="full_block">
				<div class="like_icon" id="${aphorism.id}"></div>
				<span class="like_count">${likeCount}</span>
				<div class="time_block">										
					<fmt:formatDate value="${aphorism.createdTime}" pattern="dd MMM yyyy в HH:mm:ss"/>											
				</div>
				<div class="quote_block">
					${aphorism.text}					
				</div>
				<div class="author_block">
					<span class="author_name">${aphorism.author}</span>
				</div>
			</div>
			</c:forEach>
		</div>
		
		

		
		<script>
			var x = document.getElementsByClassName("like_icon");
			var i;
			for (i = 0; i < x.length; i++) {
				x[i].addEventListener("click", 
					function(event) {
						console.log(event.target.id);
						$.ajax({
					        type:'post',//тип запроса: get,post либо head
					        url:'LikeServlet',//url адрес файла обработчика
					        data:{'id':event.target.id},//параметры запроса
					        response:'text',//тип возвращаемого ответа text либо xml
					        success:function (data) {//возвращаемый результат от сервера
					        	console.log("ajax success")
					            console.log(data);
					        }
					    });
					}, false
				);
			}
		</script>
	</body>
</html>	
	
