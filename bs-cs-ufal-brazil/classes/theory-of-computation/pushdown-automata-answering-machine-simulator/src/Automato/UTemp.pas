unit UTemp;

interface
uses inifiles,sysutils;
type
    TTemp = class
            FitaControle :String;

            Constructor Create;
            Function NumeroRecado(Arquivo:String):Integer;
            Procedure GetFita(Arquivo:String);
            Procedure Empilha(Arquivo:String; Mensagem:String;var Erro:boolean);
            Procedure Desempilha(Arquivo:String; Var Mensagem:String);
    end;

implementation

Constructor TTemp.Create;
begin
     inherited Create;
end;

Function TTemp.NumeroRecado(Arquivo:String):Integer;
var ArqTMP:Tinifile;
begin
   if FileExists(Arquivo) then
   begin
        ArqTMP:=Tinifile.Create(Arquivo);
        NumeroRecado:=ArqTMP.ReadInteger('Geral','Numero de recados',-1);
        ArqTMP.Free;
   end
   else NumeroRecado:=0;
end;

Procedure TTemp.GetFita(Arquivo:String);
var ArqTMP:Tinifile;
begin
     if FileExists(Arquivo) then
     begin
          ArqTMP:=TINIfile.Create(Arquivo);
          Self.FitaControle:=ArqTMP.ReadString('Geral','Fita Controle','');
     end;
end;

Procedure TTemp.Empilha(Arquivo:String; Mensagem:String;var Erro:boolean);
var ArqTMP:Tinifile;
    NumRecadoTMP:integer;
    Fita:String;
begin
   Erro:=false;
   NumRecadoTMP:=NumeroRecado(Arquivo);
   if NumRecadoTMP = 10 then
      Erro:=True
   else
   begin
        ArqTMP:=TINIfile.Create(Arquivo);
        INC(NumRecadoTMP);
        Fita:=ArqTMP.ReadString('Geral','Fita Controle','');
        Fita:=Fita+'ww';
        ArqTMP.WriteString('Geral','Fita Controle',Fita);
        ArqTMP.WriteInteger('Geral','Numero de recados',NumRecadoTMP);
        ArqTMP.WriteString('Mensagens',inttostr(NumRecadoTMP),Mensagem);
        ArqTMP.Free;
   end;
end;

Procedure TTEMP.Desempilha(Arquivo:String; Var Mensagem:String);
var ArqTMP:TINIfile;
    NumRecadoTMP,ChaveDeletada:integer;
begin
   if FileExists(Arquivo) then
   begin
        ArqTMP:=TINIfile.Create(Arquivo);
        Mensagem:=ArqTMP.ReadString('Mensagens',inttostr(NumeroRecado(Arquivo)),'ERROR');
        ChaveDeletada:=NumeroRecado(Arquivo);
        ArqTMP.DeleteKey('Mensagens',inttostr(ChaveDeletada));
        NumRecadoTMP:=NumeroRecado(Arquivo);
        ArqTMP.WriteInteger('Geral','Numero de recados',NumRecadoTMP-1);
        ArqTMP.Free;
   end
   else
       Mensagem:='S';
end;

end.
