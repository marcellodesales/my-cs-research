unit Umaceio;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Antialiased, StdCtrls, OvalBtn, jpeg, ExtCtrls, Buttons, graph, pilha;

type
  TF_Maceio = class(TForm)
    MyBitmap: TImage;
    v1: TOvalButton;
    v2: TOvalButton;
    v3: TOvalButton;
    v4: TOvalButton;
    v5: TOvalButton;
    v6: TOvalButton;
    v7: TOvalButton;
    v8: TOvalButton;
    v9: TOvalButton;
    v10: TOvalButton;
    v11: TOvalButton;
    v12: TOvalButton;
    v13: TOvalButton;
    v15: TOvalButton;
    v14: TOvalButton;
    ufAL: TLabel;
    A12: TAntialiasedLine;
    A13: TAntialiasedLine;
    A34: TAntialiasedLine;
    A1415: TAntialiasedLine;
    A45: TAntialiasedLine;
    A56: TAntialiasedLine;
    A67: TAntialiasedLine;
    A78: TAntialiasedLine;
    A89: TAntialiasedLine;
    A910: TAntialiasedLine;
    A1011: TAntialiasedLine;
    A1112: TAntialiasedLine;
    A1213: TAntialiasedLine;
    A1314: TAntialiasedLine;
    A152: TAntialiasedLine;
    A24: TAntialiasedLine;
    A36: TAntialiasedLine;
    A57: TAntialiasedLine;
    v16: TOvalButton;
    A416: TAntialiasedLine;
    V916: TAntialiasedLine;
    A816: TAntialiasedLine;
    A1016: TAntialiasedLine;
    A216: TAntialiasedLine;
    A210: TAntialiasedLine;
    A211: TAntialiasedLine;
    A213: TAntialiasedLine;
    A1214: TAntialiasedLine;
    A214: TAntialiasedLine;
    A1113: TAntialiasedLine;
    A1013: TAntialiasedLine;
    A413: TAntialiasedLine;
    A1316: TAntialiasedLine;
    SpeedButton1: TSpeedButton;
    c13: TLabel;
    c36: TLabel;
    c67: TLabel;
    c78: TLabel;
    c89: TLabel;
    c816: TLabel;
    c916: TLabel;
    c45: TLabel;
    c24: TLabel;
    c413: TLabel;
    c416: TLabel;
    c12: TLabel;
    c216: TLabel;
    c210: TLabel;
    c211: TLabel;
    c213: TLabel;
    c215: TLabel;
    c1415: TLabel;
    c1214: TLabel;
    c214: TLabel;
    c1314: TLabel;
    c1213: TLabel;
    c1112: TLabel;
    c1011: TLabel;
    c1013: TLabel;
    c1316: TLabel;
    c1016: TLabel;
    c910: TLabel;
    c56: TLabel;
    c57: TLabel;
    SpeedButton2: TSpeedButton;
    EOrigem: TEdit;
    EDestino: TEdit;
    MenorCusto: TEdit;
    MMCaminho: TMemo;
    c34: TLabel;
    c1113: TLabel;
    Origem: TLabel;
    Label1: TLabel;
    Bevel1: TBevel;
    Bevel2: TBevel;
    Label2: TLabel;
    SpeedButton3: TSpeedButton;
    Checkanima: TCheckBox;
    SpeedButton4: TSpeedButton;
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure SpeedButton2Click(Sender: TObject);
    Procedure ValoresGrafoMaceio;
    procedure IniciaCorGrafo;
    procedure Escrevecaminho(Nlinhas:integer);
    procedure FormPaint(Sender: TObject);
    procedure EOrigemKeyPress(Sender: TObject; var Key: Char);
    procedure EDestinoKeyPress(Sender: TObject; var Key: Char);
    procedure SpeedButton3Click(Sender: TObject);
    procedure v1Click(Sender: TObject);
    Procedure InCluiReiraValoresOrigemDestino(butao:TOvalButton);
    procedure v3Click(Sender: TObject);
    procedure v2Click(Sender: TObject);
    procedure v4Click(Sender: TObject);
    procedure v5Click(Sender: TObject);
    procedure v6Click(Sender: TObject);
    procedure v7Click(Sender: TObject);
    procedure v8Click(Sender: TObject);
    procedure v9Click(Sender: TObject);
    procedure v10Click(Sender: TObject);
    procedure v11Click(Sender: TObject);
    procedure v12Click(Sender: TObject);
    procedure v13Click(Sender: TObject);
    procedure v14Click(Sender: TObject);
    procedure v15Click(Sender: TObject);
    procedure v16Click(Sender: TObject);
    procedure CheckanimaClick(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);

  private
    { Private declarations }
  public
    { Public declarations }
    procedure PinteVertice(num:integer;cor:integer);
  end;
  TBairros = Array[1..16] of string;
     // A primeira posição do array indica a quant de elementos do array
