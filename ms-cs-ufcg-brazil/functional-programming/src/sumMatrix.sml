(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

load("ListPair");

exception incompatibleMatrix;

(*
   [ListPair.map f (xs, ys)] applies function f to the pairs of corresponding
   elements of xs and ys and returns the list of results.  Hence 
   map f (xs, ys) has the same result and effect as List.map f (zip (xs, ys)).
*)

fun sum_matrix ([],[])  = []
    | sum_matrix (_,[]) = raise incompatibleMatrix
    | sum_matrix([],_)  = raise incompatibleMatrix

    | sum_matrix ((h1::t1) : real list list, (h2::t2) : real list list) = 
 
        if (length(h1)=length(h2)) then (ListPair.map op+ (h1,h2))::sum_matrix(t1,t2)
        else raise incompatibleMatrix
   
(** Testes **)
val m1 = [[2.0, 3.0, 2.5],
               [1.0, 0.7, 4.2]];
val m2 = [[7.0, 3.5, 2.5],
               [5.0, 1.7, 2.2]];
val err1 = [[2.0, 3.0, 2.5],
               [1.0, 4.2]];
val err2 = [[2.0, 3.0, 2.5],
                 [1.0, 0.7, 4.2],
                 [1.0, 0.7, 4.2]];
sum_matrix ([],[]);
sum_matrix ([[]],[[]]);
sum_matrix(m1,m2);
sum_matrix(m1,err1);
sum_matrix(m2,err2);
