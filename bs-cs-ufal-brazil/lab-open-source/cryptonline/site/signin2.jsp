<%@ page import="br.ufal.rsacripto.UseCase" %>
<%@ page import="br.ufal.rsacripto.User" %>
<%@ page import="br.ufal.rsacripto.Rsa" %>
<%@ page import="br.ufal.rsacripto.ServletUtility" %>
<%
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");								

		String n = request.getParameter("n");
		String e = request.getParameter("e");
		String d = request.getParameter("d");
		try {
			User user;;
			if (n != null && e != null && d != null){
				Rsa rsa = new Rsa(Double.parseDouble(n),Double.parseDouble(e),Double.parseDouble(d));
				user = UseCase.getInstance().createNewUser(firstName,lastName,email,password1,rsa);
			} else user = UseCase.getInstance().createNewUser(firstName,lastName,email,password1);
			session.setAttribute("message","Usuário cadastrado com sucesso!");
			ServletUtility.sendRedirect(response,"index.jsp");
		} catch (Exception ex) {
			session.setAttribute("message",ex.getMessage());
			ServletUtility.sendRedirect(response,"index.jsp");
		}
%>

