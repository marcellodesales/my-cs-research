unit graph;

interface
uses pilha, Graphics, Forms, UManipulaOBJ, StdCtrls;

const MaxElementos = 16;
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
    function ShortestPath(Grafo:graphtype; origem, destino:integer; var Caminho:visitas; formulario:TForm; anima:TCheckBox):integer;
    function GetMenorCusto(grafo:graphtype; origem, destino:integer; var Visitados:visitas; var CaminhoCurto:visitas; formulario:TForm; anima:TCheckBox):integer;

implementation
uses Umaceio,UExemplo;

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

function GetMenorCusto(grafo:graphtype; origem, destino:integer; var Visitados:visitas; var CaminhoCurto:visitas; formulario:TForm; anima:TCheckBox):integer;
var nodegrafo:node;
    Custo,MenorCusto,NoMenor:Integer;
    AuxVisita:StackEntry;
    AuxCaminho:Visitas;
begin
     nodegrafo:=grafo[origem];
     MenorCusto:=infinito;
     Custo:=0; NoMenor:=-1;
     CreateStackE(AuxCaminho);
     if anima.Checked then
     begin
          PinteVertice(Origem,clRed,Formulario);
          if formulario = F_Maceio then
             Delay(0.0001)
          else
              if formulario = F_Exemplo then
                 Delay(0.5);
     end;
     while nodegrafo <> nil do
     begin
          if nodegrafo^.valor <> destino then
             if not JaVisitado(visitados,nodegrafo^.valor) then
             begin
                  PushE(nodegrafo^.valor,visitados);
                  Custo:=GetMenorCusto(grafo,nodegrafo^.valor, destino, Visitados, AuxCaminho,Formulario,anima);
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
     if anima.Checked then
        PinteVertice(origem,clGray,Formulario);
end;

function ShortestPath(Grafo:graphtype; origem, destino:integer; var Caminho:visitas; formulario:TForm; anima:TCheckBox):integer;
var Visitados:visitas;
begin
     CreateStackE(Caminho);
     CreateStackE(Visitados);
     PushE(origem,Visitados);
     if origem <> destino then
        ShortestPath:=GetMenorCusto(Grafo, origem, destino, Visitados, Caminho, formulario, anima)
     else ShortestPath:=0;
     PushE(origem,Caminho);
end;

end.
