unit Fenviar;

interface

uses 
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons, jpeg, ExtCtrls;

type
  TFrSend = class(TFrame)
    GroupBox1: TGroupBox;
    GroupBox2: TGroupBox;
    Image1: TImage;
    Label1: TLabel;
    Label2: TLabel;
    EditN: TEdit;
    EditE: TEdit;
    GroupBox3: TGroupBox;
    inputMessage: TMemo;
    GroupBox4: TGroupBox;
    finalMessage: TMemo;
    buttonGenerate: TBitBtn;
    buttonClose: TBitBtn;
    procedure buttonGenerateClick(Sender: TObject);
    procedure buttonCloseClick(Sender: TObject);
    procedure EditNKeyPress(Sender: TObject; var Key: Char);
    procedure EditEKeyPress(Sender: TObject; var Key: Char);
  private
    function getCriptedBlock(x:int64) : int64;
  public
    function getAsciiForm(wideStr :wideString):wideString;
    procedure getAsciiBlocks();
    procedure getCriptedBlocks();
  end;

var
  codedString : wideString;
  codedBlocks : array of int64;
  N,E         : int64;

implementation
uses principal,MsgDlg;
{$R *.DFM}

procedure TFrSend.buttonGenerateClick(Sender: TObject);
begin
 if (editN.Text <> '') and (editE.Text <> '') and (inputMessage.Text <> '') then
 begin
        main.log.Lines.Add('');
        main.log.Lines.Add('-> Enviando Mensagem');
        main.log.Lines.Add('');
        main.log.Lines.Add('   -> Mensagem original');
        main.log.Lines.Add('      '+inputMessage.Text);
        main.log.Lines.Add('');

        N := strtoint(EditN.Text);
        E := strtoint(EditE.text);

        main.log.Lines.Add('   -> Configurando chave pública (N,E)');
        main.log.Lines.Add('      ('+EditN.Text+','+EditE.text+');');
        main.log.Lines.Add('');

        codedString := self.getAsciiForm(inputMessage.Text);

        main.log.Lines.Add('   -> Transformando Mensagem/ASCII');
        main.log.Lines.Add('      '+codedString);
        main.log.Lines.Add('');

        self.getAsciiBlocks;
        self.getCriptedBlocks;
   end
   else begin
         if (editN.Text = '') then
                messagedialog('É necessário digitar o valor da chave N!',mtError,[mbOk],0)
         else
         if (editE.Text = '') then
                messagedialog('É necessário digitar o valor da chave E!',mtError,[mbOk],0)
         else
         if  (inputMessage.Text = '') then
                messagedialog('É necessário digitar o texto a ser enviado!',mtError,[mbOk],0)
   end;
end;

function TFrSend.getCriptedBlock(x:int64) :int64;
var modN: int64;
begin
        modN := main.rsaObject.algebra.getPotenciaModuloN(x,E,N);
        main.log.Lines.Add('      Bloco('+intToStr(x)+') = '+intToStr(x)+' ^ '+intToStr(E)+' mod '+intToStr(N)+' = '+intToStr(modN));
        getCriptedBlock :=  modN;
end;

procedure TFrSend.getCriptedBlocks();
var code,cod:widestring;
    i,tamtotal :smallint;
    bl: int64;
begin
        tamtotal := length(codedBlocks);
        i := 0;
        code := '';
        main.log.Lines.Add('   -> Codificando blocos');
        main.log.Lines.Add('      Bloco(x) = x ^ E mod N');
        main.log.Lines.Add('');
        while i <> tamtotal do
        begin
                code := code + 'Bloco('+inttostr(codedBlocks[i])+') ';
                bl   := self.getCriptedBlock(codedBlocks[i]);
                cod  := cod + inttostr(bl) +'-';
                inc(i);
        end;
        DELETE (cod,length(cod),1);
        main.log.Lines.Add('');
        main.log.Lines.Add('   -> Resumo dos blocos');
        main.log.Lines.Add('      '+code);
        main.log.Lines.Add('');
        main.log.Lines.Add('   -> Mensagem Criptografada');
        main.log.Lines.Add('      '+cod);

        finalMessage.Text := cod;
end;

function TFrSend.getAsciiForm(wideStr :wideString) : wideString;
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

procedure TFrSend.getAsciiBlocks;
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

procedure TFrSend.buttonCloseClick(Sender: TObject);
begin
        self.Visible := false;
        main.GBcript.Visible := false;
end;

procedure TFrSend.EditNKeyPress(Sender: TObject; var Key: Char);
begin
        if not(key in ['0'..'9',#8,#13]) then
        begin
            messagedialog('Este campo só aceita números!'+#13+'Você não digitou um numeral!',mtWarning,[mbOk],0);
            key := #0;
        end;
end;

procedure TFrSend.EditEKeyPress(Sender: TObject; var Key: Char);
begin
        EditNKeyPress(Sender,Key);
end;

end.
