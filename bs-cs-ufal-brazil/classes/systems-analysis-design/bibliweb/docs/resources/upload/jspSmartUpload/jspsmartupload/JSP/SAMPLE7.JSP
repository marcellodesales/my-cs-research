<%@ page language="java" import="java.sql.*,com.jspsmart.upload.*"%><jsp:useBean id="mySmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" /><%


	// Connect to the database
	Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql:///test");

	// SQL Request
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery("SELECT * FROM TFILES");

	// if the resultset is not null
	if (rs.next()){

		// Initialization
		mySmartUpload.initialize(pageContext);
	
		// Download field
		mySmartUpload.downloadField(rs,"FILE","application/x-msdownload", "sample7.txt");

		// Field To File
		// mySmartUpload.fieldToFile(rs,"FILE","c:\\temp\\sample7.txt");

	}

	rs.close();
	stmt.close();
	con.close();

%>