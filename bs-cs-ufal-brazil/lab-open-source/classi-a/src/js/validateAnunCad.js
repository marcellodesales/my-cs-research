function validatePrice(price){
        re =  /^[0-9]+\,[0-9]{2}$/;
        return re.test(price);
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
                if (document.main.price.value == ""){
                        if (!confirm("Deseja deixar o campo do pre�o vazio?\nCaso seja necess�rio o pre�o clique em Cancelar.")){
                                document.main.price.focus();
                                return false;
                        }
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
}

function validateSignAnnounce(){
        if (document.main.kindOfAnnounce.selectedIndex == 0){
                alert("O tipo de anuncio n�o est� selecionado!\nFavor escolher o tipo desejado.")
                document.main.kindOfAnnounce.focus();
                return false;
        }
        else
        if (document.main.category.selectedIndex == 0){
                alert("O tipo da especifica��o do anuncio n�o est� selecionado!\nFavor escolher a especifica��o desejado.")
                document.main.category.focus();
                return false;
        }
        else
        if (document.main.category.options[document.main.category.selectedIndex].text == "Ve�culos"){
                if (document.main.subcategory.selectedIndex == 0){
                        alert("A subcategoria do anuncio n�o est� selecionado!\nFavor escolher uma subcategoria desejada.")
                        document.main.subcategory.focus();
                        return false;
                }
                else
                if (document.main.especification.selectedIndex == 0){
                        alert("A marca do ve�culo n�o est� selecionada!\nFavor escolher uma marca desejada.")
                        document.main.brandOfEspecification.focus();
                        return false;
                }
                else return validateDescriptions();
        }
        else
        if (document.main.category.options[document.main.category.selectedIndex].text == "Im�veis"){
                if (document.main.subcategory.selectedIndex == 0){
                        alert("A subcategoria do anuncio n�o est� selecionado!\nFavor escolher uma subcategoria desejada.")
                        document.main.subcategory.focus();
                        return false;
                }
                else
                if (document.main.especification.selectedIndex == 0){
                        alert("A bairro do im�vel n�o est� selecionado!\nFavor escolher uma marca desejada.")
                        document.main.brandOfEspecification.focus();
                        return false;
                }
                else return validateDescriptions();
        }
        else
        if (document.main.category.options[document.main.category.selectedIndex].text == "Inform�tica"){
                if (document.main.subcategory.selectedIndex == 0){
                        alert("A subcategoria do anuncio n�o est� selecionado!\nFavor escolher uma subcategoria desejada.")
                        document.main.subcategory.focus();
                        return false;
                }
                else return validateDescriptions();
        }
        else return validateDescriptions();
}