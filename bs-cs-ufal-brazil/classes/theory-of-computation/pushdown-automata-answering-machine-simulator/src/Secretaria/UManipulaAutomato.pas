unit UManipulaAutomato;

interface
uses SysUtils,StdCtrls,UAutomatoPilha;

Type
  TGraficosAutomato = Class
    Procedure ColocaExplicacaoNoMemo(Memo:TMemo;Texto:String);
    Procedure ColocaAutomatonoMemoPilha(Memo:Tmemo;PilhadoAutomato:String;NumRecadosINI:Integer);
    procedure MemosEmpiOuDesem(Acao:byte;Memo:TMemo;NumRecadosINI:Integer;SimbPilha:Char);
  end;

implementation

Procedure TGraficosAutomato.ColocaExplicacaoNoMemo(Memo:TMemo;Texto:String);
var indini,indfim:integer;
    linha:String;
begin
     indini:=1;
     repeat
           indfim:=pos(#13,Texto);
           If indfim=0 then
           begin
                indfim:=length(Texto);
                linha:=Copy(Texto,indini,indfim-indini+1);
           end
           else
           begin
                delete(Texto,indfim,1);
                linha:=Copy(Texto,indini,indfim-indini);
           end;
           Memo.Lines.Add(linha);
           if indfim<>length(Texto) then
              linha:=Copy(Texto,indini,indfim-indini+1)
           else linha:=Copy(Texto,indini,indfim-indini+1);
           indini:=indfim;
     until indfim=length(Texto);
end;

Procedure TGraficosAutomato.ColocaAutomatonoMemoPilha(Memo:Tmemo;PilhadoAutomato:String;NumRecadosINI:Integer);
{Pré-Condição: nenhum;
 Pós-Condição: }
var CharPilha:String;
    i:shortint;
begin
     For i:=1 to NumRecadosINI+1 do
     begin
          CharPilha:=Copy(PilhadoAutomato,i,1);
          Memo.Lines[11-i]:=CharPilha;
     end;
end;

procedure TGraficosAutomato.MemosEmpiOuDesem(Acao:byte;Memo:TMemo;NumRecadosINI:Integer;SimbPilha:Char);
{Pré-Condição: nenhuma;
 Pós-Condição: Se o parâmetro Acao for 0, então empilha um M,
       Caso contrário desempilha M. (simbolo da pilha do Automato)}
const linha=10;
begin
     if Acao=0 then
        Memo.Lines[linha-NumRecadosINI]:=SimbPilha
     else
         Memo.Lines[linha-NumRecadosINI-1]:='';
end;

end.
