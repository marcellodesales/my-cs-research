unit testeAritMod;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, ExtCtrls;

type
  TFTesteAritMod = class(TForm)
    aSum: TEdit;
    bSum: TEdit;
    nSum: TEdit;
    respSum: TEdit;
    Image1: TImage;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Image2: TImage;
    aMul: TEdit;
    Label5: TLabel;
    bMul: TEdit;
    Label6: TLabel;
    nMul: TEdit;
    Label7: TLabel;
    respMul: TEdit;
    SpeedButton1: TSpeedButton;
    SpeedButton2: TSpeedButton;
    BitBtn1: TBitBtn;
    BitBtn2: TBitBtn;
    BitBtn3: TBitBtn;
    Label1: TLabel;
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
    procedure BitBtn3Click(Sender: TObject);
    procedure aSumKeyPress(Sender: TObject; var Key: Char);
    procedure bSumKeyPress(Sender: TObject; var Key: Char);
    procedure BitBtn2Click(Sender: TObject);
  private
    { Private declarations }
    procedure clearAll;
  public
    { Public declarations }
  end;

var
  FTesteAritMod: TFTesteAritMod;

implementation
uses msgdlg;
{$R *.DFM}

procedure TFTesteAritMod.FormCreate(Sender: TObject);
begin
        clearAll();
end;

procedure TFTesteAritMod.clearAll;
var i : Integer;
begin
  for i := 0 to ComponentCount -1 do
     if Components[i] is TEdit then
            TEdit(Components[i]).clear();
end;

procedure TFTesteAritMod.SpeedButton1Click(Sender: TObject);
var sum : int64;
begin
   if ((aSum.text <> '') and (bSum.text <> '') and (nSum.text <> '')) then
   begin
        sum := (StrToint64(aSum.text) + StrToint64(bSum.text)) mod StrToint64(nSum.text);
        if sum < 0 then
                sum := sum + StrToint64(nSum.text);
        respSum.text := intToStr(sum);
   end else
   begin
        if (aSum.text = '') then
            messagedialog('É necessário digitar (a) da soma modular.',mtError,[mbOk],0)
        else
        if (bSum.text = '') then
            messagedialog('É necessário digitar (b) da soma modular.',mtError,[mbOk],0)
        else
        if (nSum.text = '') then
            messagedialog('É necessário digitar (n) da soma modular.',mtError,[mbOk],0)
   end;
end;

procedure TFTesteAritMod.SpeedButton2Click(Sender: TObject);
var mult : int64;
begin
   if ((aMul.text <> '') and (bMul.text <> '') and (nMul.text <> '')) then
   begin
        mult := (StrToint64(aMul.text) * StrToint64(bMul.text)) mod StrToint64(nMul.text);
        if mult < 0 then
                mult := mult + StrToint64(nMul.text);
        respMul.text := intToStr(mult);
   end else
   begin
        if (aMul.text = '') then
            messagedialog('É necessário digitar (a) da multiplicação modular.',mtError,[mbOk],0)
        else
        if (bMul.text = '') then
            messagedialog('É necessário digitar (b) da multiplicação modular.',mtError,[mbOk],0)
        else
        if (nMul.text = '') then
            messagedialog('É necessário digitar (n) da multiplicação modular.',mtError,[mbOk],0)
   end;

end;

procedure TFTesteAritMod.BitBtn3Click(Sender: TObject);
begin
        clearAll();
end;

procedure TFTesteAritMod.aSumKeyPress(Sender: TObject; var Key: Char);
begin
        if not(key in ['0'..'9',#8,#13]) then
        begin
            messagedialog('Este campo só aceita números!'+#13+'Você não digitou um numeral!',mtWarning,[mbOk],0);
            key := #0;
        end;
end;

procedure TFTesteAritMod.bSumKeyPress(Sender: TObject; var Key: Char);
begin
        aSumKeyPress(Sender,Key);
end;

procedure TFTesteAritMod.BitBtn2Click(Sender: TObject);
begin
        close;
end;

end.
