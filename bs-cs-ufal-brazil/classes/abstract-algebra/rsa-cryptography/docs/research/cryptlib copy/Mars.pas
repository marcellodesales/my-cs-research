unit Mars;
{
This is the closest Pascal rendition of the MARS block cipher I can get.
Since Delphi 3 lacks true unsigned 32bit integers, I don't know how
well it truly works. The cipher was made at IBM by

Carolynn Burwick,        Don Coppersmith,     Edward D’Avignon,
                           (he ^ helped make DES)
Rosario Gennaro,         Shai Halevi,         Charanjit Jutla,
Stephen M. Matyas Jr.,   Luke O’Connor,       Mohammad Peyravian,
                David Safford,       Nevenko Zunic
}

interface

uses
  Windows, Messages, SysUtils, Classes,
  Graphics, Controls, Forms, Dialogs;

type
  keyarray = array[0..7] of integer;//key is variable, up to 256-bits
  array40 = array[0..39] of integer;
  array4 = array[0..3] of integer;
  array3 = array[0..2] of integer;
  chararray = array[0..31] of char;

  TMars = class(TComponent)
  private
     ikey : string;
     IFile : string; {input file}
     OFile : string; {output file}
     function expandkey: array40;
     function e(a,key1,key2 : integer): array3;
     {encrypt stages}
     function encryptstage3(d:array4; k : array40): array4;
     function encryptstage2(d:array4;k : array40): array4;
     function encryptstage1(d:array4; k : array40): array4;
     {decrypt stages}
     function decryptstage3(d:array4; k : array40): array4;
     function decryptstage2(d:array4;k : array40): array4;
     function decryptstage1(d:array4; k : array40): array4;
     {encrypt\decrypt block functions}
     function encryptblock(d:array4;k:array40): array4;
     function decryptblock(d:array4;k:array40): array4;
     procedure rotrword(d : array4); //move the words right 1
  public
     function testcipher : string;
     property Key : string read ikey write ikey;
     property InputFile : string read IFile write IFile;
     property OutputFile : string read Ofile write Ofile;
     procedure EncipherFile;
     procedure DecipherFile;
  end;

procedure Register;

implementation

