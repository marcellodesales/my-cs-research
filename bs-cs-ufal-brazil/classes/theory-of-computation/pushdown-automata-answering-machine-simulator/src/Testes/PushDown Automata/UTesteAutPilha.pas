unit UTesteAutPilha;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, StdCtrls,
  Buttons,UPilhaTeste,UAutomatoTeste;

type
  TF_TesteAutPilha = class(TForm)
    GroupBox2: TGroupBox;
    SpeedButton1: TSpeedButton;
    SpeedButton2: TSpeedButton;
    Label1: TLabel;
    Label2: TLabel;
    Edit1: TEdit;
    MRecados: TMemo;
    Edit2: TEdit;
    GroupBox1: TGroupBox;
    Label3: TLabel;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    LExplica: TLabel;
    Edit3: TEdit;
    Edit4: TEdit;
    Edit5: TEdit;
    Edit6: TEdit;
    GroupBox3: TGroupBox;
    Label7: TLabel;
    Label8: TLabel;
    BitBtn1: TBitBtn;
    MPilha: TMemo;
    procedure Escreve_Autom_Edits;
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_TesteAutPilha        : TF_TesteAutPilha;
  AutomatoTeste     : TAutomato;
  Recados      : Tpilhas;
  PilhaRecados : Tpilha;

implementation
Uses MSGDLG;

{$R *.DFM}

procedure TF_TesteAutPilha.Escreve_Autom_Edits;
begin
     Edit3.Text:=Edit3.Text+AutomatoTeste.CharFita+'  ';
     Edit4.Text:=AutomatoTeste.Estado;
     Edit5.Text:=AutomatoTeste.CharFita;
     Edit6.Text:=AutomatoTeste.SimboloPilha;
end;

procedure TF_TesteAutPilha.FormCreate(Sender: TObject);
var i:integer;
begin
     AutomatoTeste:=TAutomato.Create;
     AutomatoTeste.Inicializa;
     Recados:=TPilhas.Create;
     for i:=0 to ComponentCount-1 do
     begin
          if (Components[i] is TEdit) then
             (Components[i] as Tedit).clear
          else
          if (Components[i] is TSpeedButton) then
             (Components[i] as TSpeedButton).flat:=true;
     end;
     Escreve_Autom_Edits;
     MPilha.Lines[10-Recados.Quant]:=AutomatoTeste.SimboloPilha;
end;

procedure TF_TesteAutPilha.SpeedButton1Click(Sender: TObject);
begin
     if Edit1.Text='' then
        MessageDialog('Você deve digitar a mensagem'#13'no campo indicado.',mtWarning,[mbOK],0)
     else
     begin
          if Recados.Quant=10 then
             MessageDialog('A secretária já está cheia com 10 mensagens.',mtInformation,[mbOK],0)
          else
          begin
               AutomatoTeste.ExecutaAutomato;
               inc(Recados.Quant);
               Recados.Empilha(edit1.Text,PilhaRecados);
               MRecados.Lines[Recados.Quant-1]:=edit1.Text;
               Escreve_Autom_Edits;
               MPilha.Lines[10-Recados.Quant]:=AutomatoTeste.SimboloPilha;
             //  Explica('Empilhamento');
          end;
     end;
end;

procedure TF_TesteAutPilha.SpeedButton2Click(Sender: TObject);
var AUXSTR:String;
begin
    if Recados.Quant=0 then
        MessageDialog('A secretária já está Vazia.',mtInformation,[mbOK],0)
     else
     begin
          AutomatoTeste.ExecutaAutomato;
          Recados.Desempilha(AUXSTR,PilhaRecados);
          Edit2.Text:=AUXSTR;
          MRecados.Lines[Recados.Quant-1]:='';
          MPilha.Lines[10-Recados.Quant]:='';
          Dec(Recados.Quant);
          Escreve_Autom_Edits;
//          Explica('Desempilhamento');
     end;
end;

procedure TF_TesteAutPilha.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
     AutomatoTeste.Free;
     Recados.Free;
end;

end.
