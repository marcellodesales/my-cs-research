unit graph;

interface
uses pilha;



const MaxElementos = 10;
      Infinito=MaxInt;
type
    tipodado=integer;
    node=^nnode;
    nnode = record
         custo:integer;
         valor:integer;
         prox:node;
    end;

    graphtype = array[1..MaxElementos] of node;
    visitas = StackTypeE;

    Procedure CreateGraph(var graph:graphtype);
    function ShortestPath(Grafo:graphtype; origem, destino:integer; var Caminho:visitas):integer;

implementation

Procedure CreateGraph(var graph:graphtype);
var i:smallint;
begin
     for i:=1 to MaxElementos do
         graph[i]:=nil;
end;

function JaVisitado(visitados:visitas; target:integer):boolean;
var i:integer;
begin
     JaVisitado:=false;
     for i:=1 to visitados.Top  do
         if visitados.StackArray[i] = target then
            JaVisitado:=true;
end;

function GetMenorCusto(grafo:graphtype; origem, destino:integer; var Visitados:visitas; var CaminhoCurto:visitas):integer;
var nodegrafo:node;
    Custo,MenorCusto,NoMenor:Integer;
    AuxVisita:StackEntry;
    AuxCaminho:Visitas;
begin
     nodegrafo:=grafo[origem];
     MenorCusto:=infinito;
     Custo:=0; NoMenor:=-1;
     CreateStackE(AuxCaminho);
     while nodegrafo <> nil do
     begin
          if nodegrafo^.valor <> destino then
             if not JaVisitado(visitados,nodegrafo^.valor) then
             begin
                  PushE(nodegrafo^.valor,visitados);
                  Custo:=GetMenorCusto(grafo,nodegrafo^.valor, destino, Visitados, AuxCaminho);
                  if Custo <> infinito then Custo:=Custo+nodegrafo^.custo;
                  PopE(AuxVisita,Visitados);
             end
             else
                 Custo:=infinito
          else
              begin
                   Custo:=nodegrafo^.custo;
                   PopEAll(AuxCaminho);
              end;
          if Custo < MenorCusto then
          begin
            MenorCusto:=Custo;
            NoMenor:=nodegrafo^.valor;
            CopyStackE(AuxCaminho,CaminhoCurto);
          end;
          nodegrafo:=nodegrafo.prox;
     end;
     if NoMenor <> -1 then
          PushE(NoMenor,CaminhoCurto);
     GetMenorCusto:=MenorCusto;
end;

function ShortestPath(Grafo:graphtype; origem, destino:integer; var Caminho:visitas):integer;
var Visitados:visitas;
begin
     CreateStackE(Caminho);
     CreateStackE(Visitados);
     PushE(origem,Visitados);
     if origem <> destino then
        ShortestPath:=GetMenorCusto(Grafo, origem, destino, Visitados, Caminho)
     else ShortestPath:=0;
     PushE(origem,Caminho);
end;

end.
