unit UProducao;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Mask, Buttons, jpeg, ExtCtrls;

type
  TF_Producoes = class(TForm)
    SpeedButton1: TSpeedButton;
    EditProducao: TMaskEdit;
    Image2: TImage;
    Image1: TImage;
    Image3: TImage;
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure EditProducaoKeyPress(Sender: TObject; var Key: Char);
  private
    { Private declarations }
    OldWinProc, NewWinProc:Pointer;
    Procedure Procedimento(Var msg:TMessage);
  public
    { Public declarations }
  end;

var
  F_Producoes: TF_Producoes;
  OutCanvas:TCanvas;

implementation
uses UGramToAf, MsgDlg;
{$R *.DFM}

Procedure TF_Producoes.Procedimento(Var msg:TMessage);
var BMPwidth, BMPHeight : integer;
    I, J :integer;
begin
     Msg.Result:=CallWindowProc(OldWinProc,ClientHandle, Msg.Msg, Msg.wparam, Msg.lparam);
     if Msg.Msg = wm_EraseBkgnd then
     begin
          BMPWidth:=self.Image2.Width;
          BMPHeight:=self.Image2.height;
          if (BMPWidth<>0) and (BMPHeight<>0) then
          begin
               OutCanvas.Handle:=Msg.WParam;
               For I:=0 to self.ClientWidth div BMPWidth do
                   For J:=0 to self.ClientHeight div BMPHeight do
                       OutCanvas.Draw(I*BMPWidth, J*BMPHeight, self.Image2.Picture.Graphic);
          end;
     end;
end;

procedure TF_Producoes.FormCreate(Sender: TObject);
begin
     NewWinproc:=MakeObjectinstance(Procedimento);
     OldWinProc:=Pointer(setwindowlong(ClientHandle, gwl_wndproc, Cardinal(newwinproc)));
     OutCanvas:=TCanvas.create;
     EditProducao.Clear;
end;

procedure TF_Producoes.SpeedButton1Click(Sender: TObject);
var Producao:string;
    i:smallint;
begin
     Producao:=trim(EditProducao.Text);
     if pos(' ',Producao)<>0 then
     begin
          MessageDialog('A produção não pode contém espaços!!!',mtError,[mbOK],0);
          exit;
     end;

     if Producao[length(Producao)]='>' then
     begin
          MessageDialog('Você deve informa o valor gerado pela produção!!!',mtinformation,[mbOK],0);
          exit;
     end;
     i:=0;
     while F_GrToAfd.MemoProducoes.Lines[i]<>'' do
     begin
           if Producao = F_GrToAfd.MemoProducoes.Lines[i] then
           begin
                MessageDialog('Esta produção já foi informada!!!',mtInformation,[mbOK],0);
                exit;
           end;
           inc(i);
     end;

     if F_GrToAfd.ProducaoValida(Producao) then
        F_GrToAfd.MemoProducoes.Lines.Insert(F_GrToAfd.MemoProducoes.Lines.Count,Producao)
     else
         MessageDialog('Os valores terminais e/ou não-terminais citados na produção não são válidos.'+#13+'Esta produção é inválida. Verifique sua digitação!!!',mtError,[mbOK],0);
end;

procedure TF_Producoes.EditProducaoKeyPress(Sender: TObject; var Key: Char);
begin
     if key=#13 then
        SpeedButton1Click(self)
     else
         if not(Key in ['A'..'Z','a'..'z','0'..'9','(',')',',',#8,'&']) then
            key:=#0;
end;

end.
