unit UGramToAf;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons;

type
  TF_GrToAfd = class(TForm)
    GroupBox2: TGroupBox;
    Label10: TLabel;
    Label11: TLabel;
    Label12: TLabel;
    Label13: TLabel;
    MemoProducoes: TMemo;
    EditVN: TEdit;
    EditVT: TEdit;
    EditSInicial: TEdit;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    SpeedButton1: TSpeedButton;
    SpeedButton4: TSpeedButton;
    GroupBox1: TGroupBox;
    Label1: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label2: TLabel;
    Label14: TLabel;
    EditEstados: TEdit;
    EditAlfabeto: TEdit;
    EditEInicial: TEdit;
    EditEFinal: TEdit;
    MemoTransicoes: TMemo;
    Label5: TLabel;
    procedure EditVNKeyPress(Sender: TObject; var Key: Char);
    procedure EditVTKeyPress(Sender: TObject; var Key: Char);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    //procedure EditVNKeyPress(Sender: TObject; var Key: Char);
    //procedure EditVTKeyPress(Sender: TObject; var Key: Char);
    procedure EditSInicialKeyPress(Sender: TObject; var Key: Char);
    Function ProducaoValida(Producao:string):Boolean;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

const MaxProducoes = 20;
      seta = '--->';
      vazio='&';
var
  F_GrToAfd: TF_GrToAfd;

  {   Gramatica  }
  VT,VN,SInicial:string;
  Producoes:array[1..MaxProducoes] of string;

  {   AFD   }
  Estados, Alfabeto, EstadoInicial, EstadoFinal:string;
  Transicoes:array[1..MaxProducoes] of string;

implementation
uses UProducao;
{$R *.DFM}

