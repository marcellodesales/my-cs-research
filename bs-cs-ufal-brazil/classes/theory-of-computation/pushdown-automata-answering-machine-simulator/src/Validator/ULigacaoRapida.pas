unit ULigacaorapida;

interface
uses inifiles;
Type
  TFastDisk = Class
    Nome:String;
    Numero:String;
    Procedure Davalores(SNome,SNumero:String);
    Procedure GetAll(Arquivo,NumLR,Nome,Fone:String);
    Procedure AtualzaINI(Arquivo:String);
  end;

implementation

Procedure TFastDisk.Davalores(SNome,SNumero:String);
begin
     Nome:=SNome;
     Numero:=SNumero;
end;

Procedure TFastDisk.GetAll(Arquivo,NumLR,Nome,Fone:String);
var FastDisk:Tinifile;
begin
     FastDisk:=TFastDisk.Create(Arquivo);
     Self.Nome:=FastDisk.ReadString('Nome',NumLR,Nome);
     Self.Numero:=FastDisk.ReadString('Telefone',NumLR,Fone);
     FastDisk.Free;
end;

Procedure TFastDisk.AtualzaINI(Arquivo,NumLR:String);
var FastDisk:Tinifile;
begin
     FastDisk:=TFastDisk.Create(Arquivo);
     FastDisk.WriteString('Nome',NumLR,Self.Nome);
     FastDisk.WriteString('Telefone',NumLR,Self.Numero);
     FastDisk.Free;
end;

end.
