unit confRsa;

interface
uses Inifiles, SysUtils;

Type
  TConfigRSA = Class
    N :int64;
    E :int64;
    D :int64;
    procedure updateRSAConfig(Arquivo:String);
    Procedure GetAllRSAConfig(Arquivo:String);
    Procedure setConfRSAChavePublica(N,E:int64);
    Procedure setConfRSAChavePrivada(D:int64);
end;

implementation

Procedure TConfigRSA.setConfRSAChavePublica(N,E:int64);
begin
        self.N := N;
        Self.E := E;
end;

Procedure TConfigRSA.setConfRSAChavePrivada(D:int64);
begin
        self.D := D;
end;

procedure TConfigRSA.updateRSAConfig(Arquivo:String);
Var ConfigINI:Tinifile;
begin
        ConfigINI := TInifile.Create(Arquivo);
        ConfigINI.WriteString('Chave Pública','N',intToStr(Self.N));
        ConfigINI.WriteString('Chave Pública','E',intToStr(self.E));
        ConfigINI.WriteString('Chave Privada','N',intToStr(Self.N));        
        ConfigINI.WriteString('Chave Privada','D',intToStr(self.D));
        ConfigINI.Free;
end;

Procedure TConfigRSA.GetAllRSAConfig(Arquivo:String);
Var ConfigINI:Tinifile;
begin
     ConfigINI:=TInifile.Create(Arquivo);
     Self.N := StrToInt64(ConfigINI.ReadString('Chave Pública','N','ERROR'));
     Self.E := StrToInt64(ConfigINI.ReadString('Chave Pública','E','ERROR'));
     Self.D := StrToInt64(ConfigINI.ReadString('Chave Privada','D','ERROR'));
     ConfigINI.Free;
end;

end.
