<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>		
		<meta charset="utf-8">	
		<link rel="stylesheet" type="text/css" href="/static/css/aphorism_add.css">
		<script src="/static/js/utils.js"></script>
		<script>
			window.onload = function setDateTimeToNow() {
				var dateTime = getISO8601DateTime(new Date());			
				document.getElementById("createdTime").value = dateTime;
			}
		</script>
	</head>
	<body>		
		<form action="aphorism_add" method="post" enctype="multipart/form-data">
			<div class="table">
				<div class="row">
					<div class="cell">
						<label for="createdTime">Дата добавления:</label>
					</div>
					<div class="cell">
						<input id="createdTime" name="createdTime" type="datetime-local">
					</div>	
				</div>		
				<div class="row">
					<div class="cell">
						<label for="video">Код видео YouTube:</label>
					</div>	
					<div class="cell">
						<input id="video" type="text" name="video"></input>	
					</div>	
				</div>
				<div class="row">
					<div class="cell">
						<label for="image">Url изображения:</label>
					</div>	
					<div class="cell">
						<input id="image" type="text" name="image" maxlength="500"></input>	
					</div>	
				</div>	
				<div class="row">
					<div class="cell">
						<label for="user_image">Загрузить изображение:</label>
					</div>	
					<div class="cell">
						<input id="user_image" type="file" name="user_image"></input>	
					</div>	
				</div>	
				<div class="row">
					<div class="cell">
						<label for="text">Текст:</label>
					</div>	
					<div class="cell">
						<textarea id="text" name="text"></textarea>	
					</div>	
				</div>
				<div class="row">
					<div class="cell">
						<label for="author">Автор:</label>
					</div>	
					<div class="cell">
						<input id="author" type="text" name="author">
					</div>	
				</div>
				<div class="row">
					<div></div>
					<div class="cell">
						<input type="submit">
					</div>	
				</div>
			</div>
		</form>		
	</body>		
</html>
