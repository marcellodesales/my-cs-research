            <?
            $fields = "titulo_anuncio,preco_anuncio,cod_anuncio,imagem_anuncio,cod_tipo_anuncio";
    
            $query = "SELECT $fields FROM t_anuncio WHERE titulo_anuncio LIKE '%$keyWords%'";

            $stage = "keyWords=$keyWords"; //it's for the indexes   
