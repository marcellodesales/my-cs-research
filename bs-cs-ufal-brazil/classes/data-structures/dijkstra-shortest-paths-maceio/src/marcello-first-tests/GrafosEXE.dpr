program GrafosEXE;

uses
  Forms,
  Uexemplo in '..\Principal\Exemplo\Uexemplo.pas' {F_Exemplo},
  Ucusto in 'Ucusto.pas',
  graph in '..\..\algoritmo\graph.pas',
  pilha in '..\..\algoritmo\pilha.pas';

{$R *.RES}
{$R insere.RES}
begin
  Application.Initialize;
  Application.CreateForm(TF_Exemplo, F_Exemplo);
  Application.Run;
end.
