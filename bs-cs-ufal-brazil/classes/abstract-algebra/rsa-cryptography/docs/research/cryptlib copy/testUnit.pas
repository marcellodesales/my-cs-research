unit testUnit;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, RC6unit, Cast, Mars;

type
  TForm1 = class(TForm)
    Button1: TButton;
    Memo1: TMemo;
    Button2: TButton;
    Mars: TMars;
    Cast: TCast;
    RC6: TRC6;
    procedure Button2Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.DFM}

procedure TForm1.Button2Click(Sender: TObject);
begin
Application.Terminate;
end;

procedure TForm1.Button1Click(Sender: TObject);
begin
memo1.Lines.Add(Mars.testcipher);{This are simple test}
memo1.Lines.Add(Cast.testcipher);{functions I used in debugging}
memo1.Lines.Add(RC6.testcipher);

{Now Test the file encryption}
with Mars do
     begin
     {Set Key}
     Key := 'Í«‰Ô;mÙå%ŠÞ#ñƒŽÏ‘bIñ';
     {give the files to encrypt}
     InputFile := 'marstest.txt';
     OutputFile := 'marstest.ccc';
     {Encrypt}
     EncipherFile;
     {Set Key if this was another
     time without the key already set}
     {give the files to decrypt}
     InputFile := 'marstest.ccc';
     OutputFile := 'marstest.ddd';
     {Decrypt}
     DecipherFile;
     end;

with Cast do
     begin
     Key := 'Í«‰Ô;mÙå%ŠÞ#ñƒŽÏ‘bIñ';

     InputFile := 'Casttest.txt';
     OutputFile := 'Casttest.ccc';

     EncipherFile;

     InputFile := 'Casttest.ccc';
     OutputFile := 'Casttest.ddd';

     DecipherFile;
     end;

with RC6 do
     begin
     Key := 'Í«‰Ô;mÙå%ŠÞ#ñƒŽÏ‘bIñ';

     InputFile := 'RC6test.txt';
     OutputFile := 'RC6test.ccc';

     EncipherFile;

     InputFile := 'RC6test.ccc';
     OutputFile := 'RC6test.ddd';

     DecipherFile;
     end;
showmessage('Test is Done');
end;

end.
