
  function openAnnounceImage(a,b) {
        window.open('/common/commonOpenAnnounceImage.php?idImage='+a+'&title='+b,'_blank','toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=yes,width=480,height=400');
  }

  function validateGetAnnounce(){
        if (document.getAnnounce.idAnnounce.value == ""){
                alert("� necess�rio digitar um c�digo de an�ncio v�lido!");
                document.getAnnounce.idAnnounce.focus();
                return false;
        } else
        if (document.getAnnounce.idAnnounce.value.length < 15){
                alert("O c�digo an�ncio digitado � inv�lido!\nEle deve conter exatos 15 caracteres!\nOBS: Lembre-se que � caso sensitivo!");
                document.getAnnounce.idAnnounce.focus();
                return false;
        }
  }

  function openWindow(pg, name, width, height){
  	window.open(pg,name,'toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=no,width='+width+',height='+height);
  }

  function validateKeyWords(){
	if (document.search.keyWords.value == ""){
		alert("Digite uma palavra para ser pesquisada!");
		document.search.keyWords.focus();
		return false;
	} else
	if (document.search.keyWords.value.length < 2){
                alert("� necess�rio que a palavra contenha mais de 1 letra");
                document.search.keyWords.focus();
                return false;
	}
  } 

 function validateEmailRefer(){
        if (document.emailRefer.friendName.value == ""){
                alert("Voc� n�o digitou o nome de seu amigo!");
                document.emailRefer.friendName.focus();
                return false;
        } else
        if (document.emailRefer.friendEmail.value == ""){
                alert("� necess�rio digitar o email de seu amigo tamb�m!");
                document.emailRefer.friendEmail.focus();
                return false;
        } else
/*
        if (!verifyEmail(document.emailRefer.friendEmail.value)){
                alert("O email de seu amigo � inv�lido!");
                document.emailRefer.friendEmail.focus();
                return false;
        } else
*/
        if (document.emailRefer.senderName.value == ""){
                alert("Voc� ainda n�o digitou seu nome!");
                document.emailRefer.senderName.focus();
                return false;
        } else
        if (document.emailRefer.senderEmail.value == ""){
                alert("� necess�rio digitar seu email!");
                document.emailRefer.senderEmail.focus();
                return false;
        } else {
		fN = document.emailRefer.friendName.value;
		fE = document.emailRefer.friendEmail.value;
		sN = document.emailRefer.senderName.value;
		sE = document.emailRefer.senderEmail.value;
		pg = 'referClassiaByEmail.php?friendName='+fN+'&friendEmail='+fE+'&senderName='+sN+'&senderEmail='+sE;
		//window.open(pg,'EmailSender','toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=no,width=480,height=400');
		document.location.href="http://www.classia.com.br/news/"+pg;
		return false;
	}

  }
var message="  Classi-A / O Classificado que tem tudo. Tudo!\nDesenvolvido por LX4 Web Group Development\n                   Macei�-Alagoas-Brasil";

if (document.layers) {
document.captureEvents(Event.MOUSEDOWN);
}
document.onmousedown=showStatys;


function getDesc(){
   if (document.cx4.php.idCategory.options[document.cx4.php.idCategory.selectedIndex].text != document.cx4.php.descCategory.value){
        document.cx4.php.submit();
   } else {
        alert("Voc� j� est� visualizando todos os an�ncios da categoria "+document.cx4.php.descCategory.value);
        document.cx4.php.idCategory.focus();
        return false;
   }
}
