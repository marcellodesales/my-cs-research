<%@ page import="br.ufal.rsacripto.UseCase" %>
<%@ page import="br.ufal.rsacripto.RsaSender" %>
<%@ page import="br.ufal.rsacripto.User" %>
<%@ page import="br.ufal.rsacripto.ServletUtility" %>
<%@ page import="java.util.Iterator" %>
<%
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		String n = request.getParameter("n");
		String e = request.getParameter("e");		
		
		User user = (User)session.getAttribute("user");	
		if (user != null){		
			try{
				RsaSender sender = UseCase.getInstance().sendMessage(user,message,email,name);
				session.setAttribute("message","mensagem enviada com sucesso!");
				session.setAttribute("sendMessageLog",sender.getLog());
				session.setAttribute("chriptedMessage",sender.getChriptedMessage());				
				ServletUtility.sendRedirect(response,"showEncriptedMessage.jsp");				
			} catch (Exception en) {
				out.println(en);			
				en.printStackTrace();
			}
		}		
%>
