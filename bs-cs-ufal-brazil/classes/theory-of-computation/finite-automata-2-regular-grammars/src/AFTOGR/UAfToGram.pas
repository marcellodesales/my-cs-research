unit UAfToGram;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Buttons, StdCtrls, ExtCtrls;

type
  TF_AfToGR = class(TForm)
    GroupBox1: TGroupBox;
    Label1: TLabel;
    Eestados: TEdit;
    Ealfabeto: TEdit;
    Label3: TLabel;
    EEinicial: TEdit;
    EEFinal: TEdit;
    Label4: TLabel;
    Label5: TLabel;
    MTransicoes: TMemo;
    Label2: TLabel;
    GroupBox2: TGroupBox;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    MProducoes: TMemo;
    ESimboloNT: TEdit;
    ESimboloT: TEdit;
    ESimboloINI: TEdit;
    SpeedButton2: TSpeedButton;
    SpeedButton1: TSpeedButton;
    bitbtn1: TSpeedButton;
    SpeedButton3: TSpeedButton;
    Bevel1: TBevel;
    Label10: TLabel;
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure EestadosKeyPress(Sender: TObject; var Key: Char);
    procedure EalfabetoKeyPress(Sender: TObject; var Key: Char);
    procedure EEinicialKeyPress(Sender: TObject; var Key: Char);
    procedure EEFinalKeyPress(Sender: TObject; var Key: Char);
    procedure BitBtn1Click(Sender: TObject);
    Function TransicaoValida(Transicao:string):Boolean;
    procedure SpeedButton2Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

const MaxProducoes = 20;
      seta = '=';
      seta2 = '--->';
var
  F_AfToGR: TF_AfToGR;

  {   Gramatica  }
  VT,VN,SInicial:string;
  Producoes:array[1..MaxProducoes] of string;

  {   AFD   }
  Estados, Alfabeto, EstadoInicial, EstadoFinal:string;
  Transicoes:array[1..MaxProducoes] of string;
implementation

uses UTransicoes, MsgDlg;
{$R *.DFM}

function ParteTextoDir(Frase: string; Parte: string): string;
// Retorna uma parte de um texto depois de um caractere especificado
var i: integer;
begin
     i:=Pos(Parte,Frase);
     ParteTextoDir:=Copy(Frase,i+length(Parte),length(Frase)-i+1);
end;

function ParteTextoEsq(Frase: string; Parte: string): string;
// Retorna uma parte de um texto depois de um caractere especificado
var i: integer;
begin
     i:=Pos(Parte,Frase);
     ParteTextoEsq:=Copy(Frase,1,i-1);
end;

procedure TF_AfToGR.SpeedButton1Click(Sender: TObject);
begin
     Application.CreateForm(TF_Transicao,F_Transicao);
     F_Transicao.show;
end;

procedure TF_AfToGR.FormCreate(Sender: TObject);
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
    MTransicoes.Clear;
    MProducoes.Clear;
end;

