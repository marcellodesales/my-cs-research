unit potenciamodulon;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, rsa;

type
  TFmoduloN = class(TForm)
    Edit1: TEdit;
    Edit2: TEdit;
    Button1: TButton;
    Edit3: TEdit;
    Edit4: TEdit;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure FormCreate(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FmoduloN: TFmoduloN;
  rsaObject : TRsa;
implementation

{$R *.DFM}

procedure TFmoduloN.FormClose(Sender: TObject; var Action: TCloseAction);
begin
        rsaObject.close();
        self.Free;
end;

procedure TFmoduloN.FormCreate(Sender: TObject);
begin
        rsaObject := TRsa.Create;
        rsaObject.start();
        Edit3.Text := intToStr(rsaObject.E);
        Edit4.Text := intToStr(rsaObject.N);
        edit1.Clear;
        Edit2.Clear;
end;

procedure TFmoduloN.Button1Click(Sender: TObject);
begin
     edit2.text := intToStr(rsaObject.algebra.getPotenciaModuloN(StrToInt64(edit1.Text),StrToInt64(edit3.text),StrToInt64(edit4.text)));
end;

end.
