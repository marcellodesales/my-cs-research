program MaceioEXE;

uses
  Forms,
  Umaceio in 'Umaceio.pas' {F_Maceio};

{$R *.RES}

begin
  Application.Initialize;
  Application.CreateForm(TF_Maceio, F_Maceio);
  Application.Run;
end.
