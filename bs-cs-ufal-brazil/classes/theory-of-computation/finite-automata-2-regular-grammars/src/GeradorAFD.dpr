program GeradorAFD;

uses
  Forms,
  SysUtils,
  UPrinc in 'UPrinc.pas' {F_Princ},
  UAfToGram in 'AFTOGR\UAfToGram.pas' {F_AfToGR},
  UGramToAf in 'GrTOAF\UGramToAf.pas' {F_GrToAfd},
  UTransicoes in 'Trans\UTransicoes.pas' {F_Transicao},
  UProducao in 'Prod\UProducao.pas' {F_Producoes},
  USplash in 'Splash\USplash.pas' {F_Splash};

{$R *.RES}
var
  F_Splash  : Tform;
  TempoINI  : TDateTime;
  Intervalo : single;
begin
  Application.Initialize;
  TempoINI := Time;

    Intervalo:=10;
    F_Splash := TF_Splash.Create(Application);
    F_Splash.Show;
    F_Splash.Update;
    try
       Application.CreateForm(TF_Princ, F_Princ);
    finally
       repeat
       until Time-TempoINI > Intervalo/SecsPerDay;
    F_Splash.Close;
    F_Splash.Free;
    end;
    Application.CreateForm(TF_Princ, F_Princ);
    Application.Run;
end.
