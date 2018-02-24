function likeClick (element) {
	var postId = element.id;
	loginInVkAndSetLike(element, postId);
}
	
function setLikeAndUpdateCount (element, postId, userId) {
	console.log("getLikeCount has postId = " + postId);
	$.ajax({
	    type:'post',
	    url:'aphorism_like',
	    data:{'id':postId, 'userId':userId}
	}).done(data => {
		element.querySelector(".like_count").innerHTML = data.likeCount;
		if (data.userLiked) {
			$(element).addClass("liked");
    	} else {
        	$(element).removeClass("liked");
    	}
	});
}	

function loginInVkAndSetLike(element, postId) {
    if (isLogged()) {
            setLikeAndUpdateCount(element, postId, getUserId());
            return;
    }
    VK.Auth.login(function(event) {
            if (event.status == "connected") {
            	setUserId(event.session.mid);
                setLikeAndUpdateCount(element, postId, event.session.mid);
                refreshLikeIcons(event.session.mid);
            }
    });
}

function login() {
    if (isLogged()) {
        return;
    }
    VK.Auth.login(function(event) {    	
        if (event.status == "connected") {
        	setUserId(event.session.mid);
        	refreshLikeIcons(event.session.mid);
        }
    });
}

function refreshLikeIcons(userId) {
	if (userId === "undefined"){
		return;
	}
    $.ajax({
            type: 'get',
            url: 'aphorism_get_my_likes',
            data: {'userId':userId}
    }).done(likedList => {
            updateAphorismsMyLiked(likedList);
    });
}

function updateAphorismsMyLiked(likedList) {
    var likeLinkElements = document.querySelectorAll(".like_link");
    var i;
    for (i = 0; i < likeLinkElements.length; i++) {
            var currentElement = likeLinkElements[i];
            var currentId = currentElement.id;
            if (likedList.indexOf(currentId) >= 0) {
                $(currentElement).addClass("liked");
            } else {
                $(currentElement).removeClass("liked");
            }
    }
}