const
{this is big total s-box}
Sbox : array[0..511] of longint = (
$09d0c479, $28c8ffe0, $84aa6c39, $9dad7287, $7dff9be3, $d4268361,
$c96da1d4, $7974cc93, $85d0582e, $2a4b5705, $1ca16a62, $c3bd279d,
$0f1f25e5, $5160372f, $c695c1fb, $4d7ff1e4, $ae5f6bf4, $0d72ee46,
$ff23de8a, $b1cf8e83, $f14902e2, $3e981e42, $8bf53eb6, $7f4bf8ac,
$83631f83, $25970205, $76afe784, $3a7931d4, $4f846450, $5c64c3f6,
$210a5f18, $c6986a26, $28f4e826, $3a60a81c, $d340a664, $7ea820c4,
$526687c5, $7eddd12b, $32a11d1d, $9c9ef086, $80f6e831, $ab6f04ad,
$56fb9b53, $8b2e095c, $b68556ae, $d2250b0d, $294a7721, $e21fb253,
$ae136749, $e82aae86, $93365104, $99404a66, $78a784dc, $b69ba84b,
$04046793, $23db5c1e, $46cae1d6, $2fe28134, $5a223942, $1863cd5b,
$c190c6e3, $07dfb846, $6eb88816, $2d0dcc4a, $a4ccae59, $3798670d,
$cbfa9493, $4f481d45, $eafc8ca8, $db1129d6, $b0449e20, $0f5407fb,
$6167d9a8, $d1f45763, $4daa96c3, $3bec5958, $ababa014, $b6ccd201,
$38d6279f, $02682215, $8f376cd5, $092c237e, $bfc56593, $32889d2c,
$854b3e95, $05bb9b43, $7dcd5dcd, $a02e926c, $fae527e5, $36a1c330,
$3412e1ae, $f257f462, $3c4f1d71, $30a2e809, $68e5f551, $9c61ba44,
$5ded0ab8, $75ce09c8, $9654f93e, $698c0cca, $243cb3e4, $2b062b97,
$0f3b8d9e, $00e050df, $fc5d6166, $e35f9288, $c079550d, $0591aee8,
$8e531e74, $75fe3578, $2f6d829a, $f60b21ae, $95e8eb8d, $6699486b,
$901d7d9b, $fd6d6e31, $1090acef, $e0670dd8, $dab2e692, $cd6d4365,
$e5393514, $3af345f0, $6241fc4d, $460da3a3, $7bcf3729, $8bf1d1e0,
$14aac070, $1587ed55, $3afd7d3e, $d2f29e01, $29a9d1f6, $efb10c53,
$cf3b870f, $b414935c, $664465ed, $024acac7, $59a744c1, $1d2936a7,
$dc580aa6, $cf574ca8, $040a7a10, $6cd81807, $8a98be4c, $accea063,
$c33e92b5, $d1e0e03d, $b322517e, $2092bd13, $386b2c4a, $52e8dd58,
$58656dfb, $50820371, $41811896, $e337ef7e, $d39fb119, $c97f0df6,
$68fea01b, $a150a6e5, $55258962, $eb6ff41b, $d7c9cd7a, $a619cd9e,
$bcf09576, $2672c073, $f003fb3c, $4ab7a50b, $1484126a, $487ba9b1,
$a64fc9c6, $f6957d49, $38b06a75, $dd805fcd, $63d094cf, $f51c999e,
$1aa4d343, $b8495294, $ce9f8e99, $bffcd770, $c7c275cc, $378453a7,
$7b21be33, $397f41bd, $4e94d131, $92cc1f98, $5915ea51, $99f861b7,
$c9980a88, $1d74fd5f, $b0a495f8, $614deed0, $b5778eea, $5941792d,
$fa90c1f8, $33f824b4, $c4965372, $3ff6d550, $aca5fec0, $8630e964,
$5b3fbbd6, $7da26a48, $b203231a, $04297514, $2d639306, $2eb13149,
$16a45272, $532459a0, $8e5f4872, $f966c7d9, $07128dc0, $0d44db62,
$afc8d52d, $06316131, $d838e7ce, $1bc41d00, $3a2e8c0f, $ea83837e,
$b984737d, $13ba4891, $c4f8b949, $a6d6acb3, $a215cdce, $8359838b,
$6bd1aa31, $f579dd52, $21b93f93, $f5176781, $187dfdde, $e94aeb76,
$2b38fd54, $431de1da, $ab394825, $9ad3048f, $dfea32aa, $659473e3,
$623f7863, $f3346c59, $ab3ab685, $3346a90b, $6b56443e, $c6de01f8,
$8d421fc0, $9b0ed10c, $88f1a1e9, $54c1f029, $7dead57b, $8d7ba426,
$4cf5178a, $551a7cca, $1a9a5f08, $fcd651b9, $25605182, $e11fc6c3,
$b6fd9676, $337b3027, $b7c8eb14, $9e5fd030,

$6b57e354, $ad913cf7, $7e16688d, $58872a69, $2c2fc7df, $e389ccc6,
$30738df1, $0824a734, $e1797a8b, $a4a8d57b, $5b5d193b, $c8a8309b,
$73f9a978, $73398d32, $0f59573e, $e9df2b03, $e8a5b6c8, $848d0704,
$98df93c2, $720a1dc3, $684f259a, $943ba848, $a6370152, $863b5ea3,
$d17b978b, $6d9b58ef, $0a700dd4, $a73d36bf, $8e6a0829, $8695bc14,
$e35b3447, $933ac568, $8894b022, $2f511c27, $ddfbcc3c, $006662b6,
$117c83fe, $4e12b414, $c2bca766, $3a2fec10, $f4562420, $55792e2a,
$46f5d857, $ceda25ce, $c3601d3b, $6c00ab46, $efac9c28, $b3c35047,
$611dfee3, $257c3207, $fdd58482, $3b14d84f, $23becb64, $a075f3a3,
$088f8ead, $07adf158, $7796943c, $facabf3d, $c09730cd, $f7679969,
$da44e9ed, $2c854c12, $35935fa3, $2f057d9f, $690624f8, $1cb0bafd,
$7b0dbdc6, $810f23bb, $fa929a1a, $6d969a17, $6742979b, $74ac7d05,
$010e65c4, $86a3d963, $f907b5a0, $d0042bd3, $158d7d03, $287a8255,
$bba8366f, $096edc33, $21916a7b, $77b56b86, $951622f9, $a6c5e650,
$8cea17d1, $cd8c62bc, $a3d63433, $358a68fd, $0f9b9d3c, $d6aa295b,
$fe33384a, $c000738e, $cd67eb2f, $e2eb6dc2, $97338b02, $06c9f246,
$419cf1ad, $2b83c045, $3723f18a, $cb5b3089, $160bead7, $5d494656,
$35f8a74b, $1e4e6c9e, $000399bd, $67466880, $b4174831, $acf423b2,
$ca815ab3, $5a6395e7, $302a67c5, $8bdb446b, $108f8fa4, $10223eda,
$92b8b48b, $7f38d0ee, $ab2701d4, $0262d415, $af224a30, $b3d88aba,
$f8b2c3af, $daf7ef70, $cc97d3b7, $e9614b6c, $2baebff4, $70f687cf,
$386c9156, $ce092ee5, $01e87da6, $6ce91e6a, $bb7bcc84, $c7922c20,
$9d3b71fd, $060e41c6, $d7590f15, $4e03bb47, $183c198e, $63eeb240,
$2ddbf49a, $6d5cba54, $923750af, $f9e14236, $7838162b, $59726c72,
$81b66760, $bb2926c1, $48a0ce0d, $a6c0496d, $ad43507b, $718d496a,
$9df057af, $44b1bde6, $054356dc, $de7ced35, $d51a138b, $62088cc9,
$35830311, $c96efca2, $686f86ec, $8e77cb68, $63e1d6b8, $c80f9778,
$79c491fd, $1b4c67f2, $72698d7d, $5e368c31, $f7d95e2e, $a1d3493f,
$dcd9433e, $896f1552, $4bc4ca7a, $a6d1baf4, $a5a96dcc, $0bef8b46,
$a169fda7, $74df40b7, $4e208804, $9a756607, $038e87c8, $20211e44,
$8b7ad4bf, $c6403f35, $1848e36d, $80bdb038, $1e62891c, $643d2107,
$bf04d6f8, $21092c8c, $f644f389, $0778404e, $7b78adb8, $a2c52d53,
$42157abe, $a2253e2e, $7bf3f4ae, $80f594f9, $953194e7, $77eb92ed,
$b3816930, $da8d9336, $bf447469, $f26d9483, $ee6faed5, $71371235,
$de425f73, $b4e59f43, $7dbe2d4e, $2d37b185, $49dc9a63, $98c39d98,
$1301c9a2, $389b1bbf, $0c18588d, $a421c1ba, $7aa3865c, $71e08558,
$3c5cfcaa, $7d239ca4, $0297d9dd, $d7dc2830, $4b37802b, $7428ab54,
$aeee0347, $4b3fbb85, $692f2f08, $134e578e, $36d9e0bf, $ae8b5fcf,
$edb93ecf, $2b27248e, $170eb1ef, $7dc57fd6, $1e760f16, $b1136601,
$864e1b9b, $d7ea7319, $3ab871bd, $cfa4d76f, $e31bd782, $0dbeb469,
$abb96061, $5370f85d, $ffb07e37, $da30d0fb, $ebc977b6, $0b98b40f,
$3a4d0fe6, $df4fc26b, $159cf22a, $c298d6e2, $2b78ef6a, $61a94ac0,
$ab561187, $14eea0f0, $df0d4164, $19af70ee);

