unit rsa;

interface
uses algebra,confRsa,Sysutils,forms;
Type
  TRsa = Class
    P:  int64;
    Q:  int64;
    E:  int64;
    N:  int64;
    D:  int64;
    FI: int64;
    algebra: TAlgebra;
    confRsa : TConfigRSA;
    procedure start();
    procedure close();
   private
    function getEChavePublica():int64;
    function getDInversoE():int64;
   public
    procedure setPrimos();
    procedure setChavePublica();
    procedure setChavePrivada();
  end;

implementation
uses math,principal;

procedure TRsa.start();
begin
        self.algebra := TAlgebra.Create();
        Self.confRsa := TConfigRSA.Create();

        if (FileExists(ExtractFilePath(Application.ExeName)+'configuração.ini')) then
        begin
                self.confRsa.GetAllRSAConfig(ExtractFilePath(Application.ExeName)+'configuração.ini');
                self.N := Self.confRsa.N;
                Self.E := Self.confRsa.E;
                Self.D := Self.confRsa.D;
        end
        else
        begin
                self.setPrimos();
                Self.setChavePublica();
                Self.setChavePrivada();
                Self.close();
        end;
end;

procedure TRsa.close();
begin
        Self.confRsa.setConfRSAChavePublica(self.N,Self.E);
        Self.confRsa.setConfRSAChavePrivada(Self.D);
        Self.confRsa.updateRSAConfig(ExtractFilePath(Application.ExeName)+'configuração.ini');
end;

procedure TRsa.setPrimos();
begin
        Randomize();
        self.P := self.algebra.getNewPrimo(random(200));
        self.Q := self.algebra.getNewPrimo(random(300));
        main.log.Lines.Add('   -> Configurando primos aleatórios');
        main.log.Lines.Add('      P = '+intToStr(self.P));
        main.log.Lines.Add('      Q = '+intToStr(self.Q));
        main.log.Lines.Add('');
end;

procedure TRsa.setChavePublica();
begin
        self.N := Self.P * Self.Q;
        main.log.Lines.Add('   -> Calculando Chaves Públicas');
        main.log.Lines.Add('      N = P * Q; N = '+intToStr(self.N));
        Self.E := Self.getEChavePublica();
        main.log.Lines.Add('         E = '+intToStr(self.E));
        main.log.Lines.Add('');    
        main.log.Lines.Add('         Chave Pública (N,E) = ('+intToStr(self.N)+','+intToStr(self.E)+');');
        main.log.Lines.Add('');
end;

procedure TRsa.setChavePrivada();
begin
        main.log.Lines.Add('   -> Calculando Chaves Privadas');
        main.log.Lines.Add('');
        main.log.Lines.Add('     -> Calculando (D)');
        Self.D := Self.getDInversoE();
        main.log.Lines.Add('');
        main.log.Lines.Add('      Chave Privada (N,D) = ('+intToStr(self.N)+','+intToStr(self.D)+');');
end;

function TRSA.getEChavePublica():int64;
var
  aux: integer;
  mdc: int64;
begin
        Self.FI := Self.algebra.getEuler(self.P,Self.Q);
        main.log.Lines.Add('      FI = (P-1) * (Q-1); FI = '+intToStr(self.FI));
        main.log.Lines.Add('');
        main.log.Lines.Add('      -> Calculando (E)');

        aux := 2;
        main.log.Lines.Add('         Enquanto mdc('+intToStr(aux)+','+intToStr(self.FI)+') != 1');
        mdc := Self.algebra.getMdc(aux,Self.FI);
        while mdc <> 1 do
        begin
           main.log.Lines.Add('             mdc('+intToStr(aux)+','+intToStr(self.FI)+') = '+intToStr(mdc));
           inc (aux);
           mdc := Self.algebra.getMdc(aux,Self.FI);
        end;
        main.log.Lines.Add('             mdc('+intToStr(aux)+','+intToStr(self.FI)+') = '+intToStr(mdc)+'; Correto!');
        getEChavePublica := aux;
end;

function TRSA.getDInversoE():int64;
var u1,u2,u3,vv,v1,v2,v3,t1,t2,t3,inverse:int64;
begin
	u1 := 1;  u2 := 0;  u3 := self.FI;
	v1 := 0;  v2 := 1;  v3 := self.E;

        main.log.Lines.Add('         Inicializando (p1,p2,p3) = (1, 0, FI(n))');
        main.log.Lines.Add('         Inicializando (q1,q2,q3) = (0, 1,  E  ))');
        main.log.Lines.Add('         Enquanto q3 != 0');
        main.log.Lines.Add('             quoc = p3 / q3');
        main.log.Lines.Add('             (t1,t2,t3) = (p1,p2,p3) - quoc * (q1,q2,q3)');
        main.log.Lines.Add('             Depois dá valores:');
        main.log.Lines.Add('             (p1,p2,p3) = (q1,q2,q3)');
        main.log.Lines.Add('             (q1,q2,q3) = (t1,t2,t3)');

	while (v3 <> 0) do
         begin
         main.log.Lines.Add('           ('+intToStr(v3)+' <> 0) , então:');
                q  := self.algebra.getQuociente(u3,v3);
	     	t1 := u1 - q * v1;
	     	t2 := u2 - q * v2;
	     	t3 := u3 - q * v3;

	     	u1 := v1;  u2 := v2;  u3 := v3;
	     	v1 := t1;  v2 := t2;  v3 := t3;

        main.log.Lines.Add('             quoc = '+intToStr(u3)+' / '+intToStr(v3)+' = '+intToStr(q));
        main.log.Lines.Add('             (t1,t2,t3) = ('+intToStr(u1)+','+intToStr(u2)+','+intToStr(u3)+') - '+intToStr(q)+' * ('+intToStr(v1)+','+intToStr(v2)+','+intToStr(v3)+') = ('+intToStr(t1)+','+intToStr(t2)+','+intToStr(t3)+')');
        main.log.Lines.Add('             (p1,p2,p3) = ('+intToStr(v1)+','+intToStr(v2)+','+intToStr(v3)+')');
        main.log.Lines.Add('             (q1,q2,q3) = ('+intToStr(t1)+','+intToStr(t2)+','+intToStr(t3)+')');
	end;

        main.log.Lines.Add('         q3 é zero(0). Verifica agora o valor de p2. Caso seja negativo, inverte somando-se com FI. (representar o número negativo de z(n) por um positivo.)');
        main.log.Lines.Add('');
        main.log.Lines.Add('         u2 = '+intToStr(u2)+';');
	vv := u2;
	if (vv < 0) then
        begin
		inverse := vv + self.FI;
                main.log.Lines.Add('          Como u2 é negativo, faz-se:');
                main.log.Lines.Add('          D = u2 + FI; D = '+intToStr(u2)+' + '+intToStr(self.FI)+' = '+intToStr(inverse));
        end
	else begin
          	inverse := vv;
                main.log.Lines.Add('         D = u2; D = '+intToStr(u2));
        end;
	result := inverse;
end;


end.

