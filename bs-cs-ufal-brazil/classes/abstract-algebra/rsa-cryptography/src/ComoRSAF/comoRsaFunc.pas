unit comoRsaFunc;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, RxGIF, ExtCtrls;

type
  TFHowRSAWorks = class(TForm)
    Image1: TImage;
    Memo1: TMemo;
    Image2: TImage;
    Label1: TLabel;
    BitBtn1: TBitBtn;
    procedure BitBtn1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  FHowRSAWorks: TFHowRSAWorks;

implementation

{$R *.DFM}

procedure TFHowRSAWorks.BitBtn1Click(Sender: TObject);
begin
        close();
end;

end.
