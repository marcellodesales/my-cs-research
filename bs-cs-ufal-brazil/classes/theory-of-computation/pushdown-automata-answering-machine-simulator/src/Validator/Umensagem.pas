unit Umensagem;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, jpeg, ExtCtrls, Buttons, UTemp; //UAutomatoPilha;

type
  TF_Mensagem = class(TForm)
    Image1: TImage;
    MemoMesg: TMemo;
    SpeedButton1: TSpeedButton;
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure FormActivate(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_Mensagem: TF_Mensagem;
  Automato:TTemp;
  Arquivo:String;

implementation
uses MsgDlg,UDiscador;

{$R *.DFM}

procedure TF_Mensagem.SpeedButton1Click(Sender: TObject);
var erro:boolean;
begin
(*     if Automato.Estado='q2' then
     begin
          Automato.CharFita:='c';
          Automato.ExecutaAutomato;
     end;
     Automato.CharFita:='w';
     Automato.ExecutaAutomato; *)
     Automato.Empilha(Arquivo,MemoMesg.Text,erro);
   //  Automato.AtualizaAutomatoINI(Arquivo);
     Close;
end;

procedure TF_Mensagem.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Automato.Destroy;
end;

procedure TF_Mensagem.FormActivate(Sender: TObject);
begin
     Arquivo:=ExtractFilePath(Application.ExeName)+'Numeros\'+F_Discador.CFone.Text+'.tmp';
     Automato:=TTemp.Create;
     MemoMesg.Clear;
end;

end.
