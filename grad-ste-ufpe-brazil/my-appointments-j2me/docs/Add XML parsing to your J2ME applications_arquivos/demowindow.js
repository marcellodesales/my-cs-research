// Adapted from Danny Goodman's
// "JavaScript Bible, 3rd Edition" (www.dannyg.com) 
// We have to invoke window.open twice for Netscape 2, Unix and Mac-based machines
// demoURL comes from JavaScript variable sourced from the XML file (url attribute of the demourl element)
function makeDemoWindow() {
	var demoWindow = window.open(demoURL,"","resizable,scrollbars")
	if (navigator.appVersion.charAt(0) == "2" && navigator.appName == "Netscape") {
		demoWindow = window.open(demoURL,"","resizable,scrollbars")
		}
	}