unit UAvisoAutomato;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, jpeg, ExtCtrls;

type
  TF_AvisoAutomato = class(TForm)
    Image1: TImage;
    Label1: TLabel;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_AvisoAutomato: TF_AvisoAutomato;

implementation

{$R *.DFM}

procedure TF_AvisoAutomato.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
     Release;
end;

end.