procedure TF_AfToGR.EestadosKeyPress(Sender: TObject; var Key: Char);
begin
     key:=upcase(key);
     if not(key in ['A'..'Z',#8]) then
        key:=#0
     else
         if pos(key,EEstados.Text)<>0 then
         begin
              MessageDialog('{ '+key+' } Esse estado já foi informado. Tente outro.',mtInformation,[mbOK],0);
              key:=#0;
         end;
end;

procedure TF_AfToGR.EalfabetoKeyPress(Sender: TObject; var Key: char);
begin
     if key in ['A'..'Z'] then
             key:=char(ord(key)+32);
     if (key in ['a'..'z','0'..'9',#8]) then
     begin
      if (pos(key,EAlfabeto.Text)<>0) then
      begin
        MessageDialog('{ '+key+' } Esse caracter do alfabeto já foi informado. Tente outro.',mtError,[mbOK],0);
        key:=#0;
      end;
     end
     else key:=#0;
end;

procedure TF_AfToGR.EEinicialKeyPress(Sender: TObject; var Key: Char);
begin
     key:=upcase(key);
     if not (key in ['A'..'Z',#8]) then
     begin
          key:=#0;
          exit;
     end;
     if Eestados.Text='' then
     begin
        MessageDialog('Primeiro você deve informa o conjunto de estados.',mtInformation,[mbOK],0);
        key:=#0;
        EEstados.SetFocus;
     end
     else
     begin
         if (key<>#8) and (Pos(key,Eestados.Text)=0) then
         begin
              messageDialog('O estado inicial tem que pertencer ao conjunto de estados.',mtInformation,[mbOK],0);
              key:=#0;
          end
     end;
end;

procedure TF_AfToGR.EEFinalKeyPress(Sender: TObject; var Key: Char);
begin
     key:=upcase(key);
     if not (key in ['A'..'Z',#8]) then
     begin
          key:=#0;
          exit;
     end;
     if Eestados.Text='' then
     begin
        messagedialog('Primeiro você deve informa o conjunto de estados.',mtError,[mbOK],0);
        key:=#0;
        EEstados.SetFocus;
     end
     else
     begin
         if (key<>#8) and (Pos(key,Eestados.Text)=0) then
         begin
              messagedialog('O(s) estado(s) final(is) deve(m) pertencer ao conjunto de estados.',mtError,[mbOK],0);
              key:=#0;
          end
     end;
end;

Function TF_AfToGR.TransicaoValida(Transicao:string):Boolean;
var i:integer;
    Aux:String;
begin
     TransicaoValida:=true;
     Aux:=Copy(Transicao,2,1);
     if Pos(Aux,EEstados.Text)=0 then
     begin
        TransicaoValida:=false;
        exit;
     end;

     Aux:=Copy(Transicao,4,1);
     if Pos(Aux,EAlfabeto.Text)=0 then
     begin
        TransicaoValida:=false;
        exit;
     end;

     Aux:=ParteTextoDir(Transicao,seta);
     if Aux<>'&' then
        for i:=1 to length(Aux) do
            if Pos(Aux[i],EEstados.Text)=0 then
            begin
                 TransicaoValida:=false;
                 exit;
            end;
end;

Function ExisteProducao(Producao:string):Boolean;
var i:integer;
begin
     i:=1;
     ExisteProducao:=False;
     while Producoes[i] <> '' do
     begin
          if Producoes[i] = Producao then
              ExisteProducao:=True;
          inc(i);
     end;
end;

Procedure AutomatoTOGramatica;
var
    indTrans, indEstados, indProd :integer;
    PartEsqProd,PartDirProd,terminal:string;
begin
     VT:=Alfabeto;
     VN:=Estados;
     SInicial:=EstadoInicial;
     indTrans:=1;
     indProd:=1;
     while Transicoes[indTrans]<>'' do
     begin
          PartEsqProd:=Copy(Transicoes[indTrans],2,1);
          PartEsqProd:=PartEsqProd+seta2;
          terminal:=Copy(Transicoes[indTrans],4,1);
          PartDirProd:=ParteTextoDir(Transicoes[indTrans],seta);
          for indEstados:=1 to length(PartDirProd) do  //Adiciona producao de Terminal caso
          begin               //a transição do AF vá para um estado
              if (Pos(PartDirProd[indEstados],EstadoFinal)<>0) and (not ExisteProducao(PartEsqProd+terminal)) then
              begin
                   Producoes[indProd]:=PartEsqProd+terminal;
                   inc(indProd);
              end;
              if not (ExisteProducao(PartEsqProd+terminal+PartDirProd[indEstados])) then
              begin
                 Producoes[indProd]:=PartEsqProd+terminal+PartDirProd[indEstados];
                 inc(indProd);
              end;
          end;
          inc(indTrans);
     end;
end;

procedure TF_AfToGR.BitBtn1Click(Sender: TObject);
var i:byte;
begin
      { Validando Dados do Automato }
      if (EEstados.Text='') or (EAlfabeto.Text='') or
         (EEInicial.Text='') or (EEFinal.Text='') or
         (MTransicoes.Lines.Count=0) then
      begin
           Messagedialog('Existem informações do autômato que não foram fornecidas.'+#13+
           'Verifique as informações digitadas!!!',mtInformation,[mbOK],0);
      end;

      If (Pos(EEInicial.Text,EEstados.Text)=0) or
         (Pos(EEFinal.Text,EEstados.Text)=0) then
      begin
           Messagedialog('O estado inicial e/ou o estado final do autômato não pertencem ao conjunto de estados.',mtError,[mbOK],0);
           exit;
      end;

      i:=0;
      while MTransicoes.Lines[i]<>'' do
      begin
           if not TransicaoValida(MTransicoes.Lines[i]) then
           begin
                Messagedialog('Alguma das transições contém estado ou caracter de alfabeto inválido!!!',mtInformation,[mbOK],0);
                exit;
           end;
           inc(i);
      end;

      { Inicializando Automato com valores fornecidos pelo usuário }
      Estados:=EEstados.Text;
      Alfabeto:=EAlfabeto.Text;
      EstadoInicial:=EEInicial.Text;
      EstadoFinal:=EEFinal.Text;
      i:=1;
      while MTransicoes.Lines[i-1]<>'' do
      begin
           Transicoes[i]:=TRIM(MTransicoes.Lines[i-1]);
           inc(i);
      end;
      AutomatoToGramatica;

      ESimboloNT.Text := Vn;
      ESimboloT.Text :=  Vt;
      ESimboloIni.Text := SInicial;
      i:=1;
      while Producoes[i]<>'' do
      begin
           MProducoes.Lines.Insert(MProducoes.Lines.Count,Producoes[i]);
           inc(i);
      end;
end;

procedure TF_AfToGR.SpeedButton2Click(Sender: TObject);
begin
     self.FormCreate(F_AfToGR);
end;

procedure TF_AfToGR.SpeedButton3Click(Sender: TObject);
begin
     close;
end;

procedure TF_AfToGR.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Action:=caFree;
end;

end.
