unit Freceber;

interface

uses 
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, jpeg, ExtCtrls;

type
  TFrReceive = class(TFrame)
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    Image1: TImage;
    Label1: TLabel;
    Label2: TLabel;
    EditNN: TEdit;
    Edit2: TEdit;
    GroupBox3: TGroupBox;
    cryptedMessage: TMemo;
    GroupBox4: TGroupBox;
    originalMessage: TMemo;
    buttonGenerate: TBitBtn;
    buttonClose: TBitBtn;
    procedure buttonCloseClick(Sender: TObject);
    procedure buttonGenerateClick(Sender: TObject);
    procedure cryptedMessageKeyPress(Sender: TObject; var Key: Char);
  private
    { Private declarations }
   function decriptBlock(block,D,N:int64) : int64;
  public
    { Public declarations }
    ascii     : widestring;
    procedure getCriptedBlocks(cryptedMessage :WideString);
    procedure getDecryptedBlocks();
    function getTextFromAscii():wideString;
  end;

var
  cryptedBlocks,decryptedBlocks : array of int64;

implementation
uses principal,MsgDlg;
{$R *.DFM}

procedure TFrReceive.buttonCloseClick(Sender: TObject);
begin
        Visible := false;
        main.GBdescript.Visible := false;
end;

procedure TFrReceive.buttonGenerateClick(Sender: TObject);
begin
   if (cryptedMessage.Text <> '') then
   begin
        main.log.Lines.Add('');
        main.log.Lines.Add('-> Recebendo Mensagens');
        main.log.Lines.Add('');
        main.log.Lines.Add('   -> Mensagem codificada');
        main.log.Lines.Add('      '+cryptedMessage.Text);
        main.log.Lines.Add('');

        self.getCriptedBlocks(cryptedMessage.Text);
        Self.getDecryptedBlocks();
        originalMessage.Text := Self.getTextFromAscii();

        main.log.Lines.Add('');
        main.log.Lines.Add('   -> Mensagem Original');
        main.log.Lines.Add('      '+originalMessage.Text);
   end
   else messagedialog('É necessário digitar uma mensagem criptografada!',mtError,[mbOk],0);
end;

procedure TFrReceive.getCriptedBlocks(cryptedMessage :WideString);
var quantBlocks,tam : integer;
    crypted     : widestring;
    aux,blocks  : string;
    i           : integer;
begin
      quantBlocks := 0;
      crypted := cryptedMessage;
      aux := '';
      tam := length(cryptedMessage);
      for i:=1 to tam+1 do
      begin
                if ((crypted[i] <> '-') and (i <> tam+1)) then
                        aux := aux + crypted[i]
                else begin
                        inc(quantBlocks);
                        blocks := blocks + 'Bloco('+aux+') ';
                        setLength(cryptedBlocks,quantBlocks);
                        cryptedBlocks[quantBlocks-1] := strToInt64(aux);
                        aux := '';
                end;
      end;
      main.log.Lines.Add('   -> Configurando blocos');
      main.log.Lines.Add('      '+blocks);

end;

function TFrReceive.decriptBlock(block,D,N : int64) : int64;
var modN: int64;
begin
        modN := main.rsaObject.algebra.getPotenciaModuloN(block,D,N);
        main.log.Lines.Add('      Ascii('+intToStr(block)+') = '+intToStr(block)+' ^ *** mod '+intToStr(N)+' = '+intToStr(modN));
        decriptBlock := modN;
end;

procedure TFrReceive.getDecryptedBlocks();
var decr : int64;
    i :integer;
begin
        main.log.Lines.Add('');
        main.log.Lines.Add('   -> Decodificando blocos');
        main.log.Lines.Add('      Ascii(Bloco) = x ^ D mod N');
        main.log.Lines.Add('');

        setLength(decryptedBlocks,length(cryptedBlocks));
        for i:=0 to length(cryptedBlocks)-1 do
        begin
                decr := self.decriptBlock(cryptedBlocks[i],main.rsaObject.D,main.rsaObject.N);
                decryptedBlocks[i] := decr;
                ascii := ascii + intToStr(decr);
        end;

        main.log.Lines.Add('');
        main.log.Lines.Add('   -> Mensagem em ASCII');
        main.log.Lines.Add('      '+ascii);
end;

function TFrReceive.getTextFromAscii():wideString;
var temp,sTemp :widestring;
begin
      while ascii <> '' do
        begin
          temp  := Copy (ascii, 1, 3);
          sTemp := sTemp + Char(StrToInt(temp)-100);
          Delete (ascii, 1, 3);
        end;
        getTextFromAscii := sTemp;
end;

procedure TFrReceive.cryptedMessageKeyPress(Sender: TObject;
  var Key: Char);
begin
        if not(key in ['0'..'9',#8,#13,'-']) then
        begin
            messagedialog('Este campo só aceita números e hífen!'+#13+'Você não digitou um numeral!',mtWarning,[mbOk],0);
            key := #0;
        end;
end;

end.
