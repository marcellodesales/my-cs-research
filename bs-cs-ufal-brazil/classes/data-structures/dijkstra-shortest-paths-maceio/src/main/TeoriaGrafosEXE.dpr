program TeoriaGrafosEXE;

uses
  Forms,
  Uprinc in 'Uprinc.pas' {F_Princ},
  Ututorial in 'Tutor\Ututorial.pas' {F_Tutorial},
  UManipuladocs in 'Tutor\UManipuladocs.pas',
  Umaceio in 'Maceio\Umaceio.pas' {F_Maceio},
  pilha in 'Units\pilha.pas',
  graph in 'Units\graph.pas',
  Uexemplo in 'Exemplo\Uexemplo.pas' {F_Exemplo},
  UManipulaTBAdj in 'Exemplo\UManipulaTBAdj.pas',
  Msgdlg in 'Units\Msgdlg.pas',
  UConfiguracao in 'Configuracao\UConfiguracao.pas' {F_Config},
  UConfig in 'Units\UConfig.pas',
  UManipulaOBJ in 'Units\UManipulaOBJ.pas';

{$R *.RES}

begin
  Application.Initialize;
  Application.CreateForm(TF_Princ, F_Princ);
  Application.Run;
end.
