unit UAutomatoTeste;

interface
Type
  TEstado       = String[2];
  TCharFita     = String;
  TSimboloPilha = Char;

  TAutomato = Class
    Estado       : ShortString;
    CharFita     : Char;
    SimboloPilha : Char;
    FitaControle : String;
    PilhaAutomato: String;
    Mensagem     : String;

    procedure Inicializa;
    Procedure ExecutaAutomato;
  end;

implementation

procedure TAutomato.Inicializa;
begin
    Self.Estado:='q1';
    Self.SimboloPilha:='S';
    Self.CharFita:=' ';
    Self.FitaControle:='';
    Self.PilhaAutomato:='S';
end;

Procedure TAutomato.ExecutaAutomato;
begin
   with self do
   begin
     if Estado='q1' then
     begin
          if (CharFita='w') and ((SimboloPilha='S') or (SimboloPilha='M')) then
          begin
                SimboloPilha:='M'; //Inicializa(Estado,CharFita,'M')
                PilhaAutomato:=PilhaAutomato+'M';
          end;
          if (CharFita='c') and (SimboloPilha='M') then
             Estado:='q2'; //Inicializa('q2',CharFita,'M');
     end
     else
     begin
          if CharFita='r' then
             if SimboloPilha='M' then
             begin
                //Inicializa(Estado,CharFita,'M');
                delete(PilhaAutomato,length(PilhaAutomato),1);
                SimboloPilha:=PilhaAutomato[length(PilhaAutomato)];
             end;
          if CharFita='c' then
                Estado:='q1'; //Inicializa('q1',CharFita,SimboloPilha);

     end;
     FitaControle:=FitaControle+charFita;
   end;
end;

end.
