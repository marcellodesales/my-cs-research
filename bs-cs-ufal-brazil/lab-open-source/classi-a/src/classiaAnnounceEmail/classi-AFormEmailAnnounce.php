<form name="emailAnnounce" action="<? echo $PHP_SELF ?>" onSubmit="return validateClassiaEmailAnnounce()">
<table width=80% border=0 cellspacing=0 cellpadding=1>
<tr>
  <td colspan="2" bgcolor="#000066">
	<font size=2 face=Verdana color=white><b>
	Anúncio online no http://www.Classi-A.com.br<BR>"<? echo $adTitle ?>"</b></font></td>
</tr>
<tr>
  <td colspan="2" bgcolor="#eaeaea">
        <img src="/common/images/sendAnuncio.gif" align="left"><font size=2 face=Verdana><p align="justify">
        Mande este anúncio para seu amigo. Apenas preencha o formulário e clique em "Eviar Email".</font></p></td>
</tr>
<tr bgcolor="white">
  <td width="30%"><font size=2 face=Verdana>Nome do amigo:</td>
  <td><input SIZE=30 maxlength="30" type="Text" name="friendName"></td>
</tr>
<tr bgcolor="#eaeaea">
  <td><font size=2 face=Verdana>Email do amigo:</td>
  <td><input SIZE=30 maxlength="30" type="Text" name="friendEmail"></td>
</tr>
<tr bgcolor="white">
  <td><font size=2 face=Verdana>Seu Nome:</td>
  <td><input SIZE=30 maxlength="30" type="Text" name="senderName"></td>
</tr>
<tr bgcolor="#eaeaea">
  <td><font size=2 face=Verdana>Seu Email</td>
  <td><input SIZE=30 maxlength="30" type="Text" name="senderEmail"></td>
</tr>
<tr>
  <td colspan="2" bgcolor="#000066">
	<font size=2 face=Verdana color=white><b>
	Corpo do email</b></font></td>
</tr>
<tr bgcolor="#eaeaea">
  <td colspan="2" align="center">
<textarea cols="45" rows="10" name="body" wrap=virtual>
  Esse anúncio lhe interessa...

  * Título: <? echo $adTitle ?>

  * Descrição:
  <? echo $adDescription ?>
  * Data: <? echo  $dateOfAd ?>
</textarea></font></td>
</tr>
<tr> 
  <td colspan="2" bgcolor="#eaeaea" align="right">
	<input type=submit name="sent" value="Enviar Email"></td>
</tr>
</table>
</form>