const Bairro :TBairros =('do Tabuleiro','do Farol','do Feitosa','do Jacintínho',
                       'da Mangabeiras','da Cruz das Almas','da Jatiúca','da Ponta Verde',
                       'da Pajuçara','do Jaraguá','do Prado','do Pontal','do Trapiche','Vergel',
                       'do Bom Parto','do Poço');
var
  F_Maceio: TF_Maceio;
  GrafoMaceio:GraphType;

implementation
uses MsgDlg;
{$R *.DFM}

procedure Ordena(Var A:string; Var B:string);
var aux:string;
begin
     if strtoint(A)>strtoint(b) then
     begin
          Aux:=A;
          A:=B;
          B:=Aux;
     end;
end;

procedure TF_Maceio.PinteVertice(num:integer;cor:integer);
var i:integer;
    nome:string;
begin
     nome:='v'+inttostr(num);
     for i:=0 to ComponentCount-1 Do
         if (Components[i] is TOvalButton) then
            If (Components[i] as TOvalButton).Name = nome then
               (Components[i] as TOvalButton).Color:=cor;
end;

procedure TF_Maceio.Escrevecaminho(Nlinhas:integer);
var i,j,z,cont:integer;
    A,B,C:string;
begin
      for i:=0 to ComponentCount-1 Do
          if (Components[i] is TOvalButton) then
          begin
             for j:=0 to MMCaminho.Lines.Count do
                 if (Components[i] as TOvalButton).Caption = MMCaminho.Lines[j-1] then
                    (Components[i] as TOvalButton).Color:=clRed;
          end
          else
              if (Components[i] is TAntialiasedLine) then
              begin
                   cont:=Length((Components[i] as TAntialiasedLine).name);
                   for z:=0 to Nlinhas-2 do
                   begin
                        A:=MMCaminho.lines[z];
                        C:=MMCaminho.lines[z+1];
                        Ordena(A,C);
                        B:='A'+A+C;
                        //PinteAr-esta(B,clRed);
                        //B:=Copy((Components[i] as TAntialiasedLine).Name,2,cont-1);
                        //if (A=B) or (B=C) then
                        //begin
                        If (Components[i] as TAntialiasedLine).Name = B then
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
                       for z:=0 to Nlinhas-2 do
                       begin
                            A:=MMCaminho.lines[z];
                            C:=MMCaminho.lines[z+1];
                            Ordena(A,C);
                            B:='c'+A+C;
                            //A:=MMCaminho.lines[z-1]+MMCaminho.lines[z];
                            //C:=MMCaminho.lines[z]+MMCaminho.lines[z-1];
                            //B:=Copy((Components[i] as TLabel).Name,2,cont-1);
                            If (Components[i] as TLabel).Name = B then
                               (Components[i] as TLabel).Color:=clRed;
                       end;
                  end;
end;

procedure TF_Maceio.IniciaCorGrafo;
var i:integer;
begin
     For i:=0 to ComponentCount-1 do
         if (Components[i] is TAntialiasedLine) then
         begin
            (Components[i] as TAntialiasedLine).Color:=clBlue;
            (Components[i] as TAntialiasedLine).ForeColor:=clBlue;
         end
         else
             if (Components[i] is TLabel) then
             begin
                  if (Components[i] as TLabel).transparent = false then
                  begin
                       (Components[i] as TLabel).Font.color:=clwhite;
                       (Components[i] as TLabel).color:=clBlue;
                  end;
             end
             else
                 if (Components[i] is TOvalButton) then
                    (Components[i] as TOvalButton).Color:=clBtnFace;
