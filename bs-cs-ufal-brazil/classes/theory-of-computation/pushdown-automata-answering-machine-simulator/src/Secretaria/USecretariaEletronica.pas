unit USecretariaEletronica;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, jpeg, ExtCtrls,Buttons, Menus, UAutomatoPilha,UConfigSecretaria,
  UManipulaAutomato,MMSystem,UmanipulaButao,UTemp, Animate, GIFCtrl;

const
    MaxMensagem=10;
type
  TF_SecretariaEletronica = class(TForm)
    Image1: TImage;
    ButDesliON: TImage;
    ButDesliOFF: TImage;
    LuzOFF: TImage;
    LabTipo_da_mensagem: TLabel;
    TelefoneSecretaria: TLabel;
    Data: TLabel;
    UltimoAcesso: TLabel;
    DataULTAcess: TLabel;
    Tracohoriz: TShape;
    Hora: TLabel;
    Timer1: TTimer;
    Memo2: TMemo;
    MPilha: TMemo;
    Panel2: TPanel;
    Cfita: TEdit;
    Label1: TLabel;
    Label4: TLabel;
    EEstado: TEdit;
    ECharfita: TEdit;
    Label5: TLabel;
    Label6: TLabel;
    Shape1: TShape;
    ApagarDown: TImage;
    PlayDown: TImage;
    PlayUP: TImage;
    ApagarUP: TImage;
    EscolhaSec: TSpeedButton;
    Label2: TLabel;
    NumRec: TLabel;
    RxGIFAnimator2: TRxGIFAnimator;
    procedure ButDesliONMouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure FormCreate(Sender: TObject);
    procedure Arquivo1Click(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure inicializa_objetos;
    Procedure EscreveAutomatoEdits;
    procedure Inicializa_Se_Config_NAO_existe;
    procedure Inicializa_Se_Config_existe;
    procedure PlayUPClick(Sender: TObject);
    procedure ApagarUPClick(Sender: TObject);
    procedure EscolhaSecClick(Sender: TObject);
    procedure Abre_Configuracao_Automato_do_FONE;
    Procedure ApagaAutomatoVisual;
    Procedure DesempilhaINI;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
{    Procedure DesempilhaINI;}

  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_SecretariaEletronica: TF_SecretariaEletronica;
  Automato:TAutomato;      //UAutomatoPilha
  Config:TConfigSecretaria;  //UConfigSecretaria
  ManipulaAUT:TGraficosAutomato;  //UManipulaAutomato;
  Butao:TButoes; //UmanipulaButao
  Arquivo,ArquivoConfigSecret:String;
  FONE:String;
  NumRecadoINI,indFita:Integer;
  ExisteSecretaria:Boolean;

implementation
uses msgdlg, //Mensagensdlg em português
     UEscolheNumero;
{$R *.DFM}

procedure TF_SecretariaEletronica.FormCreate(Sender: TObject);
begin
     inicializa_objetos;
     if not fileexists(ArquivoConfigSecret) then
          Inicializa_Se_Config_NAO_existe //cria tb mensagens, se esta não existir
     else
          Inicializa_Se_Config_existe;
end;

procedure TF_SecretariaEletronica.Timer1Timer(Sender: TObject);
begin
     Hora.Caption:=TimeToStr(now);
end;

procedure TF_SecretariaEletronica.inicializa_objetos;
var i:integer;
begin
     ArquivoConfigSecret:=ExtractFilePath(Application.ExeName)+'Configuração Secretária.ini';
     for i:=0 to ComponentCount-1 do
         if (Components[i] is TEdit) then
            (Components[i] as TEdit).Clear;
     Memo2.Clear;
     ButdesliOFF.Visible:=false;
     LuzOFF.Visible:=false;
     Data.Caption:=FormatDateTime('dddd, dd "de" mmmm "de" yyyy',date);
     TelefoneSecretaria.Caption:='';
     NumRec.Caption:='';
     Butao:=TButoes.Create;
end;

procedure TF_SecretariaEletronica.Inicializa_Se_Config_NAO_existe;
var DataAtual:String;
begin
    Config:=TConfigSecretaria.Create;
    DataAtual:=Data.Caption; //inicializado no inicializa_objetos;
    DataULTAcess.Caption:=Data.Caption; //para mostrar pela primeira vez a data de ultimo acesso
    Config.DaValoresSistema(DataAtual,TimeToStr(now),TelefoneSecretaria.Caption);
    Config.AtualizaConfig(ArquivoConfigSecret);
    Config.Free;
    MessageDialog('Criado arquivo de configuração da Secretária Automato.',mtInformation,[mbOK],1);
end;

procedure TF_SecretariaEletronica.Inicializa_Se_Config_existe;
var DataINI,secret:string;
begin
    Config:=TConfigSecretaria.Create;
    Config.GetAllConfig(ArquivoConfigSecret);
    DataINI:=Config.DataUltimoAcesso;
    DataULTAcess.Caption:=DataINI;
    DataINI:=Data.Caption;
    Secret:=Config.UltimaSecretaria;
    Config.DaValoresSistema(DataINI,TimeToStr(now),Secret);
    Config.AtualizaConfig(ArquivoConfigSecret);
    Config.Free;
end;

procedure TF_SecretariaEletronica.Arquivo1Click(Sender: TObject);
begin
     Application.Terminate;
end;


procedure TF_SecretariaEletronica.PlayUPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,PlayUP,PlayDown);
     DesempilhaINI;
     ManipulaAUT.MemosEmpiOuDesem(1,MPilha,NumRecadoINI,Automato.SimboloPilha);
     ECharFita.Text:=IntToStr(Automato.IndFita);
     NumRec.Caption:=IntToStr(Automato.NumeroRecado(Arquivo));
