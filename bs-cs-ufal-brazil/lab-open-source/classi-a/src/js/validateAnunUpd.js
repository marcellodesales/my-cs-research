function validatePrice(price){
        re =  /^[0-9]+\,[0-9]{2}$/;
        return re.test(price);
}

function newPrice(x){
	re = /./gi;
	price = x;
	return price.replace(re, ",");
}


function validateDescriptions(){
                if (document.main.title.value == ""){
                       alert("O campo do t�tulo do An�ncio est� vazio!\nFavor digitar o t�tulo desejado.")
                       document.main.title.focus();
                       return false;
                }
                else
                if (document.main.descAnnounce.value == ""){
                        alert("O campo da descri��o do An�ncio est� vazio!\nFavor digitar a descri��o do que voc� deseja an�nciar.")
                        document.main.descAnnounce.focus();
                        return false;
                }
                else
                if ((document.main.price.value == "") || (document.main.price.value == "N�o informado")) {
                        if (!confirm("Deseja deixar o campo do pre�o sem ser preenchido?\nCaso seja necess�rio o pre�o clique em Cancelar.")){
                                document.main.price.focus();
                                return false;
                        }
                        else giveValues();
                }
                else
                if (!validatePrice(document.main.price.value)){
                        alert("O formato do pre�o do an�ncio est� errado!\nDigite o formato do pre�o no seguinte formato: 000,00.")
                        document.main.price.focus();
                        return false;
                }
                if (!confirm("Todos os dados do anuncio est�o corretos?\nCaso n�o estejam clique em Cancelar.")){
                       document.main.title.focus();
                       return false;
                }
                return true;
}



function validateSignAnnounce(){
        return validateDescriptions();
}
