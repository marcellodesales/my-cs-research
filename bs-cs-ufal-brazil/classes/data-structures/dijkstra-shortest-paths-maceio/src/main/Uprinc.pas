unit Uprinc;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Menus, ImgList, ExtCtrls, StdCtrls, ShellAPI, ComCtrls, UConfig; {UrlMon;}

type
  TF_Princ = class(TForm)
    MainMenu1: TMainMenu;
    Arquivo1: TMenuItem;
    Abrir: TMenuItem;
    Online1: TMenuItem;
    N2: TMenuItem;
    FecharTutorial1: TMenuItem;
    Exemplo1: TMenuItem;
    Cida1: TMenuItem;
    Configurao1: TMenuItem;
    Ajuda1: TMenuItem;
    Sobre1: TMenuItem;
    ImageList1: TImageList;
    CaminhosmaiscurtosExemploterico1: TMenuItem;
    N1: TMenuItem;
    FecharAplicao1: TMenuItem;
    Image1: TImage;
    Principais1: TMenuItem;
    procedure AbrirClick(Sender: TObject);
    procedure Cida1Click(Sender: TObject);
    Procedure Fechar_Ffilho_Aberto;
    procedure CaminhosmaiscurtosExemploterico1Click(Sender: TObject);
    procedure Online1Click(Sender: TObject);
    procedure OpenObject(sObjectPath : PChar);
    procedure FecharAplicao1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure Principais1Click(Sender: TObject);
    procedure FecharTutorial1Click(Sender: TObject);
  private
    { Private declarations }
    OldWinProc, NewWinProc:Pointer;
    Procedure newWinProcedimento(Var msg:TMessage);
  public
    { Public declarations }
  end;

var
  F_Princ: TF_Princ;
  OutCanvas:TCanvas;
  Configuracao:TConfig;

implementation
uses UTutorial,Umaceio, Uexemplo, MsgDlg, UConfiguracao;

{$R *.DFM}

Procedure TF_Princ.newWinProcedimento(Var msg:TMessage);
var BMPwidth, BMPHeight : integer;
    I, J :integer;
begin
     Msg.Result:=CallWindowProc(OldWinProc,ClientHandle, Msg.Msg, Msg.wparam, Msg.lparam);
     if Msg.Msg = wm_EraseBkgnd then
     begin
          BMPWidth:=self.Image1.Width;
          BMPHeight:=self.Image1.height;
          if (BMPWidth<>0) and (BMPHeight<>0) then
          begin
               OutCanvas.Handle:=Msg.WParam;
               For I:=0 to self.ClientWidth div BMPWidth do
                   For J:=0 to self.ClientHeight div BMPHeight do
                       OutCanvas.Draw(I*BMPWidth, J*BMPHeight, self.Image1.Picture.Graphic);
          end;
     end;
end;

Procedure TF_Princ.Fechar_Ffilho_Aberto;
var i:integer;
begin
     for i:=0 to ComponentCount-1 do
         if (Components[i] is TForm) then
            if ((Components[i] as TForm).FormStyle=fsMDIChild) then
               (Components[i] as TForm).Close;
end;

procedure TF_Princ.AbrirClick(Sender: TObject);
begin
     F_Princ.FecharTutorial1.Enabled:=true;
     Fechar_Ffilho_Aberto;
     LockWindowUpdate(Handle);
     F_Tutorial:=TF_Tutorial.Create(self);
     LockWindowUpdate(0);
end;

procedure TF_Princ.Cida1Click(Sender: TObject);
begin
     Fechar_Ffilho_Aberto;
     LockWindowUpdate(Handle);
     F_Maceio:=TF_Maceio.Create(self);
     LockWindowUpdate(0);
end;

procedure TF_Princ.CaminhosmaiscurtosExemploterico1Click(Sender: TObject);
begin
     Fechar_Ffilho_Aberto;
     LockWindowUpdate(Handle);
     F_Exemplo:=TF_Exemplo.Create(self);
     LockWindowUpdate(0);
end;

procedure TF_Princ.OpenObject(sObjectPath : PChar);
begin
 ShellExecute(0, Nil, sObjectPath, Nil, Nil, SW_NORMAL);
end;

procedure TF_Princ.Online1Click(Sender: TObject);
var TempString : array[0..79] of char;
    arqConf:string;
    site:PWidechar;
begin
     ArqConf:=ExtractFilePath(Application.ExeName)+'configuracao.ini';
     configuracao:=TConfig.Create;
//     site:=Configuracao.GetURLTutor(ArqConf);
  //   HlinkNavigateString(nil,site);
     StrPCopy(TempString,Configuracao.GetURLTutor(ArqConf));
     OpenObject(TempString);
     configuracao.Free;
end;

procedure TF_Princ.FecharAplicao1Click(Sender: TObject);
begin
     if (messagedialog('Deseja realmente Fechar?',mtconfirmation,[mbYes,mbNo],0)=mrYes) then
        Application.Terminate;
end;

procedure TF_Princ.FormCreate(Sender: TObject);
begin
     NewWinproc:=MakeObjectinstance(newWinProcedimento);
     OldWinProc:=Pointer(setwindowlong(ClientHandle, gwl_wndproc, Cardinal(newwinproc)));
     OutCanvas:=TCanvas.create;
end;

procedure TF_Princ.Principais1Click(Sender: TObject);
begin
     Application.CreateForm(TF_Config, F_Config);
     F_Config.show;
end;

procedure TF_Princ.FecharTutorial1Click(Sender: TObject);
begin
        F_Tutorial.SpeedButton1.OnClick(self);
end;

end.
