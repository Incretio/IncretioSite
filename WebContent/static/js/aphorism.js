function likeClick (element, postId) {
		var likeCount = getLikeCount(postId);
		var child = getFirstChildElementByClassName(element, "like_count");
		//child.innerHTML = likeCount;
		var has = invertClass(element, "liked");
		if (has){
			child.innerHTML = "1";			
		} else {
			child.innerHTML = "0";
		}
	}
function getLikeCount (postId) {
	// ajax to java
	return 2;
}	