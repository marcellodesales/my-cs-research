unit algebra;

interface
Type
  TAlgebra = Class
    a: integer;
    b: integer;
    u: integer;
    t: integer;
   private
    function quociente : int64;
    function mdc(p,q:int64) : int64;
    function mdcExtendido(a,b: int64) : int64;
    function euler(p,q :int64) : int64;
    function potenciaModuloN(a,e,n :int64) : int64;
   public
    procedure setNumbers(a: integer; b: integer);
    procedure resetNumbers();
    function getNewPrimo(input:integer) : int64;
    function isPrimo(x:integer) : boolean;
    function getQuociente() : int64; overload;
    function getQuociente(a,b :int64) : int64; overload;
    function getResto(a,b :int64) : int64; overload;
    function getMdc(p,q:int64) : int64; overload;
    function getMdcExtendido(p,q:int64):int64; overload;
    function getEuler(a,b:int64) : int64;
    function getPotenciaModuloN(a,e,n :int64) : int64;
  end;

implementation
uses math;

procedure TAlgebra.resetNumbers();
begin
        self.a := 0;
        self.b := 0;
end;

function TAlgebra.getMdcExtendido(p,q:int64):int64;
begin
        getMdcExtendido := self.mdcExtendido(p,q);
end;

function TAlgebra.getMdc(p,q:int64) : int64;
begin
        getMdc := self.mdc(p,q);
end;

function TAlgebra.getEuler(a,b : int64) : int64;
begin
        getEuler := self.euler(a,b);
end;

function TAlgebra.getPotenciaModuloN(a,e,n :int64) : int64;
begin
        getPotenciaModuloN := self.potenciaModuloN(a,e,n);
end;

function TAlgebra.getQuociente() : int64;
begin
        getQuociente := self.quociente();
end;

function TAlgebra.getQuociente(a,b :int64) : int64;
begin
        getQuociente := (a div b);
end;

function TAlgebra.getNewPrimo(input:integer) : int64;
var rand : int64;
begin
        randomize;
        rand := random(1000)+input;
        while not(self.isPrimo(rand)) do
        begin
                rand := random(1000) + input;
        end;
        getNewPrimo := rand;
end;

procedure TAlgebra.setNumbers(a: integer; b: integer);
begin
        self.a := a;
        self.b := b;
end;

function TAlgebra.quociente() : int64;
begin
        quociente := (self.a div self.b);
end;

function TAlgebra.isPrimo(x:integer) : boolean;
var i ,v  : int64;
begin
     v:=x;
     i:=2;

     while ((self.getResto(v,i)<>0) and ((i*i)<=v) ) do
       inc(i);

     isPrimo := (self.getResto(v,i)<>0)
end;

function TAlgebra.getResto(a,b :int64) : int64;
var q:int64;
begin
        q := 1;
        while (b * q <= a) do
                q := q + 1;
        q := q - 1;
        getResto := (a - b * q);
end;

function TAlgebra.mdc(p,q:int64) : int64;
var r: int64;
begin
        r := self.getResto(p,q);
        while (r > 0) do
        begin
             p := q;
             q := r;
             r := self.getResto(p,q);
        end;
        mdc := q;
end;
function quoc(a,b:int64):int64;
var q:int64;
begin
        q := 1;
        while (b * q <= a) do
                q := q + 1;
        q := q - 1;
        result := q;
end;


function TAlgebra.mdcExtendido(a,b: int64) : int64;
var q, r, x1, x2, y1, y2, mdc, t, u: int64;
begin
   self.a := a;
   self.b := b;

  if (self.b = 0) then
  begin
      mdc := self.a;
      self.t := 1;
      self.u := 0;
  end;
  x2 := 1;  x1 := 0;
  y2 := 0;  y1 := 1;

  while (self.b > 0) do
  begin
    q := self.quociente();
    r := self.a - Self.b * q;

    self.t := x2 - q * x1;
    self.u := y2 - q * y1;

    x2 := x1;
    x1 := self.t;
    y2 := y1;
    y1 := self.u;

    self.a := self.b;
    self.b := r;
  end;

  mdc := self.a;
  self.t := x2;
  self.u := y2;

  mdcExtendido := mdc;
end;

function TAlgebra.euler(p, q : int64) : int64;
begin
   euler := (p - 1)*(q - 1);
end;

function TAlgebra.potenciaModuloN(a,e,n :int64) : int64;
var
  a1, a2, p : int64;
begin
    a1 := a;
    a2 := e;
    p  := 1;
    while a2 <> 0 do
      begin
          if (a2 mod 2) <> 0 then
             p := a1*p mod n;
            a2 := self.getQuociente(a2,2);
            a1 := a1*a1 mod n;
      end;
    potenciaModuloN := p;
end;
end.
