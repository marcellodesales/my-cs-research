unit principal;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Buttons, ExtCtrls, StdCtrls, jpeg, Wordcap, Menus, OleCtrls;

type
  TFormPrinc = class(TForm)
    MoveElevador: TTimer;
    Elevsimula: TImage;
    LeftDoor: TShape;
    RightDoor: TShape;
    TimerPorta: TTimer;
    Image1: TImage;
    Image3: TImage;
    Image4: TImage;
    Image5: TImage;
    Image6: TImage;
    Image7: TImage;
    Image9: TImage;
    Image10: TImage;
    Image11: TImage;
    q4normal: TImage;
    trans1q3q4: TImage;
    q3normal: TImage;
    trans1q2q3: TImage;
    q2normal: TImage;
    trans1q1q2: TImage;
    inicioq2: TImage;
    inicioq5: TImage;
    trans0q2q1: TImage;
    trans0q3q2: TImage;
    trans0q4q3: TImage;
    trans0q5q4: TImage;
    q5normal: TImage;
    trans1q4q5: TImage;
    inicioq3: TImage;
    inicioq4: TImage;
    Labelq2: TLabel;
    Labelq3: TLabel;
    Labelq4: TLabel;
    Labelq5: TLabel;
    final2: TImage;
    final3: TImage;
    final4: TImage;
    final5: TImage;
    GroupBox1: TGroupBox;
    Image2: TImage;
    Button1: TSpeedButton;
    Button3: TSpeedButton;
    ButtonT: TSpeedButton;
    Button2: TSpeedButton;
    Button5: TSpeedButton;
    Button4: TSpeedButton;
    LabelAndar: TLabel;
    Mostrador: TEdit;
    ButtonSimula: TButton;
    Imagdestinos: TImage;
    Labelq1: TLabel;
    q1normal: TImage;
    Q0normal: TImage;
    final0: TImage;
    final1: TImage;
    trans1q0q1: TImage;
    inicioq0: TImage;
    inicioq1: TImage;
    trans0q1q0: TImage;
    Labelq0: TLabel;
    GroupBox2: TGroupBox;
    imagfita: TImage;
    ExibeFita: TEdit;
    MSOfficeCaption1: TMSOfficeCaption;
    automatoaparece: TImage;
    procedure Button1Click(Sender: TObject);
    procedure ButtonTClick(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button4Click(Sender: TObject);
    procedure Button5Click(Sender: TObject);
    procedure TimerTimer(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure ButtonSimulaClick(Sender: TObject);
    procedure MoveElevadorTimer(Sender: TObject);
    procedure AtualizaMostrador;
    procedure MovimentaElevador;
    procedure MostraFita;
    procedure AbrePorta;
    procedure FechaPorta;
    procedure AtivaBotoes;
    procedure ApagaAutomato;
    procedure ExibeLabelEstado(estado:integer);
    procedure ExibeEstadoFinal(estado:integer);
    procedure ExibeEstado(estado:integer);
    procedure ExibeSeta(Andar:integer; Passo:char);
    procedure ExibeEstadoInicial(estado:integer);
    procedure MostraAutomato;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

  StackEntry=char;
  Stack = ^NodeStack;
  NodeStack = record
            value:StackEntry;
            next:Stack;
  End;

const posicoes:array[0..5] of Integer = (451,387,323,259,195,131);

var
  FormPrinc: TFormPrinc;
  Andares:Array[1..6] of Integer;
  QuantAndar:Integer;
  q0:Integer;
  Fita:Stack;
  val:integer;
  Passo:integer;
  TopAtual,TopInic,TopFim:Integer;
  LargPorta:Integer;
  AbrirPorta:Boolean;
implementation

{$R *.DFM}

procedure pop(var X:StackEntry; var S:Stack);
var aux:Stack;
begin
     If S<>nil Then
     Begin
          aux:=S^.next;
          x:=S^.value;
          dispose(S);
          S:=aux;
     End;
end;

procedure push(x:StackEntry; var S:Stack);
var aux:Stack;
begin
     new(aux);
     aux^.value:=x;
     aux^.next:=S;
     S:=aux;
end;

procedure TFormPrinc.AtivaBotoes;
begin
     ButtonT.Down:=False;
     Button1.Down:=False;
     Button2.Down:=False;
     Button3.Down:=False;
     Button4.Down:=False;
     Button5.Down:=False;
end;

procedure RetiraAndar(Andar:Integer);
Var Ind1,Ind2:Integer;
Begin
     Ind1:=1;
     While Andares[Ind1]<>Andar Do
     Inc(Ind1);
     For Ind2:=Ind1 To QuantAndar-1 Do
         Andares[Ind2]:=Andares[Ind2+1];
     Dec(QuantAndar);
End;

procedure AdicionaAndar(Andar:Integer);
Begin
     Inc(QuantAndar);
     Andares[QuantAndar]:=Andar;
End;

procedure TFormPrinc.AtualizaMostrador;
var Ind:Integer;
    StrAndares:String;
Begin
     If Mostrador.Text<>'' then
        Mostrador.Clear;

     For Ind:=1 To QuantAndar Do
          StrAndares:=StrAndares+IntToStr(Andares[Ind]);
     If Pos('0',StrAndares)<>0 Then
     Begin
        Insert('T',StrAndares,Pos('0',StrAndares));
        Delete(StrAndares,Pos('0',StrAndares),1);
     End;
     Mostrador.Text:=StrAndares;
End;

procedure AndarParaFita;
Var Ind,Aux:Integer;
    NumAlfa:Integer;
    Alfa:Char;
Begin
     For Ind:=QuantAndar-1 Downto 1 Do
     Begin
          NumAlfa:=Andares[Ind+1]-Andares[Ind];
          If NumAlfa<0 Then Alfa:='0'
          Else Alfa:='1';
          For Aux:=1 To Abs(NumAlfa) Do Push(Alfa,fita);
            Push('P',Fita);
     End;
     NumAlfa:=Andares[1]-q0;
     If NumAlfa<0 Then Alfa:='0'
          Else Alfa:='1';
     For Aux:=1 To Abs(NumAlfa) Do Push(Alfa,fita);
End;

procedure TFormPrinc.Button1Click(Sender: TObject);
begin
     If Button1.Down Then
        AdicionaAndar(1)
     Else RetiraAndar(1);
     AtualizaMostrador;
end;

procedure TFormPrinc.Button2Click(Sender: TObject);
begin
     If Button2.Down Then
          AdicionaAndar(2)
     Else RetiraAndar(2);
     AtualizaMostrador;
end;

procedure TFormPrinc.Button3Click(Sender: TObject);
begin
     If Button3.Down Then
          AdicionaAndar(3)
     Else RetiraAndar(3);
     AtualizaMostrador;
end;

procedure TFormPrinc.Button4Click(Sender: TObject);
begin
     If Button4.Down Then
          AdicionaAndar(4)
     Else RetiraAndar(4);
     AtualizaMostrador;
end;

procedure TFormPrinc.Button5Click(Sender: TObject);
begin
     If Button5.Down Then
          AdicionaAndar(5)
     Else RetiraAndar(5);
     AtualizaMostrador;
end;

procedure TFormPrinc.ButtonTClick(Sender: TObject);
begin
     If ButtonT.Down Then
          AdicionaAndar(0)
     Else RetiraAndar(0);
     AtualizaMostrador;
end;

procedure DoorSimula(acao:byte);
begin
     If acao=1 Then val:=-5
     else val:=5;
     //FormPrinc.Timer.Enabled:=True;
end;

procedure TFormPrinc.TimerTimer(Sender: TObject);
begin
     LeftDoor.Width:=LeftDoor.Width+val;
     RightDoor.Width:=RightDoor.Width+val;
     RightDoor.Left:=RightDoor.Left+val*(-1);
     If (LeftDoor.Width=5) or (LeftDoor.Width=90) then
   //     Timer.Enabled:=False;
end;

procedure TFormPrinc.FormCreate(Sender: TObject);
begin
     q0:=0;
     Fita:=nil;
     TopAtual:=451;
     LargPorta:=22;
end;

procedure TFormPrinc.MostraFita;
var x:char;
    AuxFita:Stack;
begin
     ExibeFita.Clear;
     AuxFita:=nil;
     While Fita<>nil do
     begin
          pop(x,Fita);
          push(x,AuxFita);
          If x<>'P' then
          ExibeFita.Text:=ExibeFita.Text+x;
     end;
     While AuxFita<>nil Do
     begin
          pop(x,AuxFita);
          push(x,Fita);
     end;
end;

procedure TFormPrinc.AbrePorta;
var Largura:integer;
begin
     For Largura:=21 Downto 4 Do
        Begin
             LeftDoor.Width:=Largura;
             RightDoor.Width:=Largura;
             RightDoor.Left:=RightDoor.Left+1;
        End;
end;

procedure TFormPrinc.FechaPorta;
var Largura:integer;
begin
     For Largura:=5 To 22 Do
       Begin
            LeftDoor.Width:=LargPorta;
            RightDoor.Left:=RightDoor.Left-1;
            RightDoor.Width:=LargPorta;
       End;
end;

procedure TFormPrinc.MovimentaElevador;
var passoStr:char;
begin
     If Fita=nil Then
       Begin
            ButtonSimula.Enabled:=True;
            RetiraAndar(q0);
            AtualizaMostrador;
            AtivaBotoes;
            AbrePorta;
            Exit;
       End;
     Pop(passoStr,Fita);

     If PassoStr='P' Then
       Begin
          RetiraAndar(q0);
          AtualizaMostrador;
          AbrePorta;
          FechaPorta;
          MoveElevador.Enabled:=True;
       End
     Else
        Begin
           Passo:=StrToInt(PassoStr);
           If Passo=1 Then Inc(q0)
           Else Dec(q0);
           TopFim:=posicoes[q0];
           MoveElevador.Enabled:=True;
        End;
end;

procedure TFormPrinc.MoveElevadorTimer(Sender: TObject);
begin
     If (TopAtual=Posicoes[q0]) Then
       Begin
            If q0=0 then LabelAndar.Caption:='T'
            Else LabelAndar.Caption:=IntToStr(q0);
            beep;
       End;
     If TopAtual=TopFim Then
        Begin
             MoveElevador.Enabled:=False;
             MovimentaElevador;
        End
     Else
        Begin
             If TopFim>TopAtual Then Inc(TopAtual)
             Else Dec(TopAtual);
             Elevsimula.Top:=TopAtual;
             RightDoor.Top:=TopAtual;
             LeftDoor.Top:=TopAtual;
        End;
End;

procedure TFormPrinc.ApagaAutomato;
begin
      inicioq0.Visible:=False;
      inicioq1.Visible:=False;
      inicioq2.Visible:=False;
      inicioq3.Visible:=False;
      inicioq4.Visible:=False;
      inicioq5.Visible:=False;

      trans1q0q1.Visible:=False;
      trans1q1q2.Visible:=False;
      trans0q1q0.Visible:=False;
      trans1q2q3.Visible:=False;
      trans0q2q1.Visible:=False;
      trans1q3q4.Visible:=False;
      trans0q3q2.Visible:=False;
      trans1q4q5.Visible:=False;
      trans0q4q3.Visible:=False;
      trans0q5q4.Visible:=False;

      Labelq0.Visible:=False;
      Labelq1.Visible:=False;
      Labelq2.Visible:=False;
      Labelq3.Visible:=False;
      Labelq4.Visible:=False;
      Labelq5.Visible:=False;

      final0.Visible:=False;
      final1.Visible:=False;
      final2.Visible:=False;
      final3.Visible:=False;
      final4.Visible:=False;
      final5.Visible:=False;

      q0normal.Visible:=False;
      q1normal.Visible:=False;
      q2normal.Visible:=False;
      q3normal.Visible:=False;
      q4normal.Visible:=False;
      q5normal.Visible:=False;
end;

procedure TFormPrinc.ExibeLabelEstado(estado:integer);
begin
     case estado of
        0:Labelq0.Visible:=True;
        1:Labelq1.Visible:=True;
        2:Labelq2.Visible:=True;
        3:Labelq3.Visible:=True;
        4:Labelq4.Visible:=True;
        5:Labelq5.Visible:=True;
     end;
end;

procedure TFormPrinc.ExibeEstadoInicial(estado:integer);
begin
     case estado of
        0:inicioq0.Visible:=True;
        1:inicioq1.Visible:=True;
        2:inicioq2.Visible:=True;
        3:inicioq3.Visible:=True;
        4:inicioq4.Visible:=True;
        5:inicioq5.Visible:=True;
     end;
end;

procedure TFormPrinc.ExibeEstadoFinal(estado:integer);
begin
     case estado of
        0:final0.Visible:=True;
        1:final1.Visible:=True;
        2:final2.Visible:=True;
        3:final3.Visible:=True;
        4:final4.Visible:=True;
        5:final5.Visible:=True;
     end;
     ExibeLabelEstado(estado);
end;

procedure TFormPrinc.ExibeEstado(estado:integer);
begin
     case estado of
        0:q0normal.Visible:=True;
        1:q1normal.Visible:=True;
        2:q2normal.Visible:=True;
        3:q3normal.Visible:=True;
        4:q4normal.Visible:=True;
        5:q5normal.Visible:=True;
     end;
     ExibeLabelEstado(estado);
end;

procedure TFormPrinc.ExibeSeta(Andar:integer; Passo:char);
begin
     If Andar=0 Then
       If Passo='1' Then trans1q0q1.Visible:=True;
     If Andar=1 Then
       If Passo='1' Then trans1q1q2.Visible:=True
       Else trans0q1q0.Visible:=True;
     If Andar=2 Then
        If Passo='1' Then trans1q2q3.Visible:=True
        Else trans0q2q1.Visible:=True;
     If Andar=3 Then
        If Passo='1' Then trans1q3q4.Visible:=True
        Else trans0q3q2.Visible:=True;
     If Andar=4 Then
        If Passo='1' Then trans1q4q5.Visible:=True
        Else trans0q4q3.Visible:=True;
     If Andar=5 Then trans0q5q4.Visible:=True;
end;

procedure TFormPrinc.MostraAutomato;
var  ind,auxq0,Final,Atual:Integer;
     AuxFita:String;
begin
     ApagaAutomato;
     AuxFita:=ExibeFita.Text;
     Final:=Andares[QuantAndar];
     auxq0:=q0;
     ExibeEstadoInicial(auxq0);
     ExibeEstado(auxq0);
     Atual:=q0;
     For ind:=1 to Length(AuxFita) Do
     Begin
         ExibeEstado(Atual);
         ExibeSeta(Atual,AuxFita[ind]);
         If AuxFita[ind]='1' Then inc(Atual)
         else dec(Atual);
     End;
     ExibeEstadoFinal(Final);
end;

procedure TFormPrinc.ButtonSimulaClick(Sender: TObject);
begin
     automatoaparece.Visible:=false;
     FechaPorta;
     ButtonSimula.Enabled:=False;
     AndarParaFita;
     MostraFita;
     MostraAutomato;
     MovimentaElevador;
end;

end.



