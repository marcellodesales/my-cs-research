unit UManipulaOBJ;

interface
uses OvalBtn, Forms, SysUtils;

procedure PinteVertice(num:integer; cor:integer; Formulario:TForm);
procedure Delay(tempo:single);

implementation

procedure PinteVertice(num:integer; cor:integer; Formulario:TForm);
var i:integer;
    nome:string;
begin
     nome:='v'+inttostr(num);
     for i:=0 to Formulario.ComponentCount-1 Do
         if (Formulario.Components[i] is TOvalButton) then
            If (Formulario.Components[i] as TOvalButton).Name = nome then
               (Formulario.Components[i] as TOvalButton).Color:=cor;
end;

procedure Delay(tempo:single);
var Tempoini :TDateTime;
begin
     Tempoini := now;
     repeat
          Application.ProcessMessages;
     until Now > tempoini + Tempo * (1/24/60/60);
end;

end.
