unit testagrafo;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,graph,
  StdCtrls, pilha;

type
  TForm1 = class(TForm)
    Button1: TButton;
    EditMenor: TEdit;
    Label1: TLabel;
    EditOrigem: TEdit;
    Label2: TLabel;
    EditDestino: TEdit;
    Label3: TLabel;
    MemoCaminho: TMemo;
    Label4: TLabel;
    procedure FormCreate(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
  MeuGrafo:GraphType;
implementation

{$R *.DFM}

procedure TForm1.FormCreate(Sender: TObject);
var i:smallint;
begin
     CreateGraph(MeuGrafo);
     for i:=1 to 6 do
         new(MeuGrafo[i]);

     MeuGrafo[1]^.valor:=4; MeuGrafo[1]^.custo:=6;
     new(MeuGrafo[1]^.prox);
     MeuGrafo[1]^.prox^.valor:=3; MeuGrafo[1]^.prox^.custo:=4;
     MeuGrafo[1]^.prox^.prox:=nil;

     MeuGrafo[2]^.valor:=1; MeuGrafo[2]^.custo:=3;
     new(MeuGrafo[2]^.prox);
     MeuGrafo[2]^.prox^.valor:=5; MeuGrafo[2]^.prox^.custo:=2;
     new(MeuGrafo[2]^.prox^.prox);
     MeuGrafo[2]^.prox^.prox^.valor:=4; MeuGrafo[2]^.prox^.prox^.custo:=5;
     MeuGrafo[2]^.prox^.prox^.prox:=nil;

     MeuGrafo[3]^.valor:=6; MeuGrafo[3]^.custo:=10;
     MeuGrafo[3]^.prox:=nil;

     MeuGrafo[4]^.valor:=1; MeuGrafo[4]^.custo:=6;
     new(MeuGrafo[4]^.prox);
     MeuGrafo[4]^.prox^.valor:=3; MeuGrafo[4]^.prox^.custo:=3;
     new(MeuGrafo[4]^.prox^.prox);
     MeuGrafo[4]^.prox^.prox^.valor:=5; MeuGrafo[4]^.prox^.prox^.custo:=4;
     MeuGrafo[4]^.prox^.prox^.prox:=nil;

     MeuGrafo[5]^.valor:=1; MeuGrafo[5]^.custo:=8;
     new(MeuGrafo[5]^.prox);
     MeuGrafo[5]^.prox^.valor:=6; MeuGrafo[5]^.prox^.custo:=2;
     MeuGrafo[5]^.prox^.prox:=nil;

     MeuGrafo[6]^.valor:=2; MeuGrafo[6]^.custo:=7;
     MeuGrafo[6]^.prox:=nil;

end;

procedure TForm1.Button1Click(Sender: TObject);
var Custo:integer;
    valor:StackEntry;
    MenorCaminho:Visitas;
begin
     EditMenor.Clear;
     MemoCaminho.Clear;
     CreateStackE(MenorCaminho);
     Custo:=ShortestPath(MeuGrafo,StrToInt(EditOrigem.Text),StrToInt(EditDestino.Text),MenorCaminho);
     EditMenor.Text:=IntToStr(Custo);
     while not Empty(MenorCaminho) do
     begin
          PopE(valor,MenorCaminho);
          MemoCaminho.Lines.Insert(MemoCaminho.Lines.Count,IntToStr(valor));
     end;
     EditOrigem.SetFocus;
end;

end.
