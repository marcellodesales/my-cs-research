unit receber;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, jpeg, ExtCtrls,rsa;

type
  TFReceive = class(TForm)
    GroupBox1: TGroupBox;
    Image1: TImage;
    Label1: TLabel;
    Label2: TLabel;
    Edit1: TEdit;
    Edit2: TEdit;
    GroupBox2: TGroupBox;
    cryptedMessage: TMemo;
    GroupBox3: TGroupBox;
    originalMessage: TMemo;
    Button1: TButton;
    MasciiText: TMemo;
    Mblocos: TMemo;
    procedure Button1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
     function decriptBlock(block,D,N:int64) : int64;
  public
    procedure getCriptedBlocks(cryptedMessage :WideString);
    procedure getDecryptedBlocks();
    function getTextFromAscii():wideString;
  end;

var
  FReceive  : TFReceive;
  ascii     : widestring;
  rsaObject : TRsa;
  cryptedBlocks,decryptedBlocks : array of int64;

implementation

{$R *.DFM}

procedure TFReceive.FormCreate(Sender: TObject);
begin
        rsaObject := TRsa.Create();
        rsaObject.start();
        Edit1.Text := intToStr(rsaObject.N);

        cryptedMessage.Clear;
        originalMessage.Clear;
        MasciiText.Clear;
        Mblocos.Clear;
        ascii := '';
end;

procedure TFReceive.Button1Click(Sender: TObject);
begin
        self.getCriptedBlocks(cryptedMessage.Text);
        Self.getDecryptedBlocks();
        originalMessage.Text := Self.getTextFromAscii();
end;

procedure TFReceive.getCriptedBlocks(cryptedMessage :WideString);
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
                        blocks := blocks + 'C('+aux+') ';
                        setLength(cryptedBlocks,quantBlocks);
                        cryptedBlocks[quantBlocks-1] := strToInt64(aux);
                        aux := '';
                end;
      end;
      Mblocos.Text := blocks;
end;

function TFReceive.decriptBlock(block,D,N : int64) : int64;
begin
    decriptBlock := rsaObject.algebra.getPotenciaModuloN(block, D, N);
end;

procedure TFReceive.getDecryptedBlocks();
var decr : int64;
    i :integer;
begin
        setLength(decryptedBlocks,length(cryptedBlocks));
        for i:=0 to length(cryptedBlocks)-1 do
        begin
                decr := self.decriptBlock(cryptedBlocks[i],rsaObject.D,rsaObject.N);
                decryptedBlocks[i] := decr;
                ascii := ascii + intToStr(decr);
        end;
        MasciiText.Text := ascii;
end;

function TFReceive.getTextFromAscii():wideString;
var temp,sTemp :widestring;
begin
      while ascii <> '' do
        begin
          temp  := Copy (ascii, 1, 3);
          sTemp := sTemp + Char(StrToInt(temp)-100);
          Delete (ascii, 1, 3);
        end;
{        temp:='';
        for i:=1 to length(decryptedBlocks) do
                temp := temp + Char(decryptedBlocks[i]);
}
        getTextFromAscii := sTemp;
end;

procedure TFReceive.FormClose(Sender: TObject; var Action: TCloseAction);
begin
        self.Free;
end;

end.
