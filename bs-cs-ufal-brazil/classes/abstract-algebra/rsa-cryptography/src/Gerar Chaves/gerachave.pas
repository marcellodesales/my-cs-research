unit gerachave;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,RSA,
  StdCtrls,confRsa, jpeg, ExtCtrls;

type
  TFgenerateKeys = class(TForm)
    Button1: TButton;
    Image1: TImage;
    GroupBox2: TGroupBox;
    GroupBox3: TGroupBox;
    Edit4: TEdit;
    Label3: TLabel;
    Label4: TLabel;
    Edit3: TEdit;
    Image2: TImage;
    GroupBox4: TGroupBox;
    Edit6: TEdit;
    Label6: TLabel;
    Image3: TImage;
    procedure FormCreate(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FgenerateKeys: TFgenerateKeys;
  rsaObject : TRSA;

implementation

{$R *.DFM}

procedure TFgenerateKeys.FormCreate(Sender: TObject);
begin
        rsaObject := TRSA.Create();
        rsaObject.start();
        Edit3.Text := intToStr(rsaObject.N);
        Edit4.Text := intToStr(rsaObject.E);
        Edit6.Text := intToStr(rsaObject.D);
end;

procedure TFgenerateKeys.Button1Click(Sender: TObject);
begin
        rsaObject.setPrimos();
        rsaObject.setChavePublica();
        rsaObject.setChavePrivada();

        edit3.Text:= inttostr(rsaObject.N);
        edit4.Text:= inttostr(rsaObject.E);
        edit6.Text:= inttostr(rsaObject.D);
end;

procedure TFgenerateKeys.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
        rsaObject.close();
        SELF.Free;
end;

end.
