{$ifdef ver100}
ButtonCaptions : array [0..10] of string = (
{$else}
ButtonCaptions : array [0..8] of string = (
{$endif}
  '&Sim',
  '&N�o',
  '&Ok',
  '&Cancelar',
  '&Abortar',
  '&Retrair',
  '&Ignorar',
  '&Todas',
{$ifdef ver100}
  'N�o para To&dos',
  'Sim para &Todos',
{$endif}
  '&Ajuda'
);

DialogTitles : array [0..3] of string = (
  'Advert�ncia',
  'Erro',
  'Informa��o',
  'Confirma��o'
);

