unit UmanipulaButao;

interface
uses ExtCtrls,SysUtils,Forms;

Type
  TButoes = Class
    ButaoUP:TImage;
    ButaoDown:TImage;
    procedure delayButoes(TempoSeg:single; ImagemUP,ImagemDown:Timage);
    Procedure DelayAbreFormulario(TempoSeg:Single; Formulario:TForm);
  end;

implementation

Procedure TButoes.DelayAbreFormulario(TempoSeg:Single; Formulario:TForm);
var NumSeg   :single;
    Tempoini :TDateTime;
begin
     Tempoini := now;
     NumSeg:=Temposeg;
     repeat
          Application.ProcessMessages;
          Formulario.ShowModal;
     until Now > tempoini + NumSeg * (1/24/60/60);
     Formulario.Close;
end;

procedure TButoes.delayButoes(TempoSeg:single; ImagemUP,ImagemDown:Timage);
var NumSeg   :single;
    Tempoini :TDateTime;
begin
     Tempoini := now;
     NumSeg:=Temposeg;
     ImagemUP.Visible:=false;
     repeat
          Application.ProcessMessages;
           ImagemDown.Visible:=true;
     until Now > tempoini + NumSeg * (1/24/60/60);
     ImagemDown.Visible:=false;
     ImagemUP.Visible:=true;
end;

end.
