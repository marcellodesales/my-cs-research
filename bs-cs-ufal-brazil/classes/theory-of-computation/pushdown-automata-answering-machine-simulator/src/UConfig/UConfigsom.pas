unit UConfigsom;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  cmpMidiMixer, ComCtrls, StdCtrls, Buttons, Wordcap;

type
  TF_ConfigSom = class(TForm)
    MidiMixer1: TMidiMixer;
    GroupBox1: TGroupBox;
    Label1: TLabel;
    Label2: TLabel;
    tbPan: TTrackBar;
    Label3: TLabel;
    Label4: TLabel;
    GroupBox2: TGroupBox;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    tbLeftGVolume: TTrackBar;
    tbRightGVolume: TTrackBar;
    tbGVolume: TTrackBar;
    tbGPan: TTrackBar;
    btnGMute: TSpeedButton;
    btnMute: TSpeedButton;
    MSOfficeCaption1: TMSOfficeCaption;
    SpeedButton1: TSpeedButton;
    procedure FormShow(Sender: TObject);
    procedure MidiMixer1ControlChange(sender: TObject;
      control: TMixerControlType);
    procedure tbLeftGVolumeChange(Sender: TObject);
    procedure tbRightGVolumeChange(Sender: TObject);
    procedure tbGVolumeChange(Sender: TObject);
    procedure btnGMuteClick(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
  private
    function CalcPanAndVolume (l, r : Integer; var pan, volume : Integer) : boolean;
    procedure CalcLRFromPanAndVolume (pan, volume : Integer; var l, r : Integer);
  public
    { Public declarations }
  end;

var
  F_ConfigSom: TF_ConfigSom;

implementation

{$R *.DFM}

function TF_ConfigSom.CalcPanAndVolume (l, r : Integer; var pan, volume : Integer) : boolean;
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

procedure TF_ConfigSom.CalcLRFromPanAndVolume (pan, volume : Integer; var l, r : Integer);
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

procedure TF_ConfigSom.FormShow(Sender: TObject);
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

procedure TF_ConfigSom.MidiMixer1ControlChange(sender: TObject;
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



procedure TF_ConfigSom.tbLeftGVolumeChange(Sender: TObject);
begin
  with MidiMixer1 do
    ControlValue [mtAudioVolume, mcLeft] := ControlMax [mtAudioVolume] - tbLeftGVolume.Position
end;

procedure TF_ConfigSom.tbRightGVolumeChange(Sender: TObject);
begin
  with MidiMixer1 do
    ControlValue [mtAudioVolume, mcRight] := ControlMax [mtAudioVolume] - tbRightGVolume.Position
end;

procedure TF_ConfigSom.tbGVolumeChange(Sender: TObject);
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

procedure TF_ConfigSom.btnGMuteClick(Sender: TObject);
begin
  with MidiMixer1 do
    case btnGMute.Down of
      False : ControlValue [mtAudioMute, mcLeft] := ControlMin [mtAudioMute];
      True  : ControlValue [mtAudioMute, mcLeft] := ControlMax [mtAudioMute]
    end
end;

procedure TF_ConfigSom.SpeedButton1Click(Sender: TObject);
begin
     Close;
end;

end.
