unit pilha;

interface
const MaxStack = 20;
type
    StackEntry = integer;
    StackTypeE = record
         Top:integer;
         StackArray:array[1..MaxStack] of StackEntry;
    end;

    StackPointer = ^StackNode;
    StackNode = record
              Value:StackEntry;
              NextNode:StackPointer;
    end;
    StackTypeD = record
              Top:StackPointer;
              Size:Integer;
    end;

    procedure CreateStackE(var S:StackTypeE);
    procedure PushE(X:StackEntry; var S:StackTypeE);
    procedure PopE(var X:StackEntry; var S:StackTypeE);
    procedure PopEAll(var S:StackTypeE);
    procedure CopyStackE(S:StackTypeE; var CopyS:StackTypeE);
    function Empty(S:StackTypeE):Boolean;
    function Full(S:StackTypeE):Boolean;
    function Size(S:StackTypeE):integer;

implementation

procedure CreateStackE(var S:StackTypeE);
begin
     S.top:=0;
end;

procedure PushE(X:StackEntry; var S:StackTypeE);
begin
     If Full(S) Then
     Else
         begin
              Inc(S.Top);
              S.StackArray[S.Top]:=X;
         end;
end;

procedure PopE(var X:StackEntry; var S:StackTypeE);
begin
     If Empty(S) Then
     Else
      begin
         X:=S.StackArray[S.Top];
         Dec(S.Top);
      end;
end;

procedure PopEAll(var S:StackTypeE);
begin
     S.Top:=0;
end;

procedure CopyStackE(S:StackTypeE; var CopyS:StackTypeE);
var i:integer;
begin
     CreateStackE(CopyS);
     for i:= 1 to S.Top do
          CopyS.StackArray[i]:=S.StackArray[i];
     CopyS.Top := S.Top;
end;

function Empty(S:StackTypeE):Boolean;
begin
     Empty:=S.Top=0;
end;

function Full(S:StackTypeE):Boolean;
begin
     Full:=S.Top=MaxStack;
end;

function Size(S:StackTypeE):integer;
begin
     Size:=S.Top;
end;

end.
