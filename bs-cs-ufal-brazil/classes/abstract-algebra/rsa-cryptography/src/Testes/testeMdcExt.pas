unit testeMdcExt;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs, algebra,
  StdCtrls, Buttons, ExtCtrls;

type
  TFTesteMdcExt = class(TForm)
    a: TEdit;
    b: TEdit;
    mdc: TEdit;
    Image1: TImage;
    Bevel1: TBevel;
    BitBtn1: TBitBtn;
    extendido: TEdit;
    Bevel2: TBevel;
    BitBtn2: TBitBtn;
    BitBtn3: TBitBtn;
    BitBtn4: TBitBtn;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    Bevel3: TBevel;
    Bevel4: TBevel;
    t: TLabel;
    u: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    answer: TLabel;
    procedure FormCreate(Sender: TObject);
    procedure BitBtn4Click(Sender: TObject);
    procedure BitBtn1Click(Sender: TObject);
    procedure BitBtn2Click(Sender: TObject);
  private
    { Private declarations }
    procedure clearAll;
  public
    { Public declarations }
  end;

var
  FTesteMdcExt: TFTesteMdcExt;
  math: TAlgebra;

implementation
uses msgdlg;
{$R *.DFM}

procedure TFTesteMdcExt.FormCreate(Sender: TObject);
begin
  math := TAlgebra.Create();
  a.clear();
  b.clear();
  clearAll();
end;

procedure TFTesteMdcExt.BitBtn4Click(Sender: TObject);
begin
        close;
end;

procedure TFTesteMdcExt.BitBtn1Click(Sender: TObject);
var mdcAB:int64;
begin
   if (a.text <> '') and (b.text <> '') then
   begin
        clearAll();
        mdcAB := math.getMdcExtendido(strToInt64(a.Text),strToInt64(b.Text));
        mdc.Text := intToStr(mdcAB);
        t.caption := 't = '+intToStr(math.t);
        u.caption := 'u = '+intToStr(math.u);
        extendido.text := a.Text + ' * ('+intToStr(math.t)+') + '+ b.Text+' * ('+intToStr(math.u)+') = '+mdc.Text;
        if (mdcAB = 1) then
                answer.Visible := true;
   end else
   begin
        if (a.text = '') then
            messagedialog('É necessário digitar a.',mtError,[mbOk],0)
        else
        if (b.text = '') then
            messagedialog('É necessário digitar b.',mtError,[mbOk],0);
   end;
end;

procedure TFTesteMdcExt.clearAll;
begin
        mdc.clear();
        extendido.clear();
        t.Caption := 't  = ?';
        u.caption := 'u = ?';
end;

procedure TFTesteMdcExt.BitBtn2Click(Sender: TObject);
begin
        a.clear();
        b.clear();
        clearAll();
        answer.Visible := false;
end;

end.
