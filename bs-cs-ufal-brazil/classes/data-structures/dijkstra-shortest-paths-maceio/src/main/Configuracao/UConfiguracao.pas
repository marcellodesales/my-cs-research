unit UConfiguracao;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Buttons, StdCtrls, UConfig, Grids, Umaceio;

type
  TF_Config = class(TForm)
    GroupBox1: TGroupBox;
    Edit1: TEdit;
    Label1: TLabel;
    SpeedButton1: TSpeedButton;
    GroupBox2: TGroupBox;
    SpeedButton2: TSpeedButton;
    tabelacustos: TStringGrid;
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure SpeedButton2Click(Sender: TObject);
   // procedure PegaCustosMaceio;
  private
    { Private declarations }
  public
    { Public declarations }
    NovaAltura,VelhaAltura:integer;
  end;

var
  F_Config: TF_Config;
  Configuracao:TConfig;
  ArqConf:string;

implementation
{$R *.DFM}

{procedure TF_Config.PegaCustosMaceio;
var i,X,Y,Compri:integer;
    A,B,C,Custo:String;

begin
     for i:=0 to ComponentCount-1 Do
         if (F_Maceio.Components[i] is TLabel) then
            For X:=1 to 16 do
                For Y:=1 to 16 Do
                begin
                     A:=intTostr(X);
                     B:=intToStr(Y);
                     Compri:=Length((F_Maceio.Components[i] as TLabel).Name);
                     C:=Copy((F_Maceio.Components[i] as TLabel).Name,2,Compri-1);
                    if C = (A+B) then
                    begin
                         Custo:=(F_Maceio.Components[i] as TLabel).Caption;
                         TabelaCustos.Cells[Y,X]:=Custo;
                    end;
                end;
end;}

procedure TF_Config.FormCreate(Sender: TObject);
begin
     ArqConf:=ExtractFilePath(Application.ExeName)+'configuracao.ini';
     Configuracao:=TConfig.Create;
     self.Edit1.Text:=Configuracao.GetURLTutor(ArqConf);
     //PegaCustosMaceio;
end;

procedure TF_Config.SpeedButton1Click(Sender: TObject);
begin
     Close;
end;

procedure TF_Config.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     if Configuracao.URLTutor=self.Edit1.Text then
     else
     begin
         Configuracao.URLTutor:=self.Edit1.Text;
         Configuracao.PassaURL(ArqConf);
     end;
     Configuracao.Free;
     self.free;
end;

procedure TF_Config.SpeedButton2Click(Sender: TObject);
var
   I: Integer;
begin
     if SpeedButton2.Down = False then
     begin
          SpeedButton2.Down:=true;
          VelhaAltura:=ClientHeight;
          NovaAltura:=GroupBox2.Top+GroupBox2.Height+GroupBox1.Top+20;
          for I := Height to NovaAltura do
          begin
               Height :=I;
               Update;
          end;
     end;
end;

end.