//split the above s-box into two halves
//1st half
S0 : array[0..255] of integer = (
$09d0c479, $28c8ffe0, $84aa6c39, $9dad7287, $7dff9be3, $d4268361,
$c96da1d4, $7974cc93, $85d0582e, $2a4b5705, $1ca16a62, $c3bd279d,
$0f1f25e5, $5160372f, $c695c1fb, $4d7ff1e4, $ae5f6bf4, $0d72ee46,
$ff23de8a, $b1cf8e83, $f14902e2, $3e981e42, $8bf53eb6, $7f4bf8ac,
$83631f83, $25970205, $76afe784, $3a7931d4, $4f846450, $5c64c3f6,
$210a5f18, $c6986a26, $28f4e826, $3a60a81c, $d340a664, $7ea820c4,
$526687c5, $7eddd12b, $32a11d1d, $9c9ef086, $80f6e831, $ab6f04ad,
$56fb9b53, $8b2e095c, $b68556ae, $d2250b0d, $294a7721, $e21fb253,
$ae136749, $e82aae86, $93365104, $99404a66, $78a784dc, $b69ba84b,
$04046793, $23db5c1e, $46cae1d6, $2fe28134, $5a223942, $1863cd5b,
$c190c6e3, $07dfb846, $6eb88816, $2d0dcc4a, $a4ccae59, $3798670d,
$cbfa9493, $4f481d45, $eafc8ca8, $db1129d6, $b0449e20, $0f5407fb,
$6167d9a8, $d1f45763, $4daa96c3, $3bec5958, $ababa014, $b6ccd201,
$38d6279f, $02682215, $8f376cd5, $092c237e, $bfc56593, $32889d2c,
$854b3e95, $05bb9b43, $7dcd5dcd, $a02e926c, $fae527e5, $36a1c330,
$3412e1ae, $f257f462, $3c4f1d71, $30a2e809, $68e5f551, $9c61ba44,
$5ded0ab8, $75ce09c8, $9654f93e, $698c0cca, $243cb3e4, $2b062b97,
$0f3b8d9e, $00e050df, $fc5d6166, $e35f9288, $c079550d, $0591aee8,
$8e531e74, $75fe3578, $2f6d829a, $f60b21ae, $95e8eb8d, $6699486b,
$901d7d9b, $fd6d6e31, $1090acef, $e0670dd8, $dab2e692, $cd6d4365,
$e5393514, $3af345f0, $6241fc4d, $460da3a3, $7bcf3729, $8bf1d1e0,
$14aac070, $1587ed55, $3afd7d3e, $d2f29e01, $29a9d1f6, $efb10c53,
$cf3b870f, $b414935c, $664465ed, $024acac7, $59a744c1, $1d2936a7,
$dc580aa6, $cf574ca8, $040a7a10, $6cd81807, $8a98be4c, $accea063,
$c33e92b5, $d1e0e03d, $b322517e, $2092bd13, $386b2c4a, $52e8dd58,
$58656dfb, $50820371, $41811896, $e337ef7e, $d39fb119, $c97f0df6,
$68fea01b, $a150a6e5, $55258962, $eb6ff41b, $d7c9cd7a, $a619cd9e,
$bcf09576, $2672c073, $f003fb3c, $4ab7a50b, $1484126a, $487ba9b1,
$a64fc9c6, $f6957d49, $38b06a75, $dd805fcd, $63d094cf, $f51c999e,
$1aa4d343, $b8495294, $ce9f8e99, $bffcd770, $c7c275cc, $378453a7,
$7b21be33, $397f41bd, $4e94d131, $92cc1f98, $5915ea51, $99f861b7,
$c9980a88, $1d74fd5f, $b0a495f8, $614deed0, $b5778eea, $5941792d,
$fa90c1f8, $33f824b4, $c4965372, $3ff6d550, $4ca5fec0, $8630e964,
$5b3fbbd6, $7da26a48, $b203231a, $04297514, $2d639306, $2eb13149,
$16a45272, $532459a0, $8e5f4872, $f966c7d9, $07128dc0, $0d44db62,
$afc8d52d, $06316131, $d838e7ce, $1bc41d00, $3a2e8c0f, $ea83837e,
$b984737d, $13ba4891, $c4f8b949, $a6d6acb3, $a215cdce, $8359838b,
$6bd1aa31, $f579dd52, $21b93f93, $f5176781, $187dfdde, $e94aeb76,
$2b38fd54, $431de1da, $ab394825, $9ad3048f, $dfea32aa, $659473e3,
$623f7863, $f3346c59, $ab3ab685, $3346a90b, $6b56443e, $c6de01f8,
$8d421fc0, $9b0ed10c, $88f1a1e9, $54c1f029, $7dead57b, $8d7ba426,
$4cf5178a, $551a7cca, $1a9a5f08, $fcd651b9, $25605182, $e11fc6c3,
$b6fd9676, $337b3027, $b7c8eb14, $9e5fd030);
//2nd half
S1 : array[0..255] of integer =  (
$6b57e354, $ad913cf7, $7e16688d, $58872a69, $2c2fc7df, $e389ccc6,
$30738df1, $0824a734, $e1797a8b, $a4a8d57b, $5b5d193b, $c8a8309b,
$73f9a978, $73398d32, $0f59573e, $e9df2b03, $e8a5b6c8, $848d0704,
$98df93c2, $720a1dc3, $684f259a, $943ba848, $a6370152, $863b5ea3,
$d17b978b, $6d9b58ef, $0a700dd4, $a73d36bf, $8e6a0829, $8695bc14,
$e35b3447, $933ac568, $8894b022, $2f511c27, $ddfbcc3c, $006662b6,
$117c83fe, $4e12b414, $c2bca766, $3a2fec10, $f4562420, $55792e2a,
$46f5d857, $ceda25ce, $c3601d3b, $6c00ab46, $efac9c28, $b3c35047,
$611dfee3, $257c3207, $fdd58482, $3b14d84f, $23becb64, $a075f3a3,
$088f8ead, $07adf158, $7796943c, $facabf3d, $c09730cd, $f7679969,
$da44e9ed, $2c854c12, $35935fa3, $2f057d9f, $690624f8, $1cb0bafd,
$7b0dbdc6, $810f23bb, $fa929a1a, $6d969a17, $6742979b, $74ac7d05,
$010e65c4, $86a3d963, $f907b5a0, $d0042bd3, $158d7d03, $287a8255,
$bba8366f, $096edc33, $21916a7b, $77b56b86, $951622f9, $a6c5e650,
$8cea17d1, $cd8c62bc, $a3d63433, $358a68fd, $0f9b9d3c, $d6aa295b,
$fe33384a, $c000738e, $cd67eb2f, $e2eb6dc2, $97338b02, $06c9f246,
$419cf1ad, $2b83c045, $3723f18a, $cb5b3089, $160bead7, $5d494656,
$35f8a74b, $1e4e6c9e, $000399bd, $67466880, $b4174831, $acf423b2,
$ca815ab3, $5a6395e7, $302a67c5, $8bdb446b, $108f8fa4, $10223eda,
$92b8b48b, $7f38d0ee, $ab2701d4, $0262d415, $af224a30, $b3d88aba,
$f8b2c3af, $daf7ef70, $cc97d3b7, $e9614b6c, $2baebff4, $70f687cf,
$386c9156, $ce092ee5, $01e87da6, $6ce91e6a, $bb7bcc84, $c7922c20,
$9d3b71fd, $060e41c6, $d7590f15, $4e03bb47, $183c198e, $63eeb240,
$2ddbf49a, $6d5cba54, $923750af, $f9e14236, $7838162b, $59726c72,
$81b66760, $bb2926c1, $48a0ce0d, $a6c0496d, $ad43507b, $718d496a,
$9df057af, $44b1bde6, $054356dc, $de7ced35, $d51a138b, $62088cc9,
$35830311, $c96efca2, $686f86ec, $8e77cb68, $63e1d6b8, $c80f9778,
$79c491fd, $1b4c67f2, $72698d7d, $5e368c31, $f7d95e2e, $a1d3493f,
$dcd9433e, $896f1552, $4bc4ca7a, $a6d1baf4, $a5a96dcc, $0bef8b46,
$a169fda7, $74df40b7, $4e208804, $9a756607, $038e87c8, $20211e44,
$8b7ad4bf, $c6403f35, $1848e36d, $80bdb038, $1e62891c, $643d2107,
$bf04d6f8, $21092c8c, $f644f389, $0778404e, $7b78adb8, $a2c52d53,
$42157abe, $a2253e2e, $7bf3f4ae, $80f594f9, $953194e7, $77eb92ed,
$b3816930, $da8d9336, $bf447469, $f26d9483, $ee6faed5, $71371235,
$de425f73, $b4e59f43, $7dbe2d4e, $2d37b185, $49dc9a63, $98c39d98,
$1301c9a2, $389b1bbf, $0c18588d, $a421c1ba, $7aa3865c, $71e08558,
$3c5cfcaa, $7d239ca4, $0297d9dd, $d7dc2830, $4b37802b, $7428ab54,
$aeee0347, $4b3fbb85, $692f2f08, $134e578e, $36d9e0bf, $ae8b5fcf,
$edb93ecf, $2b27248e, $170eb1ef, $7dc57fd6, $1e760f16, $b1136601,
$864e1b9b, $d7ea7319, $3ab871bd, $cfa4d76f, $e31bd782, $0dbeb469,
$abb96061, $5370f85d, $ffb07e37, $da30d0fb, $ebc977b6, $0b98b40f,
$3a4d0fe6, $df4fc26b, $159cf22a, $c298d6e2, $2b78ef6a, $61a94ac0,
$ab561187, $14eea0f0, $df0d4164, $19af70ee);