procedure TF_GrToAfd.EditVNKeyPress(Sender: TObject; var Key: Char);
begin
     key:=upcase(key);
     if not(key in ['A'..'Z',#8]) then
        key:=#0
     else
         if pos(key,EditVn.Text)<>0 then
         begin
              ShowMessage('{ '+key+' } Esse valor não-terminal já foi informado. Tente outro.');
              key:=#0;
         end;
end;

procedure TF_GrToAfd.EditVTKeyPress(Sender: TObject; var Key: Char);
begin
     if key in ['A'..'Z'] then
        key:=char(ord(key)+32);
     if (key in ['a'..'z','0'..'9',#8]) then
     begin
      if (pos(key,EditVT.Text)<>0) then
      begin
        ShowMessage('{ '+key+' } Esse valor terminal já foi informado. Tente outro.');
        key:=#0;
      end;
     end
     else key:=#0;

end;

function ParteEsquerda(producao:string):string;
var i:smallint;
begin
     i:=pos(seta,producao);
     ParteEsquerda:=copy(producao,1,i-1);
end;

function ParteDireita(producao:string):string;
var i:smallint;
begin
     i:=pos(seta,producao);
     ParteDireita:=copy(producao,i+4,length(producao)-i+1);
end;

Function TF_GrToAfd.ProducaoValida(Producao:string):Boolean;
var Aux:String;
begin
     ProducaoValida:=true;
     Aux:=Copy(Producao,1,1);
     if Pos(Aux,EditVn.Text)=0 then
     begin
        ProducaoValida:=false;
        exit;
     end;

     Aux:=Copy(Producao,6,1);
     if ((Aux='&') and (length(ParteDireita(Producao))>1)) or ((Aux<>'&') and (Pos(Aux,EditVT.Text)=0)) then
     begin
        ProducaoValida:=false;
        exit;
     end;

     Aux:=ParteDireita(Producao);
     if length(Aux)>1 then
        if Pos(Aux[2],EditVn.Text) = 0 then
        begin
             ProducaoValida:=false;
              exit;
        end;
end;

function SeparaVirgula(texto:string):string;
var aux:string;
    i:byte;
begin
     aux:=texto[1];
     for i:=2 to length(texto) do
         aux:=aux+','+texto[i];
     SeparaVirgula:=aux;
end;

procedure EncontraAFDEquivalente;
var NovoEstado:char;
    i, indEstado, indAlfabeto, indTrans :byte;
    PartEsqProd, PartDirProd, PartEsqTrans, PartDirTrans :string;

begin
     { Definindo todos Estados do Automato}
     for NovoEstado := 'A' to 'Z' do
         if Pos(NovoEstado,VN) = 0 then
            break;
      Estados:=VN+NovoEstado;

      { Definindo o Alfabeto do Automato }
      Alfabeto:=VT;

      { Definindo o Estado Inicial }
      EstadoInicial:=SInicial;

      { Encontrando os Estados Finais }
      i:=1;
      EstadoFinal:=NovoEstado;
      while Producoes[i]<>'' do
      begin
           if ParteDireita(Producoes[i])=vazio then
              EstadoFinal:=ParteEsquerda(Producoes[i])+EstadoFinal;
           inc(i);
      end;

      { Encontrando as Transicoes do Automato }
      indTrans:=1;
      for indEstado := 1 to length(Estados) do
          for indAlfabeto := 1 to length(Alfabeto) do
          begin
               i:=1;
               PartEsqTrans:=Estados[indEstado]+','+Alfabeto[indAlfabeto];
               PartDirTrans:='';
               while Producoes[i] <> '' do
               begin
                    PartEsqProd:=ParteEsquerda(Producoes[i]);
                    PartDirProd:=ParteDireita(Producoes[i]);
                    if (PartEsqProd=Estados[indEstado]) and
                       (PartDirProd[1]=Alfabeto[indAlfabeto]) then
                    begin
                         if length(PartDirProd)>1 then
                            PartDirTrans:=PartDirTrans+PartDirProd[2]
                         else PartDirTrans:=PartDirTrans+NovoEstado;
                    end;
                    inc(i);
               end;
               if PartDirTrans='' then PartDirTrans:=vazio
               else PartDirTrans:='{'+SeparaVirgula(PartDirTrans)+'}';
               PartEsqTrans:='('+PartEsqTrans+') = ';
               Transicoes[indTrans]:=PartEsqTrans+PartDirTrans;
               inc(IndTrans);
          end;
end;

procedure TF_GrToAfd.SpeedButton1Click(Sender: TObject);
var i:byte;
begin
     { Validando Dados do Automato }
     if (EditVN.Text='') or (EditVT.Text='') or
        (EditSInicial.Text='') or (MemoProducoes.Lines.Count=0) then
     begin
          ShowMessage('Existem informações do gramática que não foram fornecidas.'+#13+
          'Verifique as informações digitadas!!!');
     end;

     If (Pos(EditSInicial.Text,EditVN.Text)=0) then
     begin
          ShowMessage('O símbolo inicial da gramática não pertencem aos valores não-terminais.');
          exit;
     end;

     i:=0;
     while MemoProducoes.Lines[i]<>'' do
     begin
          if not ProducaoValida(MemoProducoes.Lines[i]) then
          begin
               ShowMessage('Alguma das produções contém valores terminais ou não-terminais inválido!!!');
               exit;
          end;
          inc(i);
     end;

     { Inicializando Automato com valores fornecidos pelo usuário }
     VN:=EditVN.Text;
     VT:=EditVT.Text;
     SInicial:=EditSInicial.Text;
     i:=1;
     while MemoProducoes.Lines[i-1]<>'' do
     begin
         Producoes[i] := trim(MemoProducoes.Lines[i-1]);
         inc(i);
     end;

     EncontraAFDEquivalente;

     EditEstados.Text := Estados;
     EditAlfabeto.Text := Alfabeto;
     EditEInicial.Text := EstadoInicial;
     EditEFinal.Text := EstadoFinal;
     i:=1;
     while Transicoes[i]<>'' do
     begin
          MemoTransicoes.Lines.Insert(MemoTransicoes.Lines.Count,Transicoes[i]);
          inc(i);
     end;
end;

procedure TF_GrToAfd.SpeedButton3Click(Sender: TObject);
begin
     FormCreate(self);
end;

procedure TF_GrToAfd.SpeedButton2Click(Sender: TObject);
begin
     Application.CreateForm(TF_Producoes,F_Producoes);
     F_Producoes.show;
end;

procedure TF_GrToAfd.FormCreate(Sender: TObject);
var i:integer;
begin
     For i:=1 to MaxProducoes do
     begin
        Transicoes[i]:='';
        Producoes[i]:='';
     end;
     VT:=''; VN:=''; SInicial:='';
     Estados:=''; Alfabeto:=''; EstadoInicial:=''; EstadoFinal:='';

     For i:=0 to ComponentCount-1 do
         if (Components[i] is TEdit) then
            (Components[i] as TEdit).Clear;
     self.MemoTransicoes.Clear;
     self.MemoProducoes.Clear;
end;

procedure TF_GrToAfd.SpeedButton4Click(Sender: TObject);
begin
     close;
end;

procedure TF_GrToAfd.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Action:=caFree;
end;

procedure TF_GrToAfd.EditSInicialKeyPress(Sender: TObject; var Key: Char);
begin
     key:=upcase(key);
     if not (key in ['A'..'Z',#8]) then
     begin
          key:=#0;
          exit;
     end;
     if EditVn.Text='' then
     begin
        ShowMessage('Primeiro você deve informa os estados não terminais.');
        key:=#0;
        EditVn.SetFocus;
     end
     else
     begin
         if (key<>#8) and (Pos(key,EditVn.Text)=0) then
         begin
              Showmessage('O símbolo inicial tem que pertencer aos estados não-terminais.');
              key:=#0;
          end
     end;
end;

end.
