function verifyChoice(){
        a = document.kindOfAnnounce.idKindOfAnnounce.options[document.kindOfAnnounce.idKindOfAnnounce.selectedIndex].text;
        b = document.kindOfAnnounce.descKindOfAnnounce.value;
        c = document.kindOfAnnounce.descCategory.value;
        if (a == b){
                D = (a != "Todos") ? " cujo tipo de anuncio é "+a+"." : " sem especificar algum tipo de anuncio.";
                alert("Você já está visualizando os anuncios pertencentes\n a "+c+D);
                document.kindOfAnnounce.idKindOfAnnounce.focus();
                return false;
        } else {
                document.kindOfAnnounce.descKindOfAnnounce.value = document.kindOfAnnounce.idKindOfAnnounce.options[document.kindOfAnnounce.idKindOfAnnounce.selectedIndex].text;
        }
}

function makeChoiceSubCategory(idCat,descCat,idSubCat,descSubCat){
        document.getSubcategory.idCategory.value         = idCat;
        document.getSubcategory.descCategory.value       = descCat;
        document.getSubcategory.idSubcategory.value      = idSubCat;
        document.getSubcategory.descSubcategory.value    = descSubCat;
        document.getSubcategory.descKindOfAnnounce.value = document.kindOfAnnounce.idKindOfAnnounce.options[document.kindOfAnnounce.idKindOfAnnounce.selectedIndex].text;
        document.getSubcategory.submit();
}

function makeChoiceEspecification(idCat,descCat,idSubCat,descSubCat,idEspec,descEspec){
        document.getSubcategory.idEspecification.value   = idEspec;
        document.getSubcategory.descEspecification.value = descEspec;
        makeChoiceSubCategory(idCat,descCat,idSubCat,descSubCat);
}

