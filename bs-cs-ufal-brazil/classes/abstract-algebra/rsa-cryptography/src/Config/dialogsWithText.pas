unit dialogsWithText;

interface
uses Dialogs,StdCtrls,ComCtrls;

    procedure FileSaveClick(Sender: TObject; SaveDialog: TSaveDialog; richText: TRichEdit); overload;
    procedure FileOpenClick(Sender: TObject; OpenDialog: TOpenDialog; richText: TRichEdit); overload;
    procedure FileSaveAsClick(Sender: TObject; SaveDialog: TSaveDialog; richText: TRichEdit); overload;
    procedure FileSaveClick(Sender: TObject; SaveDialog: TSaveDialog; memo: TMemo); overload;
    procedure FileOpenClick(Sender: TObject; OpenDialog: TOpenDialog; memo: TMemo); overload;
    procedure FileSaveAsClick(Sender: TObject; SaveDialog: TSaveDialog; memo: TMemo); overload;

//    procedure FileNewClick(Sender: TObject);



implementation

procedure FileOpenClick(Sender: TObject; OpenDialog: TOpenDialog; richText: TRichEdit);
var
  Res : Integer;
begin
  { Open a file. First check to see if the current file needs }
  { to be saved. Same logic as in FileNewClick above. }
  if richText.Modified then begin
    Res := Application.MessageBox(
    'THE CURRENT FILE HAS CHANGED. SAVE CHANGES?',
      'ScratchPad Message', MB_YESNOCANCEL);
    if Res = IDYES then
      FileSaveClick(Sender);
    if Res = IDCANCEL then
      Exit;
  end;
  { Execute the File Open dialog. If OK was pressed then }
  { open the file using the LoadFromFile method. First   }
  { clear the FileName property. }

  OpenDialog.FileName := '';
  if OpenDialog.Execute then
  begin
    if richText.Lines.Count > 0 then
      richText.Clear;
    richText.Lines.LoadFromFile(OpenDialog.FileName);
    SaveDialog.FileName := OpenDialog.FileName;
  end;
end;

procedure FileSaveClick(Sender: TObject);
begin
  { If a filename has already been provided then there is }
  { no need to bring up the File Save dialog. Just save the }
  { file using SaveToFile. }

  if SaveDialog.FileName <> '' then
  begin
    Log.Lines.SaveToFile(SaveDialog.FileName);
    { Set Modified to False since we've just saved. }
    Log.Modified := False;
  { If no filename was set then do a SaveAs. }
  end else FileSaveAsClick(Sender);
end;

procedure FileSaveAsClick(Sender: TObject);
begin
  { Display the File Save dialog to save the file. }
  { Set Modified to False since we just saved.     }
  SaveDialog.Title := 'Salvar Como';
  if SaveDialog.Execute then
  begin
    Log.Lines.SaveToFile(SaveDialog.FileName);
    Log.Modified := False;
  end;
end;

end.
