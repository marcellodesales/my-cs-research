unit grafos;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs;

type
  TForm1 = class(TForm)
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

function distance(origem, destino, visitados):integer;
begin
     node:=grafo[origem];
     auxdist:=infinito;
     while node <> nil do
     begin
          custo:=node^.custo;
          if node^.valor <> destino
             if node^.valor nao visitado then
                custo:=distance(node^.valor, destino, visitados)
             else
                 custo:=infinito;


end;
{$R *.DFM}

end.
 