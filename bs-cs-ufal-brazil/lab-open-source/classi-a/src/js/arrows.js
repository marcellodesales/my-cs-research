<!--
var onImg = new Array();
var offImg = new Array();

// Files and directory structures
var arrowDir = "/common/images/";

// Arrows for HTML headers within content units
var arrPrefix = arrowDir + "a_";
var arrHLName = arrowDir + "a_003366.gif";
var arrW = "9";
var arrH = "8";
var padArr = "12";

// Swap images on based on a name array
function imageOn(name) {
   if (document.images) {
    document.images[name].src = onImg[name].src
   }
}

// Swap images off based on a name array
function imageOff(name) {
   if (document.images) {
    document.images[name].src = offImg[name].src
   }
}

// Helper code to generate Image objects for content arrows
function ContentArrowInit(color,refName) {
  if (document.images) {
  	var arrName = arrPrefix + color + ".gif";
  	onImg[refName] = eval("new Image(" + arrW + "," + arrH + ")");
  	offImg[refName] = eval("new Image(" + arrW + "," + arrH + ")");
  	onImg[refName].src = arrHLName;
  	offImg[refName].src = arrName;
  }
  return true;
}

if (document.images) {
  onImg["navArrowOn"] = eval("new Image(" + navArrW + "," + navArrH + ")");
  onImg["navArrowOn"].src = navArrHLName;
  offImg["navArrowOff"] = eval("new Image(" + navArrW + "," + navArrH + ")");
  offImg["navArrowOff"].src = navArrName;
}


// -->
