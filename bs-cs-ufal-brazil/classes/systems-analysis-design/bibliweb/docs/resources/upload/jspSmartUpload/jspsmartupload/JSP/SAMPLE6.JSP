<%@ page language="java" import="com.jspsmart.upload.*"%><jsp:useBean id="mySmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" /><%
	
	// Initialization
	mySmartUpload.initialize(pageContext);
	
	// Download file
	mySmartUpload.downloadFile("/upload/sample.zip");
	
	// With a physical path
	// mySmartUpload.downloadFile("c:\\temp\\sample.zip")
	
	// With options
	// mySmartUpload.downloadFile("/upload/sample.zip","application/x-zip-compressed","downloaded.zip")

%>