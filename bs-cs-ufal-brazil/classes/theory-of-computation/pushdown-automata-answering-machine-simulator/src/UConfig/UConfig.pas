unit UConfig;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  StdCtrls, Buttons,UConfigINI, Wordcap, ComCtrls, cmpMidiMixer;

type
  TF_Config = class(TForm)
    GroupBox2: TGroupBox;
    Label1: TLabel;
    TempoSpl: TEdit;
    BitBtn1: TBitBtn;
    BitBtn2: TBitBtn;
    EscolhaSistema: TGroupBox;
    ChecaSplash: TCheckBox;
    ChecaBarraTarefas: TCheckBox;
    MSOfficeCaption1: TMSOfficeCaption;
    GroupBox1: TGroupBox;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    btnGMute: TSpeedButton;
    tbLeftGVolume: TTrackBar;
    tbRightGVolume: TTrackBar;
    tbGVolume: TTrackBar;
    tbGPan: TTrackBar;
    GroupBox3: TGroupBox;
    Label2: TLabel;
    Label3: TLabel;
    Label4: TLabel;
    Label9: TLabel;
    btnMute: TSpeedButton;
    tbPan: TTrackBar;
    MidiMixer1: TMidiMixer;
    procedure BitBtn1Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure BitBtn2Click(Sender: TObject);
    procedure TempoSplKeyPress(Sender: TObject; var Key: Char);
    procedure FormShow(Sender: TObject);
    procedure tbLeftGVolumeChange(Sender: TObject);
    procedure tbRightGVolumeChange(Sender: TObject);
    procedure btnGMuteClick(Sender: TObject);
    procedure tbGVolumeChange(Sender: TObject);
    procedure tbGPanChange(Sender: TObject);
    procedure MidiMixer1ControlChange(sender: TObject;
      control: TMixerControlType);
  private
    function CalcPanAndVolume (l, r : Integer; var pan, volume : Integer) : boolean;
    procedure CalcLRFromPanAndVolume (pan, volume : Integer; var l, r : Integer);
  public
    { Public declarations }
  end;

var
  F_Config: TF_Config;
  ArquivoConfig:String;

implementation
uses Uprincipal;
{$R *.DFM}

procedure TF_Config.BitBtn1Click(Sender: TObject);
begin
     Close;
end;

procedure TF_Config.FormCreate(Sender: TObject);
Var ConfigINI:TConfigINI;
begin
     ArquivoConfig:=ExtractFilePath(Application.Exename)+'configuracao.ini';
     ConfigINI:=TConfigINI.Create;
     ConfigINI.GetAll(ArquivoConfig);
    { if ConfigINI.BarraTarefas=True then
        ChecaBarraTarefas.Checked:=True
     else
         ChecaBarraTarefas.Checked:=False;}
     if ConfigINI.Splash=True then
         ChecaSplash.Checked:=True
     else
         ChecaSplash.Checked:=False;
     TempoSpl.Text:=IntToStr(ConfigINI.TempoSplash);
     ConfigINI.Free;
end;

procedure TF_Config.BitBtn2Click(Sender: TObject);
Var ConfigINI:TConfigINI;
begin
     ConfigINI:=TConfigINI.Create;
     //ConfigINI.BarraTarefas:=ChecaBarraTarefas.Checked;
     //ConfigINI.EscondeBarra(ConfigINI.BarraTarefas,ArquivoConfig);
     ConfigINI.Splash:=ChecaSplash.Checked;
     ConfigINI.SomMudo:=(not F_Princ.SomMudo.Animate);
     ConfigINI.TempoSplash:=StrToInt(TempoSpl.Text);
     ConfigINI.AtualizaINI(ArquivoConfig);
     ConfigINI.Free;
end;

