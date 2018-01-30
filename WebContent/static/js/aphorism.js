function likeClick (element, postId) {
	getLikeCount(element, postId);
	$(element).toggleClass('liked');
}
	
function getLikeCount (element, postId) {
	console.log("getLikeCount has postId = " + postId);
	$.ajax({
	    type:'post',
	    url:'aphorism_like',
	    data:{'id':postId}
	}).done(data => {
		console.log(data);
		element.querySelector(".like_count").innerHTML = data;
	});
}	