(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

fun atomicElementList (x,[]) = []
    | atomicElementList (x,(h::t)) = if (x=h) then atomicElementList(x,t) else h::remove(x,t)

val l1 = [1,2,3,4,5,6];
val l3 = [6,9,10,11];

(* remove da lista vazia *)
atomicElementList (4,[]);
(* lista com 1 ocorrencia *)
atomicElementList (4,l1);
(* lista com mais de 1 ocorrencia *)
atomicElementList (4,l1@l1);
(* lista sem ocorrencias *)
atomicElementList (4,l3);