procedure TF_Config.TempoSplKeyPress(Sender: TObject; var Key: Char);
begin
     if not (key in ['0'..'9',#8]) then
        Key:=#0;
end;

function TF_Config.CalcPanAndVolume (l, r : Integer; var pan, volume : Integer) : boolean;
var
  panRange : Integer;
begin
  volume := l;
  panRange := (tbPan.Max - tbPan.Min) div 2;
  if r > volume then volume := r;
  result := volume <> 0;
  if result then
    pan := (r - l) * panRange div volume
  else
    pan := 0
end;

procedure TF_Config.CalcLRFromPanAndVolume (pan, volume : Integer; var l, r : Integer);
var
  panRange : Integer;
begin
  panRange := (tbPan.Max - tbPan.Min) div 2;
  if pan < 0 then
  begin
    l := volume;
    r := (pan + panRange) * volume div PanRange
  end
  else
  begin
    r := volume;
    l := (panRange - pan) * volume div panRange
  end
end;

procedure TF_Config.FormShow(Sender: TObject);
var
  lValue, rValue, min, max, pan, volume : Integer;
begin
  with MidiMixer1 do
  begin
    Active := True;
    if ControlSupported [mtMidiVolume] then
    begin
      max := ControlMax [mtMidiVolume];
      min := ControlMin [mtMidiVolume];
      lValue := ControlValue [mtMidiVolume, mcLeft];
      rValue := ControlValue [mtMidiVolume, mcRight];

      CalcPanAndVolume (lValue, rValue, pan, volume);
      tbPan.Position := pan;
    end
    else
    begin
    end;

    if ControlSupported [mtAudioVolume] then
    begin
      max := ControlMax [mtAudioVolume];
      min := ControlMin [mtAudioVolume];
      lValue := ControlValue [mtAudioVolume, mcLeft];
      rValue := ControlValue [mtAudioVolume, mcRight];

      tbLeftGVolume.Min := min;
      tbLeftGVolume.Max := max;

      tbRightGVolume.Min := min;
      tbRightGVolume.Max := max;

      tbGVolume.Min := min;
      tbGVolume.Max := max;

      tbLeftGVolume.Position := max - lValue;
      tbRightGVolume.Position := max - rValue;
      CalcPanAndVolume (lValue, rValue, pan, volume);
      tbGPan.Position := pan;
      tbGVolume.Position := max - volume
    end
    else
    begin
      tbLeftGVolume.Enabled := False;
      tbrightGVolume.Enabled := False;
    end;

    if ControlSupported [mtAudioMute] then
      btnGMute.Down := ControlValue [mtAudioMute, mcLeft] <> ControlMin [mtAudioMute]
    else
      btnGMute.Enabled := False;

    if ControlSupported [mtMidiMute] then
      btnMute.Down := ControlValue [mtMidiMute, mcLeft] <> ControlMin [mtMidiMute]
    else
      btnMute.Enabled := False;

    if ControlSupported [mtAudioTreble] then
    begin
      max := ControlMax [mtAudioTreble];
    end
    else

    if ControlSupported [mtAudioBass] then
    begin
      max := ControlMax [mtAudioBass];
    end
    else
  end
end;

procedure TF_Config.tbLeftGVolumeChange(Sender: TObject);
begin
  with MidiMixer1 do
    ControlValue [mtAudioVolume, mcLeft] := ControlMax [mtAudioVolume] - tbLeftGVolume.Position
end;

procedure TF_Config.tbRightGVolumeChange(Sender: TObject);
begin
  with MidiMixer1 do
    ControlValue [mtAudioVolume, mcRight] := ControlMax [mtAudioVolume] - tbRightGVolume.Position
end;

procedure TF_Config.btnGMuteClick(Sender: TObject);
begin
  with MidiMixer1 do
    case btnGMute.Down of
      False :begin
                  ControlValue [mtAudioMute, mcLeft] := ControlMin [mtAudioMute];
                  F_Princ.SomMudo.Animate:=True;
                  F_Princ.SomMudo.Hint:='Aplicação rodando com som.';
             end;
      True  :begin
                  ControlValue [mtAudioMute, mcLeft] := ControlMax [mtAudioMute];
                  F_Princ.SomMudo.Animate:=False;
                  F_Princ.SomMudo.Hint:='Aplicação sem som.';
             end;
    end;
end;

procedure TF_Config.tbGVolumeChange(Sender: TObject);
var
  max, l, r : Integer;
  values : TChannelValues;
begin
  with MidiMixer1 do
  begin
    max := ControlMax [mtAudioVolume];
    CalcLRFromPanAndVolume (tbGPan.Position, max - tbGVolume.Position, l, r);
    values [mcLeft] := l;
    values [mcRight] := r;
    ControlValues [mtAudioVolume] := values
  end

end;

procedure TF_Config.tbGPanChange(Sender: TObject);
var
  max, l, r : Integer;
  values : TChannelValues;
begin
  with MidiMixer1 do
  begin
    max := ControlMax [mtAudioVolume];
    CalcLRFromPanAndVolume (tbGPan.Position, max - tbGVolume.Position, l, r);
    values [mcLeft] := l;
    values [mcRight] := r;
    ControlValues [mtAudioVolume] := values
  end

end;

procedure TF_Config.MidiMixer1ControlChange(sender: TObject;
  control: TMixerControlType);
var
  lValue, rValue, max, min, pan, volume : Integer;
begin
  with MidiMixer1 do
  begin
    lValue := ControlValue [control, mcLeft];
    rValue := ControlValue [control, mcRight];
    max := ControlMax [control];
    min := ControlMin [control];

    case control of
      mtMidiVolume, mtAudioVolume :
        begin
          if CalcPanAndVolume (lValue, rValue, pan, volume) then
          case control of
            mtMidiVolume : tbPan.Position := pan;
            mtAudioVolume : tbGPan.Position := pan;
          end;

          case control of
            mtMidiVolume :
              begin
              end;
            mtAudioVolume :
              begin
                tbLeftGVolume.Position := max - lValue;
                tbRightGVolume.Position := max - rValue;
                tbGVolume.Position := max - volume
              end;
          end
        end;

      mtAudioMute : btnGMute.Down := lValue <> min;
      mtMidiMute  : btnMute.Down := lValue <> min;
    end
  end
end;

end.