end;

procedure TF_Maceio.FormCreate(Sender: TObject);
var i,CompLabelCust:integer;
begin
     For i:=0 to ComponentCount-1 do
         if (Components[i] is TOvalButton) then
            (Components[i] as TOvalButton).ShowHint:=true
         else
         begin
           if (Components[i] is TLabel) then
           begin
             CompLabelCust:=Length((Components[i] as TLabel).Name);
             if CompLabelCust = 3 then
                (Components[i] as TLabel).Hint:='Custo '+Copy((Components[i] as TLabel).Name,2,1)+' <----> '+Copy((Components[i] as TLabel).Name,3,1);
             if CompLabelCust = 4 then
                (Components[i] as TLabel).Hint:='Custo '+Copy((Components[i] as TLabel).Name,2,1)+' <----> '+Copy((Components[i] as TLabel).Name,3,2);
             if CompLabelCust = 5 then
                (Components[i] as TLabel).Hint:='Custo '+Copy((Components[i] as TLabel).Name,2,2)+' <----> '+Copy((Components[i] as TLabel).Name,4,2);
             (Components[i] as TLabel).ShowHint:=true;
           end
           else
               if (Components[i] is TEdit) then
                  (Components[i] as TEdit).clear;
         end;
     ValoresGrafoMaceio;
end;

