(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

(*
3) Re-implemente as seguintes funções:
*)

(*
a) Função union - união dos valores de duas listas
(usando filter, exists, ...)
*)
load "List";

fun naoTem [] x = true |
    naoTem (h::t) x =
      if (x=h) then false
      else naoTem t x ;

fun uniao [] l = l |
    uniao l [] = l |
    uniao list1 list2 =
    let
      val naoTemEmL1=naoTem list1
    in
      list1 @ List.filter (fn (x)=>naoTemEmL1 x ) (list2)
    end;

uniao [1,2,3,4] [5,6,7,8,9];
(*val it = [1, 2, 3, 4, 5, 6, 7, 8, 9] : int list*)
uniao [1,2,3,4] [1,2,5,6,7,8,9];
(*val it = [1, 2, 3, 4, 5, 6, 7, 8, 9] : int list*)
uniao [] [];
(*val it = [] : ''a list*)
uniao [] [1,2];
(*val it = [1, 2] : int list*)
uniao [1,2,3] [];
(*val it = [1, 2, 3] : int list*)

(*
b) Função exists - quantificador existencial
(usando foldl ou foldr)
*)
(*
fun foldl f e [] = e
  | foldl f e (x::xs) = foldl f (f(x,e)) xs;
*)
fun exists pred [] = false
|
    exists pred lista =
      List.foldl (fn(a1,a2)=>pred a1 orelse a2) false lista;

fun exists pred [] = false
|
    exists pred lista =
      foldl op orelse false lista;

exists (fn x=>x>10) [1,2,3,4,5,6,7,8,9,10];
(*val it = false : bool*)
exists (fn x=>x>10) [1,2,3,4,5,6,7,8,9,10,11];
(*val it = true : bool*)
exists (fn x=>x mod 2 = 0) [1,3,5,7,9,11];
(*val it = false : bool*)
exists (fn x=>x mod 2 = 0) [1,2,3,4,5,6,7,8,9,10,11];
(*val it = true : bool*)
exists (fn x=>x>10) [];
(*val it = false : bool*)

