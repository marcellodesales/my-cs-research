unit Uexemplo;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, jpeg, ExtCtrls, Antialiased, OvalBtn, Grids, MMSystem, UManipulaTBAdj,
  graph, pilha, Buttons, ComCtrls;

type
  TF_Exemplo = class(TForm)
    GroupBox1: TGroupBox;
    v1: TOvalButton;
    v5: TOvalButton;
    v4: TOvalButton;
    v3: TOvalButton;
    v2: TOvalButton;
    A12: TAntialiasedLine;
    A54: TAntialiasedLine;
    A15: TAntialiasedLine;
    A32: TAntialiasedLine;
    A34: TAntialiasedLine;
    A13: TAntialiasedLine;
    A52: TAntialiasedLine;
    A53: TAntialiasedLine;
    A24: TAntialiasedLine;
    cimadir3: TImage;
    Image1: TImage;
    Image2: TImage;
    Image3: TImage;
    Image4: TImage;
    Image5: TImage;
    Image6: TImage;
    Image7: TImage;
    Image8: TImage;
    V12: TLabel;
    V15: TLabel;
    V52: TLabel;
    V13: TLabel;
    V34: TLabel;
    V54: TLabel;
    V53: TLabel;
    V24: TLabel;
    V32: TLabel;
    GroupBox2: TGroupBox;
    tabela2: TStringGrid;
    GroupBox3: TGroupBox;
    Label2: TLabel;
    Label3: TLabel;
    EditOrigem: TEdit;
    EditDestino: TEdit;
    GroupBox4: TGroupBox;
    Label1: TLabel;
    EditMenor: TEdit;
    MemoCaminho: TMemo;
    Label4: TLabel;
    Label5: TLabel;
    StatusBar1: TStatusBar;
    Bevel1: TBevel;
    Button1: TSpeedButton;
    Button2: TSpeedButton;
    SpeedButton1: TSpeedButton;
    Checkanima: TCheckBox;
    procedure Button1Click(Sender: TObject);
    procedure manipulabutao(butao:integer);
    Procedure TabelaAdjacencia;
    Procedure InsereCusto;
    procedure FormCreate(Sender: TObject);
    procedure MudaCor(Objetoaresta,Objetocusto:String; Var ObjetoValor:String);
    procedure Som(numero:integer); //Retorna o som do número
    procedure Delay(tempo:single);
    procedure Button2Click(Sender: TObject);
    procedure IniciaCorGrafo;
    procedure Escrevecaminho(Nlinhas:integer);
    procedure DaValores_Aleatorios_Custos;
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure EditOrigemKeyPress(Sender: TObject; var Key: Char);
    procedure PinteVertice(num:integer;cor:integer);
    Procedure InCluiReiraValoresOrigemDestino(butao:TOvalButton);
    procedure v1Click(Sender: TObject);
    procedure v2Click(Sender: TObject);
    procedure v3Click(Sender: TObject);
    procedure v4Click(Sender: TObject);
    procedure v5Click(Sender: TObject);
  private
    { Private declarations }

  public
    { Public declarations }
  end;

var
  F_Exemplo  : TF_Exemplo;
  Grafo  : TCustos;
  TabAdj : TabelaAdjacencia;
  TabDis : TabelaDistancia;
  MeuGrafo:GraphType;
implementation
uses MsgDlg;
{$R *.DFM}

procedure TF_Exemplo.PinteVertice(num:integer;cor:integer);
var i:integer;
    nome:string;
begin
     nome:='v'+inttostr(num);
     for i:=0 to ComponentCount-1 Do
         if (Components[i] is TOvalButton) then
            If (Components[i] as TOvalButton).Name = nome then
               (Components[i] as TOvalButton).Color:=cor;
end;

procedure TF_Exemplo.Delay(tempo:single);
var Tempoini :TDateTime;
begin
     Tempoini := now;
     repeat
          Application.ProcessMessages;
     until Now > tempoini + Tempo * (1/24/60/60);
end;

procedure TF_Exemplo.Som(numero:integer); //Retorna o som do número
var NumSeg   :single;
    Tempoini :TDateTime;
begin
     Tempoini := now;
     NumSeg:=1;
     PlaySound(PChar(numero),HInstance, snd_ASync or snd_Memory or snd_Resource);
     repeat
          Application.ProcessMessages;
     until Now > tempoini + NumSeg * (1/24/60/60);
     SndPlaySound('Coloca nada para parar o som.wav',SND_ASync); //dá stop
end;

Procedure TF_Exemplo.TabelaAdjacencia;
var i,j:byte;
begin
     for i:=1 to 5 do
     begin
         tabela2.Cells[i,0]:='vértice '+inttostr(i);
         tabela2.Cells[0,i]:='vértice '+inttostr(i);
     end;
     Tabela2.Cells[2,1]:='V';
     Tabela2.Cells[3,1]:='V';
     Tabela2.Cells[5,1]:='V';
     Tabela2.Cells[4,2]:='V';
     Tabela2.Cells[2,3]:='V';
     Tabela2.Cells[4,3]:='V';
     Tabela2.Cells[2,5]:='V';
     Tabela2.Cells[3,5]:='V';
     Tabela2.Cells[4,5]:='V';
     For i:=1 to 5 do
         for j:=1 to 5 do
             if Tabela2.Cells[i,j]='' then
                Tabela2.Cells[i,j]:='F';
