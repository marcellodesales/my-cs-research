unit UAFD;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Antialiased, jpeg, ExtCtrls;

type
  TF_AFD = class(TForm)
    Image3: TImage;
    linha3: TAntialiasedLine;
    Image4: TImage;
    Image5: TImage;
    Image6: TImage;
    Image7: TImage;
    Image8: TImage;
    Image9: TImage;
    Image10: TImage;
    Image11: TImage;
    Image12: TImage;
    Image13: TImage;
    Image15: TImage;
    Image16: TImage;
    Image17: TImage;
    Image1: TImage;
    Image2: TImage;
    q0: TLabel;
    q1: TLabel;
    q2: TLabel;
    q3: TLabel;
    q4: TLabel;
    q5: TLabel;
    q6: TLabel;
    q10: TLabel;
    q9: TLabel;
    q8: TLabel;
    q7: TLabel;
    q11: TLabel;
    q12: TLabel;
    q13: TLabel;
    q14: TLabel;
    Linha2: TAntialiasedLine;
    Linha22: TAntialiasedLine;
    linha21: TAntialiasedLine;
    Linha35: TAntialiasedLine;
    Linha35N: TAntialiasedLine;
    Linha23: TAntialiasedLine;
    Linha24: TAntialiasedLine;
    Linha241: TAntialiasedLine;
    Linha23N: TAntialiasedLine;
    linha214: TAntialiasedLine;
    Linha22N: TAntialiasedLine;
    Linha34: TAntialiasedLine;
    Linha344: TAntialiasedLine;
    Linha33: TAntialiasedLine;
    Linha33N: TAntialiasedLine;
    linha32: TAntialiasedLine;
    linha32n: TAntialiasedLine;
    LinhaF1: TAntialiasedLine;
    LinhaF2: TAntialiasedLine;
    LinhaF3: TAntialiasedLine;
    LinhaF4: TAntialiasedLine;
    q15: TLabel;
    N2: TLabel;
    N21: TLabel;
    N214: TLabel;
    N3: TLabel;
    N32: TLabel;
    N326: TLabel;
    N322: TLabel;
    N324: TLabel;
    N325: TLabel;
    N327: TLabel;
    N35: TLabel;
    N34: TLabel;
    N33: TLabel;
    N359: TLabel;
    N358: TLabel;
    N355: TLabel;
    N354: TLabel;
    N344: TLabel;
    N334: TLabel;
    N338: TLabel;
    N336: TLabel;
    N24: TLabel;
    N23: TLabel;
    N22: TLabel;
    N241: TLabel;
    N231: TLabel;
    N235: TLabel;
    N237: TLabel;
    N221: TLabel;
    N223: TLabel;
    F12: TLabel;
    F14: TLabel;
    F15: TLabel;
    F16: TLabel;
    F17: TLabel;
    F18: TLabel;
    F19: TLabel;
    F13: TLabel;
    F11: TLabel;
    F10: TLabel;
    F20: TLabel;
    F21: TLabel;
    F22: TLabel;
    F23: TLabel;
    F24: TLabel;
    F25: TLabel;
    F26: TLabel;
    F27: TLabel;
    F28: TLabel;
    F29: TLabel;
    F30: TLabel;
    F31: TLabel;
    F32: TLabel;
    F33: TLabel;
    F34: TLabel;
    F35: TLabel;
    F36: TLabel;
    F37: TLabel;
    F38: TLabel;
    F39: TLabel;
    F40: TLabel;
    F41: TLabel;
    F42: TLabel;
    F43: TLabel;
    F44: TLabel;
    F45: TLabel;
    F46: TLabel;
    F47: TLabel;
    F48: TLabel;
    F49: TLabel;
    Memo1: TMemo;
    NomeAFD: TLabel;
    Image14: TImage;
    N320: TLabel;
    procedure FormCreate(Sender: TObject);
    Procedure Dacores(Num1,Num2,Num3:TLabel;Linha1,Linha2,Linha3:TAntialiasedLine);
    procedure EscrevePretoEstados;
    Procedure EscreveAzul;
    Procedure LinhasRedFinais;
    procedure AFDMudaCorNFinal(X,Y,Z,T:String);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);

  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_AFD: TF_AFD;

implementation
uses UDiscador;
{$R *.DFM}

Procedure TF_AFD.Dacores(Num1,Num2,Num3:TLabel;Linha1,Linha2,Linha3:TAntialiasedLine);
begin
     Num1.Font.Color:=clRed;
     Num2.Font.Color:=clRed;
     Num3.Font.Color:=clRed;
     Linha1.Color:=clRed;
     Linha1.ForeColor:=clRed;
     Linha2.Color:=clRed;
     Linha2.ForeColor:=clRed;
     Linha3.Color:=clRed;
     Linha3.ForeColor:=clRed;
end;

