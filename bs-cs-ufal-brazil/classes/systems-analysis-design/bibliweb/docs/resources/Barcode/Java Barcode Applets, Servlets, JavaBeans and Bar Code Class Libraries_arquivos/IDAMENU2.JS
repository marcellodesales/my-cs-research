/*
NOTE: This site requires a JavaScript enabled browser!

IDAutomation.com JavaScript Drop Down Menu Generator V1.0
Copyright IDAutomation.com, Inc. 2001. All Rights Reserved.

You may ONLY use this JavaScript Drop Down Menu code and technique
on your site if you place a link from your site to http://www.idautomation.com/
*/

function IDA1(){
var URL = document.form.m1.options[document.form.m1.selectedIndex].value;
window.location.href = URL;
}
function IDA2(){
var URL = document.form.m2.options[document.form.m2.selectedIndex].value;
window.location.href = URL;
}

document.write('<TABLE width="100%" cellpadding="0" cellspacing="0"><TR><TD bgcolor="#FFFFFF" nowrap>');

document.write('<p align="left"><img border="0" src="http://www.idautomation.com/img/idalogo3.gif" width="468" height="60" align="left" >');

//CREATE SEARCH BOX
//document.write('<FORM ACTION="http://search.freefind.com/find.html" METHOD="GET"> ');
//document.write('<FONT size=1 face="arial,helvetica" > ');
//document.write('<INPUT TYPE="HIDDEN" NAME="id" SIZE="-1" VALUE="3536369"> ');
//document.write('<INPUT TYPE="HIDDEN" NAME="pageid" SIZE="-1" VALUE="r"> ');
//document.write('<INPUT TYPE="HIDDEN" NAME="mode" SIZE="-1" VALUE="ALL"> ');
//document.write('<INPUT type="HIDDEN" name="n" value="0"> ');
//document.write('<INPUT TYPE="TEXT" NAME="query" SIZE="15"><small> ');
//document.write('<INPUT TYPE="SUBMIT" VALUE=" Find "><br> ');
//document.write('</small>Search our site</A>');
//document.write('</font></FORM>');

document.write('<a href="http://www.bbbonline.org/cks.asp?id=101051010574016823" TARGET=new><img src="http://www.idautomation.com/img/bbonlineseal.gif" alt="BBBOnLine Reliability Seal" align="right" WIDTH=135 HEIGHT=48 BORDER=0></a>');

//CREATE SEARCH BOX

//CREATE THE TABLE
document.write('</TD></TR><TR><TD bgcolor="#CCCCCC" valign="bottom" align="center" nowrap>');
document.write('<font size="2">');
//document.write('<br>')

document.write('<form name="form">');

document.write('<select name="m1" size=1 onChange="javascript:IDA1()">');
document.write('<option value="">Our Barcode Products...  ');
document.write('<option value="http://www.bizfonts.com/main.html#BarcodeFonts">Barcode Fonts');
document.write('<option value="http://www.bizfonts.com/main.html#2D">2D Barcode Fonts');
document.write('<option value="http://www.bizfonts.com/main.html#MICRandOCR">MICR & OCR Fonts');
document.write('<option value="http://www.bizfonts.com/fontpackage/">Complete Font Package');
document.write('<option value="http://www.idautomation.com/activex/">ActiveX Controls & Add-ins');
document.write('<option value="http://www.idautomation.com/aspbarcode/">ASP Barcode Server');
document.write('<option value="http://www.idautomation.com/java/">Java Products');
document.write('<option value="http://www.idautomation.com/scanners/">Barcode Scanners');
document.write('<option value="http://www.bizfonts.com/crystal/">Crystal Reports UFL');
document.write('<option value="http://www.bizfonts.com/vba/">VBA for Access and Excel');
document.write('<option value="http://www.bizfonts.com/lotus/">LotusScript Functions');
document.write('<option value="http://www.bizfonts.com/main.html#Tools">Font Tools & Source Code');
document.write('<option value="http://www.idautomation.com/sitemap/">ORDER NOW');
document.write('<option value="http://www.idautomation.com/sitemap/">DOWNLOAD EVAL');
document.write('<option value="">---------------------------------------------');
document.write('</select>');

//document.write(' <b>[<a target="_top" href="http://www.idautomation.com/">IDAutomation.com Home</a>]</b> ');
//document.write('<br>')
document.write(' <b><font size="2">[<a target="_top" href="http://www.idautomation.com/sitemap/">Order Now</a> | <a href="http://www.idautomation.com/sitemap/">Pricing</a> | <a href="http://www.idautomation.com/sitemap/">Download</a> | <a href="http://www.idautomation.com/">Home</a>]</font></b> ');


