unit UFastDiskINI;

interface
uses inifiles,SysUtils;
Type
  TFastDisk = Class
    Nome:String;
    Numero:String;
    Procedure Davalores(SNome,SNumero:String);
    Procedure GetAll(Arquivo,NumLR:String);
    Function GetNome(Arquivo,NumLR:String):String;
    Function GetFone(Arquivo,NumLR:String):String;
    Procedure AtualzaINI(Arquivo,NumLR:String);
  end;

implementation

Function TFastDisk.GetNome(Arquivo,NumLR:String):String;
var FastDisk:Tinifile;
begin
     FastDisk:=TIniFile.Create(Arquivo);
     GetNome:=FastDisk.ReadString('Nome',NumLR,'ERROR');
     FastDisk.Free;
end;

Function TFastDisk.GetFone(Arquivo,NumLR:String):String;
var FastDisk:Tinifile;
begin
     FastDisk:=TIniFile.Create(Arquivo);
     GetFone:=FastDisk.ReadString('Telefone',NumLR,'ERROR');
     FastDisk.Free;
end;

Procedure TFastDisk.Davalores(SNome,SNumero:String);
begin
     Nome:=SNome;
     Numero:=SNumero;
end;

Procedure TFastDisk.GetAll(Arquivo,NumLR:String);
var FastDisk:Tinifile;
begin
     FastDisk:=TIniFile.Create(Arquivo);
     Self.Nome:=FastDisk.ReadString('Nome',NumLR,'ERROR');
     Self.Numero:=FastDisk.ReadString('Telefone',NumLR,'ERROR');
     FastDisk.Free;
end;

Procedure TFastDisk.AtualzaINI(Arquivo,NumLR:String);
var FastDisk:Tinifile;
begin
     FastDisk:=TInifile.Create(Arquivo);
     FastDisk.WriteString('Nome',NumLR,Self.Nome);
     FastDisk.WriteString('Telefone',NumLR,Self.Numero);
     FastDisk.Free;
end;

end.
