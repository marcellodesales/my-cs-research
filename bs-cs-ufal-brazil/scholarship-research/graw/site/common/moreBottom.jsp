<%
	// Begin Prev/Next Links
    // Don't display PREV link if on first page
	int prevoffset;
    if (offset != 0) {
        prevoffset = offset - limit;
		out.println("<a href='"+request.getRequestURI()+"?offset="+prevoffset+"'><img src='../sources/images/forum/prev.gif' border=0> Anterior</a>&nbsp;&nbsp;");
    } else out.println("Anterior&nbsp;");
	
	int pages = Math.abs(totalrows/limit)+1;

    // Check to see if current page is last page
	int newoffset = 0;
    if (!(((offset/limit)) == pages) && pages != 1) {	
        // Not on the last page yet, so display a NEXT Link
        newoffset = offset + limit;
        out.println("<a href='"+request.getRequestURI()+"?offset="+newoffset+"'>Próximo  <img src='../sources/images/forum/next.gif' border=0></a>");
    } else out.println("&nbsp;Próximo");
%>