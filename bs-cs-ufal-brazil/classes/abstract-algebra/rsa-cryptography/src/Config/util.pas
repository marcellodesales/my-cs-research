unit util;

interface
uses Dialogs,StdCtrls,ComCtrls,Forms,Graphics;
Type
 TUtil = Class

 public
     procedure richTextSaveAsClick(Sender: TObject; SaveDialog: TSaveDialog; richText: TRichEdit);
     procedure memoFileSaveAsClick(Sender: TObject; SaveDialog: TSaveDialog; memo: TMemo);
     Function printerIsOnLine():Boolean;
     procedure FormImagePaint(Sender: TObject; formulario:TForm; imagem: TBitmap);
 private

 end;

implementation

procedure TUtil.richTextSaveAsClick(Sender: TObject; SaveDialog: TSaveDialog; richText: TRichEdit);
begin
  { Display the File Save dialog to save the file. }
  { Set Modified to False since we just saved.     }
  SaveDialog.Title := 'Salvar Como';
  if SaveDialog.Execute then
  begin
    richText.Lines.SaveToFile(SaveDialog.FileName);
    richText.Modified := False;
  end;
end;

procedure TUtil.FormImagePaint(Sender: TObject; formulario:TForm; imagem: TBitmap);
var x, y: Integer;
begin
  y := 0;
  while y < formulario.Height do
  begin
    x := 0;
    while x < formulario.Width do
    begin
      formulario.Canvas.Draw(x, y, imagem);
      x := x + imagem.Width;
    end;
    y := y + imagem.Height;
  end;
end;

procedure TUtil.memoFileSaveAsClick(Sender: TObject; SaveDialog: TSaveDialog; memo: TMemo);
begin
  { Display the File Save dialog to save the file. }
  { Set Modified to False since we just saved.     }
  SaveDialog.Title := 'Salvar Como';
  if SaveDialog.Execute then
  begin
    memo.Lines.SaveToFile(SaveDialog.FileName);
    memo.Modified := False;
  end;
end;

function TUtil.printerIsOnLine() : Boolean;
Const
 PrnStInt : Byte = $17;
   StRq : Byte = $02;
   PrnNum : Word = 0; { 0 para LPT1, 1 para LPT2, etc. }
Var
  nResult : byte;
Begin (* PrinterOnLine*)
  Asm
    mov ah,StRq;
    mov dx,PrnNum;
    Int $17;
    mov nResult,ah;
  end;
  printerIsOnLine := (nResult and $80) = $80;
End;

end.
