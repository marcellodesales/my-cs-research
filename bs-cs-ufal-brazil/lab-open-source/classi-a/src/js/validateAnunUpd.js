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
                       alert("O campo do título do Anúncio está vazio!\nFavor digitar o título desejado.")
                       document.main.title.focus();
                       return false;
                }
                else
                if (document.main.descAnnounce.value == ""){
                        alert("O campo da descrição do Anúncio está vazio!\nFavor digitar a descrição do que você deseja anúnciar.")
                        document.main.descAnnounce.focus();
                        return false;
                }
                else
                if ((document.main.price.value == "") || (document.main.price.value == "Não informado")) {
                        if (!confirm("Deseja deixar o campo do preço sem ser preenchido?\nCaso seja necessário o preço clique em Cancelar.")){
                                document.main.price.focus();
                                return false;
                        }
                        else giveValues();
                }
                else
                if (!validatePrice(document.main.price.value)){
                        alert("O formato do preço do anúncio está errado!\nDigite o formato do preço no seguinte formato: 000,00.")
                        document.main.price.focus();
                        return false;
                }
                if (!confirm("Todos os dados do anuncio estão corretos?\nCaso não estejam clique em Cancelar.")){
                       document.main.title.focus();
                       return false;
                }
                return true;
}



function validateSignAnnounce(){
        return validateDescriptions();
}
