program Elevador;

uses
  Forms,
  SysUtils,
  Splash in 'Splash.pas' {FSplash},
  principal in 'principal.pas' {FormPrinc};

{$R *.RES}
var
  FSplash  : Tform;
  TempoINI  : TDateTime;
  Intervalo : single;
begin
  Application.Initialize;
  TempoINI := Time;

    Intervalo:=1;
    FSplash := TFSplash.Create(Application);
    FSplash.Show;
    FSplash.Update;
    try
       Application.CreateForm(TFormPrinc, FormPrinc);
  finally
       repeat
       until Time-TempoINI > Intervalo/SecsPerDay;
    FSplash.Close;
    FSplash.Free;
    end;
    Application.CreateForm(TFormPrinc, FormPrinc);
    Application.Run;
end.
