function getFirstChildElementByClassName (parentElement, className) {
		return parentElement.getElementsByClassName(className)[0];
	}
function invertClass (element, className) {
	if (hasClass(element, className)) {
		removeClass(element, className);
		return false;
	} else {
		elent.addClass(element, className);
		return true;
	}
}
function hasClass (element, className) {
	return element.classList.contains(className);
}
function removeClass (element, className) {
	element.classList.remove(className);
}
function addClass (element, className) {
	element.classList.add(className);
}
function getISO8601DateTime(currentDateTime) {
	var day = currentDateTime.getDate();
	day = (day < 10) ? "0" + day : day;
	var month = currentDateTime.getMonth() + 1;
	month = (month < 10) ? "0" + month : month;
	var year = currentDateTime.getFullYear();
	var hour = currentDateTime.getHours();
	hour = (hour < 10) ? "0" + hour : hour;
	var minute = currentDateTime.getMinutes();
	minute = (minute < 10) ? "0" + minute : minute;
	return year + "-" + month + "-" + day + "T" + hour + ":" + minute;
}