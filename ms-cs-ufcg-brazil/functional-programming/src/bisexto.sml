(* 
 * @author Marcello de Sales.
 * Federal University of Campina Grande
 * Functional Programming - Master of Science in Computer Science
 * April 2003
 *)

type date = {
	day   : int,
	month : int,
	year  : int
};

fun bisexto(year:int) = (((year mod 4 = 0) andalso (year mod 100 <> 0)) orelse (year mod 400 = 0)); 

fun daysInFebruary(year) = if bisexto(year) then 29 else 28; 

val daysInMonthOfYear = fn
 		  (1,year) => 31
		| (2,year) => daysInFebruary(year)
		| (3,year) => 31
		| (4,year) => 30
		| (5,year) => 31
		| (6,year) => 30
		| (7,year) => 31
		| (8,year) => 31
		| (9,year) => 30
		|(10,year) => 31
		|(11,year) => 30
		| _        => 31;


fun isYearValid(year:int) = (year>0);

fun isMonthValid(mes:int) = (mes>0) andalso (mes<13);

fun isDayValid(d:date) = (#day d > 0) andalso (#day d < daysInMonthOfYear(#month d,#year d)+1);

fun isValidDate(d: date) = isYearValid(#year d) andalso isMonthValid(#month d) andalso isDayValid(d);

fun isBissexto(d:date) = isValidDate(d) andalso bisexto(#year d);
			
fun relatorio(d:date) = let
				val date = Int.toString(#day d)^"/" ^ int.toString(#month d) ^"/"^ int.toString(#year d);
			in
				if (isBissexto(d)) then 
					date ^ " pertence a um ano bissexto!"
				else date ^ " não pertence a um ano bissexto!"
			end;


(*-------  Testando método bisexto = fn : int -> bool --------------*);

(* Pré-Condição: Recebe um valor do ano da data                     *);
(* Pós-Condição: Se é ou não bissexto                               *);

val ano2000 = bisexto(2000);
val ano1999 = bisexto(1999);
val ano2004 = bisexto(2004);
val ano1991 = bisexto(1991);
val ano1584 = bisexto(1584);
val ano1793 = bisexto(1793);

(*---  Testando método daysInFebruary = fn : int -> int --------*);

(* Pré-Condição: Recebe o valor do ano;                         *);
(* Pós-Condição: a quantidade de dias para o mes de fevereiro   *);
(*               em relação ao ano ser bissexto ou não.         *);

val ano2000 = daysInFebruary(2000);
val ano1999 = daysInFebruary(1999);
val ano2004 = daysInFebruary(2004);
val ano1991 = daysInFebruary(1991);
val ano1584 = daysInFebruary(1584);
val ano1793 = daysInFebruary(1793);

(*---  Testando método daysInMonthOfYear = fn : int * int -> int -----*);

(* Pré-Condição: Recebe uma tupla (mês,ano);                          *);
(* Pós-Condição: a quantidade de dias para o mes de fevereiro         *);
(*               em relação ao ano ser bissexto ou não.               *);

val mes02ano2000 = daysInMonthOfYear(2,2000);
val mes03ano1999 = daysInMonthOfYear(3,1999);
val mes04ano2004 = daysInMonthOfYear(4,2004);
val mes02ano1991 = daysInMonthOfYear(2,1991);
val mes12ano1584 = daysInMonthOfYear(12,1584);
val mes09ano1793 = daysInMonthOfYear(9,1793);

(*---  Testando método isYearValid = fn : int -> bool     ------*);

(* Pré-Condição: Recebe o valor do ano;                         *);
(* Pós-Condição: True se ano é válido; False caso contrário     *);

val ano2000 = isYearValid(2000);
val ano1999 = isYearValid(1999);
val ano0 = isYearValid(0);

(*---  Testando método isMonthValid = fn : int -> bool     ------*);

(* Pré-Condição: Recebe o valor do mês;                         *);
(* Pós-Condição: True se mês é válido; False caso contrário     *);

val mes10 = isMonthValid(10);
val mes12 = isMonthValid(12);
val mes33 = isMonthValid(33);
val mes1 = isMonthValid(1);
val mes0 = isMonthValid(0);

(*-  Testando método isDayValid = fn : {day : int, month : int, year : int} -> bool  -*);

(* Pré-Condição: Recebe uma data:Date a ser analisada;    *);
(* Pós-Condição: Verifica se o dia é válido para aquela data.  *);

val dia1  = isDayValid({day=10,month=05,year=2002});
val dia2  = isDayValid({day=19,month=11,year=2002});
val dia3  = isDayValid({day=0,month=04,year=2002});
val dia4  = isDayValid({day=45,month=02,year=2002});

(*-  Testando método isDayValid = fn : {day : int, month : int, year : int} -> bool  -*);

(* Pré-Condição: Recebe uma data:Date a ser analisada;    *);
(* Pós-Condição: Verifica se o dia é válido para aquela data.  *);

val dia1  = isDayValid({day=10,month=05,year=2002});
val dia2  = isDayValid({day=19,month=11,year=2002});
val dia3  = isDayValid({day=0,month=04,year=2002});
val dia4  = isDayValid({day=45,month=02,year=2002});

(*-  Testando método isValidDate = fn : {day : int, month : int, year : int} -> bool  -*);

(* Pré-Condição: Recebe uma data:Date a ser analisada;    *);
(* Pós-Condição: Verifica se o dia é válido para aquela data.  *);

val dia1  = isValidDate({day=10,month=05,year=2002});
val dia2  = isValidDate({day=19,month=11,year=2002});
val dia3  = isValidDate({day=0,month=04,year=2002});
val dia4  = isValidDate({day=45,month=02,year=2002});


val dia1  = relatorio({day=10,month=05,year=2002});
val dia2  = relatorio({day=19,month=11,year=2002});
val dia3  = relatorio({day=0,month=04,year=2002});
val dia4  = relatorio({day=45,month=02,year=2002});