end;

procedure TF_Exemplo.manipulabutao(butao:integer);
var X:string;
    i:integer;
begin
     X:='v'+intToStr(butao);
     For i:=0 to ComponentCount-1 do
     begin
          if (Components[i] is TOvalButton) then
             if (Components[i] as TOvalButton).Name=X then
                (Components[i] as TOvalButton).Color:=clRed;
     end;
     Delay(1);
     For i:=0 to ComponentCount-1 do
     begin
          if (Components[i] is TOvalButton) then
             if (Components[i] as TOvalButton).Name=X then
                (Components[i] as TOvalButton).Color:=clBtnFace;
     end;
end;

procedure TF_Exemplo.IniciaCorGrafo;
var i:integer;
begin
     For i:=0 to ComponentCount-1 do
         if (Components[i] is TAntialiasedLine) then
         begin
            (Components[i] as TAntialiasedLine).Color:=clBlack;
            (Components[i] as TAntialiasedLine).ForeColor:=clBlack;
         end
         else
             if (Components[i] is TLabel)then
                (Components[i] as TLabel).Font.color:=clBlack
             else
                 if (Components[i] is TOvalButton) then
                    (Components[i] as TOvalButton).Color:=clBtnFace;
end;

procedure TF_Exemplo.MudaCor(Objetoaresta,Objetocusto:String; Var ObjetoValor:String);
var i,j:integer;
begin
     For i:=0 to ComponentCount-1 do
     begin
          if (Components[i] is TAntialiasedLine) then
             if (Components[i] as TAntialiasedLine).Name=ObjetoAresta then
             begin
                (Components[i] as TAntialiasedLine).Color:=clRed;
                (Components[i] as TAntialiasedLine).ForeColor:=clRed;
                Delay(0.5);
                For j:=0 to ComponentCount-1 do
                begin
                    if (Components[j] is TLabel) then
                       if (Components[j] as TLabel).Name=ObjetoCusto then
                       begin
                           (Components[j] as TLabel).Font.color:=clRed;
                            ObjetoValor:=(Components[j] as TLabel).Caption;
                       end;
                end;
                Delay(0.5);
             end;
     end;
end;

procedure TF_Exemplo.DaValores_Aleatorios_Custos;
var i,A:integer;
    B:string;
begin
     for i:=0 to ComponentCount-1 do
         if (Components[i] is TLabel) then
         begin
              B:=Copy((Components[i] as TLabel).Name,1,1);
              if B = 'V' then
              begin
                   A:=Random(10);
                   (Components[i] as TLabel).Caption:=intToStr(A);
              end;
         end;
end;

procedure TF_Exemplo.FormCreate(Sender: TObject);
var i:smallint;
    A:integer;
begin
     TabelaAdjacencia;
     DaValores_Aleatorios_Custos;
     MemoCaminho.Clear;
     IniciaCorGrafo;
     for i:=0 to ComponentCount-1 do
         if (Components[i] is TEdit) then
            (Components[i] as TEdit).clear;
     CreateGraph(MeuGrafo);
     for i:=1 to 5 do
         new(MeuGrafo[i]);
     MeuGrafo[1]^.valor:=2; MeuGrafo[1]^.custo:=StrToInt(V12.caption);
     new(MeuGrafo[1]^.prox);
     MeuGrafo[1]^.prox^.valor:=3; MeuGrafo[1]^.prox^.custo:=StrToInt(V13.caption);
     new(MeuGrafo[1]^.prox^.prox);
     MeuGrafo[1]^.prox^.prox^.valor:=5; MeuGrafo[1]^.prox^.prox^.custo:=StrToInt(V15.caption);
     MeuGrafo[1]^.prox^.prox^.prox:=nil;

     MeuGrafo[2]^.valor:=4; MeuGrafo[2]^.custo:=StrToInt(V24.caption);
     MeuGrafo[2]^.prox:=nil;

     MeuGrafo[3]^.valor:=2; MeuGrafo[3]^.custo:=StrToInt(V32.caption);
     new(MeuGrafo[3]^.prox);
     MeuGrafo[3]^.prox^.valor:=4; MeuGrafo[3]^.prox^.custo:=StrToInt(V34.caption);
     MeuGrafo[3]^.prox^.prox:=nil;

     MeuGrafo[4]:=nil;

     MeuGrafo[5]^.valor:=2; MeuGrafo[5]^.custo:=StrToInt(V52.caption);;
     new(MeuGrafo[5]^.prox);
     MeuGrafo[5]^.prox^.valor:=3; MeuGrafo[5]^.prox^.custo:=StrToInt(V53.caption);
     new(MeuGrafo[5]^.prox^.prox);
     MeuGrafo[5]^.prox^.prox^.valor:=4; MeuGrafo[5]^.prox^.prox^.custo:=StrToInt(V54.caption);
     MeuGrafo[5]^.prox^.prox^.prox:=nil;
