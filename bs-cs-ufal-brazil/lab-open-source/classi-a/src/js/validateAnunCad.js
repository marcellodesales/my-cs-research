function validatePrice(price){
        re =  /^[0-9]+\,[0-9]{2}$/;
        return re.test(price);
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
                if (document.main.price.value == ""){
                        if (!confirm("Deseja deixar o campo do preço vazio?\nCaso seja necessário o preço clique em Cancelar.")){
                                document.main.price.focus();
                                return false;
                        }
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
}

function validateSignAnnounce(){
        if (document.main.kindOfAnnounce.selectedIndex == 0){
                alert("O tipo de anuncio não está selecionado!\nFavor escolher o tipo desejado.")
                document.main.kindOfAnnounce.focus();
                return false;
        }
        else
        if (document.main.category.selectedIndex == 0){
                alert("O tipo da especificação do anuncio não está selecionado!\nFavor escolher a especificação desejado.")
                document.main.category.focus();
                return false;
        }
        else
        if (document.main.category.options[document.main.category.selectedIndex].text == "Veículos"){
                if (document.main.subcategory.selectedIndex == 0){
                        alert("A subcategoria do anuncio não está selecionado!\nFavor escolher uma subcategoria desejada.")
                        document.main.subcategory.focus();
                        return false;
                }
                else
                if (document.main.especification.selectedIndex == 0){
                        alert("A marca do veículo não está selecionada!\nFavor escolher uma marca desejada.")
                        document.main.brandOfEspecification.focus();
                        return false;
                }
                else return validateDescriptions();
        }
        else
        if (document.main.category.options[document.main.category.selectedIndex].text == "Imóveis"){
                if (document.main.subcategory.selectedIndex == 0){
                        alert("A subcategoria do anuncio não está selecionado!\nFavor escolher uma subcategoria desejada.")
                        document.main.subcategory.focus();
                        return false;
                }
                else
                if (document.main.especification.selectedIndex == 0){
                        alert("A bairro do imóvel não está selecionado!\nFavor escolher uma marca desejada.")
                        document.main.brandOfEspecification.focus();
                        return false;
                }
                else return validateDescriptions();
        }
        else
        if (document.main.category.options[document.main.category.selectedIndex].text == "Informática"){
                if (document.main.subcategory.selectedIndex == 0){
                        alert("A subcategoria do anuncio não está selecionado!\nFavor escolher uma subcategoria desejada.")
                        document.main.subcategory.focus();
                        return false;
                }
                else return validateDescriptions();
        }
        else return validateDescriptions();
}