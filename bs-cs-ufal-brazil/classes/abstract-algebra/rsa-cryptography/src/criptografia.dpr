program criptografia;

{%ToDo 'criptografia.todo'}

uses
  Forms,
  SysUtils,
  rsa in 'Objetos\rsa.pas',
  confRsa in 'Config\confRsa.pas',
  algebra in 'Objetos\algebra.pas',
  principal in 'principal.pas' {main},
  Fgerachave in 'Gerar Chaves\Fgerachave.pas' {FrGenerateKeys: TFrame},
  Fenviar in 'Enviar\Fenviar.pas' {FrSend: TFrame},
  Freceber in 'Receber\Freceber.pas' {FrReceive: TFrame},
  Msgdlg in 'Config\Msgdlg.pas',
  splash in 'Splash\splash.pas' {Fsplash},
  util in 'Config\util.pas',
  testePrimo in 'Testes\testePrimo.pas' {FTestPrimeNumber},
  testeMdcExt in 'Testes\testeMdcExt.pas' {FTesteMdcExt},
  testeAritMod in 'Testes\testeAritMod.pas' {FTesteAritMod},
  comoRsaFunc in 'ComoRSAF\comoRsaFunc.pas' {FHowRSAWorks},
  sobre in 'Sobre\sobre.pas' {FAbout};

{$R *.RES}
var
  F_Splash  : Tform;
  TempoINI  : TDateTime;
  Intervalo : single;
begin
    Application.Initialize;
    Application.Title := 'Criptografia RSA - maRcello Junior, marcuS túlio, Alex Moreira';

    TempoINI := Time;
    Intervalo:=10;

    F_Splash := TFsplash.Create(nil);
    F_Splash.Show;
    F_Splash.Update;

{    try
       Application.CreateForm(Tmain, main);
    finally

    end;
}
    Application.CreateForm(Tmain, main);
  repeat
    until Time-TempoINI > Intervalo/SecsPerDay;

  F_Splash.Hide;
  F_Splash.Free;

  Application.Run
end.
