function getISO8601DateTime(dateTime) {
	var day = dateTime.getDate();
	day = (day < 10) ? "0" + day : day;
	var month = dateTime.getMonth() + 1;
	month = (month < 10) ? "0" + month : month;
	var year = dateTime.getFullYear();
	var hour = dateTime.getHours();
	hour = (hour < 10) ? "0" + hour : hour;
	var minute = dateTime.getMinutes();
	minute = (minute < 10) ? "0" + minute : minute;
	return year + "-" + month + "-" + day + "T" + hour + ":" + minute;
}