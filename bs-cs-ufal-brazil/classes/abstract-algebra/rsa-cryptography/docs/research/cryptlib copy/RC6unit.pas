unit RC6unit;

interface

uses
  Windows, Messages, SysUtils, Classes,
  Graphics, Controls, Forms, Dialogs;

const
c = 8; //# of key (32bit) words (this is a 192bit key increase # of words for more security can be expandable up to 256 bits)
r = 20; //# of rounds (can be changed if desired)

type
  array1 = array[0..c-1] of integer;
  array2 = array[0..2*r+3] of integer;
  array3 = array[0..3] of integer;

  TRC6 = class(Tcomponent)
  private
    Ikey : string; {internal key of the component Key is 192-bits}
    IFile : string; {input file}
    OFile : string; {output file}
    function getfirstbits(a : integer; amount : byte): byte;
    function keyexpand: array2;
    procedure rotrword(d : array3); //move the words right 1
    procedure rotlword(d : array3); //move the words right 1
    function f(b : integer): integer;
    {Encrypt/decrypt block}
    function encryptblock(d : array3; S : array2): array3;
    function decryptblock(d : array3; S : array2): array3;
  public
    property Key : string read Ikey write Ikey; {property used to get the key}
    property InputFile : string read IFile write IFile;
    property OutputFile : string read Ofile write Ofile;
    function testcipher: string;
    procedure encipherfile;
    procedure decipherfile;
  end;

procedure Register;

implementation

const
twoto32 = 2147483647{*2};//if you have Delphi 4 erase the brackets around the {*2} and change all the integer types to unsigned integers
bits = 5;//number of bits that can get 32 combos

procedure Register;
begin
  RegisterComponents('Crypto', [TRC6]);
end;

{******************}
{******************}
{******************}
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

//if you don't run on a intel or compatible you can use these routines instead
{
Function ROTL(A: integer; Amount: BYTE): integer;
begin
ROTL := (a shL amount)or(a shr (32-amount));
end;

Function ROTR(A: integer; Amount: BYTE): integer;
begin
ROTR := (a shr amount)or(a shl (32-amount));
end;
}
function TRC6.getfirstbits(a : integer; amount : byte): byte;
var
b : byte;
begin
b := a shl amount;
getfirstbits := b shr amount;
end;

{******************}
{******************}
{******************}

function TRC6.keyexpand: array2;
const
P = $B7E1f4b2;
Q = $9E37376a;
var
i,A,B,j,v,k : integer;
S : array2;
strn : string[32];
key : array1;
begin
{the user can pass any key length up to 256-bits}
strn := copy(ikey,1,length(ikey));;
for i := length(ikey) to 32 do
    strn[i] := #0;{Pad the key with nulls}
key[0] := 0;key[4] := 0;
key[1] := 0;key[5] := 0;
key[2] := 0;key[6] := 0;
key[3] := 0;key[7] := 0;
move(strn,key,sizeof(key));

S[0] := P;

for i := 1 to (2*r+3) do
    S[i] := S[i-1] + Q;

A := 0;B := 0;i := 0;j := 0;

v := 3 * (2*r-4);
for k := 1 to v do
    begin
    S[i] := rotl(S[i]+A+B,3);
    A := S[i];

    Key[j] := rotl(key[j]+A+B,(A+B) and 31);
    i := (i+1)mod (2*r+4);
    j := (j+1)mod c;
    end;
keyexpand := S;
end;

{******************}
{******************}
{******************}

function TRC6.f(b : integer): integer;
begin
f := (b*(2*b+1))mod twoto32;
end;

{******************}

procedure TRC6.rotlword(d : array3); //move the words right 1
var
b : array3;
begin
b[0] := d[1]; b[1] := d[2]; b[2] := d[3]; b[3] := d[0];
d := b;
end;

{******************}

procedure TRC6.rotrword(d : array3); //move the words right 1
var
b : array3;
begin
b[0] := d[3]; b[1] := d[0]; b[2] := d[1]; b[3] := d[2];
d := b;
end;

