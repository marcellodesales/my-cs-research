unit UEscolheNumero;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  jpeg, ExtCtrls, StdCtrls, Buttons,UmanipulaButao;

type
  TF_EscolhaNumero = class(TForm)
    Image1: TImage;
    Label1: TLabel;
    ComboFones: TComboBox;
    SpeedButtonOK: TSpeedButton;
    procedure FormCreate(Sender: TObject);
    procedure SpeedButtonOKClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_EscolhaNumero: TF_EscolhaNumero;
  PastaArquivo:String;
  FormularioAbre:TButoes;

implementation
uses USecretariaEletronica,Msgdlg,UAvisoAutomato;
{$R *.DFM}

procedure TF_EscolhaNumero.FormCreate(Sender: TObject);
var SearchFiles: TSearchRec;
    Arquivo,AuxArquivo:String;
begin
     ComboFones.Clear;
     PastaArquivo:=ExtractFilePath(Application.ExeName)+'Numeros\';
     If FileExists('*.ini') Then
     begin
          FindFirst(PastaArquivo+'\*.tmp', faAnyFile, SearchFiles);
          Arquivo:=SearchFiles.Name;
          repeat
                AuxArquivo:=Arquivo;
                ComboFones.Items.Add(Copy(Arquivo,1,length(Arquivo)-4));
                FindNext(SearchFiles);
                Arquivo:=SearchFiles.Name;
          until Arquivo=AuxArquivo;
     end;
end;

procedure TF_EscolhaNumero.SpeedButtonOKClick(Sender: TObject);
begin
     If ComboFones.Text<>'' then
     begin
          Hide;
          F_SecretariaEletronica.TelefoneSecretaria.Caption:=ComboFones.Text;
          Application.CreateForm(TF_AvisoAutomato,F_AvisoAutomato);
          F_AvisoAutomato.ShowModal;
          Close;
     end
     else
         MessageDialog('O número do telefone não pode ser nulo!! Clique no botão '
         +'fechar se deseja cancelar a operação.',mtInformation,[mbOK],0);
end;

end.
