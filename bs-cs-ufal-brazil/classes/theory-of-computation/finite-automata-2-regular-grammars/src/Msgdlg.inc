{$ifdef ver100}
ButtonCaptions : array [0..10] of string = (
{$else}
ButtonCaptions : array [0..8] of string = (
{$endif}
  '&Sim',
  '&Não',
  '&Ok',
  '&Cancelar',
  '&Abortar',
  '&Retrair',
  '&Ignorar',
  '&Todas',
{$ifdef ver100}
  'Não para To&dos',
  'Sim para &Todos',
{$endif}
  '&Ajuda'
);

DialogTitles : array [0..3] of string = (
  'Advertência',
  'Erro',
  'Informação',
  'Confirmação'
);

