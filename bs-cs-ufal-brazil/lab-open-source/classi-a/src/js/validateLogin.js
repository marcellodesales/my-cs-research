
function validateUserLogin(){
        if (document.login.username.value == ''){
                alert("O campo do nome de usuário está vazio!\nFavor digitar um nome de usuário.");
                document.login.username.focus();
                return false;
        }
        else
        if (document.login.username.value.length < 4){
                alert("O campo do nome de usuário deve conter no mínimo 4 caracteres!");
                document.login.username.focus();
                return false;
        }
        else
        if (document.login.password.value == ''){
                alert("O campo da senha está vazio! Favor digitar uma senha.");
                document.login.password.focus();
                return false;
        }
        else
        if (document.login.password.value.length < 4){
                alert("O campo da senha deve conter no mínimo 4 caracteres!");
                document.login.password.focus();
                return false;
        } 
}
	 
function validateForgottenPassword(){
        if (document.lostPassword.usernameLost.value == ""){
                alert("É necessário digitar o nome de um usuário do Classi-A!");
               	document.lostPassword.usernameLost.focus();
                return false;
        }
        if (document.lostPassword.usernameLost.value.length < 4){
                alert("O nome de usuário deve ter no mínimo 4 caracters!");
                document.lostPassword.usernameLost.focus();
                return false;
        } else {
             
                un = document.lostPassword.usernameLost.value;
                pg = 'lostPassword.php?username='+un;
                //window.open(pg,'LostPassword','toolbar=no,location=no,directories=no,menubar=no,scrollbars=yes,resizable=no,width=480,height=430');
		document.location.href="http://www.classia.com.br/news/"+pg;
		return false;
        }
}	

function mOvr(src,clrOver){ 
	if (!src.contains(event.fromElement)){ 
		src.style.cursor = 'hand'; 
		src.bgColor = clrOver; 
	} 
} 

function mOut(src,clrIn){ 
	if (!src.contains(event.toElement)){ 
		src.style.cursor = 'default'; 
		src.bgColor = clrIn; 
	} 
} 
