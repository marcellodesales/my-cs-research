unit USplash;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  jpeg, ExtCtrls, KZTransImg;

type
  TF_Splash = class(TForm)
    KZTransImage1: TKZTransImage;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_Splash: TF_Splash;

implementation

{$R *.DFM}

end.