document.write('<select name="m2" size=1 onChange="javascript:IDA2()">');
document.write('<option value="">FAQ and Technical Support....');
document.write('<option value="">---------------------------------------------');
document.write('<option value="">TECHNICAL SUPPORT');
document.write('<option value="http://www.idautomation.com/barcoding4beginners.html"> -  Bar Coding for Beginners');
document.write('<option value="http://www.bizfonts.com/fontfaq.htm#font-installation"> -  Font Installation Procedures');
document.write('<option value="http://www.bizfonts.com/fontfaq.htm#font-questions"> -  Font FAQ and Support');
document.write('<option value="http://www.bizfonts.com/fontfaq.htm#font-tools"> -  Font Tools');
document.write('<option value="http://www.idautomation.com/support.htm#activex"> -  ActiveX Controls');
document.write('<option value="http://www.idautomation.com/support.htm#java"> -  Java Products');
document.write('<option value="http://www.idautomation.com/support.htm#scanner"> -  Barcode Scanners');
document.write('<option value="http://www.bizfonts.com/datamatrix/faq.html"> - DataMatrix Fonts');
document.write('<option value="http://www.bizfonts.com/pdf417/faq.html"> - PDF417 Fonts');
document.write('<option value="http://www.bizfonts.com/upc-ean/faq.htm"> - UPC/EAN Fonts');
document.write('<option value="">---------------------------------------------');
document.write('<option value="">BARCODE FAQ & TUTORIALS');
document.write('<option value="http://www.idautomation.com/internet-barcode-faq.html"> - Internet Barcoding');
document.write('<option value="http://www.idautomation.com/activex/ActivexManual.html"> - ActiveX Controls');
document.write('<option value="http://www.idautomation.com/code128faq.html"> - Code 128');
document.write('<option value="http://www.idautomation.com/code39faq.html"> - Code 39');
document.write('<option value="http://www.idautomation.com/pdf417faq.html"> - PDF417');
document.write('<option value="http://www.idautomation.com/datamatrixfaq.html"> - DataMatrix');
document.write('<option value="http://www.idautomation.com/maxicodefaq.html"> - MaxiCode');
document.write('<option value="http://www.bizfonts.com/internetfonts/"> - Internet Fonts');
document.write('<option value="http://www.bizfonts.com/vba/#Excel"> - Microsoft Excel');
document.write('<option value="http://www.bizfonts.com/vba/#Access"> - Microsoft Access');
document.write('<option value="http://www.bizfonts.com/vba/#Word"> - Word mail-merge');
document.write('<option value="http://www.bizfonts.com/lotus/#Lotus-barcode-tutorial"> - Lotus Notes');
document.write('<option value="http://www.bizfonts.com/lotus/#Approach-Barcode"> - Lotus Approach');
document.write('<option value="">---------------------------------------------');
document.write('<option value="">MORE INFORMATION');
document.write('<option value="http://www.idautomation.com/sitemap/"> - ORDER NOW');
document.write('<option value="http://www.idautomation.com/sitemap/"> - DOWNLOAD EVAL');
document.write('<option value="http://www.idautomation.com/licenses.html"> - License Agreements');
document.write('<option value="http://www.idautomation.com/privacy.html"> - Privacy Policy');
document.write('<option value="http://www.idautomation.com/press/"> - Press Releases');
document.write('<option value="http://www.idautomation.com/affiliates/#Affiliate"> - Affiliate Program');
document.write('<option value="http://www.idautomation.com/affiliates/"> - Resellers');
document.write('<option value="http://www.idautomation.com/contactus.htm"> - Contact us');
document.write('<option value="">---------------------------------------------');
document.write('</select>');
document.write(' </font>');

document.write('</form>');

//document.write('<br>');
//document.write('<a href="http://www.idautomation.com/">IDAutomation.com Home</a>');
//document.write('<br>');
//document.write('<a href="http://www.idautomation.com/search/">Site Search by Keyword</a>');
//document.write('<br>');
//document.write(' <font size="2"><b> Your Source for Quality Symbology </b>™</font>');

//document.write('</TD></TR><TR><TD bgcolor="#CCCCCC">');

//document.write('<p align="left"><b><font face="Arial" color="#FFFFFF" size="2">');
//document.write('This website is owned and operated by IDAutomation.com, Inc. - a Better Business Bureau Member');
document.write('</font></b></TD></TR></TABLE>');