procedure TF_AFD.EscrevePretoEstados;
begin
     Q0.font.color:=clBlack;
     Q1.font.color:=clBlack;
     Q2.font.color:=clBlack;
     Q3.font.color:=clBlack;
     Q4.font.color:=clBlack;
     Q5.font.color:=clBlack;
     Q6.font.color:=clBlack;
     Q7.font.color:=clBlack;
     Q8.font.color:=clBlack;
     Q9.font.color:=clBlack;
     Q10.font.color:=clBlack;
     Q11.font.color:=clBlack;
     Q12.font.color:=clBlack;
     Q13.font.color:=clBlack;
     Q14.font.color:=clBlack;
     Q15.font.color:=clBlack;
end;

Procedure TF_AFD.EscreveAzul;
var i:integer;
begin
     For i:=0 to ComponentCount-1 do
     begin
         if (Components[i] is TAntialiasedLine) then
         begin
            (Components[i] as TAntialiasedLine).Color:=clBlue;
            (Components[i] as TAntialiasedLine).ForeColor:=clBlue;
         end;
         if (Components[i] is TLabel) then
            (Components[i] as TLabel).Font.Color:=clBlue;
     end;
end;

Procedure TF_AFD.LinhasRedFinais;
begin
     LinhaF1.Color:=clRed;
     LinhaF1.ForeColor:=clRed;
     LinhaF2.Color:=clRed;
     LinhaF2.ForeColor:=clRed;
     LinhaF3.Color:=clRed;
     LinhaF3.ForeColor:=clRed;
     LinhaF4.Color:=clRed;
     LinhaF4.ForeColor:=clRed;
end;

procedure TF_AFD.AFDMudaCorNFinal(X,Y,Z,T:String);
var i:integer;
begin
     for i:=0 to ComponentCount-1 do
     begin
          if (Components[i] is TLabel) then
          begin
             if (Components[i] as TLabel).Name=X then
                (Components[i] as TLabel).Font.Color:=clRed;
             if (Components[i] as TLabel).Name=Y then
                (Components[i] as TLabel).Font.Color:=clRed;
             if (Components[i] as TLabel).Name=Z then
                (Components[i] as TLabel).Font.Color:=clRed;
             if (Components[i] as TLabel).Name=T then
                (Components[i] as TLabel).Font.Color:=clRed;
         end;
     end;
end;

procedure TF_AFD.FormCreate(Sender: TObject);
var A,B,C:Char;
    NumF1,NumF2,NumF3,NumF4:String;
begin
     EscreveAzul;
     EscrevePretoEstados;
     A:=F_Discador.Cfone.text[1];
     B:=F_Discador.Cfone.text[2];
     C:=F_Discador.Cfone.text[3];
     Case A of
          '2':Case B of
                   '1':Case C of
                            '4':Dacores(N2,N21,N214,Linha2,Linha21,Linha214);
                   end;
                   '2':Case C of
                            '1':Dacores(N2,N22,N221,Linha2,Linha22,Linha22N);
                            '3':Dacores(N2,N22,N223,Linha2,Linha22,Linha22N);
                   end;
                   '3':Case C of
                            '1':Dacores(N2,N23,N231,Linha2,Linha23,Linha23N);
                            '5':Dacores(N2,N23,N235,Linha2,Linha23,Linha23N);
                            '7':Dacores(N2,N23,N237,Linha2,Linha23,Linha23N);
                   end;
                   '4':Case C of
                            '1':Dacores(N2,N24,N241,Linha2,Linha24,Linha241);
                   end;
              end;
          '3':Case B of
                   '2':Case C of
                            '0':Dacores(N3,N32,N320,Linha3,Linha32,Linha32N);
                            '2':Dacores(N3,N32,N322,Linha3,Linha32,Linha32N);
                            '4':Dacores(N3,N32,N324,Linha3,Linha32,Linha32N);
                            '5':Dacores(N3,N32,N325,Linha3,Linha32,Linha32N);
                            '6':Dacores(N3,N32,N326,Linha3,Linha32,Linha32N);
                            '7':Dacores(N3,N32,N327,Linha3,Linha32,Linha32N);
                   end;
                   '3':Case C of
                            '4':Dacores(N3,N33,N334,Linha3,Linha33,Linha33N);
                            '6':Dacores(N3,N33,N336,Linha3,Linha33,Linha33N);
                            '8':Dacores(N3,N33,N338,Linha3,Linha33,Linha33N);
                   end;
                   '4':Case C of
                            '4':Dacores(N3,N34,N344,Linha3,Linha34,Linha344);
                   end;
                   '5':Case C of
                            '4':Dacores(N3,N35,N354,Linha3,Linha35,Linha35N);
                            '5':Dacores(N3,N35,N355,Linha3,Linha35,Linha35N);
                            '8':Dacores(N3,N35,N358,Linha3,Linha35,Linha35N);
                            '9':Dacores(N3,N35,N359,Linha3,Linha35,Linha35N);
                   end;
          end;
     end;

     NumF1:='F1'+F_Discador.Cfone.text[4];
     NumF2:='F2'+F_Discador.Cfone.text[5];
     NumF3:='F3'+F_Discador.Cfone.text[6];
     NumF4:='F4'+F_Discador.Cfone.text[7];
     AFDMudaCorNFinal(NumF1,NumF2,NumF3,NumF4);
     LinhasRedFinais;
     NomeAFD.Font.Color:=clRed;
end;

procedure TF_AFD.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Close;
     Release;
end;

end.
