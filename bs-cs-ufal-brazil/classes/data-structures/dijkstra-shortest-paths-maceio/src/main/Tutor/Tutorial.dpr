program Tutorial;

uses
  Forms,
  Ututorial in 'Ututorial.pas' {Form1},
  UManipuladocs in 'UManipuladocs.pas';

{$R *.RES}

begin
  Application.Initialize;
  Application.CreateForm(TForm1, Form1);
  Application.Run;
end.