Procedure TF_Maceio.ValoresGrafoMaceio;
var i:smallint;
begin
     for i:=1 to 16 do
         new(GrafoMaceio[i]);
     GrafoMaceio[1]^.valor:=2; GrafoMaceio[1]^.custo:=StrToInt(C12.caption);
     new(GrafoMaceio[1]^.prox);
     GrafoMaceio[1]^.prox^.valor:=3; GrafoMaceio[1]^.prox^.custo:=StrToInt(c13.caption);
     GrafoMaceio[1]^.prox^.prox:=nil;

     GrafoMaceio[2]^.valor:=1; GrafoMaceio[2]^.custo:=StrToInt(c12.caption);
     new(GrafoMaceio[2]^.prox);
     GrafoMaceio[2]^.prox.valor:=4; GrafoMaceio[2]^.prox^.custo:=StrToInt(c24.caption);
     new(GrafoMaceio[2]^.prox^.prox);
     GrafoMaceio[2]^.prox^.prox^.valor:=10; GrafoMaceio[2]^.prox^.prox^.Custo:=StrToInt(c210.caption);
     new(GrafoMaceio[2]^.prox^.prox^.prox);
     GrafoMaceio[2]^.prox^.prox^.prox^.valor:=11; GrafoMaceio[2]^.prox^.prox^.prox^.custo:=StrToInt(c211.caption);
     new(GrafoMaceio[2]^.prox^.prox^.prox^.prox);
     GrafoMaceio[2]^.prox^.prox^.prox^.prox^.valor:=13; GrafoMaceio[2]^.prox^.prox^.prox^.prox^.custo:=StrToInt(c213.caption);
     new(GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox);
     GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.valor:=14; GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.custo:=StrToInt(c214.caption);
     new(GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.prox);
     GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.prox^.valor:=15; GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.prox^.custo:=StrToInt(c215.caption);
     new(GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.prox^.prox);
     GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.prox^.prox^.valor:=16; GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.prox^.prox^.custo:=StrToInt(c216.caption);
     GrafoMaceio[2]^.prox^.prox^.prox^.prox^.prox^.prox^.prox^.prox:=nil;

     GrafoMaceio[3]^.valor:=1; GrafoMaceio[3]^.custo:=StrToInt(c13.caption);
     new(GrafoMaceio[3]^.prox);
     GrafoMaceio[3]^.prox^.valor:=4; GrafoMaceio[3]^.prox^.custo:=StrToInt(c34.caption);
     new(GrafoMaceio[3]^.prox^.prox);
     GrafoMaceio[3]^.prox^.prox^.valor:=6; GrafoMaceio[3]^.prox^.prox^.custo:=StrToInt(c36.caption);
     GrafoMaceio[3]^.prox^.prox^.prox:=nil;

     GrafoMaceio[4]^.valor:=2; GrafoMaceio[4]^.custo:=StrToInt(c24.caption);
     new(GrafoMaceio[4]^.prox);
     GrafoMaceio[4]^.prox^.valor:=3; GrafoMaceio[4]^.prox^.custo:=StrToInt(c34.caption);
     new(GrafoMaceio[4]^.prox^.prox);
     GrafoMaceio[4]^.prox^.prox^.valor:=5; GrafoMaceio[4]^.prox^.prox^.custo:=StrToInt(c45.caption);
     new(GrafoMaceio[4]^.prox^.prox^.prox);
     GrafoMaceio[4]^.prox^.prox^.prox^.valor:=13; GrafoMaceio[4]^.prox^.prox^.prox^.custo:=StrToInt(c413.caption);
     new(GrafoMaceio[4]^.prox^.prox^.prox^.prox);
     GrafoMaceio[4]^.prox^.prox^.prox^.prox^.valor:=16; GrafoMaceio[4]^.prox^.prox^.prox^.prox^.custo:=StrToInt(c416.caption);
     GrafoMaceio[4]^.prox^.prox^.prox^.prox^.prox:=nil;

     GrafoMaceio[5]^.valor:=4; GrafoMaceio[5]^.custo:=StrToInt(c45.caption);;
     new(GrafoMaceio[5]^.prox);
     GrafoMaceio[5]^.prox^.valor:=6; GrafoMaceio[5]^.prox^.custo:=StrToInt(c56.caption);
     new(GrafoMaceio[5]^.prox^.prox);
     GrafoMaceio[5]^.prox^.prox^.valor:=7; GrafoMaceio[5]^.prox^.prox^.custo:=StrToInt(c57.caption);
     GrafoMaceio[5]^.prox^.prox^.prox:=nil;

     GrafoMaceio[6]^.valor:=3; GrafoMaceio[6]^.custo:=StrToInt(c36.caption);;
     new(GrafoMaceio[6]^.prox);
     GrafoMaceio[6]^.prox^.valor:=5; GrafoMaceio[6]^.prox^.custo:=StrToInt(c56.caption);
     new(GrafoMaceio[6]^.prox^.prox);
     GrafoMaceio[6]^.prox^.prox^.valor:=7; GrafoMaceio[6]^.prox^.prox^.custo:=StrToInt(c67.caption);
     GrafoMaceio[6]^.prox^.prox^.prox:=nil;

     GrafoMaceio[7]^.valor:=5; GrafoMaceio[7]^.custo:=StrToInt(c57.caption);;
     new(GrafoMaceio[7]^.prox);
     GrafoMaceio[7]^.prox^.valor:=6; GrafoMaceio[7]^.prox^.custo:=StrToInt(c67.caption);
     new(GrafoMaceio[7]^.prox^.prox);
     GrafoMaceio[7]^.prox^.prox^.valor:=8; GrafoMaceio[7]^.prox^.prox^.custo:=StrToInt(c78.caption);
     GrafoMaceio[7]^.prox^.prox^.prox:=nil;

     GrafoMaceio[8]^.valor:=7; GrafoMaceio[8]^.custo:=StrToInt(c78.caption);;
     new(GrafoMaceio[8]^.prox);
     GrafoMaceio[8]^.prox^.valor:=9; GrafoMaceio[8]^.prox^.custo:=StrToInt(c89.caption);
     new(GrafoMaceio[8]^.prox^.prox);
     GrafoMaceio[8]^.prox^.prox^.valor:=16; GrafoMaceio[8]^.prox^.prox^.custo:=StrToInt(c816.caption);
     GrafoMaceio[8]^.prox^.prox^.prox:=nil;

     GrafoMaceio[9]^.valor:=8; GrafoMaceio[9]^.custo:=StrToInt(c89.caption);;
     new(GrafoMaceio[9]^.prox);
     GrafoMaceio[9]^.prox^.valor:=10; GrafoMaceio[9]^.prox^.custo:=StrToInt(c910.caption);
     new(GrafoMaceio[9]^.prox^.prox);
     GrafoMaceio[9]^.prox^.prox^.valor:=16; GrafoMaceio[9]^.prox^.prox^.custo:=StrToInt(c916.caption);
     GrafoMaceio[9]^.prox^.prox^.prox:=nil;

     GrafoMaceio[10]^.valor:=2; GrafoMaceio[10]^.custo:=StrToInt(c210.caption);
     new(GrafoMaceio[10]^.prox);
     GrafoMaceio[10]^.prox^.valor:=9; GrafoMaceio[10]^.prox^.custo:=StrToInt(c910.caption);
     new(GrafoMaceio[10]^.prox^.prox);
     GrafoMaceio[10]^.prox^.prox^.valor:=11; GrafoMaceio[10]^.prox^.prox^.custo:=StrToInt(c1011.caption);
     new(GrafoMaceio[10]^.prox^.prox^.prox);
     GrafoMaceio[10]^.prox^.prox^.prox^.valor:=13; GrafoMaceio[10]^.prox^.prox^.prox^.custo:=StrToInt(c1013.caption);
     new(GrafoMaceio[10]^.prox^.prox^.prox^.prox);
     GrafoMaceio[10]^.prox^.prox^.prox^.prox^.valor:=16; GrafoMaceio[10]^.prox^.prox^.prox^.prox^.custo:=StrToInt(c1016.caption);
     GrafoMaceio[10]^.prox^.prox^.prox^.prox^.prox:=nil;

     GrafoMaceio[11]^.valor:=2; GrafoMaceio[11]^.custo:=StrToInt(c211.caption);;
     new(GrafoMaceio[11]^.prox);
     GrafoMaceio[11]^.prox^.valor:=10; GrafoMaceio[11]^.prox^.custo:=StrToInt(c1011.caption);
     new(GrafoMaceio[11]^.prox^.prox);
     GrafoMaceio[11]^.prox^.prox^.valor:=12; GrafoMaceio[11]^.prox^.prox^.custo:=StrToInt(c1112.caption);
     new(GrafoMaceio[11]^.prox^.prox^.prox);
     GrafoMaceio[11]^.prox^.prox^.prox^.valor:=13; GrafoMaceio[11]^.prox^.prox^.prox^.custo:=StrToInt(c1113.caption);
     GrafoMaceio[11]^.prox^.prox^.prox^.prox:=nil;

     GrafoMaceio[12]^.valor:=11; GrafoMaceio[12]^.custo:=StrToInt(c1112.caption);;
     new(GrafoMaceio[12]^.prox);
     GrafoMaceio[12]^.prox^.valor:=13; GrafoMaceio[12]^.prox^.custo:=StrToInt(c1213.caption);
     new(GrafoMaceio[12]^.prox^.prox);
     GrafoMaceio[12]^.prox^.prox^.valor:=14; GrafoMaceio[12]^.prox^.prox^.custo:=StrToInt(c1214.caption);
     GrafoMaceio[12]^.prox^.prox^.prox:=nil;

     GrafoMaceio[13]^.valor:=2; GrafoMaceio[13]^.custo:=StrToInt(c213.caption);
     new(GrafoMaceio[13]^.prox);
     GrafoMaceio[13]^.prox^.valor:=4; GrafoMaceio[13]^.prox^.custo:=StrToInt(c413.caption);
     new(GrafoMaceio[13]^.prox^.prox);
     GrafoMaceio[13]^.prox^.prox^.valor:=10; GrafoMaceio[13]^.prox^.prox^.custo:=StrToInt(c1013.caption);
     new(GrafoMaceio[13]^.prox^.prox^.prox);
     GrafoMaceio[13]^.prox^.prox^.prox^.valor:=11; GrafoMaceio[13]^.prox^.prox^.prox^.custo:=StrToInt(c1113.caption);
     new(GrafoMaceio[13]^.prox^.prox^.prox^.prox);
     GrafoMaceio[13]^.prox^.prox^.prox^.prox^.valor:=12; GrafoMaceio[13]^.prox^.prox^.prox^.prox^.custo:=StrToInt(c1213.caption);
     new(GrafoMaceio[13]^.prox^.prox^.prox^.prox^.prox);
     GrafoMaceio[13]^.prox^.prox^.prox^.prox^.prox^.valor:=14; GrafoMaceio[13]^.prox^.prox^.prox^.prox^.prox^.custo:=StrToInt(c1314.caption);
     new(GrafoMaceio[13]^.prox^.prox^.prox^.prox^.prox^.prox);
     GrafoMaceio[13]^.prox^.prox^.prox^.prox^.prox^.prox^.valor:=16; GrafoMaceio[13]^.prox^.prox^.prox^.prox^.prox^.prox^.custo:=StrToInt(c1316.caption);
     GrafoMaceio[13]^.prox^.prox^.prox^.prox^.prox^.prox^.prox:=nil;

     GrafoMaceio[14]^.valor:=2; GrafoMaceio[14]^.custo:=StrToInt(c214.caption);;
     new(GrafoMaceio[14]^.prox);
     GrafoMaceio[14]^.prox^.valor:=12; GrafoMaceio[14]^.prox^.custo:=StrToInt(c1214.caption);
     new(GrafoMaceio[14]^.prox^.prox);
     GrafoMaceio[14]^.prox^.prox^.valor:=13; GrafoMaceio[14]^.prox^.prox^.custo:=StrToInt(c1314.caption);
     new(GrafoMaceio[14]^.prox^.prox^.prox);
     GrafoMaceio[14]^.prox^.prox^.prox^.valor:=15; GrafoMaceio[14]^.prox^.prox^.prox^.custo:=StrToInt(c1415.caption);
     GrafoMaceio[14]^.prox^.prox^.prox^.prox:=nil;

     GrafoMaceio[15]^.valor:=2; GrafoMaceio[15]^.custo:=StrToInt(c215.caption);
     new(GrafoMaceio[15]^.prox);
     GrafoMaceio[15]^.prox^.valor:=14; GrafoMaceio[15]^.prox^.custo:=StrToInt(c1415.caption);
     GrafoMaceio[15]^.prox^.prox:=nil;

     GrafoMaceio[16]^.valor:=2; GrafoMaceio[16]^.custo:=StrToInt(c216.caption);
     new(GrafoMaceio[16]^.prox);
     GrafoMaceio[16]^.prox.valor:=4; GrafoMaceio[16]^.prox^.custo:=StrToInt(c416.caption);
     new(GrafoMaceio[16]^.prox^.prox);
     GrafoMaceio[16]^.prox^.prox^.valor:=8; GrafoMaceio[16]^.prox^.prox^.Custo:=StrToInt(c816.caption);
     new(GrafoMaceio[16]^.prox^.prox^.prox);
     GrafoMaceio[16]^.prox^.prox^.prox^.valor:=9; GrafoMaceio[16]^.prox^.prox^.prox^.custo:=StrToInt(c916.caption);
     new(GrafoMaceio[16]^.prox^.prox^.prox^.prox);
     GrafoMaceio[16]^.prox^.prox^.prox^.prox^.valor:=10; GrafoMaceio[16]^.prox^.prox^.prox^.prox^.custo:=StrToInt(c1016.caption);
     new(GrafoMaceio[16]^.prox^.prox^.prox^.prox^.prox);
     GrafoMaceio[16]^.prox^.prox^.prox^.prox^.prox^.valor:=13; GrafoMaceio[16]^.prox^.prox^.prox^.prox^.prox^.custo:=StrToInt(c1316.caption);
     GrafoMaceio[16]^.prox^.prox^.prox^.prox^.prox^.prox:=nil;
