unit testePrimo;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, ExtCtrls;

type
  TFTestPrimeNumber = class(TForm)
    Image1: TImage;
    BitBtn1: TBitBtn;
    Edit1: TEdit;
    Label1: TLabel;
    logPrime: TMemo;
    Bevel1: TBevel;
    Bevel2: TBevel;
    Label2: TLabel;
    BitBtn2: TBitBtn;
    BitBtn3: TBitBtn;
    Label3: TLabel;
    BitBtn4: TBitBtn;
    procedure BitBtn1Click(Sender: TObject);
    procedure BitBtn2Click(Sender: TObject);
    procedure BitBtn4Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
    function isPrimo(x:int64) : boolean;
    function getResto(a,b :int64) : int64;
    procedure clearAll;
  public
    { Public declarations }
  end;

var
  FTestPrimeNumber: TFTestPrimeNumber;
  f : int64;
implementation

{$R *.DFM}

procedure TFTestPrimeNumber.BitBtn1Click(Sender: TObject);
begin
        logPrime.Clear();
        if self.isPrimo(strToInt64(edit1.text)) then
           logPrime.Lines.Add(edit1.text+'é primo, pois resto de x / '+intToStr(f)+' é diferente de 0')
        else logPrime.Lines.Add(edit1.text+' não é primo, pois resto de x / '+intToStr(f)+' é igual a 0');
end;

function TFTestPrimeNumber.getResto(a,b :int64) : int64;
var q:int64;
begin
        q := 1;
        while (b * q <= a) do
                q := q + 1;
        q := q - 1;
        getResto := (a - b * q);
end;

function TFTestPrimeNumber.isPrimo(x:int64) : boolean;
var a,m:int64;
begin
     f:=2;
     logPrime.Lines.Add(intToStr(x)+' é primo?');
     logPrime.Lines.Add('f = 2; (Fator)');
     logPrime.Lines.Add('Enquanto (f não dividir x) e (f * f) é menor ou igual a x)');
     logPrime.Lines.Add('f = f + 1');
     logPrime.Lines.Add('');
     m := f*f;
     while ((self.getResto(x,f)<>0) and ((m) <= x) ) do
     begin
        logPrime.Lines.Add('('+intToStr(f)+' não dividi '+intToStr(x)+') e ');
        logPrime.Lines.Add('('+intToStr(m)+' é menor ou igual a '+intToStr(x)+')');
        inc(f);
        a := f;
        logPrime.Lines.Add('Então f = f + 1; f = '+intToStr(a));
        logPrime.Lines.Add('');
     end;
        logPrime.Lines.Add('O fator final é f = '+intToStr(f));
        logPrime.Lines.Add('Pois '+intToStr(f)+' divide '+intToStr(x)+' ou');
        logPrime.Lines.Add('f*f='+intToStr(m)+' <= f');
        logPrime.Lines.Add('');

     isPrimo := (self.getResto(x,f)<>0)
end;

procedure TFTestPrimeNumber.BitBtn2Click(Sender: TObject);
begin
        self.Close;
end;

procedure TFTestPrimeNumber.clearAll;
begin
        logPrime.Clear;
        edit1.Clear;
end;

procedure TFTestPrimeNumber.BitBtn4Click(Sender: TObject);
begin
        clearAll();
end;

procedure TFTestPrimeNumber.FormCreate(Sender: TObject);
begin
        clearAll();
end;

end.
