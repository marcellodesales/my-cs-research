unit principal;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  Menus, rsa, util, ToolWin, ComCtrls, StdCtrls,Fgerachave, Fenviar, Freceber,
  Buttons, RXCtrls, Animate, GIFCtrl, ImgList, ExtCtrls, RxGIF;

type
  Tmain = class(TForm)
    MainMenu1: TMainMenu;
    Arquivo1: TMenuItem;
    ChavesPblicas1: TMenuItem;
    Verificar1: TMenuItem;
    NmeroPrimos1: TMenuItem;
    RSA1: TMenuItem;
    Codificao1: TMenuItem;
    Decodificao1: TMenuItem;
    aModn1: TMenuItem;
    AlgoritmoEuclidianoExtendido1: TMenuItem;
    GroupBox1: TGroupBox;
    log: TRichEdit;
    StatusBar: TStatusBar;
    ToolBar1: TToolBar;
    FrGenerateKeys: TFrGenerateKeys;
    BitBtn1: TBitBtn;
    buttonPrinter: TBitBtn;
    Sair1: TMenuItem;
    FrSend: TFrSend;
    FrReceive: TFrReceive;
    GBcript: TGroupBox;
    RxGIFAnimator1: TRxGIFAnimator;
    RxLabel1: TRxLabel;
    GBdescript: TGroupBox;
    RxGIFAnimator2: TRxGIFAnimator;
    RxLabel2: TRxLabel;
    ToolButton1: TToolButton;
    ToolButton2: TToolButton;
    ImageList1: TImageList;
    ToolButton4: TToolButton;
    ToolButton6: TToolButton;
    ToolButton8: TToolButton;
    ToolButton7: TToolButton;
    Histricodechavesgravadas1: TMenuItem;
    ToolButton3: TToolButton;
    ToolButton5: TToolButton;
    Principal1: TMenuItem;
    SalvarLogdeCalculo1: TMenuItem;
    ImprimirLogdeCalculo1: TMenuItem;
    N4: TMenuItem;
    N5: TMenuItem;
    Autores1: TMenuItem;
    Referncias1: TMenuItem;
    Sobre1: TMenuItem;
    N6: TMenuItem;
    ToolButton9: TToolButton;
    ToolButton12: TToolButton;
    ToolButton13: TToolButton;
    ToolButton14: TToolButton;
    ToolButton15: TToolButton;
    ToolButton16: TToolButton;
    GBHowRSA: TGroupBox;
    ImageHowRSAWorks: TImage;
    SaveDialog: TSaveDialog;
    savableMemo: TMemo;
    ToolButton10: TToolButton;
    ToolButton11: TToolButton;
    ToolButton17: TToolButton;
    ToolButton18: TToolButton;
    procedure Codificao1Click(Sender: TObject);
    procedure Decodificao1Click(Sender: TObject);
    procedure ChavesPblicas1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure FrGenerateKeysBitBtn1Click(Sender: TObject);

    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure FrSend1buttonGenerateClick(Sender: TObject);
    procedure FrReceive1buttonGenerateClick(Sender: TObject);
    procedure FrReceivebuttonGenerateClick(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
    procedure buttonPrinterClick(Sender: TObject);
    procedure Sair1Click(Sender: TObject);
    procedure ToolButton2Click(Sender: TObject);
    procedure ToolButton4Click(Sender: TObject);
    procedure ToolButton5Click(Sender: TObject);
    procedure ToolButton6Click(Sender: TObject);
    procedure ToolButton14Click(Sender: TObject);
    procedure ToolButton7Click(Sender: TObject);
    procedure ToolButton3Click(Sender: TObject);
    procedure ToolButton13Click(Sender: TObject);
    procedure ToolButton12Click(Sender: TObject);
    procedure ToolButton9Click(Sender: TObject);
    procedure Autores1Click(Sender: TObject);
    procedure Referncias1Click(Sender: TObject);
    procedure Sobre1Click(Sender: TObject);
    procedure Histricodechavesgravadas1Click(Sender: TObject);
    procedure SalvarLogdeCalculo1Click(Sender: TObject);
    procedure ImprimirLogdeCalculo1Click(Sender: TObject);
    procedure NmeroPrimos1Click(Sender: TObject);
    procedure AlgoritmoEuclidianoExtendido1Click(Sender: TObject);
    procedure aModn1Click(Sender: TObject);
    procedure ImageHowRSAWorksClick(Sender: TObject);
    procedure ToolButton10Click(Sender: TObject);
    procedure ToolButton17Click(Sender: TObject);
    procedure ToolButton18Click(Sender: TObject);

  private
    procedure openFrame(frame: TFrame);

  public
    rsaObject: TRsa;
    util:TUtil;
    procedure generateKeysGiveKeysValue;
    procedure formSendClear;
    procedure formReceiveClear;
    procedure configStatusBar;
    procedure onDevelopment;
  end;

var
  main: Tmain;

implementation
uses MsgDlg, testePrimo,testeMdcExt,testeAritMod,comoRsaFunc,sobre;
//uses ,back;enviar, receber, gerachave, potenciamodulon;

{$R *.DFM}

procedure Tmain.FormCreate(Sender: TObject);
begin
        Application.HintColor := clSilver;

        util := TUtil.Create();

        rsaObject := TRsa.Create();
        rsaObject.start();

        configStatusBar();

        self.generateKeysGiveKeysValue();
        self.formSendClear();
        self.formReceiveClear();

        log.Clear();
end;

procedure Tmain.configStatusBar();
begin
        StatusBar.Panels[1].Text := 'N = '+intToStr(rsaObject.N);
        StatusBar.Panels[2].Text := 'E = '+intToStr(rsaObject.E);
end;

Procedure Tmain.openFrame(frame: TFrame);
Var
i : Integer;
begin
  for i := 0 to ComponentCount -1 do
     if Components[i] is TFrame then
       begin
            TFrame(Components[i]).Visible := false;
       end;
  frame.Visible := true;
end;

procedure Tmain.generateKeysGiveKeysValue;
begin
        FrGenerateKeys.EditN.Text := intTostr(rsaObject.N);
        FrGenerateKeys.EditE.Text := intToStr(rsaObject.E);
        FrGenerateKeys.EditD.Text := intToStr(rsaObject.D);
end;

procedure Tmain.formReceiveClear;
begin
        FrReceive.cryptedMessage.Clear;
        FrReceive.originalMessage.Clear;
        FrReceive.EditNN.Text := intTostr(rsaObject.N);
        FrReceive.ascii := '';
end;

procedure Tmain.formSendClear;
begin
        FrSend.EditN.clear();
        FrSend.EditE.clear();
        FrSend.inputMessage.Clear();
        FrSend.finalMessage.Clear();
end;

procedure Tmain.Codificao1Click(Sender: TObject);
begin
        self.formSendClear();
        self.openFrame(FrSend);
        GBdescript.Visible := false;
        GBcript.Visible := true;
end;

procedure Tmain.Decodificao1Click(Sender: TObject);
begin
        self.formReceiveClear();
        self.openFrame(FrReceive);
        GBcript.Visible := false;
        GBdescript.Visible := true;
end;

procedure Tmain.ChavesPblicas1Click(Sender: TObject);
begin
        GBcript.Visible := false;
        GBdescript.Visible := false;
        self.openFrame(FrGenerateKeys);
end;

procedure Tmain.ToolButton6Click(Sender: TObject);
begin
        ChavesPblicas1Click(Sender);
end;

procedure Tmain.FrGenerateKeysBitBtn1Click(Sender: TObject);
begin
        FrGenerateKeys.BitBtn1Click(Sender);
end;

procedure Tmain.FrSend1buttonGenerateClick(Sender: TObject);
begin
        FrSend.buttonGenerateClick(Sender);
end;

procedure Tmain.FrReceive1buttonGenerateClick(Sender: TObject);
begin
        FrReceive.buttonGenerateClick(Sender);
end;

procedure Tmain.FrReceivebuttonGenerateClick(Sender: TObject);
begin
        FrReceive.buttonGenerateClick(Sender);
end;

procedure Tmain.FormClose(Sender: TObject; var Action: TCloseAction);
begin
        rsaObject.close();
end;

procedure Tmain.BitBtn1Click(Sender: TObject);
begin
        log.Clear();
end;

procedure Tmain.buttonPrinterClick(Sender: TObject);
begin
     if log.Text <> '' then
        if util.printerIsOnLine() then
                log.Print('Log de Cálculo - Criptografia RSA')
        else messagedialog('Verifique se a impressora está pronta!'+#13+'    Impressão cancelada.',mtError,[mbOk],0)
     else messagedialog('Não há nada a ser impresso.'+#13+'   Impressão cancelada.',mtError,[mbOk],0);
end;

procedure Tmain.ToolButton14Click(Sender: TObject);
begin
        buttonPrinterClick(Sender);
end;

procedure Tmain.ImprimirLogdeCalculo1Click(Sender: TObject);
begin
        buttonPrinterClick(Sender);
end;

procedure Tmain.Sair1Click(Sender: TObject);
begin
     if (messagedialog('Deseja realmente Fechar?',mtconfirmation,[mbYes,mbNo],0)=mrYes) then
        Application.Terminate;
end;

procedure Tmain.ToolButton2Click(Sender: TObject);
begin
        Codificao1Click(Sender);
end;

procedure Tmain.ToolButton4Click(Sender: TObject);
begin
        Decodificao1Click(Sender);
end;

procedure Tmain.ToolButton5Click(Sender: TObject);
begin
        Sair1Click(Sender);
end;

procedure Tmain.onDevelopment;
begin
        messagedialog('Ainda em fase de desenvolvimento!'+#13+' Alex Amorim - alexmuller@bol.com.br'+#13+' Marcello Junior - masj@tci.ufal.br'+#13+' Marcus Túlio - marcustc@yahoo.com',mtInformation,[mbOk],0);
end;

procedure Tmain.ToolButton7Click(Sender: TObject);
begin
        self.onDevelopment();
end;

procedure Tmain.ToolButton3Click(Sender: TObject);
begin
        if log.text <> '' then
        begin
                savableMemo.Text := log.Text;
                util.memoFileSaveAsClick(Sender,saveDialog,savableMemo);
        end
        else messagedialog('O Log de Calculo está vazio. Impossível salvar!',mtError,[mbOk],0);
end;

procedure Tmain.ToolButton13Click(Sender: TObject);
begin
        Autores1Click(Sender);
end;

procedure Tmain.ToolButton12Click(Sender: TObject);
begin
        self.onDevelopment();
end;

procedure Tmain.ToolButton9Click(Sender: TObject);
begin
        self.onDevelopment();
end;

procedure Tmain.Autores1Click(Sender: TObject);
begin
        Application.CreateForm(TFAbout, FAbout);
end;

procedure Tmain.Referncias1Click(Sender: TObject);
begin
        self.onDevelopment();
end;

procedure Tmain.Sobre1Click(Sender: TObject);
begin
        self.onDevelopment();
end;

procedure Tmain.Histricodechavesgravadas1Click(Sender: TObject);
begin
        self.onDevelopment();
end;

procedure Tmain.SalvarLogdeCalculo1Click(Sender: TObject);
begin
        ToolButton3Click(Sender);
end;

procedure Tmain.NmeroPrimos1Click(Sender: TObject);
begin
      Application.CreateForm(TFTestPrimeNumber, FTestPrimeNumber);
end;

procedure Tmain.AlgoritmoEuclidianoExtendido1Click(Sender: TObject);
begin
        Application.CreateForm(TFTesteMdcExt, FTesteMdcExt);
end;

procedure Tmain.aModn1Click(Sender: TObject);
begin
        Application.CreateForm(TFTesteAritMod, FTesteAritMod);
end;

procedure Tmain.ImageHowRSAWorksClick(Sender: TObject);
begin
        Application.CreateForm(TFHowRSAWorks, FHowRSAWorks);
end;

procedure Tmain.ToolButton10Click(Sender: TObject);
begin
        NmeroPrimos1Click(Sender);
end;

procedure Tmain.ToolButton17Click(Sender: TObject);
begin
        aModn1Click(Sender);
end;

procedure Tmain.ToolButton18Click(Sender: TObject);
begin
        Application.CreateForm(TFTesteMdcExt, FTesteMdcExt);
end;

end.
