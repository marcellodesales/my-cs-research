function showStatus(text)
{
	window.status = text;
	return true;
}

function showStatys(e) {
if (document.all) {
if (event.button == 2) {
alert(message);
return false;
}
}
if (document.layers) {
if (e.which == 3) {
alert(message);
return false;
}
}
}

function clearStatus()
{
	window.status = '';
	return true;
}
