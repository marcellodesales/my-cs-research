forCategory = new Array(
new Array(
          new Array('[ Selecione ]','0'),
          new Array('Animais','5'),
          new Array('Artes','21'),
          new Array('Avisos','6'),
          new Array('CDs','24'),
          new Array('Comidas','16'),
          new Array('Consórcios','14'),
          new Array('Cursos','10'),
          new Array('Dicas e Lazer','18'),
          new Array('Eletrodomésticos','22'),
          new Array('Empregos','8'),
          new Array('Festas e Eventos','13'),
          new Array('Imóveis','2'),
          new Array('Informática','19'),
          new Array('Inst. Musicais','25'),
          new Array('Jóias','26'),
          new Array('Livros','23'),
          new Array('Máquinas e Motores','17'),
          new Array('Marmitas','3'),
          new Array('Massagens','20'),
          new Array('Móveis e Decorações','27'),
          new Array('Outros','7'),
          new Array('Pensionatos','9'),
          new Array('Serviços','15'),
          new Array('Telefones','4'),
          new Array('Títulos','11'),
          new Array('Utilidades Públicas','12'),
          new Array('Veículos','1')
          )
);

clearAll = new Array(
new Array(
        new Array('             ','0'),
        new Array('             ','0'),
        new Array('             ','0'),
        new Array('             ','0'),
        new Array('             ','0'),
        new Array('             ','0')
)
);

clear = new Array(
	    new Array('             ','0'),
 	    new Array('             ','0'),
            new Array('             ','0'),
            new Array('             ','0'),
            new Array('             ','0'),
            new Array('             ','0')
);

forSubcategory = new Array(
        clear,
        new Array(
                  new Array('[ Selecione ]','0'),
                  new Array('Antiguidade','16'),
                  new Array('Quadros','27')
        ),        
        clear,
        clear,
        clear,
        clear,
        clear,
        clear,        
        clear,
        clear,
        clear,                
        new Array(
                  new Array('[ Selecione ]','0'),
                  new Array('Apartamento','10'),
                  new Array('Casa','9'),
                  new Array('Fazenda','26'),
                  new Array('Ponto Comercial','13'),
                  new Array('Sítio','11'),
                  new Array('Terrenos','12')
        ),
        new Array(
                  new Array('[ Selecione ]','0'),
                  new Array('Acessórios','20'),
                  new Array('Computador Desktop','21'),
                  new Array('Computador Portátil','22'),
                  new Array('CPU´s','28'),
                  new Array('Domínios','29'),
                  new Array('Driver','30'),
                  new Array('Hardware','31'),
                  new Array('Impressoras','23'),
                  new Array('Memória','32'),
                  new Array('Monitor','33'),
                  new Array('MP3','34'),
                  new Array('Notebook','35'),
                  new Array('Outros','39'),
                  new Array('Placas','36'),
                  new Array('Scanner','37'),
                  new Array('Serviços','24'),
                  new Array('Software','25'),
                  new Array('Teclado','38')
        ),
        clear,
        clear,
        clear,
        clear,
        clear,
        clear,        
        clear,
        clear,
        clear,                
        clear,                
        new Array(
                  new Array('[ Selecione ]','0'),
                  new Array('Celular','15'),
                  new Array('Residencial','14')
        ),
        clear,                
        clear,                        
        new Array(
                  new Array('[ Selecione ]','0'),
                  new Array('Caminhão','2'),
                  new Array('Carro','1'),
                  new Array('Especiais','5'),
                  new Array('Moto','4'),
                  new Array('Náutica','7'),
                  new Array('Trator','3'),
                  new Array('Utilitários','6')
        )
);

brandOfCars  = new Array(
new Array(
         new Array('[ Selecione ]','0'),
         new Array('Chevrolet','1'),
         new Array('Fiat','2'),
         new Array('Ford','3'),
         new Array('Importados','6'),
         new Array('Outro','5'),
         new Array('Volkswagen','4')
         )
);

neigborhoods = new Array(
new Array(
          new Array('Aldebaran','19'),
          new Array('Barro Duro','9'),
          new Array('Bebedouro','17'),
          new Array('Benedito Bentes','5'),
          new Array('Centro','16'),
          new Array('Cruz das Almas','13'),
          new Array('Farol','1'),
          new Array('Jacintinho','11'),
          new Array('Jaraguá','18'),
          new Array('Jatiúca','4'),
          new Array('Murilópolis','20'),
          new Array('Outro','15'),
          new Array('Pajuçara','14'),
          new Array('Poço','6'),
          new Array('Ponta Verde','3'),
          new Array('Pontal','8'),
          new Array('Serraria','10'),
          new Array('Stela Mares','12'),
          new Array('Tabuleiro dos Martins','2'),
          new Array('Trapiche','7')
          )
);

function emptyItems(select){
           for (i = select.options.length; i >= 0; i--) {
                   select.options[i] = null;
           }
}

function fillSelectFromArray(selectCtrl, itemArray) {
        var i, j;
        var prompt;
        emptyItems(selectCtrl);
        j = 0;
        if (itemArray != null) {
                for (i = 0; i < itemArray.length; i++) {
                        selectCtrl.options[j] = new Option(itemArray[i][0]);
                        if (itemArray[i][1] != null) {
                                selectCtrl.options[j].value = itemArray[i][1];
                        }
                        j++;
                }
                selectCtrl.options[0].selected = true;
        }
}

function erase(){
   if (document.main.category.options[document.main.category.selectedIndex].text == "Veículos"){
      fillSelectFromArray(document.main.especification,brandOfCars[0]);
   } else {
           if (document.main.category.options[document.main.category.selectedIndex].text == "Imóveis"){
           fillSelectFromArray(document.main.especification, neigborhoods[0]);
        } else {
                fillSelectFromArray(document.main.especification, clearAll[0]);
        }
   }
}
