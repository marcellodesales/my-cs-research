unit Fgerachave;

interface

uses 
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, jpeg, ExtCtrls;

type
  TFrGenerateKeys = class(TFrame)
    GroupBox1: TGroupBox;
    GroupBox3: TGroupBox;
    Label3: TLabel;
    Label4: TLabel;
    Image2: TImage;
    EditE: TEdit;
    EditN: TEdit;
    GroupBox4: TGroupBox;
    Label6: TLabel;
    Image3: TImage;
    EditD: TEdit;
    Image1: TImage;
    BitBtn1: TBitBtn;
    BitBtn3: TBitBtn;
    Memo1: TMemo;
    procedure BitBtn1Click(Sender: TObject);
    procedure BitBtn3Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

implementation
uses principal;

{$R *.DFM}

procedure TFrGenerateKeys.BitBtn1Click(Sender: TObject);
begin
        main.log.Lines.Add('');
        main.log.Lines.Add('-> Gerando novas chaves');
        main.log.Lines.Add('');
        main.rsaObject.setPrimos();
        main.rsaObject.setChavePublica();
        main.rsaObject.setChavePrivada();
        main.log.Lines.Add('');
        main.generateKeysGiveKeysValue();
        main.configStatusBar();
end;

procedure TFrGenerateKeys.BitBtn3Click(Sender: TObject);
begin
        visible := false;
end;

end.
