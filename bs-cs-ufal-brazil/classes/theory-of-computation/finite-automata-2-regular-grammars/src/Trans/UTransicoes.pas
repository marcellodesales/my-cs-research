unit UTransicoes;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Buttons, StdCtrls, Mask, jpeg, ExtCtrls;

type
  TF_Transicao = class(TForm)
    SpeedButton1: TSpeedButton;
    ETransicao: TMaskEdit;
    Image1: TImage;
    Image2: TImage;
    Image3: TImage;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure ETransicaoKeyPress(Sender: TObject; var Key: Char);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_Transicao: TF_Transicao;

implementation
uses UAfToGram, MsgDlg;

{$R *.DFM}

procedure TF_Transicao.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Free;
end;

procedure TF_Transicao.SpeedButton1Click(Sender: TObject);
var Transicao:String;
    i:smallint;
begin
     Transicao:=trim(ETransicao.Text);
     if pos(' ',Transicao)<>0 then
     begin
          MessageDialog('A transi��o n�o pode cont�m espa�os!!!',mtError,[mbOK],0);
          exit;
     end;

     if Transicao[length(Transicao)]='>' then
     begin
          MessageDialog('Voc� deve informar o estado destino da transi��o!!!',mtInformation,[mbOK],0);
          exit;
     end;

     i:=0;
     while F_AfToGr.MTransicoes.Lines[i]<>'' do
     begin
           if Transicao = F_AfToGr.MTransicoes.Lines[i] then
           begin
                MessageDialog('Esta transi��o j� foi informada!!!',mtError,[mbOK],0);
                exit;
           end;
           inc(i);
     end;

     if F_AfToGr.TransicaoValida(Transicao) then
        F_AfToGr.MTransicoes.lines.Insert(F_AfToGr.MTransicoes.lines.Count,Transicao)
     else
         MessageDialog('Os estados e/ou o caracter do alfabeto citados na transi��o n�o s�o v�lidos.'+#13+'Esta transi��o � inv�lida. Verifique sua digita��o!!!',mtError,[mbOK],0);
end;

procedure TF_Transicao.FormCreate(Sender: TObject);
begin
     ETransicao.Clear;
end;

procedure TF_Transicao.ETransicaoKeyPress(Sender: TObject; var Key: Char);
begin
     if key=#13 then
        SpeedButton1Click(self);
end;

end.
