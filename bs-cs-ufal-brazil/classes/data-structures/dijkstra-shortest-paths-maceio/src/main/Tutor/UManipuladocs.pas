unit UManipuladocs;

interface
Type
    TTutorial = Class
       Documento:String;
       Pasta:String;
       Function GetDoc:String;
       Function GetPasta:string;
       Function GetArquivo:String;
       Procedure PassaValores(Doc,Pasta:String);
    end;

implementation

Function TTutorial.GetDoc:String;
begin
     GetDoc:=Self.Documento;
end;

Function TTutorial.GetPasta:string;
begin
     GetPasta:=Self.Pasta;
end;

Procedure TTutorial.PassaValores(Doc,Pasta:string);
begin
     Self.Documento:=Doc;
     Self.Pasta:=Pasta;
end;

Function TTutorial.GetArquivo:String;
begin
     GetArquivo:=Self.Pasta+Self.Documento;
end;

end.
 