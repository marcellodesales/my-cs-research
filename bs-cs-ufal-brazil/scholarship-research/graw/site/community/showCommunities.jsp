<table width="80%" border="0" cellspacing="0" cellpadding="0">

		    
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
									<a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ com.getID() %>"><%=com.getTitle()%> </a>
								</td>
								<td align="Left" >
									 <img src="../sources/images/administrator.gif" alt="Você é um administrador - Permissões especiais para essa comunidade."> 
								</td>
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
									<a class="Link" href="<%=servletDir+"br.ufal.graw.web.community.OpenCommunity?communityID="+ com.getID() %>"><%=com.getTitle()%> </a>
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
							<td>
							<%
							if ( ! master){ %>
								<a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.Disassociate?communityID="+ com.getID() %>">[ Desassociar-se ]</a>	<%
							}else{ %>
							 <img src="../sources/images/administrator.gif" alt="Você é um administrador - Permissões especiais para essa comunidade."> <%							
							}
							%>
							</td>
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
					<!---------------------- Area de visibilidade Pública ------------------------>
                        
                      <tr><td height="14" colspan="2"><BR><b><font color="#FF0000">&nbsp;Comunidades Públicas: </font></b></td></tr>
		      				<tr> 				
							<td id=headerSimplesFont colspan="2">&nbsp;Cursos</td>
						</tr>
						<%
						communities =user.getExtraCourses();
						numberOfElements =  communities.size();
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
								<a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.Disassociate?communityID="+ com.getID() %>">[ Desassociar-se ]</a>	<%
							}else{ %>
							 <img src="../sources/images/administrator.gif" alt="Administrador - Permissões especiais"> <%							
							}
							/* Condicao para colocar a legenda do master */
							 if ( (master) && (!hasAnyMasterPrivilegy)){
								 hasAnyMasterPrivilegy = true;
							 }
							%>
							
							</td>
							<tr><%
						}
						%>
						<tr> 				
							<td id=headerSimplesFont colspan="2">&nbsp;Grupos</td>
						</tr>
						<%
						communities = user.getGroups();
						numberOfElements =  communities.size();
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
								<a  class="Link" href="<%=servletDir+"br.ufal.graw.web.community.Disassociate?communityID="+ com.getID() %>">[ Desassociar-se ]</a>	<%
							}else{ %>
							 <img src="../sources/images/administrator.gif" alt="Administrador - Permissões especiais"> <%							
							}
							/* Condicao para colocar a legenda do master */
							 if ( (master) && (!hasAnyMasterPrivilegy)){
								 hasAnyMasterPrivilegy = true;
							 }
							%>
							</td>
							</tr> <%			
						}
						%>
			<% if (hasAnyMasterPrivilegy){	%>
			<tr> 				
    				<td  colspan="2">&nbsp;</td>
			</tr>
			<tr> 				
				
    <td id=headerSimplesFont colspan="2">&nbsp;<img src="../sources/images/administrator.gif" alt="Administrador - Permissões especiais"> 
      - Permissões especiais na comunidade.</td>
			</tr>
			<%}
			%>												
		</table>