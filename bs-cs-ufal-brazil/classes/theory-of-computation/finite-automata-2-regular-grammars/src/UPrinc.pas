unit UPrinc;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Menus, ImgList, ExtCtrls, jpeg, ComCtrls, ToolWin, Slideform;

type
  TF_Princ = class(TForm)
    MainMenu1: TMainMenu;
    Transf: TMenuItem;
    AFDTOGRM: TMenuItem;
    GRTOAFDM: TMenuItem;
    N1: TMenuItem;
    FecharAplicao1: TMenuItem;
    Ajuda1: TMenuItem;
    Sobre1: TMenuItem;
    ImageList1: TImageList;
    Image1: TImage;
    procedure AFDTOGRMClick(Sender: TObject);
    procedure GRTOAFDMClick(Sender: TObject);
    procedure FecharAplicao1Click(Sender: TObject);
    Procedure Fechar_Ffilho_Aberto;
    procedure FormCreate(Sender: TObject);
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

implementation

uses UGramToAf, UAfToGram, MsgDlg;

{$R *.DFM}

Procedure TF_Princ.Fechar_Ffilho_Aberto;
var i:integer;
begin
     for i:=0 to ComponentCount-1 do
         if (Components[i] is TForm) then
            if ((Components[i] as TForm).FormStyle=fsMDIChild) then
               (Components[i] as TForm).Close;
end;

procedure TF_Princ.AFDTOGRMClick(Sender: TObject);
begin
     Fechar_Ffilho_Aberto;
     LockWindowUpdate(Handle);
     F_AfToGr:=TF_AfToGr.Create(self);
     LockWindowUpdate(0);
end;

procedure TF_Princ.GRTOAFDMClick(Sender: TObject);
begin
     Fechar_Ffilho_Aberto;
     LockWindowUpdate(Handle);
     F_GrToAfd:=TF_GrToAfd.Create(self);
     LockWindowUpdate(0);
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

end.
