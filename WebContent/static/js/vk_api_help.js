var apiId = "6368146";
var vkCookieName = "vk_app_6368146";
var userIdName = "userId";

function vkInit() {
        VK.init({
                apiId: apiId
        });
}

function isLogged() {
	return hasCookie(userIdName);
}

function hasCookie(cname) {
	var cvalue = getCookie(cname);
	return (cvalue != "");
}

function getUserId() {
	return getCookie(userIdName);
}

function setUserId(newUserId) {
	$.cookie(userIdName, newUserId);
}
function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                    c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                    return c.substring(name.length, c.length);
            }
    }
    return "";
}

