unit UAutomatoPilha;

interface
uses Inifiles,SysUtils;

Type
  TAutomato = Class
    Estado       :ShortString;
    IndFita     :Integer;
    SimboloPilha :Char;
    FitaControle :String;
    PilhaAutomato:String;

    Constructor Create;
    procedure Inicializa; overload;
    procedure Inicializa(VEstado:shortstring;VSimboloPilha:Char;VIndFita:Integer); overload;
    Procedure ExecutaAutomato(Arquivo:String;var Mensagem:String);
    Procedure AtualizaAutomatoINI(Arquivo:string);
    Function GetFita(Arquivo:String):String;
    Function GetPilha(Arquivo:String):String;
    Procedure GetAll(Arquivo:String);
    function GetExplicacao:String;

    Function NumeroRecado(Arquivo:String):Integer;

    Private
           Procedure Empilha(Arquivo:String; Mensagem:String;var Erro:boolean);
           Procedure Desempilha(Arquivo:String; Var Mensagem:String);
    end;

implementation

Constructor TAutomato.Create;
begin
     inherited create;
end;

procedure TAutomato.Inicializa;
begin
    Self.Estado:='q1';
    Self.SimboloPilha:='S';
    Self.IndFita:=0;
    Self.FitaControle:='';
    Self.PilhaAutomato:='S';
end;

procedure TAutomato.Inicializa(VEstado:shortstring;VSimboloPilha:Char;VIndFita:Integer);
begin
    Self.Estado:=VEstado;
    Self.IndFita:=VIndFita;
    Self.SimboloPilha:=VSimboloPilha;
end;

Procedure TAutomato.ExecutaAutomato(Arquivo:String;var Mensagem:String);
var Erro:Boolean;
begin
   with self do
   begin
     if Estado='q1' then
     begin
          if (FitaControle[IndFita]='w') and ((SimboloPilha='S') or (SimboloPilha='M')) then
          begin
                SimboloPilha:='M'; //Inicializa(Estado,IndFita,'M')
                PilhaAutomato:=PilhaAutomato+'M';
                Empilha(Arquivo,Mensagem,Erro);
          end;
          if (FitaControle[IndFita]='c') and (SimboloPilha='M') then
             Estado:='q2'; //Inicializa('q2',IndFita,'M');
     end
     else
     begin
          if FitaControle[IndFita]='w' then
             if SimboloPilha='M' then
             begin
                //Inicializa(Estado,IndFita,'M');
                delete(PilhaAutomato,length(PilhaAutomato),1);
                SimboloPilha:=PilhaAutomato[length(PilhaAutomato)];
                Desempilha(Arquivo,Mensagem);
             end;
          if FitaControle[IndFita]='c' then
                Estado:='q1'; //Inicializa('q1',IndFita,SimboloPilha);

     end;
   end;
   inc(Self.IndFita);
   Self.AtualizaAutomatoINI(Arquivo);
end;

Procedure TAutomato.AtualizaAutomatoINI(Arquivo:string);
var ArqINI:Tinifile;
begin
     ArqINI:=Tinifile.Create(Arquivo);
     ArqINI.WriteString('Automato','Estado',Self.Estado);
     ArqINI.WriteInteger('Automato','Indice da Fita',Self.IndFita);
     ArqINI.WriteString('Automato','Simbolo Pilha',Self.SimboloPilha);
     ArqINI.WriteString('Automato','Fita de controle',Self.FitaControle);
     ArqINI.WriteString('Automato','Pilha do Automato',Self.PilhaAutomato);
     ArqINI.Free;
end;

Function TAutomato.GetFita(Arquivo:String):String;
var ArqINI:Tinifile;
begin
   if FileExists(Arquivo) then
   begin
        ArqINI:=Tinifile.Create(Arquivo);
        GetFita:=ArqINI.ReadString ('Automato','Fita de controle','ERROR');
        ArqINI.Free;
   end
   else GetFita:='';
end;

Function TAutomato.GetPilha(Arquivo:String):String;
var ArqINI:Tinifile;
begin
   if FileExists(Arquivo) then
   begin
        ArqINI:=Tinifile.Create(Arquivo);
        GetPilha:=ArqINI.ReadString ('Automato','Pilha do Automato','ERROR');
        ArqINI.Free;
   end
   else GetPilha:='';
end;