end;

procedure TF_Maceio.SpeedButton1Click(Sender: TObject);
begin
     Close;
end;

procedure TF_Maceio.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Action:=caFree;
end;

procedure TF_Maceio.SpeedButton2Click(Sender: TObject);
var Custo:integer;
    valor:StackEntry;
    MenorCaminho:Visitas;
begin
     if (EDestino.Text='') and (EOrigem.Text='') then
        Messagedialog('É necessário digitar os valores da origem e do destino!',mtInformation,[mbOK],0)
     else
         if EDestino.Text='' then
           Messagedialog('É necessário digitar tmabém o valor do destino!',mtInformation,[mbOK],0)
         else
     if (EOrigem.Text='') then
        Messagedialog('É necessário digitar também o valor da origem!',mtInformation,[mbOK],0)
     else
     begin
          Menorcusto.Clear;
          MMCaminho.Clear;
          IniciaCorGrafo;
          CreateStackE(MenorCaminho);
          Custo:=ShortestPath(GrafoMaceio,StrToInt(EOrigem.Text),StrToInt(EDestino.Text),MenorCaminho,F_Maceio,Checkanima);
          if Custo = Maxint then
             Menorcusto.Text:='Infinito'
          else
              Menorcusto.Text:=inttostr(custo);

          while not Empty(MenorCaminho) do
          begin
               PopE(valor,MenorCaminho);
               MMCaminho.Lines.Insert(MMCaminho.Lines.Count,IntToStr(valor));
          end;
          EOrigem.SetFocus;
          Escrevecaminho(MMCaminho.Lines.Count);
     end;
