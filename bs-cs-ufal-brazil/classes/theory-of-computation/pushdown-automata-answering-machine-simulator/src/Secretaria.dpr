program Secretaria;

uses
  Forms,
  IniFiles,
  SysUtils,
  Uprincipal in 'Uprincipal.pas' {F_Princ},
  UAFD in 'AFD\UAFD.pas' {F_AFD},
  USplash in 'Splash\USplash.pas' {F_Splash},
  UConfig in 'UConfig\UConfig.pas' {F_Config},
  UDiscador in 'Validator\UDiscador.pas' {F_Discador},
  valida in 'Validator\Valida.pas',
  Umensagem in 'Validator\Umensagem.pas' {F_Mensagem},
  UFastDisk in 'Validator\UFastDisk.pas' {F_FastDisk},
  UConfigSecretaria in 'Secretaria\UConfigSecretaria.pas',
  UEscolheNumero in 'Secretaria\UEscolheNumero.pas' {F_EscolhaNumero},
  UManipulaAutomato in 'Secretaria\UManipulaAutomato.pas',
  UAutomatoPilha in 'Automato\UAutomatoPilha.pas',
  UmanipulaButao in 'Secretaria\UmanipulaButao.pas',
  UFastDiskINI in 'Validator\UFastDiskINI.pas',
  UPilhaTeste in 'Testes\PushDown Automata\UPilhaTeste.pas',
  UAutomatoTeste in 'Testes\PushDown Automata\UAutomatoTeste.pas',
  UTesteValida in 'Testes\Teste Fone\UTesteValida.pas' {F_TesteFONE},
  UTemp in 'Automato\UTemp.pas',
  USecretariaEletronica in 'Secretaria\USecretariaEletronica.pas' {F_SecretariaEletronica},
  UConfigINI in 'UConfig\UConfigINI.pas',
  UAvisoAutomato in 'Secretaria\UAvisoAutomato.pas' {F_AvisoAutomato},
  UConfigsom in 'UConfig\UConfigsom.pas' {F_ConfigSom};

{$R *.RES}
{$R D0.RES}  //10
{$R D1.RES}
{$R D2.RES}
{$R D3.RES}
{$R D4.RES}
{$R D5.RES}
{$R D6.RES}
{$R D7.RES}
{$R D8.RES}
{$R D9.RES}
{$R INVALIDO.RES} //11
{$R Estrela.RES}  //12
{$R jogoDV.RES}   //13
{$R Lembrar.RES}  //1
{$R seunumero.RES} //15
{$R chamando.RES}  //16
{$R Digit0.RES} //20
{$R Digit1.RES} //21
{$R Digit2.RES} //22
{$R Digit3.RES}
{$R Digit4.RES}
{$R Digit5.RES}
{$R Digit6.RES}
{$R Digit7.RES}
{$R Digit8.RES}
{$R Digit9.RES} //29
{$R Recado0.RES}  //50
{$R Recado1.RES}  //51
{$R Recado2.RES}
{$R Recado3.RES}
{$R Recado4.RES}
{$R Recado5.RES}
{$R Recado6.RES}
{$R Recado7.RES}
{$R Recado8.RES}
{$R Recado9.RES}
{$R Recado10.RES} //60
{$R VOCELIGOUPARA.RES} //70
{$R ENTRADA.RES}  //100
{$R LIGOUMARCELLOJUNIOR.RES} //110
{$R RECADOSALVO.RES} //111
{$R OBRIGADOAGUARDE.RES} //112
{$R Sinal.Res} //114
{$R Ocupado.RES} //115
{$R Deixeumrecado.RES} //120
{$R AbrirApp.RES}  //200

var
  F_Splash  : Tform;
  TempoINI  : TDateTime;
  Intervalo : single;
  config    : TConfigINI;
  ArquivoConfig:String;
begin
  Application.Initialize;
  TempoINI := Time;
  ArquivoConfig:=ExtractFilePath(Application.ExeName)+'configuracao.ini';
  config:=TConfigINI.Create;
  Config.GetAll(ArquivoConfig);
  if Config.Splash=True then
  begin
    Intervalo:=Config.GetTempoSplash(ArquivoConfig);
    F_Splash := TF_Splash.Create(Application);
    F_Splash.Show;
    F_Splash.Update;
    try
       Application.CreateForm(TF_Princ, F_Princ);
  Application.CreateForm(TF_AvisoAutomato, F_AvisoAutomato);
  Config.GetAll(ArquivoConfig);
       Config.EscondeBarra(Config.BarraTarefas,ArquivoConfig);
    finally
       repeat
       until Time-TempoINI > Intervalo/SecsPerDay;
    F_Splash.Close;
    F_Splash.Free;
    Config.Free;
    end;
  end
  else
  begin
       Application.CreateForm(TF_Princ, F_Princ);
  end;
  Application.Run;
end.
