<html><!-- #BeginTemplate "/templates/default.dwt" -->
<head>
<!-- #BeginEditable "doctitle" --> 
<title>Seja Bem-Vindo ao GraW!</title>
<!-- #EndEditable -->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel=stylesheet href="sources/style/estilo.css" type="text/css">
<link rel=stylesheet href="sources/style/graw.css" type="text/css">
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0">
<br>
<table class="tabelaMaior" width="770" border="0" cellspacing="2" cellpadding="1" align="center" height="426">
  <tr> 
    <td class="texto_cabecalho" height="0" width="0"><img src="sources/images/gr101.jpg" width="306" height="100"><img src="sources/images/gr102.jpg" width="242" height="100"><img src="sources/images/gr103.jpg" width="222" height="100" usemap="#Map2" border="0"></td>
  </tr>
  <tr class="escuro"> 
    <td height="21"><font color=white size=2 face=verdana><b><!-- #BeginEditable "title" -->Informa&ccedil;&otilde;es 
      gerais sobre o graW<!-- #EndEditable --></b></font></td>
  </tr>
  <tr valign="top"> 
    <td height="276"> <!-- #BeginEditable "centro" --> 
	  <% String lang = request.getParameter("lang");
	   lang = (lang == null) ? "" : lang;	   
	   boolean inPort = true;
	   if ((lang.equals("pt-br")) || (lang.equals(""))){ %>
	  <img src="sources/images/brasil.gif"> <a href=info.jsp?lang=us><img src="sources/images/usa.gif" border=0></a> 
	  <% } else { inPort = false; %>
	  <a href=info.jsp?lang=pt-br><img src="sources/images/brasil.gif" border=0></a> 
	  <img src="sources/images/usa.gif" border=0> 
	  <% } %>
	  <br>
	  <br>
	  <% String title = (inPort) ? "Gradua&ccedil;&atilde;o na WEB" : "Graduation on the Web"; %>
	  <b>graW: <%= title %></b><br>
	  Cidorvan dos Santos Leite, <a target="_blank" href="http://www.marcellojunior.shows.it">Marcello 
	  Alves de Sales Junior</a>, Rodrigo de Barros Paes,
	  Evandro de Barros Costa (Orientador) <br>
	  Departamento de Tecnologia da Informa&ccedil;&atilde;o<BR>
	  Universidade Federal de Alagoas - UFAL, Brasil
	  <p align="center"> 
		<% if (inPort){ %>
		A busca por uma maior integra&ccedil;&atilde;o e comunica&ccedil;&atilde;o 
		entre os principais atores participantes da estrutura universit&aacute;ria 
		&eacute; algo bastante desej&aacute;vel, quando se pensa numa universidade 
		produtiva. Nesse sentido, a infra-estrutura de comunica&ccedil;&atilde;o 
		oferecida pela Internet &eacute; bastante adequada e pode viabilizar uma 
		significativa extens&atilde;o do espa&ccedil;o f&iacute;sico tradicional 
		de uma universidade, levando-a a um espa&ccedil;o virtual. Assim, pode-se 
		definir um sistema que favore&ccedil;a o trabalho cooperativo, visando 
		estimular a discuss&atilde;o e a troca de experi&ecirc;ncias, melhorar 
		e agilizar a comunica&ccedil;&atilde;o entre as entidades envolvidas, 
		diminuir custos com a coordena&ccedil;&atilde;o dos grupos e melhorar 
		a capacidade do grupo de resolver problemas.<br>
		<br>
		Uma id&eacute;ia ligada a um tal sistema &eacute; a de buscar tais caracter&iacute;sticas 
		e contribuir para enriquecer e estender o trabalho da sala de aula tradicional, 
		envolvendo alunos, professores e monitores no contexto de cursos de disciplinas 
		de gradua&ccedil;&atilde;o, aproveitando a infra-estrutura da Internet, 
		para disponibilizar recursos did&aacute;ticos e ferramentas interativas, 
		em modalidades tanto de comunica&ccedil;&atilde;o s&iacute;ncrona quanto 
		de ass&iacute;ncrona, a fim de apoiar ao processo de ensino-aprendizagem 
		em cada dom&iacute;nio de conhecimento experimentado. <br>
		<br>
		Baseado nos resultados do estudo sobre como organizar usu&aacute;rios 
		de modo a trabalharem de forma cooperativa e visto que o projeto foi concebido 
		para um ambiente universit&aacute;rio, optou-se por associar grupos de 
		usu&aacute;rios por &aacute;reas de interesse. Tais &aacute;reas, que 
		no presente ambiente, correspondem as disciplinas. Ferramentas de autoria 
		foram criadas, possibilitando que professores disponibilizem qualquer 
		tipo de conte&uacute;do para o grupo. Ferramentas de comunica&ccedil;&atilde;o, 
		tais como chat, f&oacute;rum de discuss&atilde;o e &aacute;rea de troca 
		de mensagens ass&iacute;ncrona via e-mail tamb&eacute;m foram criadas. 
		No contexto da intera&ccedil;&atilde;o, uma entidade mediadora tamb&eacute;m 
		respons&aacute;vel por uma pol&iacute;tica de privil&eacute;gios foi usada 
		como interface entre o sistema e os usu&aacute;rios. <br>
		<br>
		O graW possibilita um sens&iacute;vel aumento da integra&ccedil;&atilde;o 
		das entidades participantes ( Professor, Monitor e Aluno) em um curso 
		de gradua&ccedil;&atilde;o. Um estudo ainda mais aprofundado das tecnologias 
		de groupware est&aacute; sendo realizado para criar novas ferramentas 
		como monitora&ccedil;&atilde;o de trabalhos em grupo e calend&aacute;rio 
		cooperativo, al&eacute;m de integrar o graW com outros ambientes de coopera&ccedil;&atilde;o 
		em grupo que est&atilde;o sendo desenvolvidos na UFAL. 
		<%  } else { %>
		The search for a larger integration and communication between main actors 
		who participate of the academic structure is something desirable when 
		we think about a productive university. In that sense, the communication 
		infrastructure offered by the Internet is very adjusted and it can make 
		a significant extension of the traditional physical space of a university 
		possible, taking it to a virtual dimension. Therefore we can define a 
		system that favors the cooperative work, seeking to stimulate the discussion 
		and the exchange of experiences in order to activate and to get the communication 
		among the involved entities better as the group problem-solving capacity 
		and decrease costs with the coordination of groups. <BR>
		<BR>
		The idea linked to such system to get such characteristics and to contribute 
		enrich and to extend the traditional classroom work, involving students, 
		teachers and monitors in courses of graduation disciplines context. It 
		can take advantage of the Internet infrastructure, in order to deploy 
		didactic resources and interactive tools, in a synchronous communication 
		as of asynchronous, to assist the teaching-learning process on each domain 
		of experienced knowledge. <BR>
		<BR>
		Based on study results about how to organize users in a way they work 
		in a cooperative manner and because the project was conceived to an academic 
		environment, we chose to associate groups of users by interest fields. 
		Such fields, in the present environment, correspond to the academic disciplines. 
		Authorship tools were created in order to allow teachers to upload any 
		kind of content to the group. Communication tools such as chat, forum 
		and asynchronous email trade were also created. A mediator entity, which 
		is also responsible for privilege politics, were used as interface between 
		the system and the users in the context of interaction. <BR>
		<BR>
		graW makes a sensitive increase of integration of participant entities 
		possible (teacher, monitor and student) in an academic course. A deep 
		study on groupware technologies is being accomplished to create new tools 
		like monitoring of group-works and cooperative calendar, besides integrating 
		graW with other group cooperation environments that are being developed 
		at UFAL. 
		<% } %>
		<br>

