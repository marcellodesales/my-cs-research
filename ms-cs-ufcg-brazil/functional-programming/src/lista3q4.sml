(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

(*
4) Usando funções de alta ordem da biblioteca List, implemente
as seguintes funções. Produza as soluções mais concisas possíveis:
*)

(*
a) Recebe um inteiro como argumento e uma lista de strings, e
retorna um string com a concatenação de todos os strings da lista com
tamanho menor que n.
Cada string deve ser acrescido do sufixo ".".
*)
load "List";
load "String";
(*pode-se usar mapParcial()...*)
fun concatena lista n =
      List.foldr op^ "" ( List.map (fn str=>str^".") (List.filter (fn str=>String.size(str)<n) lista));

concatena ["1","22","333","4444","5ffff","5iiii","3##","666666"] 6;
(*val it = "1.22.333.4444.5ffff.5iiii.3##." : string*)
concatena [] 10;
(*val it = "" : string*)
concatena ["1","22","333"] ~1;
(* val it = "" : string*)


(*
b)  Recebe duas listas de inteiros como argumento e retorna uma lista
de pares de inteiros (x,y) tal que y<x, onde x é um elemento da
primeira lista e y é um elemento da segunda lista.
*)
load "List";

fun formaPares [] _ = [] |
    formaPares _ [] = [] |
    formaPares (h::t) lista2=
      (List.map (fn y1=>(h,y1)) (List.filter (fn y=>y<h) lista2)) @ (formaPares t lista2) ;

formaPares [3,4,5] [1,2,3,4];
(*
> val it =
[(3, 1), (3, 2), (4, 1), (4, 2), (4, 3), (5, 1), (5, 2), (5, 3), (5, 4)] :
  (int * int) list
*)

formaPares ([3,4,5], [1,2,3,4]);
(*! Toplevel input:
! formaPares ([3,4,5], [1,2,3,4]);
! ^^^^^^^^^^
! Type clash: expression of type
!   int list -> int list -> (int * int) list
! cannot have type
!   'b * 'c -> int list -> (int * int) list
*)



(*
-- Tambem funciona...
fun formaPares ([], _) = [] |
    formaPares (_, []) = [] |
    formaPares ((h::t), lista2)=
    let
      val aux=map (fn y1=>(h,y1)) (filter (fn y=>y<h) lista2)
    in
      aux@formaPares(t,lista2)
    end;
--E...
fun formaPares ([], _) = [] |
    formaPares (_, []) = [] |
    formaPares (lista1, lista2)=
      (map (fn y1=>(hd(lista1),y1)) (filter (fn y=>y<hd(lista1)) lista2) )@ formaPares(tl(lista1),lista2);

--Nao funciona:
fun formaPares [] _ = [] |
    formaPares _ [] = [] |
    formaPares (h::t) lista2=
      (map (fn y1=>(h,y1)) (filter (fn y=>y<h) lista2)) @ (formaPares (t, lista2)) ;
! Toplevel input:
!       (map (fn y1=>(h,y1)) (filter (fn y=>y<h) lista2)) @ (formaPares (t, lista2)) ;!                                                            ^^^^^^^^^^
! Type clash: expression of type
!   'a list -> 'a list -> ('a * 'a) list
! cannot have type
!   'b * 'c -> 'a list -> ('a * 'a) list

*)


(*
c) Simplifique a multiplicação de matrizes apresentada na seção
3.10 do Paulson.
*)

load "List";
load "ListPair";

fun headcol [] = []|
    headcol ((x::_) :: rows) = x :: headcol rows;

fun tailcols [] = [] |
    tailcols ((_::xs) :: rows) = xs::tailcols rows;

fun transp ([]::rows) = [] |
    transp rows = headcol rows :: transp (tailcols rows);

fun dotprod([],[])=0 |
    dotprod(x::xs,y::ys) = x*y+dotprod(xs,ys);

fun rowprod(row, [])=[]|
    rowprod(row, col::cols)=dotprod(row,col) :: rowprod(row,cols);

fun rowlistprod([], cols)=[]
   |rowlistprod(row::rows,cols)=
      rowprod(row,cols)::rowlistprod(rows,cols);

fun matprod(rowsA, rowsB)=
   rowlistprod(rowsA,transp rowsB);


(*-----------*)
fun calculaUmaLinha(linha,[])=[]
   |calculaUmaLinha(linha,coluna::colunas)=
   (foldl op+ 0 (map (fn(x,y)=>x*y) (ListPair.zip (linha,coluna))))::calculaUmaLinha(linha,colunas);

fun multiplica ([],cols)=[]
   |multiplica (_,[])=[]
   |multiplica ( linha::linhasA, colunasB) =
      calculaUmaLinha(linha,colunasB)::multiplica(linhasA,colunasB);

fun multMat(linhasA, linhasB)=
   multiplica(linhasA, transp linhasB);

(**)
val m1=[[1,2],[3,4],[5,6]];
val m2=[[7,8],[9,10]];
val m3=[[0,0],[0,0]];
val m4=[[1,1],[1,1]];
multMat(m1,m2);
(*val it = [[25, 28], [57, 64], [89, 100]] : int list list*)
multMat(m1,m3);
(*val it = [[0, 0], [0, 0], [0, 0]] : int list list*)
multMat(m1,m4);
(*val it = [[3, 3], [7, 7], [11, 11]] : int list list*)