Procedure TAutomato.GetAll(Arquivo:String);
var ArqINI:Tinifile;
begin
     ArqINI:=Tinifile.Create(Arquivo);
     Self.Estado:=ArqINI.ReadString('Automato','Estado','ERROR');
     Self.IndFita:=ArqINI.ReadInteger('Automato','Indice da Fita',0);
     Self.SimboloPilha:=ArqINI.ReadString('Automato','Simbolo Pilha','E')[1];
     Self.FitaControle:=ArqINI.ReadString('Automato','Fita de controle','ERROR');
     Self.PilhaAutomato:=ArqINI.ReadString('Automato','Pilha do Automato','ERROR');
     ArqINI.Free;
end;

Function TAutomato.NumeroRecado(Arquivo:String):Integer;
var ArqINI:Tinifile;
begin
   if FileExists(Arquivo) then
   begin
        ArqINI:=Tinifile.Create(Arquivo);
        NumeroRecado:=ArqINI.ReadInteger('Recados','Numero de recados',0);
        ArqINI.Free;
   end
   else NumeroRecado:=0;
end;

Procedure TAutomato.Empilha(Arquivo:String; Mensagem:String;var Erro:boolean);
var ArqINI:Tinifile;
    NumRecadoINI:integer;
begin
   Erro:=false;
   NumRecadoINI:=NumeroRecado(Arquivo);
   if NumRecadoINI = 10 then
      Erro:=True
   else
   begin
        ArqINI:=Tinifile.Create(Arquivo);
        INC(NumRecadoINI);
        ArqINI.WriteInteger('Recados','Numero de recados',NumRecadoINI);
        ArqINI.WriteString('Recado',inttostr(NumRecadoINI),Mensagem);
        ArqINI.Free;
   end;
end;

Procedure TAutomato.Desempilha(Arquivo:String; Var Mensagem:String);
var ArqINI:Tinifile;
    NumRecadoINI,ChaveDeletada:integer;
begin
   if FileExists(Arquivo) then
   begin
        ArqINI:=Tinifile.Create(Arquivo);
        Mensagem:=ArqINI.ReadString('Recado',inttostr(NumeroRecado(Arquivo)),'ERROR');
        ChaveDeletada:=NumeroRecado(Arquivo);
        ArqINI.DeleteKey('Recado',inttostr(ChaveDeletada));
        NumRecadoINI:=NumeroRecado(Arquivo);
        Dec(NumRecadoINI);
        ArqINI.WriteInteger('Recados','Numero de recados',NumRecadoINI);
        ArqINI.Free;
   end
   else
       Mensagem:='S';
end;

function TAutomato.GetExplicacao:String;
var Explication:String;
begin
     If Self.Estado='q1'
     then
         begin
              case FitaControle[Self.IndFita] of
              'w':If Self.FitaControle[length(Self.FitaControle)-1]='c' then
                    Explication:='Mudamos do estado de leitura para gravação, e empilhamos uma nova mensagem.'+
                       #13+'{q2,c,'+Self.SimboloPilha+'} --> {q1,'+Self.SimboloPilha+'}'+
                       #13+'{q1,w,'+Self.SimboloPilha+'} --> {q1,M'+Self.SimboloPilha+'}'
                  Else
                    If Self.PilhaAutomato[length(Self.PilhaAutomato)-1]='S' then
                       Explication:='Foi empilhada uma mensagem sobre a mensagem vazia S.'+
                          #13+'{q1,w,S} --> {q1,MS}'
                    else Explication:='Aconteceu o empilhamento de uma mensagem sobre outra.'+
                          #13+'{q1,w,M} --> {q1,MM}';

              'c':Explication:='Houve uma mudança do estado de leitura para o estado de gravação.'+
                       #13+'{q2,c,'+Self.SimboloPilha+'} --> {q1,'+Self.SimboloPilha+'}';
              end;
         end
     else
         begin
              case FitaControle[Self.IndFita] of
              'w':If Self.FitaControle[length(Self.FitaControle)-1]='c' then
                    Explication:='Desempilhando a última mensagem.'+
                       #13+'{q2,w,'+Self.SimboloPilha+'} --> {q2,E}'
                  Else
                       Explication:='Mudamos do estado de gravação para o estado de leitura.'+
                       #13+'{q2,c,M} --> {q2,E}';

              'c':Explication:='Mudando do estado de leitura (q2) para o estado de gravação (q1).'+
                       #13+'{q2,c,'+Self.SimboloPilha+'} --> {q1,'+Self.SimboloPilha+'}';
              end;
         end;
         GetExplicacao:=Explication;

end;

end.
