unit UDiscador;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, jpeg, ExtCtrls,valida,MMSystem, Buttons,UFastDiskINI,UmanipulaButao,
  UAutomatoPilha,UTemp, Caret; //UManipulaAutomato;

type
  TF_Discador = class(TForm)
    Cfone: TEdit;
    PrefixoV: TImage;
    PrefixoNV: TImage;
    PrefResult: TLabel;
    VNuvalido: TImage;
    XNuvalido: TImage;
    NuResult: TLabel;
    DesignDicador: TImage;
    TeclaTeoriaUP: TImage;
    LIGrapida7UP: TImage;
    LIGrapida6UP: TImage;
    LIGrapida5UP: TImage;
    LIGrapida4UP: TImage;
    LIGrapida3UP: TImage;
    LIGrapida2UP: TImage;
    LIGrapida1UP: TImage;
    TeclaLimparUP: TImage;
    TeclaDiscarUP: TImage;
    Tecla3UP: TImage;
    Tecla6UP: TImage;
    Tecla9UP: TImage;
    TeclaJVUP: TImage;
    Tecla0UP: TImage;
    Tecla8UP: TImage;
    Tecla5UP: TImage;
    Tecla2UP: TImage;
    Tecla1UP: TImage;
    Tecla4UP: TImage;
    Tecla7UP: TImage;
    TeclaStarUP: TImage;
    Tecla1: TImage;
    Tecla2: TImage;
    Tecla3: TImage;
    Tecla6: TImage;
    Tecla5: TImage;
    Tecla4: TImage;
    Tecla7: TImage;
    TeclaStar: TImage;
    Tecla0: TImage;
    Tecla8: TImage;
    Tecla9: TImage;
    TeclaJV: TImage;
    TeclaDiscar: TImage;
    TeclaLimpar: TImage;
    LIGrapida1: TImage;
    LIGrapida2: TImage;
    LIGrapida3: TImage;
    LIGrapida4: TImage;
    LIGrapida5: TImage;
    LIGrapida6: TImage;
    LIGrapida7: TImage;
    TeclaTeoria: TImage;
    Data: TLabel;
    Hora: TLabel;
    Timer1: TTimer;
    LR1: TSpeedButton;
    LR2: TSpeedButton;
    LR3: TSpeedButton;
    LR4: TSpeedButton;
    LR5: TSpeedButton;
    LR6: TSpeedButton;
    LR7: TSpeedButton;
    NomeLR: TLabel;
    FONELR: TLabel;
    DesligaDOWN: TImage;
    DesligaUP: TImage;
    ComboBox1: TComboBox;
    TelefoneDiscando: TImage;
    Caret1: TCaret;
    SomUP: TImage;
    SomDOWN: TImage;
    procedure Tecla1UPClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
    procedure Tecla2UPClick(Sender: TObject);
    procedure ApagaValidez;
    procedure SePrefixoNumeroTrue;
    procedure SeNOTprefixoNumero;
    procedure SeNprefixoTNumero;
    procedure SeTprefixoNNumero;
    procedure EscreveDataHora;
    procedure AbreDiscagemRapida(Numero:Char);
    procedure inicializaPerguntas;
    procedure Tecla3UPClick(Sender: TObject);
    procedure Tecla4UPClick(Sender: TObject);
    procedure Tecla5UPClick(Sender: TObject);
    procedure Tecla6UPClick(Sender: TObject);
    procedure Tecla7UPClick(Sender: TObject);
    procedure Tecla8UPClick(Sender: TObject);
    procedure Tecla9UPClick(Sender: TObject);
    procedure Tecla0UPClick(Sender: TObject);
    procedure TeclaDiscarUPClick(Sender: TObject);
    procedure TeclaLimparUPClick(Sender: TObject);
    procedure TeclaStarUPClick(Sender: TObject);
    procedure TeclaJVUPClick(Sender: TObject);
    procedure CfoneKeyPress(Sender: TObject; var Key: Char);
    procedure LIGrapida1UPClick(Sender: TObject);
    procedure Somdigito(Digit:Integer);
    Procedure DavalorNumero(DigitF:Integer);
    procedure TeclaTeoriaUPClick(Sender: TObject);
    Procedure DesabilitaButoesQuantoDiscar;
    Procedure HabilitaButoesDepoisDiscar;
    procedure AbreLigacoesRapidasINI;
    Procedure LENumeroFastDisk(NumeroLR:Char);
    procedure AtualizaFastDiskINI(NumeroLigRap:Char);
    procedure LIGrapida2UPClick(Sender: TObject);
    procedure LIGrapida3UPClick(Sender: TObject);
    procedure LIGrapida4UPClick(Sender: TObject);
    procedure LIGrapida5UPClick(Sender: TObject);
    procedure LIGrapida6UPClick(Sender: TObject);
    procedure LIGrapida7UPClick(Sender: TObject);
    procedure LR1Click(Sender: TObject);
    procedure LR2Click(Sender: TObject);
    procedure LR3Click(Sender: TObject);
    procedure LR4Click(Sender: TObject);
    procedure LR5Click(Sender: TObject);
    procedure LR6Click(Sender: TObject);
    procedure LR7Click(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure DesligaUPClick(Sender: TObject);
    procedure AtualizaPainel(digito:char);
    procedure SomUPClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_Discador: TF_Discador;
  LigRap:TFastDisk;
  NumeroF:integer;
  AUX:String; //Para validar o telefone
  Validatecla:boolean;
  CaminhoArquivo,NomeArq:String;
  Butao:Tbutoes;
  NumerosLR:Array[1..7] of String;
  Automato:TTemp;
  PodeAFD:Boolean;

implementation
uses MsgDlg, //Mensagens em Português
     UAFD,Umensagem,UFastDisk, UConfigsom;
{$R *.DFM}

procedure TF_Discador.FormCreate(Sender: TObject);
begin
     EscreveDataHora;
     //Digito:=TDigita.Create;
     inicializaPerguntas;
     Cfone.Text:='';
     ApagaValidez;
     CaminhoArquivo:=ExtractFilePath(Application.ExeName);
     NomeArq:='Ligações Rápidas.ini';
     AbreLigacoesRapidasINI;
     PodeAFD:=False;
end;

procedure TF_Discador.AbreLigacoesRapidasINI;
var NumeroCaixa:ShortString;
    I,j:INTEGER;
begin
     LigRap:=TFastDisk.Create;
     if Not FileExists(CaminhoArquivo+NomeArq) then
        Exit
     else
     begin
      For i:=1 to 7 do
      begin
        LigRap.GetAll(CaminhoArquivo+NomeArq,intToStr(i));
        if (LigRap.Nome='ERROR') and (LigRap.Numero='ERROR') then
        begin        //Consequência da leitura do INI
           LigRap.Nome:='';
           LigRap.Numero:='';
        end;
        NumerosLR[i]:=LigRap.Numero;
        NumeroCaixa:='LR'+intToStr(i);
        for j:=0 to ComponentCount-1 do
        begin
          if (Components[j] is TSpeedButton) then
          begin
             if (Components[j] as TSpeedButton).Name=NumeroCaixa then
             begin
                (Components[j] as TSpeedButton).Caption:=LigRap.Nome;
                (Components[j] as TSpeedButton).Hint:=LigRap.Numero;
             end;
          end;
        end;
      end;
     end;
     LigRap.Free;
end;

Procedure TF_Discador.DavalorNumero(DigitF:Integer); //Da valores dos números falados
begin
     if DigitF=0 then
        NumeroF:=20;
     if DigitF=1 then
        NumeroF:=21;
     if DigitF=2 then
        NumeroF:=22;
     if DigitF=3 then
        NumeroF:=23;
     if DigitF=4 then
        NumeroF:=24;
     if DigitF=5 then
        NumeroF:=25;
     if DigitF=6 then
        NumeroF:=26;
     if DigitF=7 then
        NumeroF:=27;
     if DigitF=8 then
        NumeroF:=28;
     if DigitF=9 then
        NumeroF:=29;
end;

procedure TF_Discador.Somdigito(Digit:integer); //Retorna o som do número
var NumSeg   :single;
    Tempoini :TDateTime;
begin
     Tempoini := now;
     NumSeg:=1;
     DavalorNumero(Digit);
     PlaySound(PChar(NumeroF),HInstance, snd_ASync or snd_Memory or snd_Resource);
     repeat
          Application.ProcessMessages;
     until Now > tempoini + NumSeg * (1/24/60/60);
     SndPlaySound('Coloca nada para parar o som.wav',SND_ASync); //dá stop
end;

procedure TF_Discador.ApagaValidez;
begin
     PrefixoV.Visible:=false;
     PrefixoNV.Visible:=false;
     XNuvalido.Visible:=false;
     VNuvalido.Visible:=false;
end;

procedure TF_Discador.EscreveDataHora;
begin
     Data.Caption:=FormatDateTime('dd "/" mm "/" yyyy',date);
end;

procedure TF_Discador.Timer1Timer(Sender: TObject);
begin
     Hora.Caption:=TimeToStr(now);
end;

procedure TF_Discador.inicializaPerguntas;
begin
    PrefResult.Caption:='O prefixo é válido????';
    NuResult.Caption:='O Número do telefone é válido???';
end;

procedure TF_Discador.AtualizaPainel(Digito:char);
begin
     If Length(CFone.Text)<7 then
        Cfone.Text:=Cfone.Text+Digito;
end;

procedure TF_Discador.Tecla0UPClick(Sender: TObject);
begin
     AtualizaPainel('0');
     Butao.delayButoes(0.3,Tecla0UP,Tecla0);
      PlaySound(PChar(10),HInstance, snd_ASync or snd_Memory or snd_Resource); //TOCA O DIGITO 0
end;

procedure TF_Discador.Tecla1UPClick(Sender: TObject);
begin
     AtualizaPainel('1');
     Butao.delayButoes(0.3,Tecla1UP,Tecla1);
     PlaySound(PChar(1),HInstance, snd_ASync or snd_Memory or snd_Resource); //TOCA O DIGITO 1
end;

procedure TF_Discador.Tecla2UPClick(Sender: TObject);
begin
     AtualizaPainel('2');
     Butao.delayButoes(0.3,Tecla2UP,Tecla2);
     PlaySound(PChar(2),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Discador.Tecla3UPClick(Sender: TObject);
begin
     AtualizaPainel('3');
     Butao.delayButoes(0.3,Tecla3UP,Tecla3);
     PlaySound(PChar(3),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Discador.Tecla4UPClick(Sender: TObject);
begin
     AtualizaPainel('4');
     Butao.delayButoes(0.3,Tecla4UP,Tecla4);
     PlaySound(PChar(4),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Discador.Tecla5UPClick(Sender: TObject);
begin
     AtualizaPainel('5');
     Butao.delayButoes(0.3,Tecla5UP,Tecla5);
     PlaySound(PChar(5),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Discador.Tecla6UPClick(Sender: TObject);
begin
     AtualizaPainel('6');
     Butao.delayButoes(0.3,Tecla6UP,Tecla6);
     PlaySound(PChar(6),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Discador.Tecla7UPClick(Sender: TObject);
begin
     AtualizaPainel('7');
     Butao.delayButoes(0.3,Tecla7UP,Tecla7);
     PlaySound(PChar(7),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Discador.Tecla8UPClick(Sender: TObject);
begin
     AtualizaPainel('8');
     Butao.delayButoes(0.3,Tecla8UP,Tecla8);
     PlaySound(PChar(8),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Discador.Tecla9UPClick(Sender: TObject);
begin
     AtualizaPainel('9');
     Butao.delayButoes(0.3,Tecla9UP,Tecla9);
     PlaySound(PChar(9),HInstance, snd_ASync or snd_Memory or snd_Resource);
end;

procedure TF_Discador.TeclaStarUPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,TeclaStarUP,TeclaStar);
     PlaySound(PChar(12),HInstance, snd_ASync or snd_Memory or snd_Resource); //Estrela
end;

procedure TF_Discador.TeclaJVUPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,TeclaJVUP,TeclaJV);
     PlaySound(PChar(13),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
end;

procedure TF_Discador.SePrefixoNumeroTrue;
begin
     PrefixoV.Visible:=true;
     Prefresult.Caption:='O prefixo é válido';
     VNuvalido.Visible:=true;
     NuResult.Caption:='O telefone é válido';
end;

procedure TF_Discador.SeNOTprefixoNumero;
begin
     PrefixoNV.Visible:=true;
     Prefresult.Caption:='O prefixo não é válido';
     XNuvalido.Visible:=true;
     NuResult.Caption:='O telefone não é válido: '+IntToStr(length(AUX))+' digitos';
     PlaySound(PChar(11),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
end;

procedure TF_Discador.SeNprefixoTNumero;
begin
    PrefixoNV.Visible:=true;
    Prefresult.Caption:='O prefixo não é válido';
    VNuvalido.Visible:=true;
    NuResult.Caption:='O telefone é válido';
    PlaySound(PChar(11),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
end;

procedure TF_Discador.SeTprefixoNNumero;
begin
     PrefixoV.Visible:=true;
     Prefresult.Caption:='O prefixo é válido';
     XNuvalido.Visible:=true;
     NuResult.Caption:='O telefone não é válido: '+IntToStr(length(AUX))+' digitos';
     PlaySound(PChar(11),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
end;

Procedure TF_Discador.DesabilitaButoesQuantoDiscar;
var i:integer;
begin
     For i:=0 to ComponentCount-1 do
         if (Components[i] is TImage) then
            (Components[i] as TImage).Enabled:=false;
end;

Procedure TF_Discador.HabilitaButoesDepoisDiscar;
var i:integer;
begin
     For i:=0 to ComponentCount-1 do
         if (Components[i] is TImage) then
            (Components[i] as TImage).Enabled:=True;
end;

Procedure DizSom(Tempo:single;Num:integer);
var Tempoini:TDateTime;
begin
     Tempoini := now;
     PlaySound(PChar(Num),HInstance, snd_ASync or snd_Memory or snd_Resource);
     repeat
          Application.ProcessMessages;
     until Now > tempoini + Tempo * (1/24/60/60);
     SndPlaySound('',SND_ASync); //dá stop
end;

(*Procedure PegaMensagemAutomatoINI(Var CharAutomato:Char;var Numero:Integer);
var i:integer;
    CharExpli:Char;
    Arquivo:String;

begin
     Arquivo:=ExtractFilePath(Application.ExeName)+'Numeros\'+F_Discador.Cfone.Text+'.ini';
     Numero:=Automato.NumeroRecado(Arquivo);
     Inc(Numero);
     Automato.GetAll(Arquivo);
     if (Automato.Estado='q2') then
        CharAutomato:='5'
     else
         if (i=0) then
            CharAutomato:='0'
         else
           CharAutomato:='1';
end;*)

procedure TF_Discador.TeclaDiscarUPClick(Sender: TObject);
Var CH1,CH2,CH3:char;
    Tempoini:TDateTime;
    NumSeg:single;
    Ocupado:Integer;
    //ExplicationAutomato:Char;
begin
     ApagaValidez;
     Butao.delayButoes(0.3,TeclaDiscarUP,TeclaDiscar);
     if Cfone.Text='' then
     begin
        PlaySound(PChar(11),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
        MessageDialog('É necessário digitar o número de um telefone',mtInformation,[mbOK],0);
     end
     else
     begin
          //PegaMensagemAutomatoINI(ExplicationAutomato,AuxMenAutINI);
          CH1:=Cfone.Text[1];
          CH2:=Cfone.Text[2];
          CH3:=Cfone.text[3];
          AUX:=Cfone.text;
          // Se o telefone é válido, então diz o número e chama....
          if ((validaprefixo(CH1,CH2,CH3)) and validafone(AUX)) then
          begin
               PodeAFD:=True;
               TelefoneDiscando.Visible:=true;
               SePrefixoNumeroTrue; //procedimento
               DesabilitaButoesQuantoDiscar;

               Randomize;
               Ocupado:=Random(100);
               If Ocupado in [40..70] then
               begin
                    Tempoini := now;
                    NumSeg:=4;
                    PlaySound(PChar(115),HInstance, snd_ASync or snd_Memory or snd_Resource or snd_LOOP);
                    repeat
                          Application.ProcessMessages;
                     until Now > tempoini + NumSeg * (1/24/60/60);
                     SndPlaySound('Coloca nada para parar o som.wav',SND_ASync); //dá stop
                     MessageDialog('O telefone discado '+CFone.text+' está ocupado. Tente novamente mais tarde.',mtError,[mbOK],0);
                     HabilitaButoesDepoisDiscar;
                     TelefoneDiscando.Visible:=False;
                     exit;
               end;

               PlaySound(PChar(16),HInstance, snd_ASync or snd_Memory or snd_Resource); //cHAMANDO

               Tempoini := now;
               NumSeg:=8;
               PlaySound(PChar(16),HInstance, snd_ASync or snd_Memory or snd_Resource or snd_LOOP); //2 loops
               repeat
                     Application.ProcessMessages;
               until Now > tempoini + NumSeg * (1/24/60/60);
               SndPlaySound('Coloca nada para parar o som.wav',SND_ASync); //dá stop

               if Cfone.Text='3262884' then
               begin
                  DizSom(8,110);
                  DizSom(2,114);
               end
               else
               begin
                    DizSom(1.5,15);
                    Somdigito(StrToInt(CFone.Text[1]));
                    Somdigito(StrToInt(CFone.Text[2]));
                    Somdigito(StrToInt(CFone.Text[3]));
                    Somdigito(StrToInt(CFone.Text[4]));
                    Somdigito(StrToInt(CFone.Text[5]));
                    Somdigito(StrToInt(CFone.Text[6]));
                    Somdigito(StrToInt(CFone.Text[7]));
               end;

               If Automato.NumeroRecado(CaminhoArquivo+'Numeros\'+Cfone.text+'.tmp')=10 then
               begin
                  PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource);
                  Messagedialog('A secretária Eletrônica do telefone '+Cfone.Text+
                   ' está cheia. Impossível gravar mensagem!',mtError,[mbOK],0);
               end
               else
               begin
                    if Cfone.Text<>'3262884' then
                    begin
                         DizSom(3,120);
                         PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource);
                         DizSom(2,114);
                    end;
                    Application.CreateForm(TF_Mensagem,F_Mensagem);
                    F_Mensagem.Caption:='Digite sua mensagem para a secretária de '+Cfone.Text;
                    F_Mensagem.ShowModal;
                    F_Mensagem.free;

                    Tempoini := now;
                    NumSeg:=4;  //Obrigao Favor aguardar um momento
                    PlaySound(PChar(112),HInstance, snd_ASync or snd_Memory or snd_Resource);
                    repeat
                        Application.ProcessMessages;
                    until Now > tempoini + NumSeg * (1/24/60/60);
                    SndPlaySound('',SND_ASync); //dá stop
                    PlaySound(PChar(111),HInstance, snd_ASync or snd_Memory or snd_Resource);
            //   ExplicacaoAFD(ExplicationAutomato,AuxMenAutINI);
               end;
               TelefoneDiscando.Visible:=False;
               HabilitaButoesDepoisDiscar;
          end
          else
          begin
               if (not validaprefixo(CH1,CH2,CH3) and not validafone(AUX)) then
                  SeNOTprefixoNumero;
          end;
          if (validaprefixo(CH1,CH2,CH3) and not validafone(AUX)) then
             SeTprefixoNNumero
          else
          begin
               if (not validaprefixo(CH1,CH2,CH3) and validafone(AUX)) then
                  SeNprefixoTNumero;
          end;
     end;
end;

procedure TF_Discador.TeclaLimparUPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,TeclaLimparUP,TeclaLimpar);
     ApagaValidez;
     inicializaPerguntas;
     Cfone.Text:='';
     PodeAFD:=false;
end;

procedure TF_Discador.CfoneKeyPress(Sender: TObject; var Key: Char);
var i:shortint;
begin
     i:=Length(Cfone.Text);
     if (not (Key in ['0'..'9',#8,#13])) or (i=8) then
        key:=#0;
end;

procedure TF_Discador.AbreDiscagemRapida(Numero:Char);
Var NumeroCaixa,NomeCaixa,NomeLRini,FOneLRini:String;
    i:integer;
begin
     NomeCaixa:='LR'+Numero;
     LigRap:=TFastDisk.Create;
     NomeLRini:=LigRap.GetNome(CaminhoArquivo+NomeArq,Numero);
     FOneLRini:=LigRap.GetFone(CaminhoArquivo+NomeArq,Numero);
     if (NomeLRini='ERROR') and (FOneLRini='ERROR') then
     begin
          NomeLRini:='';
          FOneLRini:='';
     end;
     Try
        Application.CreateForm(TF_FastDisk,F_FastDisk);
        F_FaStDisk.Caption:='Discagem Rápida '+Numero;
        F_FaStDisk.NomeFastDisk.Text:=NomeLRini;
        F_FaStDisk.FoneFastDisk.Text:=FoneLRini;
        F_FastDisk.ShowModal;
     Finally
            F_FastDisk.free;
     end;
     NumeroCaixa:='LR'+Numero;
     if (nomeLR.Caption='NomeLR') or (FONElr.Caption='FONELR') then
     begin  // Se a caixa fechar sem nenhuma entrada....
        nomeLR.Caption:='';
        FONElr.Caption:='';
     end;
     for i:=0 to ComponentCount-1 do
     begin
         if (Components[i] is TSpeedButton) then
         begin
            if (Components[i] as TSpeedButton).Name=NumeroCaixa then
            begin
                (Components[i] as TSpeedButton).Caption:=NomeLR.Caption;
                (Components[i] as TSpeedButton).Hint:=FoneLR.Caption;
            end;
         end;
     end;
end;

procedure TF_Discador.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     Automato.Free;
end;

procedure TF_Discador.TeclaTeoriaUPClick(Sender: TObject);
begin
    Butao.delayButoes(0.3,TeclaTeoriaUP,TeclaTeoria);
    if  (length(Cfone.text)<>7) and (not PodeAFD) then
    begin
        PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Lenmbrar
        MessageDialog('É preciso validar um número de telefone correto.'#13'Para isso, digite um número correto de telefone'#13'e aperte novamente em Teoria.',mtInformation,[mbOK],0);
    end
    else
    begin
         PlaySound(PChar(200),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
          Application.CreateForm(TF_AFD,F_AFD);
          F_AFD.ShowModal;
    end;
end;

procedure TF_Discador.AtualizaFastDiskINI(NumeroLigRap:Char);
var NomeArq:ShortString;
begin
     NomeArq:='Ligações Rápidas.ini';
     LigRap:=TFastDisk.Create;
     LigRap.Davalores(NomeLR.Caption,FONELR.Caption);
     LigRap.AtualzaINI(CaminhoArquivo+NomeArq,NumeroLigRap);
     LigRap.Free;
end;

procedure TF_Discador.LIGrapida1UPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,LIGrapida1UP,LIGrapida1);
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
     AbreDiscagemRapida('1');
     AtualizaFastDiskINI('1');
end;

procedure TF_Discador.LIGrapida2UPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,LIGrapida2UP,LIGrapida2);
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
     AbreDiscagemRapida('2');
     AtualizaFastDiskINI('2');
end;

procedure TF_Discador.LIGrapida3UPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,LIGrapida3UP,LIGrapida3);
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
     AbreDiscagemRapida('3');
     AtualizaFastDiskINI('3');
end;

procedure TF_Discador.LIGrapida4UPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,LIGrapida4UP,LIGrapida4);
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
     AbreDiscagemRapida('4');
     AtualizaFastDiskINI('4');
end;

procedure TF_Discador.LIGrapida5UPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,LIGrapida5UP,LIGrapida5);
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
     AbreDiscagemRapida('5');
     AtualizaFastDiskINI('5');
end;

procedure TF_Discador.LIGrapida6UPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,LIGrapida6UP,LIGrapida6);
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
     AbreDiscagemRapida('6');
     AtualizaFastDiskINI('6');
end;

procedure TF_Discador.LIGrapida7UPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,LIGrapida7UP,LIGrapida7);
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
     AbreDiscagemRapida('7');
     AtualizaFastDiskINI('7');
end;

procedure MostraMensagemLR(X:Char);
begin
   MessageDialog('Pressione o butão ao lado e digite um nome e número'+#13+
          'para pertencer a ligação rápida '+X+'.',mtInformation,[mbOK],0);
end;

Procedure TF_Discador.LENumeroFastDisk(NumeroLR:Char);
begin
     LigRap:=TFastDisk.Create;
     CFone.text:=LigRap.GetFone(CaminhoArquivo+NomeArq,NumeroLR);
     if CFone.Text='ERROR' then
        CFone.Text:='';
     LigRap.Free;
end;

procedure TF_Discador.LR1Click(Sender: TObject);
begin
     LENumeroFastDisk('1');
     if LR1.Caption='' then
        MostraMensagemLR('1')
     else
         TeclaDiscarUPClick(F_Discador);
end;

procedure TF_Discador.LR2Click(Sender: TObject);
begin
     LENumeroFastDisk('2');
     if LR2.Caption='' then
        MostraMensagemLR('2')
     else
         TeclaDiscarUPClick(F_Discador);
end;

procedure TF_Discador.LR3Click(Sender: TObject);
begin
     LENumeroFastDisk('3');
     if LR3.Caption='' then
        MostraMensagemLR('3')
     else
         TeclaDiscarUPClick(F_Discador);
end;

procedure TF_Discador.LR4Click(Sender: TObject);
begin
     LENumeroFastDisk('4');
     if LR4.Caption='' then
        MostraMensagemLR('4')
     else
         TeclaDiscarUPClick(F_Discador);
end;

procedure TF_Discador.LR5Click(Sender: TObject);
begin
     LENumeroFastDisk('5');
     if LR5.Caption='' then
        MostraMensagemLR('5')
     else
         TeclaDiscarUPClick(F_Discador);
end;

procedure TF_Discador.LR6Click(Sender: TObject);
begin
     LENumeroFastDisk('6');
     if LR6.Caption='' then
        MostraMensagemLR('6')
     else
         TeclaDiscarUPClick(F_Discador);
end;

procedure TF_Discador.LR7Click(Sender: TObject);
begin
     LENumeroFastDisk('7');
     if LR7.Caption='' then
        MostraMensagemLR('7')
     else
         TeclaDiscarUPClick(F_Discador);
end;

procedure TF_Discador.DesligaUPClick(Sender: TObject);
begin
     DesligaUP.Visible:=false;
     DesligaDOWN.Visible:=true;
     if (messagedialog('Deseja realmente desligar?',mtconfirmation,[mbYes,mbNo],0)=mrYes) then
        Close
     else
     begin
          DesligaDOWN.Visible:=false;
          DesligaUP.Visible:=true;
     end;
end;

procedure TF_Discador.SomUPClick(Sender: TObject);
begin
     Butao.delayButoes(0.3,SomUP,SomDOWN);
     PlaySound(PChar(14),HInstance, snd_ASync or snd_Memory or snd_Resource); //Inválido
     Try
        Application.CreateForm(TF_Configsom,F_ConfigSom);
        F_ConfigSom.ShowModal;
     Finally
        F_ConfigSom.free;
     end;
end;

end.
