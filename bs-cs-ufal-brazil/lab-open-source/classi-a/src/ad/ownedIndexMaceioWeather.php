<?
 
 require("/classia/weather/maceio.php"); ?>
    <table width="100%" border="0" cellspacing="0" cellpadding="1" height="95">
        <tr>
          <td bgcolor="#000066" colspan="2" align="center" height="10">
                <font size="1" color="#ffffff" face="verdana"><b>Classi-A Tempo (<? echo $maceioWeather->gmTime ?>)</b></font></td>
        </tr>
        <tr>
          <td bgcolor="#EAEAEA" height="21" align="center">
                <b><font size="1" face="verdana">Temperatura</font></b></td>
          <td bgcolor="#EAEAEA" height="21">
                <font size="1" face="Verdana"><? echo $maceioWeather->temperatureInC ?>° C</font></td>
        </tr>
        <tr>
          <td bgcolor="#EAEAEA" height="21" align="center">
                <b><font face="verdana" size="1">Umidade</font></b></td>
          <td bgcolor="#EAEAEA" height="21">
                <font face="Verdana" size="1"><? echo $maceioWeather->relativeHumidity ?>%</font></td>
        </tr>
        <tr>
          <td bgcolor="#EAEAEA" height="21" align="center">
                <b><font face="verdana" size="1">Direção do vento</font></b></td>
          <td bgcolor="#EAEAEA" height="21">
                <font face="Verdana" size="1"><? echo $maceioWeather->windAngle."° ".$maceioWeather->windDirection; ?></font></td>
        </tr>
        <tr>
          <td bgcolor="#EAEAEA" height="21" align="center">
                <b><font face="verdana" size="1">Velocidade do vento</font></b></td>
          <td bgcolor="#EAEAEA" height="21">
                <font face="Verdana" size="1"><? echo $maceioWeather->windMetersPerSecond ?> m/s</font></td>
        </tr>
    </table>
<?

?>
