<!--
/*--------------------------------------------
	Detect Browser
--------------------------------------------*/
          NS4 = (document.layers);
          IE4 = (document.all);
          ver4 = (NS4 || IE4);
          isMac = (navigator.appVersion.indexOf("Mac") != -1);
          isMenu = (NS4 || (IE4 && !isMac));
          function popUp(){return};
          function popDown(){return};
          if (!ver4) event = null;
//-->