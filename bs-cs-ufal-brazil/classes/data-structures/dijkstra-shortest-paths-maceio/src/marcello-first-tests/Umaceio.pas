unit Umaceio;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Antialiased, StdCtrls, OvalBtn, jpeg, ExtCtrls, Buttons;

type
  TF_Maceio = class(TForm)
    Image1: TImage;
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
    procedure FormCreate(Sender: TObject);
    procedure v3Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_Maceio: TF_Maceio;

implementation

{$R *.DFM}

procedure TF_Maceio.FormCreate(Sender: TObject);
var i:integer;
begin
     For i:=0 to ComponentCount-1 do
         if (Components[i] is TOvalButton) then
            (Components[i] as TOvalButton).ShowHint:=true;
end;

procedure TF_Maceio.v3Click(Sender: TObject);
begin
     if self.v3.Color=clBtnFace then
        v3.Color:=clRed
     else
         v3.Color:=clBtnFace;
end;

procedure TF_Maceio.SpeedButton1Click(Sender: TObject);
begin
     Close;
end;

procedure TF_Maceio.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Action:=caFree;
end;

end.
