<?
  if ($partnerID == null){
        header("Location: /");
  } else {
          require("/classia/classes/utility.php");
          require("/classia/classes/security.php");
          require("/classia/classes/partner.php");
          $secure  = new Security();
          if ($secure->partnerExist($partnerID)){
                $partner = new Partner($partnerID);
                $partnerExist = true;
          } else { $partnerExist = false; }
?>

<html>
<head>
<title>Classi-A Eletronic Card</title>

<link rel="stylesheet" href="/common/style/classia.css">

</head>


<body link="#284A7E" alink="#284A7E" vlink="#284A7E" topmargin="0" leftmargin="0" marginheight="0" marginwidth="0" bgcolor="#FFFFFF">
<div align="center"> 
<table width="95%" border="0" cellspacing="0" cellpadding="5">
	<tr valign="top">
      <td width="607" align="left">
        <center><font face="verdana,arial" color="#000000" size="4">&nbsp;&nbsp;<b>Cartão Eletrônico Classi-A<br>
        &nbsp;&nbsp;<font size="2">c l a s s i f i c a d o s &nbsp;&nbsp;&nbsp;d e &nbsp;&nbsp;&nbsp;a l a g o a s</font></b></center>	<br>
	<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td><img src="images/left_corner_top_B5C4CC.gif" border="0"><br></td>
				
            <td align="center" bgcolor="#B5C4CC"> <font face="verdana,arial" color="#000000" size="3"> 
              <b><? if ($partner->webpage != ""){ ?><a href="<? echo $partner->webpage; ?>" target="_new"><img alt="Acesse o site do sócio: <? echo $partner->webpage; ?>" src="/common/images/homeicon.gif" border=0></a>&nbsp;&nbsp;<? } ?><? if ($partner->email != ""){ ?><a href="mailto:<? echo $partner->email; ?>"><img alt="Envie um E-Mail para nosso sócio: <? echo $partner->email; ?>" src="/common/images/emailicon.gif" border=0></a>&nbsp;&nbsp;<? } ?><? echo $partner->name; ?></b> </font> </td>
				<td><img src="images/right_corner_top_B5C4CC.gif" border="0"><br></td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr valign="middle" bgcolor="#B5C4CC">
				<td width="100%" align="left" colspan="3">
					<table width="100%" border="0" cellpadding="5" cellspacing="5">
						<tr valign="top" align="left" bgcolor="#F0F3F5">

                  <td width="100%" align="left">
                    <table width="95%" border="0" align="center">
                      <tr>
                        <td><img src="/partner/images/photo/<? echo $partner->id.".".$partner->extPhoto; ?>" width="139" height="146" vspace="0" hspace="10" align="right"><font face="verdana,arial" size="2" color="#000000">
                          <p align="justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<? echo $partner->text; ?></p>
                          </font></td>
                      </tr>
                     <tr>
                        <td><BR><center><font face="verdana,arial" size="2" color="#000000"><? echo $partner->address; ?> Fone: <? echo $partner->phones; ?><BR> <a href="mailto:<? echo $partner->email; ?>" class="subcategory"><? echo $partner->email; ?><BR><a href="<? echo $partner->webpage; ?>" class="subcategory" target="_new"><? echo $partner->webpage; ?></a></font></td>
                      </tr>
                    </table>
                    <br>

                  </td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr valign="top">
				<td><img src="images/left_corner_bottom_B5C4CC.gif" border="0"><br></td>
				<td align="center" width="100%" bgcolor="#B5C4CC"></td>
				<td><img src="images/right_corner_bottom_B5C4CC.gif" border="0"><br></td>
			</tr>
		</table>
        <br>
		<table border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td><img src="images/left_side_B5C4CC.gif" border="0"><br></td>
				<td align="center" bgcolor="#B5C4CC" width="100%">
				<font face="verdana,arial" color="#000000" size="2">
	          <div align="center">
                <input type="button" name="fechar" value="Fechar" style="background-color: #B5C4CC" onClick="window.close();">
              </div>
		</font>
				</td>
				<td><img src="images/right_side_B5C4CC.gif" border="0"><br></td>
			</tr>
		</table>
      </td>
	</tr>
</table>



</div>
</body>
</html>
<? } ?>
