<?
  $get = "SELECT t_anuncio.cod_anuncio,titulo_anuncio,imagem_anuncio,quando_anuncio,desc_categoria_anuncio FROM t_anuncio,t_categoria_anuncio WHERE t_anuncio.cod_categoria_anuncio=t_categoria_anuncio.cod_categoria_anuncio ORDER BY quando_anuncio DESC LIMIT 0,5 ";
  $secure->connection->query($get);
  $quant = ($secure->connection->affected_rows() < 5) ? $secure->connection->affected_rows() : 5;
?>
