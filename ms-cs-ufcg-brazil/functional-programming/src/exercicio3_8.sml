(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

(* Assinatura de ORDER *)
signature ORDER =
sig
type t
val compare: t * t -> order
end;


(* Implementação de uma assinatura de ORDER que utiliza String *)
structure StringOrder:ORDER =
struct
type t = string;
val compare = String.compare
end;


(* Assinatura de TREE *)
signature TREE =
sig
type key;
type 'a tree;
(*datatype 'a tree = Lf | Br of 'a * 'a tree * 'a tree;*)
val empty   : 'a tree;
val size    : 'a tree -> int;
val depth   : 'a tree -> int;
val reflect : 'a tree -> 'a tree;
val insert  : 'a tree * key * 'a -> 'a tree;
val lookup  : 'a tree * key -> 'a;
val update  : 'a tree * key * 'a -> 'a tree;
end;


(* Implementação de uma assinatura de TREE que utiliza árvore binária *)
functor BinaryTree(chave: ORDER):TREE =
struct

type key = chave.t;
datatype 'a tree = Lf | Br of (key * 'a) * 'a tree * 'a tree;
(*type (key * 'a) bTree = (key * 'a) tree;*)


exception E of key;

val empty = Lf;

fun size Lf = 0
    | size (Br(_,t1,t2)) = 1 + size t1 + size t2;

fun depth Lf = 0
    | depth (Br(_,t1,t2)) = 1 + Int.max(depth t1, depth t2);

fun reflect tr: 'a tree = tr;

fun insert (Lf, k, v) = Br((k,v),Lf,Lf)
    | insert (Br((k1,v1),t1,t2), k, v) = (case chave.compare(k1,k) of
        GREATER => Br((k1,v1),insert(t1,k,v),t2)
        | EQUAL => raise E k
        | LESS => Br((k1,v1),t1,insert(t2,k,v)));


fun lookup (Lf, b) = raise E b
    | lookup (Br((k,v),t1,t2), b) = (case chave.compare(k,b) of
        GREATER => lookup(t1,b)
        | EQUAL => v
        | LESS => lookup(t2,b));


fun update (Lf, k, v) = raise E k
    | update (Br((k1,v1),t1,t2), k, v) = (case chave.compare(k1,k) of
        GREATER => Br((k1,v1),update(t1,k,v),t2)
        | EQUAL => Br((k, v), t1, t2)
        | LESS => Br((k1,v1),t1,update(t2,k,v)));

end;


(* Assinatura de DICTIONARY *)
signature DICTIONARY =
sig
type key;
type 'a dict;
exception E of key;
val empty : 'a dict;
val lookup : 'a dict * key -> 'a;
val insert : 'a dict * key * 'a -> 'a dict;
val update : 'a dict * key * 'a -> 'a dict;
end;


(* Implementação de um DICTIONARY que utiliza uma árvore para armazenar as informações *)
functor MeuDicionario(arv: TREE):DICTIONARY =
struct

type key = arv.key;
type 'a dict = 'a arv.tree;

exception E of key;

val empty = arv.empty;

fun lookup (d, k) = arv.lookup(d, k);
fun insert (d, k, v) = arv.insert(d, k, v);
fun update (d, k, v) = arv.update(d, k, v);

end;


(* TESTES *)
structure chave = StringOrder;
structure arv = BinaryTree(chave);
structure md = MeuDicionario(arv);
