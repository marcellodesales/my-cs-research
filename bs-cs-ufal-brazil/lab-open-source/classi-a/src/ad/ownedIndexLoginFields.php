          <form name="login" method="post" action="announcerLogin.php" onSubmit="return validateUserLogin()">
          <TD bgColor=#FFCC00 align="center">
          
              <table border="1" cellspacing="0" bordercolor="#000066">
                <tr>
                  <td bgcolor="#000066" align="center">
                    <b><u><font size="1" face="Verdana" color="#FFFFFF">
                    Login</font></u></b></td>
                </tr>
                <tr>

<?       $newUserValue = ($newUserName != "") ? $newUserName : "";  ?>
                
                  <td align="right"><font face="Verdana" size="1"><b>Usuário:<input type="text" name="username" size="9" maxlength="16" onMouseOver="return showStatus('Nome de usuário com no mínimo 4 e máximo de 16 caracteres. - Difere letras maiúsculas e minúsculas.')" onMouseOut="clearStatus()" value="<? echo $newUserValue ?>"></b></font>
                  </td>
                </tr>
                <tr>
                  <td align="right"><font face="Verdana" size="1"><b>Senha:&nbsp;&nbsp; <input type="password" name="password" size="9" maxlength="8" onMouseOver="return showStatus('Senha de usuário com no mínimo 4 e máximo de 8 caracteres. - Difere letras maiúsculas e minúsculas.')" onMouseOut="clearStatus()"></b></font>
                  </td>
                </tr>
                <tr>
                  <td align="center"><font face=verdana size=1><a href="javascript:openWindow('ownedIndexForgotPassword.php', 'forgottenPWD', 280, 330)" onMouseOver="return showStatus('Esqueceu sua senha? Receba em seu email clicando aqui!')" onMouseOut="clearStatus()" class="subcategory">Esqueceu a senha?</a></font>
                   </td>
                </tr>
                <tr>
                  <td align="center">
                     <input border="0" src="/common/images/ok_login.gif" name="submit" type="image" width="31" height="18">
                   </td>
                </tr>
              </table>
          </TD>
         </form>
