(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

(*
2) Usando map, filter e foldr, implemente as seguintes funções:
(a) Recebe n como argumento e retorna os pares (x,y), tal que x^2 + y^2 <= n^2.
(b) Recebe n como argumento, e retorna a soma dos números impares menores que n.
(c) Recebe n e x como argumento, e retorna o somatorio de (i=1...n) x^i/x!
*)

exception Subscript;

datatype 'a seq = Nil | Cons of 'a * (unit->'a seq);

val force : (unit -> 'a) -> 'a = fn d => d();

fun take (sq, 0) = []
  | take (Nil, n) = raise Subscript
  | take (Cons(x,sq), n) = x :: take(force(sq), n-1);


(*
a) Recebe n como argumento e retorna os pares (x,y),
tal que x^2 + y^2 <= n^2.
*)

load "List";
(*funcao de ordenacao do plano cartesiano com x e y nao-negativos*)
fun nextPar (0,y)=
	(y+1,0)
|
nextPar(x,y)=(x-1,y+1);

fun from ((x,y)) = Cons( (x,y) , fn()=>from(nextPar(x,y)) );

fun pitagoras n (x,y)=
	if (x*x+y*y <= n*n) then true
	else false;

val numero=5;
(*pares candidatos a satisfazeream o predicado 'pitagoras numero'*)
val pares=take(from (0,0), (numero+1)*(numero+1));
val pitagorasN = pitagoras numero;
List.filter pitagorasN pares;
(*
val it =
    [(0, 0), (1, 0), (0, 1), (2, 0), (1, 1), (0, 2), (3, 0), (2, 1), (1, 2),
     (0, 3), (4, 0), (3, 1), (2, 2), (1, 3), (0, 4), (5, 0), (4, 1), (3, 2),
     (2, 3), (1, 4), (0, 5), (4, 2), (3, 3), (2, 4), (4, 3), (3, 4)] :
  (int * int) list
*)


(*
b) Recebe n como argumento, e retorna a soma dos números impares 
menores que n.
*)

fun fromN n = Cons( n , fn()=>fromN(n+1));
fun somaImpares n=
	foldr op+ 0 (List.filter (fn num=>num mod 2 =1) (take(fromN(0),n)) );
somaImpares 15;
(*val it = 49 : int*)
somaImpares 5
(*val it = 4 : int*)

(*
c) Recebe n e x como argumento,
e retorna o somatorio de (i=1...n) x^i/x!
*)

load "Math";

fun fromR n = Cons( n , fn()=>fromR(n+1.0));
fun fat 0.0 = 1.0 | fat n = n*fat(n-1.0);
fun calculo x i = Math.pow(x,i)/fat x;
val N=5.0;
val X=1.0;
val I=1.0;

fun somaIsso n x =
let
   val parciais = map (calculo x) (take(fromR(1.0),n))
in
   foldl op+ 0.0 parciais
end;

somaIsso 2 2.0;
(*val it = 3.0 : real*)
somaIsso 10 10.0;
(*val it = 3061.92435791 : real*)





