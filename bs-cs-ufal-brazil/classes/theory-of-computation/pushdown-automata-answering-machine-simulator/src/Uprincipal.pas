unit Uprincipal;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Menus, Buttons, ExtCtrls, jpeg,MMSystem, Animate, GIFCtrl, UConfigINI,
  Wordcap;

type
  TF_Princ = class(TForm)
    Panel1: TPanel;
    SpeedButton2: TSpeedButton;
    SpeedButton3: TSpeedButton;
    SpeedButton4: TSpeedButton;
    SpeedButton5: TSpeedButton;
    SpeedButton6: TSpeedButton;
    SpeedButton7: TSpeedButton;
    SpeedButton8: TSpeedButton;
    Image1: TImage;
    SpeedButton1: TSpeedButton;
    MainMenu1: TMainMenu;
    Arquivo1: TMenuItem;
    Fechar1: TMenuItem;
    Testes1: TMenuItem;
    AFDparavalidarTelefones1: TMenuItem;
    AutmatodePilhaparaempilharmensagens1: TMenuItem;
    Simulao1: TMenuItem;
    DiscadordeTelefone1: TMenuItem;
    SecretriaEletrnica1: TMenuItem;
    Configurao1: TMenuItem;
    ProgramaPrincipal1: TMenuItem;
    Ajuda1: TMenuItem;
    Sobre1: TMenuItem;
    N2: TMenuItem;
    TeoriadaComputaoHomePage1: TMenuItem;
    N1: TMenuItem;
    InformaesdiversassobreAutomatos1: TMenuItem;
    SomMudo: TRxGIFAnimator;
    MSOfficeCaption1: TMSOfficeCaption;
    procedure SpeedButton6Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure Fechar1Click(Sender: TObject);
    procedure DiscadordeTelefone1Click(Sender: TObject);
    procedure SecretriaEletrnica1Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure SpeedButton7Click(Sender: TObject);
    procedure AFDparavalidarTelefones1Click(Sender: TObject);
    procedure Sobre1Click(Sender: TObject);
    procedure TeoriadaComputaoHomePage1Click(Sender: TObject);
    procedure InformaesdiversassobreAutomatos1Click(Sender: TObject);
    procedure ProgramaPrincipal1Click(Sender: TObject);
    procedure AutmatodePilhaparaempilharmensagens1Click(Sender: TObject);
    procedure SpeedButton8Click(Sender: TObject);
    procedure SpeedButton5Click(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_Princ: TF_Princ;
  ConfigINI:TConfigINI;
  Mudo:Boolean;
implementation
uses UConfig, UDiscador, USecretariaEletronica,MSGDLG,
     UTesteValida;
{$R *.DFM}

procedure TF_Princ.FormCreate(Sender: TObject);
begin
     ConfigINI:=TConfigINI.Create;
     ConfigINI.GetAll(ExtractFilePath(Application.Exename)+'configuracao.ini');
     Mudo:=ConfigINI.SomMudo;
     if Mudo then
     begin
        SomMudo.Animate:=False;
        SomMudo.Hint:='Aplicação sem som.';
     end
     else
     begin
         SomMudo.Animate:=True;
         SomMudo.Hint:='Aplicação rodando com som.';
     end;
    PlaySound(PChar(100),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Princ.SpeedButton6Click(Sender: TObject);
begin
     ProgramaPrincipal1Click(F_Princ);
end;

procedure TF_Princ.SpeedButton1Click(Sender: TObject);
begin
     DiscadordeTelefone1Click(F_Princ);
end;

procedure TF_Princ.SpeedButton3Click(Sender: TObject);
begin
     SecretriaEletrnica1Click(F_Princ);
end;

procedure TF_Princ.Fechar1Click(Sender: TObject);
begin
     if (messagedialog('Deseja realmente desligar a Secretária Eletrônica?',mtconfirmation,[mbYes,mbNo],0)=mrYes) then
        Application.Terminate
     else
         exit;
end;

procedure TF_Princ.DiscadordeTelefone1Click(Sender: TObject);
begin
     PlaySound(PChar(200),HInstance, snd_ASync or snd_Memory or snd_Resource);
     Application.CreateForm(TF_Discador,F_Discador);
     F_Discador.ShowModal;
end;

procedure TF_Princ.SecretriaEletrnica1Click(Sender: TObject);
begin
     try
        PlaySound(PChar(200),HInstance, snd_ASync or snd_Memory or snd_Resource);
        Application.Createform(TF_SecretariaEletronica,F_SecretariaEletronica);
        F_SecretariaEletronica.showmodal;
     finally
        F_SecretariaEletronica.free;
     end;
end;

procedure TF_Princ.SpeedButton4Click(Sender: TObject);
begin
     Sobre1Click(F_Princ);
{     Try
        PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource);
        Application.CreateForm(TF_TesteAutPilha, F_TesteAutPilha);
        F_TesteAutPilha.ShowModal;
     Finally
         F_TesteAutPilha.free;
     end;}
end;

procedure TF_Princ.SpeedButton7Click(Sender: TObject);
begin
     AFDparavalidarTelefones1Click(F_Princ);
end;

procedure TF_Princ.AFDparavalidarTelefones1Click(Sender: TObject);
begin
     Try
        PlaySound(PChar(200),HInstance, snd_ASync or snd_Memory or snd_Resource);
        Application.CreateForm(TF_TesteFONE, F_TesteFONE);
        F_TesteFONE.ShowModal;
     Finally
         F_TesteFONE.free;
     end;
end;

procedure TF_Princ.Sobre1Click(Sender: TObject);
begin
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource);
     messagedialog('Ainda em fase de desenvolvimento!'+#13+'Marcello Alves de Sales Junior.'+#13+'Klebson dos Snatos Silva.'+#13+'UFAL - Brasil'+#13+'18 de Novembro de 1999',mtInformation,[mbOK],0);
end;

procedure TF_Princ.TeoriadaComputaoHomePage1Click(Sender: TObject);
begin
     Sobre1Click(F_Princ);
end;

procedure TF_Princ.InformaesdiversassobreAutomatos1Click(Sender: TObject);
begin
     Sobre1Click(F_Princ);
end;

procedure TF_Princ.ProgramaPrincipal1Click(Sender: TObject);
begin
     PlaySound(PChar(200),HInstance, snd_ASync or snd_Memory or snd_Resource);
     Application.CreateForm(TF_Config,F_Config);
     F_Config.ShowModal;
end;

procedure TF_Princ.AutmatodePilhaparaempilharmensagens1Click(
  Sender: TObject);
begin
     Sobre1Click(F_Princ);
end;

procedure TF_Princ.SpeedButton8Click(Sender: TObject);
begin
     Sobre1Click(F_Princ);
end;

procedure TF_Princ.SpeedButton5Click(Sender: TObject);
begin
     Sobre1Click(F_Princ);
end;

procedure TF_Princ.SpeedButton2Click(Sender: TObject);
begin
     Sobre1Click(F_Princ);
end;

end.