<BR>

<b>Projeto</b><BR><BR>
Construção de Ferramentas Computacionais de suporte a um Ambiente baseado na Web para apoiar atividades de ensino-aprendiazagem. CNPq 2001/2002.
<BR><BR>
<i>Alunos de Ciência da Computação / UFAL</i><BR>
Cidorvan dos Santos Leite - Bolsista CNPq 2001-2003;<BR>
Marcello Alves de Sales Junior - Bolsista CNPq 2001-2003;<BR>
Rodrigo de Barros Paes - Bolsista CNPq 2001-2003;<BR>
<BR>
<i>Orientador</i><BR>
Prof. Dr. Evandro de Barros Costa

<center>
	<img src="../sources/images/cnpq.gif"> &nbsp;&nbsp; <img src="http://www.ufal.br/img/logotipoUfal.gif"> &nbsp;&nbsp; <img src="http://www.tci.ufal.br/sbie2000/imagens/tci6.jpg"><BR><BR>

</center>
<b>Publicações</b><BR><BR>

LEITE, C. S.; SALES JUNIOR, M. A.; PAES, R. B.; COSTA, E. B.<br>
<b>graW: A Web Interactive Environment to Support Under graduation Courses</b><br>
<a href="http://www.aace.org/conf/eLearn/default.htm" target="_blank">E-Learn 2002</a> - World 
Conference on E-Learning in Corporate, Government, Healthcare, &amp; Higher 
Education, October 15-19, 2002 - Montreal, Canada (Preceedings, CDROM);

<BR><BR>

LEITE, C. S.; SALES JUNIOR, M. A.; PAES, R. B.; COSTA, E. B.<br>
<b>graW: Um Sistema Interativo, para apoiar cursos de graduação, na Web</b><BR>
<a href="http://www.asser.com.br/atividades/cic/cic.htm" target="_blank">6º Congresso de Iniciação Científica</a>,
29 e 30 de Novembro e 01 de Dezembro de 2001, São Carlos/SP. Gráfica e Editora O Expresso, 2001. v.01. p.105 - 105.

<BR><BR>
          <div align="center"><br>
                <img src="sources/images/equipe.jpg"> <br>
        From left to right: Cidorvan, Rodrigo, Evandro and Marcello - <a href="http://www.graw.tci.ufal.br/firstpresentation/" target="_blank">graW Presentation</a>
        - April 21, 2002</div>

	  <!-- #EndEditable --> </td>
  </tr>
  <tr class="escuro"> 
    <td height="0" align="left" valign="top"> 
      <div align="center"><font color="#FFFFFF">® 2002. Todos os direitos reservados 
        - TCI - Departamento de Tecnologia da Informa&ccedil;&atilde;o - UFAL</font></div>
    </td>
  </tr>
</table>
<map name="Map2"> 
  <area shape="circle" coords="25,85,12" href="/">
  <area shape="circle" coords="83,87,13" href="signIn/index.jsp">
  <area shape="circle" coords="142,85,14" href="info.jsp">
  <area shape="circle" coords="195,85,12" href="help.html">
</map>
</body>
<!-- #EndTemplate --></html>
