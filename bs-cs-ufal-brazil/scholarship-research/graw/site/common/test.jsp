<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Vector" %>
<%@ page import="br.ufal.graw.Link" %>
<%@ page import="br.ufal.graw.DatabaseLayer" %>
<% 
		DatabaseLayer data = new DatabaseLayer();
%>
<html>
<head>
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body bgcolor="#FFFFFF" text="#000000">

<%
	int offset = (request.getParameter("offset") == null) ? 0 : Integer.parseInt(request.getParameter("offset"));
	if (offset < 0) offset = 0;
    //    Set limit.  $limit = Max number of results per 'page'
    //    Set totalrows = total number of rows that unlimited query would return
	int limit = 10;

	String query = "SELECT * FROM link LIMIT "+offset+","+limit;
	out.println(query);
	Vector result = data.query(query);

	int totalrows = result.size();	
	Enumeration e = result.elements();
	while (e.hasMoreElements()){
		Hashtable ids = (Hashtable)e.nextElement();
		out.println("<BR>"+ids.get("linkID"));
	}
	
    out.println("<BR><BR>");

	// Begin Prev/Next Links
    // Don't display PREV link if on first page
	int prevoffset;
    if (offset != 0) {
        prevoffset = offset - limit;
		out.println("<a href='"+request.getRequestURI()+"?offset="+prevoffset+"'>Anterior</a>&nbsp;&nbsp;");
    } else out.println("Anterior&nbsp;");
	
	int pages = Math.abs(totalrows/limit)+1;
	out.println("<BR><BR>Pages="+pages);
    // Check to see if current page is last page
	int newoffset = 0;
    if (!(((offset/limit)) == pages) && pages != 1) {	
        // Not on the last page yet, so display a NEXT Link
        newoffset = offset + limit;
        out.println("<a href='"+request.getRequestURI()+"?offset="+newoffset+"'>Próximo</a>");
    } else out.println("&nbsp;Próximo");
%>
</body>
</html>