end;

procedure TF_Maceio.FormPaint(Sender: TObject);
var
  x, y: Integer;
  iBMWid, iBMHeight : Integer;
begin
    iBMWid := MyBitmap.Width;
{    iBMHeight := MyBitmap.Height;
    y := 0;
    while y < Height do
    begin
         x := 0;
         while x < Width do
         begin
             Canvas.Draw(x, y, MyBitmap);
             x := x + iBMWid;
         end;
         y := y + iBMHeight;
    end;}
end;

procedure TF_Maceio.EOrigemKeyPress(Sender: TObject; var Key: Char);
begin
     if not (key in ['0'..'9',#13,#8]) then
        Key:=#0;
end;

procedure TF_Maceio.EDestinoKeyPress(Sender: TObject; var Key: Char);
begin
     if not (key in ['0'..'9',#13,#8]) then
        Key:=#0;
end;

procedure TF_Maceio.SpeedButton3Click(Sender: TObject);
begin
     Menorcusto.Clear;
     MMCaminho.Clear;
     EOrigem.Clear;
     EDestino.Clear;
     IniciaCorGrafo;
end;

Procedure TF_Maceio.InCluiReiraValoresOrigemDestino(butao:TOvalButton);
begin
     if EOrigem.Text='' then
     begin
        EOrigem.Text:=butao.Caption;
        Butao.Color:=clRed;
        Messagedialog('A Origem será o bairro '+Bairro[Strtoint(butao.Caption)]+'.',mtInformation,[mbYes],0);
     end
     else
         if EOrigem.Text=butao.Caption then
         begin
            if (Messagedialog('Deseja usar também o bairro '+Bairro[Strtoint(butao.Caption)]+' como destino?',mtconfirmation,[mbYes,mbNo],0)=mrYes) then
               EDestino.Text:=butao.Caption
         end
         else
         begin
             Edestino.Text:=butao.Caption;
             butao.Color:=clRed;
             Messagedialog('O Destino será o bairro '+Bairro[Strtoint(butao.Caption)]+'.',mtInformation,[mbYes],0);
         end;
end;

procedure TF_Maceio.v1Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v1);
end;

procedure TF_Maceio.v2Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v2);
end;

procedure TF_Maceio.v3Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v3);
end;


procedure TF_Maceio.v4Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v4);
end;

procedure TF_Maceio.v5Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v5);
end;

procedure TF_Maceio.v6Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v6);
end;

procedure TF_Maceio.v7Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v7);
end;

procedure TF_Maceio.v8Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v8);
end;

procedure TF_Maceio.v9Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v9);
end;

procedure TF_Maceio.v10Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v10);
end;

procedure TF_Maceio.v11Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v11);
end;

procedure TF_Maceio.v12Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v12);
end;

procedure TF_Maceio.v13Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v13);
end;

procedure TF_Maceio.v14Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v14);
end;

procedure TF_Maceio.v15Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v15);
end;

procedure TF_Maceio.v16Click(Sender: TObject);
begin
     InCluiReiraValoresOrigemDestino(v16);
end;

procedure TF_Maceio.CheckanimaClick(Sender: TObject);
begin
     if checkanima.Checked then
        MessageDialog('Neste grafo exemplo, ocorrerá muitas iterações. Teremos +/- 4min de duração. A escolha é sua.',mtWarning,[mbOK],0);
end;

procedure TF_Maceio.SpeedButton4Click(Sender: TObject);
begin
     Print;
end;

end.
