<%@ page import="br.ufal.rsacripto.UseCase" %>
<%@ page import="br.ufal.rsacripto.User" %>
<%@ page import="br.ufal.rsacripto.ServletUtility" %>
<%
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = UseCase.getInstance().doLogin(email,password);
		if (user != null){
			out.println("se logou");
			try{
				session.setAttribute("user",user);
				ServletUtility.sendRedirect(response,"user/");
			} catch (Exception e) {
				out.println(e);			
				e.printStackTrace();
			}
		}
%>
