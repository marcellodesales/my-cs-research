(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

(* 
 
[List.filter p xs] applies p to each element x of xs, from left to
   right, and returns the sublist of those x for which p(x) evaluated
   to true.
*)

fun commonElements (list1,list2) = 

	let 
		fun member [] x     = false
		  | member (h::t) x = (x=h) orelse (member t x);
	in 
		List.filter (member list2) (list1) 
	end; 


val l0 = []:int list;
val l1 = [1,2,3,4,5,6];
val l2 = [2,4,6];
val l3 = [6,9,10,11];

commonElements (l0,[]);
commonElements ([],l1);
commonElements (l1,l2);
commonElements (l2,l1);
commonElements (l2,l3);
commonElements (l3,l1);
commonElements (l3,l3);
commonElements (l2,[]);
