<%@ page language="java" import="java.sql.*,com.jspsmart.upload.*"%>
<jsp:useBean id="mySmartUpload" scope="page" class="com.jspsmart.upload.SmartUpload" />

<HTML>
<BODY BGCOLOR="white">

<H1>jspSmartUpload : Sample 4</H1>
<HR>

<%

	// Variables
	int count=0;

	// Connect to the database
	Class.forName("org.gjt.mm.mysql.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql:///test");

	// SQL Request
	Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY ,ResultSet.CONCUR_UPDATABLE);
	ResultSet rs = stmt.executeQuery("SELECT * FROM TFILES WHERE ID=1");

	// if the resultset is not null
	if (rs.next()){

		// Initialization
		mySmartUpload.initialize(pageContext);

		// Upload
		mySmartUpload.upload();

		// upload file in the DB if this file is not missing
		if (!mySmartUpload.getFiles().getFile(0).isMissing()){

			try {

				rs.updateString("FILENAME",mySmartUpload.getFiles().getFile(0).getFileName());
				
				// Add the current file in the DB field
				mySmartUpload.getFiles().getFile(0).fileToField(rs,"FILE");

				// Update
				rs.updateRow();
				count++;		
				
			} catch(Exception e) {
				out.println("An error occurs : " + e.toString());				
			}			

		}

	}

	// Display the number of files uploaded
	out.println(count + " file(s) uploaded in the database.");

	rs.close();
	stmt.close();
	con.close();

%>

</BODY>
</HTML>




