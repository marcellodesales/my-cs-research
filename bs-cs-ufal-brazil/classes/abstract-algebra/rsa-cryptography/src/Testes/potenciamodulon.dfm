object FmoduloN: TFmoduloN
  Left = 225
  Top = 152
  BorderStyle = bsDialog
  Caption = 'Potências módulo n'
  ClientHeight = 132
  ClientWidth = 459
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  FormStyle = fsMDIChild
  OldCreateOrder = False
  Position = poScreenCenter
  Visible = True
  OnClose = FormClose
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 33
    Top = 40
    Width = 13
    Height = 16
    Caption = 'A:'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object Label2: TLabel
    Left = 33
    Top = 95
    Width = 12
    Height = 16
    Caption = 'E:'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object Label3: TLabel
    Left = 33
    Top = 68
    Width = 13
    Height = 16
    Caption = 'N:'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object Label4: TLabel
    Left = 336
    Top = 40
    Width = 78
    Height = 16
    Caption = 'A ^ E mod N'
    Font.Charset = ANSI_CHARSET
    Font.Color = clWindowText
    Font.Height = -13
    Font.Name = 'Arial'
    Font.Style = [fsBold]
    ParentFont = False
  end
  object Edit1: TEdit
    Left = 48
    Top = 36
    Width = 145
    Height = 21
    TabOrder = 0
    Text = 'Edit1'
  end
  object Edit2: TEdit
    Left = 296
    Top = 56
    Width = 145
    Height = 21
    TabOrder = 4
    Text = 'Edit2'
  end
  object Button1: TButton
    Left = 200
    Top = 56
    Width = 89
    Height = 25
    Caption = 'Ver a^e mod n '
    Default = True
    TabOrder = 3
    OnClick = Button1Click
  end
  object Edit3: TEdit
    Left = 48
    Top = 91
    Width = 145
    Height = 21
    TabOrder = 1
    Text = 'Edit3'
  end
  object Edit4: TEdit
    Left = 48
    Top = 64
    Width = 145
    Height = 21
    TabOrder = 2
    Text = 'Edit4'
  end
end