procedure Register;
begin
  RegisterComponents('Crypto', [TMars]);
end;

{*****************************************}
{*****************************************}

//fast rotations in assembler!!!!
Function ROTL(A: Longint; Amount: BYTE): Longint; Assembler;
asm
 mov cl, Amount
 rol eax, cl
end;

Function ROTR(A: Longint; Amount: BYTE): Longint; Assembler;
asm
 mov cl, Amount
 ror eax, cl
end;

{*****************************************}
{*****************************************}

procedure TMars.rotrword(d : array4); //move the words right 1
var
b : array4;
begin
b[0] := d[3]; b[1] := d[0]; b[2] := d[1]; b[3] := d[2];
d := b;
end;

{*****************************************}
{*****************************************}

function makebitmask(w : integer): chararray;
var
m : chararray;
i,j : integer;
begin
for i := 0 to 31 do
    begin
    j := w shl (i);
    j := j shr 31;
    if j = 1 then m[i] := '1'
    else m[i] := '0';
    end;
makebitmask := m;
end;

{*****************************************}
{*****************************************}

function modifybitmask(M : chararray): chararray;
var
j,k,z,i : integer;
begin
j := 0;
k := 0;

for i := 31 downto 0 do
    begin
    if (M[i] = '1') then
       begin
       if M[i-1] = '0' then
          begin
          if j >= 10 then
             begin
             for z := i to (i+j) do
                 begin
                 if (M[z] = '1') then
                    M[z] := '1';
                 end;
             M[i-j+1] := '0';
             M[i] := '0';
             end;
          j := 0;
          end;
       inc(j);
       end
    ELSE
       begin
       if M[i-1] = '1' then
          begin
          if k >= 10 then
             begin
             for z := i to (i+k) do
                 begin
                 if (M[z] = '0') then
                    M[z] := '1';
                 end;
             M[i-k+1] := '0';
             M[i] := '0';
             end;
          k := 0;
          end;
       inc(k);
       end;
    end;