{******************}
{******************}
{******************}

function TRC6.encryptblock(d : array3; S : array2): array3;
var
t,u,i : integer;
begin
d[1] := d[1] + S[0] mod twoto32;
d[3] := d[3] + S[1] mod twoto32;

for i := 1 to r do
    begin
    t := rotl(f(d[1]), bits);
    u := rotl(f(d[3]), bits);

    d[0] := (rotl(d[0] xor t,u and 31)+S[2*i]) mod twoto32;
    d[2] := (rotl(d[2] xor u,t and 31)+S[2*i+1]) mod twoto32;
    rotlword(d);
    end;
d[0] := (d[0] + S[2*r+2]) mod twoto32;
d[2] := (d[2] + S[2*r+3]) mod twoto32;

encryptblock := d;
end;

{******************}
{******************}
{******************}

function TRC6.decryptblock(d : array3; S : array2): array3;
var
t,u,i : integer;
begin
d[0] := (d[0] - S[2*r+2]) mod twoto32;
d[2] := (d[2] - S[2*r+3]) mod twoto32;

for i := r downto 1 do
    begin
    rotrword(d);
    t := rotl(f(d[1]), bits);
    u := rotl(f(d[3]), bits);

    d[0] := (rotr(d[0]-S[2*i],u and 31) xor t) mod twoto32;
    d[2] := (rotr(d[2]-S[2*i+1],t and 31) xor u) mod twoto32;
    end;
d[1] := (d[1] - S[0]) mod twoto32;
d[3] := (d[3] - S[1]) mod twoto32;

decryptblock := d;
end;

{******************}
{******************}
{******************}

function TRC6.testcipher: string;
var
arr : array3;
strn : string[16];
S : array2;
begin
key := 'Í«‰Ô;mÙå%ŠÞ#ñƒŽÏ‘bIñ';
strn := 'Hello RC6 Cipher';
move(strn,arr,sizeof(strn));

S := keyexpand;
arr := encryptblock(arr, S);
S := keyexpand;
arr := decryptblock(arr, S);

strn := '';
move(arr,strn,sizeof(arr));
testcipher := strn+'!';
end;

{******************}
{******************}
{******************}

procedure TRC6.encipherfile;
var
S : array2;
i,numread,numwritten : integer;
buff : array[0..4095] of char;
buff2 : array[0..1023] of integer;
a : array3;
inputf,outputf : file;
begin
assignfile(inputf,ifile);{These are the input and output files}
reset(inputf,1);

assignfile(outputf, ofile);
rewrite(Outputf,1);

S := keyexpand;
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

            a := encryptblock(a,S);

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

{******************}

procedure TRC6.decipherfile;
var
S : array2;
i,numread,numwritten : integer;
buff : array[0..4095] of char;
buff2 : array[0..1023] of integer;
a : array3;
inputf,outputf : file;
begin
assignfile(inputf,ifile);{These are the input and output files}
reset(inputf,1);

assignfile(outputf, ofile);
rewrite(Outputf,1);

S := keyexpand;
repeat
      blockread(inputf,Buff,sizeof(buff), numread);

      move(Buff,buff2,sizeof(buff));

      i := 0;
      while i <= 1023 do
            begin
            {get the cipher text}
            a[0] := buff2[i];
            a[1] := buff2[i+1];
            a[2] := buff2[i+2];
            a[3] := buff2[i+3];

            a := decryptblock(a,S);

            {return the decrypted values}
            buff2[i] := a[0];
            buff2[i+1] := a[1];
            buff2[i+2] := a[2];
            buff2[i+3] := a[3];
            inc(i,4);
            end;{while}

      move(Buff2,buff,sizeof(buff2));

      blockwrite(outputf,Buff,numread,numwritten);
until (numread = 0) or (numwritten <> numread);
closefile(inputf);
closefile(outputf);
end;
end.
