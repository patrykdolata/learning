
function getCookie(cname) {
	var name = cname + "=";
	var decodedCookie = decodeURIComponent(document.cookie);
	var ca = decodedCookie.split(';');
	for(var i = 0; i < ca.length; i++) {
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

function checkCookie() {
	var lastWatched=getCookie("selected");
	if (lastWatched != "") {
		moveSlideTo(lastWatched);
	}
	setTimeout("scrollOne()", 1000)
}

function scrollOne(){
	 // horizontal and vertical scroll targets
	//window.scroll(0,100);
	var e = document.getElementById("scroll");
	if (!!e && e.scrollIntoView) {
		e.scrollIntoView();
	}
}

function moveSlideTo(date){
	document.getElementById('t-22/12/2014').classList.remove("selected");
	document.getElementById('e-22/12/2014').classList.remove("selected");
	document.getElementById('e-' + date).classList.add("selected");
	var lastTime = document.getElementById('t-' + date);
	lastTime.classList.add("selected");
	//var prev = getPreviousSiblings(lastTime.parentNode, 'li');
	//for (var i = 0, len = prev.length; i < len; i++) {
	//  prev[i].firstChild.classList.add("older-event");
	//}
}
function matches(elem, filter) {
  if (elem && elem.nodeType === 1) {
    if (filter) {
      return elem.matches(filter);
    }
    return true;
  }
  return false;
}

// this will start from the current element and get all the
// previous siblings
function getPreviousSiblings(elem, filter) {
  var sibs = [];
  while (elem = elem.previousSibling) {
    if (matches(elem, filter)) {
      sibs.push(elem);
    }
  }
  return sibs;
}