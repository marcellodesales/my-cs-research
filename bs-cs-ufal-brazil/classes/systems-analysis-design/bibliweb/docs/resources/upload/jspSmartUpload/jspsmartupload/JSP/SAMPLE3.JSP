<%@ page language="java" import="com.jspsmart.upload.*"%>
<jsp:useBean id="mySmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" />

<HTML>
<BODY BGCOLOR="white">

<H1>jspSmartUpload : Sample 3</H1>
<HR>

<%
	
	// Variables
	   int count=0;        

	// Initialization
		mySmartUpload.initialize(pageContext);

	// Only allow txt or htm files
	   mySmartUpload.setAllowedFilesList("htm,html,txt,,");

	// DeniedFilesList can also be used :
    // mySmartUpload.setDeniedFilesList("exe,bat,jsp");

	// Deny physical path
	// mySmartUpload.setDenyPhysicalPath(true);

	// Only allow files smaller than 50000 bytes
	// mySmartUpload.setMaxFileSize(50000);

	// Deny upload if the total fila size is greater than 200000 bytes
	// mySmartUpload.setTotalMaxFileSize(200000);

	// Upload	
	   mySmartUpload.upload();

	// Save the files with their original names in a virtual path of the web server
	   try {

		 count = mySmartUpload.save("/upload", mySmartUpload.SAVE_VIRTUAL);
	  
	   } catch (Exception e){

	 	 out.println("<b>Wrong selection : </b>" + e.toString());

	   }
   		
	// Display the number of files uploaded
	   out.println(count + " file(s) uploaded.");

%>
</BODY>
</HTML>