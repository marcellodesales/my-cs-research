<table class=tabelaSimples cellspacing=0 cellpadding=3 width="85%" align=center>
	<tr> 
      <td id=headerSimplesFont height="0">Busca de comunidades</td>
    </tr>
    <tr> 
	                          
    <td height="0" valign=top><b><%=user.getFirstName() %></b>, abaixo est&aacute; 
      a rela&ccedil;&atilde;o das comunidades pelo qual voc&ecirc; j&aacute; &eacute; 
      membro. Para acessar uma delas basta clicar sobre o t&iacute;tulo da mesma. 
      <img src="../sources/images/administrator.gif" alt="Administrador - Permissões especiais"> 
      indica que voc&ecirc; possui permissões especiais na comunidade (para professores, 
      monitores e administradores). Se preferir volte a p&aacute;gina inicial 
      clicando na casinha. <a href="../user/index.jsp"><img src="../sources/images/home.gif" width="20" height="20" border="0" alt="Ir a p&aacute;gina inicial"></a></td>
    </tr>
</table>

<br> 

<table class=tabelaSimples width="70%" border="0" align="center">
  <%
					Vector communities; 
					int numberOfElements;
					Community com;
					boolean master = false;
					boolean hasAnyMasterPrivilegy = false;
					
					/***************************** Visao do usuário acadêmico *******************************************/
					Community coms[] = user.getCommunityGuest();
					if (coms.length>0){	%>
					  <tr><td id=headerSimplesFont colspan=2>
						&nbsp;Você é um convidado nas seguintes comunidades:
						</td></tr>	<%
						for (int i=0 ; i< coms.length ; i++){
							com = coms[i];	%>
							<tr>
								<td align="Left">
									<a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ com.getID() %>"><%= ((Discipline)com).getCode() %> - <%=com.getTitle()%> </a>
				</td>
								
			    <td align=right><img src="../sources/images/administrator.gif" alt="Você é um administrador - Permissões especiais para essa comunidade."></td>
			</tr>
							<%
						}
						
					}
					
					if (user instanceof AcademicUser){
						AcademicUser  academicUser= (AcademicUser)user;
								
						
						%>
						<!---------------------- Area de visibilidade Privada ------------------------>
                       
						<tr> 				
							<td id=headerSimplesFont colspan="2">&nbsp;Disciplinas </td>
						</tr>
						<%
						
						communities = academicUser.getDisciplines();

						numberOfElements =  communities.size();						
						for (int i=0 ;  i < numberOfElements ; i++){	
							com = (Community)communities.get(i); %>
							<tr>
								<td>
									<a class="Link" href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ com.getID() %>"><%= ((Discipline)com).getCode() %> - <%=com.getTitle()%> </a>
								</td>
							<%
							if ( user.isResponsible(com) ){
								master = true;
							}else{
								if (user instanceof Professor ) { 					
									if ( ((Professor)user).isTeacher((Discipline)com) ){
										master = true;
									}
								}else if (user instanceof Student ){					
									if (  ((Student ) user).isMonitor((Discipline) com) ){
										master = true;
									}
								}
							}
							%>
							
    <td><div align="right">
      <%
							if ( ! master){ %>
      <a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.Disassociate?communityID="+ com.getID() %>">[ 
        Desassociar-se ]</a> 
        <%
							}else{ %>
        <img src="../sources/images/administrator.gif" alt="Você é um administrador - Permissões especiais para essa comunidade."> 
        <%							
							}
							%>
      </div></td>
	</tr>
							 <%
							 /* Condicao para colocar a legenda do master */
							 if ( (master) && (!hasAnyMasterPrivilegy)){
								 hasAnyMasterPrivilegy = true;
							 }
							 
							master=false;
						}
						%>
						
					<%
					} // ************************************ Fim da Visao estritamente Academica ********************************************************
					%>
					</table>
<BR>					

					<!---------------------- Area de visibilidade Pública ------------------------>
<%
		communities =user.getExtraCourses();
		numberOfElements =  communities.size();
%>
<table class=tabelaSimples width="70%" border="0" align="center">                        
<%		if (numberOfElements > 0){%>
 			  <tr> 				
				  <td id=headerSimplesFont colspan="2">&nbsp;Cursos</td>
			  </tr>
<%
						for (int i=0 ;  i < numberOfElements ; i++){
							com = ((Community)communities.get(i));		%>							
							<tr>
							<td align="Left">
							<a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ com.getID() %>">
							<%=com.getTitle()%>
							</a>
							</td>
							<%
							if ( user.isResponsible(com) ){
								master = true;
							}else{
								master = false;
							} %>
							
    <td align="Left"> 
      <%
							if ( ! master){ %>
      <div align="right"><a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.Disassociate?communityID="+ com.getID() %>">[ 
        Desassociar-se ]</a> 
        <%
							}else{ %>
        <img src="../sources/images/administrator.gif" alt="Administrador - Permissões especiais"> 
        <%							
							}
							/* Condicao para colocar a legenda do master */
							 if ( (master) && (!hasAnyMasterPrivilegy)){
								 hasAnyMasterPrivilegy = true;
							 }
							%>
      </div>
    </td>
							<tr><%
						}
		} else {//number		%>
					<tr><td colspan=2>Você ainda não possui cursos. </td></tr>
<%		} 		%>					
					</table>										
<br>
<%
			communities = user.getGroups();
			numberOfElements =  communities.size();
%>			

<table class=tabelaSimples width="70%" border="0" align="center">                        					
<%		if (numberOfElements > 0){%>
			<tr> 				
				<td id=headerSimplesFont colspan="2">&nbsp;Grupos</td>
			</tr>
						<%
						for (int i=0 ;  i < numberOfElements ; i++){
							com = ((Community)communities.get(i));		%>							
							<tr>
							<td align="Left">
							<a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ com.getID() %>">						
							<%=com.getTitle()%>
							</a>
							</td>
							<%
							if ( user.isResponsible(com) ){
								master = true;
							}else{
								master = false;
							} %>
							
							
    <td align="Left"> 
      <%
							if ( ! master){ %>
      <div align="right"><a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.Disassociate?communityID="+ com.getID() %>">[ 
        Desassociar-se ]</a> 
        <%
							}else{ %>
        <img src="../sources/images/administrator.gif" alt="Administrador - Permissões especiais"> 
        <%							
							}
							/* Condicao para colocar a legenda do master */
							 if ( (master) && (!hasAnyMasterPrivilegy)){
								 hasAnyMasterPrivilegy = true;
							 }
							%>
      </div>
    </td>
							</tr> <%			
						}
		} else {//number		%>
					<tr><td colspan=2>Você ainda não possui grupos.</td></tr>
<%		} 		%>					
		</table>