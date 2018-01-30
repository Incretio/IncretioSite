function likeClick1(element, postId) {
	
	Concurrent.Thread.create(function (){
		var likeCount = getLikeCount(postId);
		console.log("likeClick has likeCount = " + likeCount);
		var child = getFirstChildElementByClassName(element, "like_count");
		child.innerHTML = String(likeCount);
	});
	//setTimeout(like, 0);
}

function likeClick (event, element, postId) {
	event.preventDefault();		
	getLikeCount(element, postId);
	$(element).toggleClass('liked');
}
	
function getLikeCount (element, postId) {
	console.log("getLikeCount has postId = " + postId);
	$.ajax({
	    type:'post',//тип запроса: get,post либо head
	    url:'aphorism_like',//url адрес файла обработчика
	    data:{'id':postId}//параметры запроса
	}).done(data => {
		console.log(data);
		var child = getFirstChildElementByClassName(element, "like_count");
		child.innerHTML = data;
	});
}	