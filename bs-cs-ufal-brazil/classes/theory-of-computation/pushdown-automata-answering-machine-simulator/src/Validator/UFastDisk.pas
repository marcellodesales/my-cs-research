unit UFastDisk;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  jpeg, ExtCtrls, StdCtrls, Buttons;

type
  TF_FastDisk = class(TForm)
    Image1: TImage;
    SpeedButton1: TSpeedButton;
    Label1: TLabel;
    Label2: TLabel;
    NomeFastDisk: TEdit;
    FoneFastDisk: TEdit;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure SpeedButton1Click(Sender: TObject);
    procedure FoneFastDiskKeyPress(Sender: TObject; var Key: Char);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_FastDisk: TF_FastDisk;

implementation
uses UDiscador;
{$R *.DFM}

procedure TF_FastDisk.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Release;
end;

procedure TF_FastDisk.SpeedButton1Click(Sender: TObject);
begin
     F_Discador.NomeLR.Caption:=NomeFastDisk.Text;
     F_Discador.FONELR.Caption:=FoneFastDisk.Text;
     Close;
end;

procedure TF_FastDisk.FoneFastDiskKeyPress(Sender: TObject; var Key: Char);
begin
     if (not (Key in ['0'..'9',#8])) then
        key:=#0;
end;

end.
