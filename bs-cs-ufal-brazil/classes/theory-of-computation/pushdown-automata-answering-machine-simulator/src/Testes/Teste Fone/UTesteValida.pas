unit UTesteValida;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
   valida, StdCtrls, ExtCtrls, Menus,OleCtrls, MMSystem, jpeg, Buttons;
type
  TF_TesteFONE = class(TForm)
    GroupBox1: TGroupBox;
    Cfone: TEdit;
    Label1: TLabel;
    Button1: TButton;
    GroupBox2: TGroupBox;
    Label3: TLabel;
    Label2: TLabel;
    Gexiste2: TImage;
    Gexiste1: TImage;
    Ginexiste2: TImage;
    Ginexiste1: TImage;
    Lresult1: TLabel;
    Lresult2: TLabel;
    Label4: TLabel;
    BitBtn1: TBitBtn;
    procedure Button1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure CfoneClick(Sender: TObject);
    procedure Novo1Click(Sender: TObject);
    procedure Sair2Click(Sender: TObject);
    procedure CfoneKeyPress(Sender: TObject; var Key: Char);
    Procedure SeFONEPREFIXOTRUE;
    Procedure SeFONEPREFIXOFalse;
    Procedure SeFONEFALSEPREFIXOTRUE;
    Procedure SeFONETRUEPREFIXOFALSE;
    procedure apagafiguras;
    procedure apagacampos;
    procedure beginall;
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_TesteFONE: TF_TesteFONE;
  Z:string;
implementation

{$R *.DFM}

Procedure TF_TesteFONE.SeFONEPREFIXOTRUE;
begin
    Gexiste1.Visible:=true;
    Lresult1.Caption:='O prefixo é válido';
    Gexiste2.Visible:=true;
    Lresult2.Caption:='O telefone é válido';
    sndPlaySound('tocando.wav',snd_ASync);
end;

Procedure TF_TesteFONE.SeFONEPREFIXOFalse;
begin
     Ginexiste1.Visible:=true;
     Lresult1.Caption:='O prefixo não é válido';
     Ginexiste2.Visible:=true;
     Lresult2.Caption:='O telefone não é válido: Existem '+IntToStr(length(Z))+' digitos';
end;

Procedure TF_TesteFONE.SeFONEFALSEPREFIXOTRUE;
begin
    Gexiste1.Visible:=true;
    Lresult1.Caption:='O prefixo é válido';
    Ginexiste2.Visible:=true;
    Lresult2.Caption:='O telefone não é válido: Existem '+IntToStr(length(Z))+' digitos';
end;

Procedure TF_TesteFONE.SeFONETRUEPREFIXOFALSE;
begin
     Ginexiste1.Visible:=true;
     Lresult1.Caption:='O prefixo não é válido';
     Gexiste2.Visible:=true;
     Lresult2.Caption:='O telefone é válido';
end;

procedure TF_TesteFONE.Button1Click(Sender: TObject);
Var A,B,C:char;
begin
     if Cfone.Text='' then
        showmessage('É necessário digitar o número de um telefone')
     else
   begin
     A:=Cfone.Text[1];
     B:=Cfone.Text[2];
     C:=Cfone.text[3];
     Z:=Cfone.text;
     if ((validaprefixo(A,B,C)=true) and (validafone(Z)=true)) then
        SeFONEPREFIXOTRUE
     else
         if ((validaprefixo(A,B,C)=false) and (validafone(Z)=false)) then
            SeFONEPREFIXOFalse
         else
              if ((validaprefixo(A,B,C)=true) and (validafone(Z)=false)) then
                 SeFONEFALSEPREFIXOTRUE
              else SeFONETRUEPREFIXOFALSE;

     Z:=F_TesteFONE.Cfone.text;
     if validafone(Z)=true then
     begin
        Gexiste2.Visible:=true;
        Lresult2.Caption:='O telefone é válido';
     end
     else
     begin
        Ginexiste2.Visible:=true;
        Lresult2.Caption:='O telefone não é válido: Existem '+IntToStr(length(Z))+' digitos';
     end;
   end;
end;

procedure TF_TesteFONE.apagacampos;
begin
     Cfone.Text:='';
end;

procedure TF_TesteFONE.apagafiguras;
begin
     Gexiste1.Visible:=false;
     Gexiste2.Visible:=false;
     Ginexiste1.Visible:=false;
     Ginexiste2.Visible:=false;
end;

procedure apagalabels;
begin
     F_TesteFONE.Lresult1.Caption:='';
     F_TesteFONE.Lresult2.Caption:='';
end;

procedure TF_TesteFONE.FormCreate(Sender: TObject);
begin
     apagacampos;
     apagafiguras;
     apagalabels;
end;

procedure TF_TesteFONE.beginall;
begin
     apagacampos;
     apagafiguras;
     apagalabels;
end;

procedure TF_TesteFONE.CfoneClick(Sender: TObject);
begin
     beginall;
end;

procedure TF_TesteFONE.Novo1Click(Sender: TObject);
begin
     beginall;
end;

procedure TF_TesteFONE.Sair2Click(Sender: TObject);
begin
     Close;
end;

procedure TF_TesteFONE.CfoneKeyPress(Sender: TObject; var Key: Char);
begin
     IF not (Key in ['0'..'9',#8]) then
        Key:=#0;
end;

end.