modifybitmask := M;
end;

{*****************************************}
{*****************************************}

function undobitmask(M : chararray): integer;
var
i,j : integer;
a : char;
c : array[0..31] of integer;
begin
for i := 0 to (31 div 2) do
    begin
    a := M[i]; j := 31-i;
    M[i] := M[j];
    M[j] := a;
    end;


j := 31;
for i := 0 to 31 do
    begin
    if M[i] = '1' then
       c[j] := 1
    else
        c[j] := 0;

    c[j] := c[j] shl i;
    dec(j);
    end;

j := c[0] or c[1] or c[2] or c[3] or
     c[4] or c[5] or c[6] or c[7] or
     c[8] or c[9] or c[10] or c[11] or
     c[12] or c[13] or c[14] or c[15] or
     c[16] or c[17] or c[18] or c[19] or
     c[20] or c[21] or c[22] or c[23] or
     c[24] or c[25] or c[26] or c[27] or
     c[28] or c[29] or c[30] or c[31];
undobitmask := j;
end;

{*****************************************}
{*****************************************}

function TMars.expandkey: array40;
const
B : array[0..3] of integer= ($a4a8d57b,
$5b5d193b, $c8a8309b, $73f9a978);
n = 8;
var
p,r,w,H,j,i : integer;
k : array40;{ K[ ]is the expanded key array, consisting of 40 words}
T : array[-7..39] of integer;{ T[ ]is a temporary array, consisting of 47 words, T[-7],T[-6],...,T[39]}
M : chararray;
Uk : keyarray; {key in a arrey of words}
strn : string[32];
begin
{the user can pass any key length up to 256-bits}
strn := copy(ikey,1,length(ikey));;
for i := length(ikey) to 32 do
    strn[i] := #0;{Pad the key with nulls}
