<%@ page import="br.ufal.rsacripto.UseCase" %>
<%@ page import="br.ufal.rsacripto.User" %>
<%@ page import="br.ufal.rsacripto.ServletUtility" %>
<%@ page import="br.ufal.rsacripto.RsaReceiver" %>
<%
		String criptedMessage = request.getParameter("criptedMessage");
		
		User user = (User)session.getAttribute("user");	
		if (user != null){		
			try{
				RsaReceiver receiver = UseCase.getInstance().receiveMessage(user,criptedMessage);
				session.setAttribute("message","Mensagem recebida com sucesso!");
				session.setAttribute("receiveMessageLog",receiver.getLog());
				session.setAttribute("originalMessage",receiver.getOriginalMessage());
				ServletUtility.sendRedirect(response,"showOriginalMessage.jsp");				
			} catch (Exception e) {
				out.println(e);			
				e.printStackTrace();
			}
		}		
%>