end;

Procedure TF_SecretariaEletronica.DesempilhaINI;
var MensagemDesemp:String;
begin
     if Automato.SimboloPilha = 'S' then
     begin
        PlaySound(PChar(50+NumRecadoINI),HInstance, snd_ASync or snd_Memory or snd_Resource);
        MessageDialog('Impossível ler mais mensagens. A secretária está vazia.',mtError,[mbOK],0);
        exit;
     end;
     Automato.ExecutaAutomato(Arquivo,MensagemDesemp);
     NumRecadoINI:=Automato.NumeroRecado(Arquivo);
     EscreveAutomatoEdits;
     CFita.Text:=Automato.FitaControle;
     Memo2.Text:=MensagemDesemp;
     If Automato.SimboloPilha = 'S' then
     begin
          PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource);
          MessageDialog('Realizado o reconhecimento da fita. Símbolo S é desempilhado.'+#13+'q2,%,S --> q2,%',mtInformation ,[mbOK],0);
          deletefile(Arquivo);
          MPilha.Clear;
     end;
end;

Procedure TF_SecretariaEletronica.EscreveAutomatoEdits;
begin
     EEstado.Text:=Automato.Estado;
     ECharfita.Text:=IntToStr(Automato.IndFita);
end;

procedure TF_SecretariaEletronica.ApagarUPClick(Sender: TObject);
var ind:shortint;
begin
     Butao.delayButoes(0.3,ApagarUP,ApagarDown);
     If Automato.SimboloPilha = 'S' then
     begin
          PlaySound(PChar(50+NumRecadoINI),HInstance, snd_ASync or snd_Memory or snd_Resource);
          MessageDialog('Impossível ler mais mensagens. A secretária está vazia.',mtError,[mbOK],0);
          exit;
     end
     else
          for ind:=1 to Automato.NumeroRecado(Arquivo) do
          begin
              DesempilhaINI;
              ManipulaAUT.MemosEmpiOuDesem(1,MPilha,NumRecadoINI,Automato.SimboloPilha);
          end;
     EscreveAutomatoEdits;
     Memo2.Clear;
end;

procedure TF_SecretariaEletronica.ButDesliONMouseDown(Sender: TObject; Button: TMouseButton;
  Shift: TShiftState; X, Y: Integer);
var S:String;
begin
     ButdesliON.Visible:=false;
     ButdesliOFF.Visible:=true;
     LuzOFF.Visible:=true;
     if (messagedialog('Deseja realmente desligar e perder todas as informações?',mtconfirmation,[mbYes,mbNo],0)=mrYes) then
     begin
          S:=ExtractFilePath(Application.ExeName)+'Numeros\'+TelefoneSecretaria.Caption+'.ini';
          if FileExists(S) then
             DeleteFile(S);
          Close;
     end
     else
     begin
          ButdesliOFF.Visible:=false;
          ButdesliON.Visible:=true;
          LuzOFF.Visible:=false;
     end;
end;

procedure TF_SecretariaEletronica.Abre_Configuracao_Automato_do_FONE;
var temp:TTemp;
    aux:integer;
    Mensagem,ArquivoTemp:String;
    Erro:Boolean;
begin
     FONE:=TelefoneSecretaria.Caption;
     Arquivo:=ExtractFilePath(Application.ExeName)+'Numeros\'+FONE+'.ini';
     ArquivoTemp:=ExtractFilePath(Application.ExeName)+'Numeros\'+FONE+'.tmp';
     Automato:=TAutomato.Create;
     Automato.Inicializa;
     temp:=TTemp.Create;
     temp.GetFita(ArquivoTemp);
     insert('c',temp.FitaControle,(length(temp.FitaControle) div 2)+1);
     CFita.Text:=temp.FitaControle;
     Automato.FitaControle:=temp.FitaControle;
     Automato.AtualizaAutomatoINI(Arquivo);
     aux:=temp.NumeroRecado(ArquivoTemp);
     Automato.indFita:=1;
     while Automato.indFita<=aux do
     begin
          temp.Desempilha(ArquivoTemp,Mensagem);
          Automato.ExecutaAutomato(Arquivo,Mensagem);
     end;
     DeleteFile(ArquivoTemp);
     ApagaAutomatoVisual;
     Automato.ExecutaAutomato(Arquivo,Mensagem);

     EscreveAutomatoEdits;
     ManipulaAUT:=TGraficosAutomato.Create;
     NumRecadoINI:=Automato.NumeroRecado(Arquivo);
     NumRec.Caption:=intToStr(NumRecadoINI);
     ManipulaAUT.ColocaAutomatonoMemoPilha(MPilha,Automato.PilhaAutomato,NumRecadoINI);
end;

Procedure TF_SecretariaEletronica.ApagaAutomatoVisual;
var i:integer;
begin
    EEstado.Clear;
    ECharFita.Clear;
    For i:=10 downto 10-NumRecadoINI do
        MPilha.lines[i]:='';
end;

procedure TF_SecretariaEletronica.EscolhaSecClick(Sender: TObject);
var OldFone:string;
    Apagar:integer;
begin
     OldFone:=TelefoneSecretaria.Caption;
     If (OldFone <> '') and (Automato.SimboloPilha<>'S') then
     begin
          Apagar:=MessageDlg('Se você continuar, as mensagens que restam serão perdidas. Deseja continuar???',
          mtWarning,mbOKCancel,0);
          If Apagar = mrOk then
               deletefile(Arquivo)
          else exit;
     end;
     try
        Application.Createform(TF_EscolhaNumero,F_EscolhaNumero);
        F_EscolhaNumero.showmodal;
     finally
        F_EscolhaNumero.free;
     end;
     if OldFone<>TelefoneSecretaria.Caption then
     begin

          Abre_Configuracao_Automato_do_FONE;
          NumRecadoINI:=Automato.NumeroRecado(Arquivo);
          PlaySound(PChar(50+NumRecadoINI),HInstance, snd_ASync or snd_Memory or snd_Resource);
     end;
end;

procedure TF_SecretariaEletronica.FormClose(Sender: TObject;
  var Action: TCloseAction);
begin
     Butao.Free;
     Automato.Free;
end;

end.