uk[0] := 0;uk[4] := 0;
uk[1] := 0;uk[5] := 0;
uk[2] := 0;uk[6] := 0;
uk[3] := 0;uk[7] := 0;
move(strn,Uk,sizeof(uk));

{Now it is time to get the subkeys}
for i := -7 to -1 do
    T[i] := Sbox[i+7];

for i := 0 to 38 do{pseudo-random function for sub-key generation}
    T[i] := (rotl(T[i-7]xor T[i-2],3))xor uk[i mod n] xor i;
T[39] := n;

for j := 1 to 7 do
    begin
    for i := 1 to 39 do
	T[i] := rotl((T[i] + Sbox[t[i-1] and 511]),9);
    T[0] := T[0] - Sbox[t[39] and 511];
    end;

for i := 0 to 39 do
    k[7*i mod 40] := T[i];

i := 5;
while i <= 35 do
        begin
        j := k[i] and 3;
	w := k[i] or 4;
        {These 3 up coming routines I made up because the refernce
        material on the Mars cipher I used to make this component told
        you what you needed to do, but not how to do it. So these are
        problably the wrong way to do it.}
        M := MAKEBITMASK(w);
        M := modifybitmask(M);
        H := undobitmask(M);

	r := k[i+3] and 31;
	p := rotl(b[j],r);

	k[i] := w xor (p and H);
	inc(i,2);
	end;
expandkey := k;
end;

{*****************************************}
{*****************************************}

function TMars.e(a,key1,key2 : integer): array3;
var
i,r : integer;
lmr : array3;
begin
lmr[1] := key1 + a;
lmr[2] := (rotl(a,13) * key2);
i := lmr[1] and 511;
lmr[0] := Sbox[i];
lmr[2] := rotl(lmr[2],5);
r := lmr[2] and 31;
lmr[1] := rotl(lmr[0],r);
lmr[0] := lmr[0] xor lmr[2];
lmr[2] := rotl(lmr[2],5);
lmr[0] := lmr[0] xor lmr[2];
r := lmr[2] and 31;
lmr[0] := rotl(lmr[0],r);
e := lmr;
end;

