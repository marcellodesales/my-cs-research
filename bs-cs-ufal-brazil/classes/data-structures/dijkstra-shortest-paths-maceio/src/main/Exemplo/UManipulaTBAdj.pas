unit UManipulaTBAdj;

interface

const
     Max      = 5;{ n�mero total de v�rtice }
     Infinito = maxint;
Type
  vertice          = 1..Max;
  cont             = 0..Max;
  valor            = integer;
  TabelaAdjacencia = array[vertice,vertice] of valor;
  TabelaDistancia  = array[vertice] of valor;
  TCustos = Class
     numver : cont;     // num de v�rtices no grafo
     custo  : TabelaAdjacencia; //descreve o grafo
     Dist    : TabelaDistancia;   //menores dist�ncias do v�rtice 1
     Procedure PassaValores(TAD:TabelaAdjacencia; TD:TabelaDistancia; QuantVertice:Cont);
     Procedure Distancia(n :Cont; var custo :TabelaAdjacencia; var D:TabelaDistancia);
     function GetNumVert:integer;
     function GetCusto(x,y:vertice):integer;
     function GetDistancia(X:vertice):integer;
  end;

implementation

function TCustos.GetNumVert:integer;
begin
     GetNumVert:=self.numver;
end;

function TCustos.GetCusto(x,y:vertice):integer;
begin
     GetCusto:=self.custo[x,y];
end;

function TCustos.GetDistancia(X:vertice):integer;
begin
     GetDistancia:=self.Dist[x];
end;

Procedure TCustos.PassaValores(TAD:TabelaAdjacencia; TD:TabelaDistancia; QuantVertice:Cont);
begin
     self.numver:=QuantVertice;
     self.custo:=TAD;
     self.Dist:=TD;
end;

Procedure TCustos.Distancia(n :Cont; var custo :TabelaAdjacencia; var D:TabelaDistancia);
{Pr�Condi��o: Um grafo orientado com n v�rtices � dado com os valores na tabela custo.
P�sCondi��o: O procedimento calcula o custo do menor caminho de v�rtice 1 a todos os outros v�rtices
                       do grafo e retorna o resultado no array D}
Var
   Final : array[vertice] of Boolean;   // final[v] � true sss v est� no conjunto S
   I,w,v : vertice; //w � um v�rtice ainda � adcionado a S; v v�rtice com menores tentativas em D[ ] ;
   min   : valor;   //minima distancia de v. Igual a D[v]
Begin
     Final[1]:=true; //inicializa com o vetor 1 sozinho no conjunto S.
     D[1]:=0;
     For v:=2 to n do
     Begin
     	Final[v]:=false;
     	D[v]:=custo[1,v];
     End;
     For I:=2 to n do //come�a o loop principal; adiciona um v�rtice v a S em cada passo.
     Begin
     	Min:=infinito; //acha o v�rtice v mais pr�ximo ao v�rtice 1
     	For w:=2 to n do
     	    If not final[w] then
     	    	If D[w] < min then
	    	Begin
	             v:=w;
		     min:=D[w];
                End;
        Final[v]:=true; //Adiciona o v�rtice v ao conjunto S;
	For w:=2 to n do  //Atualiza as distancias �rmanecentes em D
	    If not final[w] then
	       If (min + custo[w,v]) < D[w] then
	          D[w]:=min + custo[w,v];
     End;
End;

{Procedure Determina(x:integer);
var TabVF:Array[vertice,vertice] of boolean;
begin
     For i:=1 to 5 do
     begin
          for j:=1 to 5 do
              if TabVF[j,i] = true then
              begin


              end;

     end;
end;}

end.