end;
Procedure TF_Exemplo.InCluiReiraValoresOrigemDestino(butao:TOvalButton);
begin
     if EditOrigem.Text='' then
     begin
        EditOrigem.Text:=butao.Caption;
        Butao.Color:=clRed;
        Messagedialog('A origem será o vértice '+butao.Caption+'.',mtInformation,[mbYes],0);
     end
     else
         if EditOrigem.Text=butao.Caption then
         begin
            if (Messagedialog('Deseja usar também o vértice '+butao.Caption+' como destino?',mtconfirmation,[mbYes,mbNo],0)=mrYes) then
               EditDestino.Text:=butao.Caption
         end
         else
         begin
             Editdestino.Text:=butao.Caption;
             butao.Color:=clRed;
             Messagedialog('O Destino será o vértice '+butao.Caption+'.',mtInformation,[mbYes],0);
         end;
end;

procedure TF_Exemplo.Button1Click(Sender: TObject);
begin
     FormCreate(F_Exemplo);
     Som(1);
{     InsereCusto;
     IniciaCorGrafo;}
end;

Procedure TF_Exemplo.InsereCusto;
var Aresta,custo,valor:String;
    i,j:byte;
begin
     For i:=1 to 5 do
         for j:=1 to 5 do
             if Tabela2.Cells[j,i]='V' then
             begin
                Aresta:='A'+IntToStr(i)+IntToStr(j);
                Custo:='V'+IntToStr(i)+IntToStr(j);
                manipulabutao(i);
                manipulabutao(j);
                MudaCor(Aresta,Custo,valor);
                Som(1);
                TabAdj[j,i]:=StrToInt(Valor);
             end
             else
             begin
                  TabAdj[j,i]:=infinito;
             end;
end;
procedure TF_Exemplo.Button2Click(Sender: TObject);
var Custo:integer;
    valor:StackEntry;
    MenorCaminho:Visitas;
begin
     if ((EditOrigem.Text='') or (EditDestino.Text='')) then
        showmessage('É necessário digitar os dois valores da origem e destino')
     else
     begin
     EditMenor.Clear;
     MemoCaminho.Clear;
     IniciaCorGrafo;
     CreateStackE(MenorCaminho);
     Custo:=ShortestPath(MeuGrafo,StrToInt(EditOrigem.Text),StrToInt(EditDestino.Text),MenorCaminho,F_Exemplo,Checkanima);
     if Custo = Maxint then
        EditMenor.Text:='Infinito'
     else
         EditMenor.Text:=inttostr(custo);

     while not Empty(MenorCaminho) do
     begin
          PopE(valor,MenorCaminho);
          MemoCaminho.Lines.Insert(MemoCaminho.Lines.Count,IntToStr(valor));
     end;
     EditOrigem.SetFocus;
     Escrevecaminho(MemoCaminho.Lines.Count);
     end;
end;

procedure TF_Exemplo.Escrevecaminho(Nlinhas:integer);
var i,j,z,cont:integer;
    Aresta,Custo,A,B:string;
begin
      for i:=0 to ComponentCount-1 Do
          if (Components[i] is TOvalButton) then
          begin
             for j:=0 to MemoCaminho.Lines.Count do
                 if (Components[i] as TOvalButton).Caption = MemoCaminho.Lines[j-1] then
                    (Components[i] as TOvalButton).Color:=clRed;
          end
          else
              if (Components[i] is TAntialiasedLine) then
              begin
                   cont:=Length((Components[i] as TAntialiasedLine).name);
                   for z:=1 to Nlinhas do
                   begin
                        A:=MemoCaminho.lines[z-1]+MemoCaminho.lines[z];
                        B:=Copy((Components[i] as TAntialiasedLine).Name,2,cont-1);
                        if A=B then
                        begin
                           (Components[i] as TAntialiasedLine).Color:=clRed;
                           (Components[i] as TAntialiasedLine).ForeColor:=clRed;
                        end;
                   end;
              end
              else
                  if (Components[i] is TLabel) then
                  begin
                       cont:=Length((Components[i] as TLabel).name);
                       for z:=1 to Nlinhas do
                       begin
                            A:=MemoCaminho.lines[z-1]+MemoCaminho.lines[z];
                            B:=Copy((Components[i] as TLabel).Name,2,cont-1);
                            if A=B then
                                 (Components[i] as TLabel).Font.Color:=clRed;
                       end;
                  end;
end;

procedure TF_Exemplo.SpeedButton1Click(Sender: TObject);
begin
     Close;
end;

procedure TF_Exemplo.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Action:=caFree;
end;

procedure TF_Exemplo.EditOrigemKeyPress(Sender: TObject; var Key: Char);
begin
     if not (key in ['1'..'9',#13,#8]) then
        Key:=#0;
end;

procedure TF_Exemplo.v1Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v1);
end;

procedure TF_Exemplo.v2Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v2);
end;

procedure TF_Exemplo.v3Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v3);
end;

procedure TF_Exemplo.v4Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v4);
end;

procedure TF_Exemplo.v5Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v5);
end;

end.


