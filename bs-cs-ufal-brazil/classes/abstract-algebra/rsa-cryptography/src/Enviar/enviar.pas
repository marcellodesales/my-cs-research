unit enviar;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls,rsa,jpeg, ExtCtrls;

type
  TFSend = class(TForm)
    cryptedMessage: TMemo;
    Button1: TButton;
    Mblocos: TMemo;
    GroupBox1: TGroupBox;
    Image1: TImage;
    Edit1: TEdit;
    Edit2: TEdit;
    Label1: TLabel;
    Label2: TLabel;
    GroupBox2: TGroupBox;
    inputMessage: TMemo;
    GroupBox3: TGroupBox;
    finalMessage: TMemo;
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure Button1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    function getCriptedBlock(x:int64) : int64;
  private
    { Private declarations }
  public
    { Public declarations }
    function getAsciiForm(wideStr :wideString):wideString;
    procedure getAsciiBlocks();
    procedure getCriptedBlocks();
  end;

var
  FSend: TFSend;
  codedString : wideString;
  codedBlocks : array of int64;
  rsaObject   : TRsa;
  N,E         : int64;
implementation

{$R *.DFM}

procedure TFSend.FormCreate(Sender: TObject);
begin
        Edit1.Clear;
        Edit2.Clear;
        inputMessage.Clear;
        cryptedMessage.Clear;
        Mblocos.Clear;
        finalMessage.Clear;
        rsaObject := TRsa.Create();
        rsaObject.start();
end;

procedure TFSend.FormClose(Sender: TObject; var Action: TCloseAction);
begin
        self.Free;
end;

procedure TFSend.Button1Click(Sender: TObject);
begin
        N := strtoint(edit1.text);
        E := strtoint(edit2.text);
        codedString := self.getAsciiForm(inputMessage.Text);
        cryptedMessage.Text := codedString;
        self.getAsciiBlocks;
        self.getCriptedBlocks;
end;

function TFSend.getCriptedBlock(x:int64) :int64;
begin
        getCriptedBlock := rsaObject.algebra.getPotenciaModuloN(x,E,N); //e passado como par
end;

procedure TFSend.getCriptedBlocks();
var code,cod:widestring;
    i,tamtotal :smallint;
    FI: int64;
begin
        tamtotal := length(codedBlocks);
        i := 0;
        code := '';
        while i <> tamtotal do
        begin
                code := code + 'C('+inttostr(codedBlocks[i])+') ';
                FI   := self.getCriptedBlock(codedBlocks[i]);
                cod  := cod + inttostr(FI) +'-';
                inc(i);
        end;
        Mblocos.Text := code;
//        DELETE (cod,length(cod)-2,1);
        finalMessage.Text := cod;

end;

function TFSend.getAsciiForm(wideStr :wideString) : wideString;
var  ascii : WideString;
     input : wideString;
     i: smallint;
begin
    ascii := '';
    input := wideStr;
    for i := 1 to length(input) do
      ascii := ascii + IntToStr(ord(input[i])+100);
    getAsciiForm := ascii;
end;

procedure TFSend.getAsciiBlocks;
var
  ContBlocos : integer;
  sAux       : string;
  i,tam      : smallint;
begin
    ContBlocos := 0;
    Randomize;
    while length(codedString) > 0 do
      begin
          tam := length(codedString);
          inc(ContBlocos);
          SetLength (codedBlocks, ContBlocos);
          i := random (length(IntToStr(n))-1)+1; //utiliza n
          if i > tam then
            i := tam;
          while codedString[i+1] = '0' do
            inc(i);
          if StrToInt64(Copy(codedString, 1, i)) > n then //utiliza n
          begin
              dec(i);
              while codedString[i+1] = '0' do
                dec(i);
          end;
          sAux := Copy(codedString, 1, i);
          Delete (codedString, 1, i);
          codedBlocks[ContBlocos - 1] := StrToInt64(sAux);
      end;
end;
end.
