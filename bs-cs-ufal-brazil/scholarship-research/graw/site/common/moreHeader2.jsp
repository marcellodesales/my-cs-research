<%
	int offset = (request.getParameter("offset") == null) ? 0 : Integer.parseInt(request.getParameter("offset"));
	if (offset < 0) offset = 0;
    //    Set limit.  $limit = Max number of results per 'page'
    //    Set totalrows = total number of rows that unlimited query would return
	int limit = 9;
	
    int begin = offset + 1;
    int endd  = begin + (limit-1);
%>
