unit sobre;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Animate, GIFCtrl, RxGIF, StdCtrls, ExtCtrls;

type
  TFAbout = class(TForm)
    Image2: TImage;
    Label1: TLabel;
    Image1: TImage;
    Image3: TImage;
    Label2: TLabel;
    Image4: TImage;
    Image5: TImage;
    Label3: TLabel;
    Label4: TLabel;
    RxGIFAnimator1: TRxGIFAnimator;
    Label6: TLabel;
    Label5: TLabel;
    Image6: TImage;
    Image7: TImage;
    Image8: TImage;
    procedure Image6Click(Sender: TObject);
  private
    { Private declarations }
   procedure openEmail(sObjectPath : PChar);
  public
    { Public declarations }
  end;

var
  FAbout: TFAbout;

implementation
uses shellapi;
{$R *.DFM}

procedure TFAbout.Image6Click(Sender: TObject);
var TempString : array[0..79] of char;
begin
   StrPCopy(TempString,image6.hint);
   openEmail(TempString);
end;

procedure TFAbout.openEmail(sObjectPath : PChar);
begin
        ShellExecute(0, Nil, sObjectPath, Nil, Nil, SW_NORMAL);
end;

end.
