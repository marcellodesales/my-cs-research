unit UConfigINI;

interface
Uses IniFiles,Windows;
Type
  TConfigINI = Class
    Splash       : Boolean;
    TempoSplash  : integer;
    BarraTarefas : Boolean;
    SomMudo      : Boolean;
    Procedure EscondeBarra(Visivel: Boolean;ArquivoConfig:String);
    Procedure GetAll(ArquivoConfig:String);
    Procedure AtualizaINI(ArquivoConfig:String);
    Function GetTempoSplash(ArquivoConfig:String):integer;
  end;

implementation

Function TConfigINI.GetTempoSplash(ArquivoConfig:String):integer;
var ArqConfig : Tinifile;
begin
     ArqConfig:=Tinifile.Create(ArquivoConfig);
     GetTempoSplash:=ArqConfig.ReadInteger('Tela de Abertura','Intervalo',-1);
     ArqConfig.Free;
end;

Procedure TConfigINI.AtualizaINI(ArquivoConfig:String);
var ArqConfig : Tinifile;
begin
     ArqConfig:=Tinifile.Create(ArquivoConfig);
     ArqConfig.WriteBool('Barra de Tarefas','Visivel',Self.BarraTarefas);
     ArqConfig.WriteBool('Tela de Abertura','Mostrar',Self.Splash);
     ArqConfig.WriteBool('Som do Ambiente','Estado Mudo',Self.SomMudo);
     ArqConfig.WriteInteger('Tela de Abertura','Intervalo',Self.TempoSplash);
     ArqConfig.Free;
end;

Procedure TConfigINI.EscondeBarra(Visivel: Boolean;ArquivoConfig:String);
var ArqConfig : Tinifile;
begin
     ArqConfig:=Tinifile.Create(ArquivoConfig);
     If Visivel = True Then
        ShowWindow(FindWindow('Shell_TrayWnd',Nil),SW_HIDE)
     else
         ShowWindow(FindWindow('Shell_TrayWnd',Nil),SW_RESTORE);
     ArqConfig.WriteBool('Barra de Tarefas','Visivel',Visivel);
     ArqConfig.Free;
end;

Procedure TConfigINI.GetAll(ArquivoConfig:String);
var ArqConfig : Tinifile;
    A,B,C:Boolean;
begin
     ArqConfig:=Tinifile.Create(ArquivoConfig);
     Self.BarraTarefas:=ArqConfig.ReadBool('Barra de Tarefas','Visivel',A);
     Self.SomMudo:=ArqConfig.ReadBool('Som do Ambiente','Estado Mudo',C);
     Self.Splash:=ArqConfig.ReadBool('Tela de Abertura','Mostrar',B);
     Self.TempoSplash:=ArqConfig.ReadInteger('Tela de Abertura','Intervalo',-1);
     ArqConfig.Free;
end;

end.
