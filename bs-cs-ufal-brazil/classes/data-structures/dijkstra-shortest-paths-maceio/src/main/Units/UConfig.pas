unit UConfig;

interface
uses Inifiles;
Type
    TConfig = Class
      URLTutor:string;
      function GetURLTutor(arquivo:string):string;
      Procedure PassaURL(Arquivo:string);
    end;

implementation

Procedure TConfig.PassaURL(Arquivo:string);
var Confini:Tinifile;
begin
     Confini:=TInifile.Create(arquivo);
     Confini.WriteString('Web','URL',self.URLTutor);
     Confini.Free;
end;

function TConfig.GetURLTutor(arquivo:string):string;
var Confini:Tinifile;
begin
     Confini:=TInifile.Create(arquivo);
     GetURLTutor:=Confini.ReadString('Web','URL','ERROR');
     Self.URLTutor:=Confini.ReadString('Web','URL','ERROR');
     Confini.Free;
end;
end.
