(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

datatype geometricFigure = line of real | 
				triangle of real * real |
				rectangle of real * real |
				circle of real |
				square of real;


load("Math");

(* 
 * Função que calculará a área de uma figura geométrica.
 * - Para uma linha a funcão retornará sempre 0.
 * - Para um triângulo a funcão retornará o valor (base * altura) / 2.
 * - Para um retângulo a funcão retornará o valor (base * altura).
 * - Para um círculo a funcão retornará o valor (PI * raio ao quadrado).
 * - Para um quadrado a funcão retornará o valor (lado ao quadrado).
 *)

(*
	Prestar atenção nos tipos dos valores passados... dá erro se colocar 2 (int) no lugar de 2.0 (real)
*)
fun area (line(_)) = 0.0
    | area (triangle(base, heith)) = (base * heith) / 2.0
    | area (rectangle(base, heith)) = base * heith
    | area (circle(radius)) = Math.pi * Math.pow(radius, 2.0)
    | area (square(side)) = Math.pow(side, 2.0)

        
(* Testes *)

val l1 = line(10.0);
val t1 = triangle(15.0, 20.0);
val r1 = rectangle(10.0, 13.0);
val c1 = circle(13.0);
val q1 = square(30.0);

area (l1);
area (t1);
area (r1);
area (c1);
area (q1);
