unit UPilhaTeste;

interface
Type
  TMensagem = String;
  Tpilha = ^Nopilha;
  Nopilha = record
     valor: Tmensagem;
     prox : Tpilha;
     Quant:integer;
  end;

  TPilhas = Class
     Quant:Integer;
     Procedure Inicializa(Pilha:Tpilha);
     Procedure Empilha(Mensagem:Tmensagem;Var Pilha:Tpilha);
     Procedure Desempilha(Var Mensagem:Tmensagem;Var Pilha:Tpilha);
     Function QuantidadeRecados(Pilha:Tpilha):Integer;
  end;

implementation

procedure Tpilhas.Inicializa(Pilha:Tpilha);
begin
    pilha:=nil;
    Pilha^.Quant:=0;
end;

procedure Tpilhas.Empilha(Mensagem:Tmensagem; var Pilha:Tpilha);
var aux:Tpilha;
begin
     new(aux);
     aux^.valor:=Mensagem;
     aux^.prox:=Pilha;
     Pilha:=aux;
end;

procedure Tpilhas.Desempilha(Var Mensagem:Tmensagem;Var Pilha:Tpilha);
var aux:Tpilha;
begin
     If Pilha<>nil Then
     Begin
          aux:=Pilha^.prox;
          Mensagem:=Pilha^.valor;
          dispose(Pilha);
          Pilha:=aux;
     End;
end;

Function Tpilhas.QuantidadeRecados(Pilha:Tpilha):Integer;
begin
     QuantidadeRecados:=Pilha^.Quant;
end;

end.
