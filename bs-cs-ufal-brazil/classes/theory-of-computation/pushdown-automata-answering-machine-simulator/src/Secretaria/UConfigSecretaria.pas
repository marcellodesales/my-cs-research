unit UConfigSecretaria;

interface
uses Inifiles;

Type
  TConfigSecretaria = Class
    DataUltimoAcesso:String;
    HoraUltimoAcesso:String;
    UltimaSecretaria:String;
    procedure AtualizaConfig(Arquivo:String);
    Procedure GetAllConfig(Arquivo:String);
    Procedure DaValoresSistema(Data,Hora,UltimaSecr:String);
  end;

implementation

Procedure TConfigSecretaria.DaValoresSistema(Data,Hora,UltimaSecr:String);
begin
    DataUltimoAcesso:=Data;
    HoraUltimoAcesso:=Hora;
    UltimaSecretaria:=UltimaSecr;
end;

procedure TConfigSecretaria.AtualizaConfig(Arquivo:String);
Var ConfigINI:Tinifile;
begin
     ConfigINI:=TInifile.Create(Arquivo);
     ConfigINI.WriteString('Data e Hora','Ultimo Acesso',DataUltimoAcesso);
     ConfigINI.WriteString('Data e Hora','Horario',HoraUltimoAcesso);
     ConfigINI.WriteString('Acessos','Ultima Secretaria',UltimaSecretaria);
     ConfigINI.Free;
end;

Procedure TConfigSecretaria.GetAllConfig(Arquivo:String);
Var ConfigINI:Tinifile;
begin
     ConfigINI:=TInifile.Create(Arquivo);
     Self.DataUltimoAcesso:=ConfigINI.ReadString('Data e Hora','Ultimo Acesso','ERROR');
     Self.HoraUltimoAcesso:=ConfigINI.ReadString('Data e Hora','Horario','ERROR');
     Self.UltimaSecretaria:=ConfigINI.ReadString('Acessos','Ultima Secretaria','ERROR');
     ConfigINI.Free;
end;
{
Var mensagens,config:Tinifile;
    a,b,c           :string;
    I               :integer;
     delayButoes(0.3,PlayUP,PlayDown);
     //memo1.Clear;
     config:=Tinifile.Create(ExtractFilePath(Application.ExeName)+'configuração.ini');
     a:=config.ReadString('Dir','Mensagens','-1');
     c:=config.ReadString('Dir','Mensagens','-1');

     if not fileexists(a) then
          Messagedialog('Arquivo de mensagens não encontrado no diretório '+a+'.',mtError,[mbOK],1)
     else
     begin
          mensagens:=Tinifile.Create(a);
          randomize;
          I:=Random(35);
          b:=IntToStr(I);
          a:=mensagens.ReadString('Mensagens',b,'-1');
          if a='-1' then
             a:='Não existe nenhuma mensagem no arquivo '+c+'.';
          //memo1.Lines[0]:=a;
     end;
     config.Free;
end;}

end.
