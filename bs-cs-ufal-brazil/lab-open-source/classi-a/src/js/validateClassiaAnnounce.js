
function openClassiaEmailAnnounce(id){
       
	pg = '../classiaAnnounceEmail/classi-AEmailAnnounce.php?adID='+id;
        window.open(pg,'ClassiaAnnounceEmail','toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=no,width=480,height=430');
	return false;
}

function validateClassiaEmailAnnounce(){

        if (document.emailAnnounce.friendName.value == ""){
                alert("O nome de seu amigo deve ser digitado!");
                document.emailAnnounce.friendName.focus();
                return false;
        } else
        if (document.emailAnnounce.friendEmail.value == ""){
                alert("O email de seu amigo deve ser digitado!");
                document.emailAnnounce.friendEmail.focus();
                return false;
        } else
        if (document.emailAnnounce.senderName.value.length == ""){
                alert("Seu nome deve ser digitado!");
                document.emailAnnounce.senderName.focus();
                return false;
        } else
        if (document.emailAnnounce.senderEmail.value == ""){
                alert("Seu email deve ser digitado!");
                document.emailAnnounce.senderEmail.focus();
                return false;
        } else
        if (document.emailAnnounce.body.value == ""){
                alert("O corpo da mensagem deve ser digitado!\nOBS: Não apague as informações de como seu amigo chegará ao anúncio");
                document.emailAnnounce.body.focus();
                return false;
        } 
}

function openClassiaPager(announcerName,announcerIcq,announceTitle){

        pg = '/classiaPager/classi-AIcqPager.php?announcerName='+announcerName+'&announcerICQ='+announcerIcq+'&announceTitle='+announceTitle;
        window.open(pg,'ClassiaPager','toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=no,width=480,height=400');

}

function validateClassiaIcqPager(){

	if (document.icqPager.subject.value == ""){
		alert("O assunto deve ser digitado!");
		document.icqPager.subject.focus();
		return false;
	} else
	if (document.icqPager.message.value == ""){
		alert("A mensagem deve ser digitada!");
		document.icqPager.message.focus();
		return false;
	} else
	if (document.icqPager.message.value.length > 450){
		alert("Somente 450 caracteres é permitido na mensagem!");
		document.icqPager.interestedName.focus();
		return false;
	} else
	if (document.icqPager.interestedName.value == ""){
		alert("Você deve digitar seu nome!");
		document.icqPager.interestedName.focus();
		return false;
	} else
	if (document.icqPager.interestedEmail.value == ""){
		alert("Você deve digitar seu email!");
		document.icqPager.interestedName.focus();
		return false;
	} 
/*	if (!validateEmail(document.icqPager.interestedEmail.value)){
		alert("Seu email não é válido!");
		document.icqPager.interestedName.focus();
		return false;	
	} 
*/
}
