unit Ututorial;

interface

uses
  Windows, Messages, SysUtils, Classes, Graphics, Controls, Forms, Dialogs,
  HTMLLite, fcTreeView, ComCtrls, StdCtrls, ExtCtrls, UManipuladocs,
  ImgList, Buttons;

type
  TF_Tutorial = class(TForm)
    html: ThtmlLite;
    StatusBar1: TStatusBar;
    Panel1: TPanel;
    Tree: TTreeView;
    ImageList1: TImageList;
    SpeedButton1: TSpeedButton;
    procedure FormCreate(Sender: TObject);
    procedure TreeChange(Sender: TObject; Node: TTreeNode);
    procedure SpeedButton1Click(Sender: TObject);
    procedure FormClose(Sender: TObject; var Action: TCloseAction);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  F_Tutorial: TF_Tutorial;
  Tutorial:TTutorial; //em UManipuladocs;
  localfisico:string;

implementation
uses UPrinc;
{$R *.DFM}

procedure TF_Tutorial.FormCreate(Sender: TObject);
begin
     localfisico:=ExtractFilePath(Application.Exename)+'\docs\';
     Tutorial:=TTutorial.Create;
     Tutorial.PassaValores('grafo.html',localfisico);
     html.LoadFromFile(Tutorial.GetArquivo);
     statusbar1.Panels[0].Text:='Tutorial de Grafos';
end;

procedure TF_Tutorial.TreeChange(Sender: TObject; Node: TTreeNode);
begin
     if node.Text='1 - O que s�o Grafos?' then
        Tutorial.PassaValores('grafo.html',localfisico);
     if node.Text='2 - Aplica��es de Grafos' then
        Tutorial.PassaValores('Aplicacoes.html',localfisico);
     if node.Text='3 - Grafos N�o Orientados' then
        Tutorial.PassaValores('grafosNO.html',localfisico);
     if node.Text='4 - Grafos Orientados' then
        Tutorial.PassaValores('grafosO.html',localfisico);
     if node.Text='5.1 - Defini��o' then
        Tutorial.PassaValores('setrepresen.html',localfisico);
    // if node.Text='Lista de Adjac�ncias' then
     //   Tutorial.PassaValores('setrepresen.html',localfisico);
     if node.Text='5.3 - Matriz de Adjac�ncias' then
        Tutorial.PassaValores('matrizesAD.html',localfisico);
     if node.Text='6.1 - Defini��o do Algorito' then
        Tutorial.PassaValores('algoritmoP1.html',localfisico);
     if node.Text='6.2 - M�todo' then
        Tutorial.PassaValores('algoritmoP2.html',localfisico);
     if node.Text='6.3 - Tabela de Adjac�ncia' then
        Tutorial.PassaValores('algoritmoP3.html',localfisico);
     if node.Text='6.4 - O algoritmo, Verifica��o e prova' then
        Tutorial.PassaValores('algoritmoP4.html',localfisico);

     html.LoadFromFile(Tutorial.GetArquivo);
     self.StatusBar1.Panels[0].Text:=node.Text;
end;

procedure TF_Tutorial.SpeedButton1Click(Sender: TObject);
begin
     self.Close;
end;

procedure TF_Tutorial.FormClose(Sender: TObject; var Action: TCloseAction);
begin
     F_Princ.FecharTutorial1.Enabled:= false;
     Action:=caFree;
end;

end.