{*****************************************}
{*****************************************}

function TMars.encryptstage1(d:array4; k : array40): array4;
var
i : integer;
abyte : array[0..3] of byte absolute d[0];
begin
{this is a mixing process that doesn't use the subkeys}
d[0] := d[0] + k[0];{add subkeys}
d[1] := d[1] + k[1];
d[2] := d[2] + k[2];
d[3] := d[3] + k[3];

for i := 0 to 7 do
	begin
	{s-box look-ups}
	d[1] := d[1] xor s0[abyte[0]];
	d[1] := d[1] + s1[abyte[1]];
	d[2] := d[2] + s0[abyte[2]];
	d[3] := d[3] xor s1[abyte[3]];
	{rotr source word d[0]}
	d[0] := rotr(d[0], 24);
	{mix some more}
	if (i = 0) or (i = 4) then
		d[0] := d[0] + d[3];
	if (i = 1) or (i = 5) then
		d[0] := d[0] + d[1];
	rotrword(d); {move the words right 1}
	end;
encryptstage1 := d;
end;

{*****************************************}
{*****************************************}

function TMars.encryptstage2(d:array4;k : array40): array4;
var
mout : array3;
i : integer;
begin
{this procedure provides the main strength of the alogorithm}
for i := 0 to 15 do
	begin
	mout := e(d[0],K[2*i+4],K[2*i+5]);
	d[0] := rotl(d[0],13);
	d[2] := d[2] + mout[1];
	if i < 8 then
		begin
		d[1] := d[1] + mout[0];
		d[3] := d[3] xor mout[2];
		end
	else
		begin
		d[3] := d[3] + mout[0];
		d[1] := d[1] xor mout[2];
		end;
	rotrword(d); {move the words right 1}
	end;
encryptstage2 := d;
end;

{*****************************************}
{*****************************************}

function TMars.encryptstage3(d:array4; k : array40): array4;
var
i : integer;
abyte : array[0..3] of byte absolute d[0];
begin
{this is a mixing process that doesn't use the subkeys again and is the
somewhat opposite of the first mixing function but has some differences}
for i := 0 to 7 do
	begin
	if (i = 2) or (i = 6) then
		d[0] := d[0] - d[3];
	if (i = 3) or (i = 7) then
		d[0] := d[0] - d[1];
        {s-box look-ups}
	d[1] := d[1] xor s1[abyte[0]];
	d[2] := d[2] - s0[abyte[3]];
	d[3] := d[3] - s1[abyte[2]];
	d[3] := d[3] xor s0[abyte[1]];
        {rotl source word d[0]}
	d[0] := rotl(d[0], 24);
	rotrword(d);{ //move the words right 1}
	end;

d[0] := d[0] - k[36];{subtract subkeys}
d[1] := d[1] - k[37];
d[2] := d[2] - k[38];
d[3] := d[3] - k[39];
encryptstage3 := d;
end;

{*****************************************}
{*****************************************}

function TMars.decryptstage1(d:array4;k : array40): array4;
var
abyte : array[0..3] of byte absolute d[0];
i : integer;
begin
d[0] := d[0] + k[36];{add subkeys}
d[1] := d[1] + k[37];
d[2] := d[2] + k[38];
d[3] := d[3] + k[39];

for i := 7 downto 0 do
	begin
	rotrword(d); {move the words right 1}
	{rotl source word d[0]}
	d[0] := rotr(d[0], 24);
	{s-box look-ups}
	d[3] := d[3] xor s0[abyte[1]];
	d[3] := d[3] + s1[abyte[2]];
	d[2] := d[2] + s0[abyte[3]];
	d[1] := d[1] xor s1[abyte[0]];
	if (i = 2) or (i = 6) then
		d[0] := d[0] + d[3];
	if (i = 3) or (i = 7) then
		d[0] := d[0] + d[1];
	end;

decryptstage1 := d;
end;

{*****************************************}
{*****************************************}

function TMars.decryptstage2(d:array4;k : array40): array4;
var
mout : array3;
i : integer;
begin
for i := 15 downto 0 do
	begin
	rotrword(d); {move the words right 1}
	d[0] := rotr(d[0],13);
	mout := e(d[0],K[2*i+4],K[2*i+5]);
	d[2] := d[2] - mout[1];
	if i < 8 then
		begin
		d[1] := d[1] - mout[0];
		d[3] := d[3] xor mout[2];
		end
	else
		begin
		d[3] := d[3] - mout[0];
		d[1] := d[1] xor mout[2];
		end;
	end;
decryptstage2 := d;
end;

{*****************************************}
{*****************************************}

function TMars.decryptstage3(d:array4;k : array40): array4;
var
i : integer;
abyte : array[0..3] of byte absolute d[0];
begin
for i := 7 downto 0 do
	begin
	rotrword(d); {move the words right 1}
	{mix some more}
	if (i = 0) or (i = 4) then
		d[0] := d[0] - d[3];
	if (i = 1) or (i = 5) then
		d[0] := d[0] - d[1];
	{rotl source word d[0]}
	d[0] := rotl(d[0], 24);
	{s-box look-ups}
	d[3] := d[3] xor s1[abyte[3]];
	d[2] := d[2] - s0[abyte[2]];
	d[1] := d[1] - s1[abyte[1]];
	d[1] := d[1] xor s0[abyte[0]];
	end;

d[0] := d[0] - k[0];{add subkeys}
d[1] := d[1] - k[1];
d[2] := d[2] - k[2];
d[3] := d[3] - k[3];

decryptstage3 := d;
end;

function TMars.encryptblock(d:array4;k:array40): array4;
begin
d := encryptstage1(d,k);
d := encryptstage2(d,k);
encryptblock := encryptstage3(d,k);
end;

{******************}

function TMars.decryptblock(d:array4;k:array40): array4;
begin
d := decryptstage1(d,k);
d := decryptstage2(d,k);
decryptblock := decryptstage3(d,k);
end;

{*****************************************}
{*****************************************}

{This was my test of the cipher}

function TMars.testcipher: string;
var
arr : array4;
bigkey : array40;
strn : string[16];
begin
key := 'Í«‰Ô;mÙå%ŠÞ#ñƒŽÏ‘bIñ';
strn := 'Hello Mars Ciphe';
move(strn,arr,sizeof(strn));

bigkey := expandkey;
arr := encryptblock(arr,bigkey);
bigkey := expandkey;
arr := decryptblock(arr,bigkey);

strn := '';
move(arr,strn,sizeof(arr));
testcipher := strn+'r!';
end;


{*****************************************}
{*****************************************}

procedure TMars.EncipherFile;
var
i,numread,numwritten : integer;
k : array40;{Subkey array}
buff : array[0..4095] of char;
buff2 : array[0..1023] of integer;
a : array4;
inputf,outputf : file;
begin
assignfile(inputf,ifile);{These are the input and output files}
reset(inputf,1);

assignfile(outputf, ofile);
rewrite(Outputf,1);

{make the subkeys}
K := expandkey;
repeat
      blockread(inputf,Buff,sizeof(buff), numread);

      move(Buff,buff2,sizeof(buff2));

      i := 0;
      while i <= 1023 do
            begin
            {get the plaintext text}
            a[0] := buff2[i];
            a[1] := buff2[i+1];
            a[2] := buff2[i+2];
            a[3] := buff2[i+3];

            a := encryptblock(a,k);

            {return the encrypted values}
            buff2[i] := a[0];
            buff2[i+1] := a[1];
            buff2[i+2] := a[2];
            buff2[i+3] := a[3];
            inc(i,4);
            end;{while}

      move(Buff2,buff,sizeof(buff));

      blockwrite(outputf,Buff,numread, numwritten);
until (numread = 0) or (numwritten <> numread);
closefile(inputf);
closefile(outputf);
end;

{******************}

procedure TMars.DecipherFile;
var
i,numread,numwritten : integer;
k : array40;{Subkey array}
buff : array[0..4095] of char;
buff2 : array[0..1023] of integer;
a : array4;
inputf,outputf : file;
begin
assignfile(inputf,ifile);{These are the input and output files}
reset(inputf,1);

assignfile(outputf, ofile);
rewrite(Outputf,1);

{make the subkeys}
K := expandkey;
repeat
      blockread(inputf,Buff,sizeof(buff), numread);

      move(Buff,buff2,sizeof(buff));

      i := 0;
      while i <= 1023 do
            begin
            {get the plaintext text}
            a[0] := buff2[i];
            a[1] := buff2[i+1];
            a[2] := buff2[i+2];
            a[3] := buff2[i+3];

            a := decryptblock(a,k);

            {return the encrypted values}
            buff2[i] := a[0];
            buff2[i+1] := a[1];
            buff2[i+2] := a[2];
            buff2[i+3] := a[3];
            inc(i,4);
            end;{while}

      move(Buff2,buff,sizeof(buff2));

      blockwrite(outputf,Buff,numread, numwritten);
until (numread = 0) or (numwritten <> numread);
closefile(inputf);
closefile(outputf);
end;

end.
