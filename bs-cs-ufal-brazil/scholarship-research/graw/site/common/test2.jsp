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
	int offset = Integer.parseInt(request.getParameter("offset"));
	if (offset < 0) offset = 0;

    //    Set limit.  $limit = Max number of results per 'page'
    //    Set totalrows = total number of rows that unlimited query would return

	int limit = 10;
    // Set 'begin' and 'end' to record range of the current page
    int begin = offset + 1;
    int endd  = begin + (limit-1);

	String query = "SELECT * FROM link LIMIT "+offset+","+limit;
	out.println(query);

	Vector result = data.query(query);
	int totalrows = result.size();
    if (endd > totalrows) endd = totalrows;
	out.println("<BR><BR>Total:"+totalrows+" / Mostrando de: "+begin+" a "+endd+"<BR><BR>");
	
			Enumeration e = result.elements();
			while (e.hasMoreElements()){
				Hashtable ids = (Hashtable)e.nextElement();
				out.println(ids.get("linkID")+"<BR>");
			}
    
	// Begin Prev/Next Links
    // Don't display PREV link if on first page
	int prevoffset;
    if (offset != 0) {
        prevoffset = offset - limit;
		out.println("<a href='"+request.getRequestURI()+"?stage&offset="+prevoffset+"'>Anterior</a>&nbsp;&nbsp;");
    }

	// Calculate total number of pages in result
	int pages = Math.abs(totalrows/limit);
	
    if ((totalrows % limit) > 0) {
        // has remainder so add one page
        pages++;
    }

    // Now loop through the pages to create numbered links
    // ex. 1 2 3 4 5 NEXT
	int newoffset = 0;
    for (int i=1; i <= pages; i++) {
        // Check if on current page
        if ((offset/limit) == (i-1)) {
            // i is equal to current page, so don't display a link
            out.println("[<font color='red'>&nbsp;"+ i +"&nbsp;</font>]");			
        } else {
            // i is NOT the current page, so display a link to page i
            newoffset = limit*(i-1);
            out.println("<a href='"+request.getRequestURI()+"?stage&offset="+newoffset+"'>"+ i +"</a>&nbsp;&nbsp;");
        }
    }

    // Check to see if current page is last page
    if (!(((offset/limit)+1) == pages) && pages != 1) {
        // Not on the last page yet, so display a NEXT Link
        newoffset = offset + limit;
        out.println("<a href='"+request.getRequestURI()+"?stage&offset="+newoffset+"'>Próximo</a>");
    } else out.println("Próximo");
%>
</body>
</html>
