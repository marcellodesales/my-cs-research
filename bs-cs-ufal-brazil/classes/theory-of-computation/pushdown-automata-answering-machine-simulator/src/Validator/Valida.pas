unit valida;
INTERFACE

function validaprefixo(X,Y,Z:char) : boolean;
{pre: O prefixo foi digitado;
 pos: Retorna true se o prefixo existe;
}
function validafone(X:string):boolean;

IMPLEMENTATION
{var
   prefixo:array[1..3] of integer;}
function validafone(X:string):boolean;
begin
     validafone:=(length(x)=7);
end;

function validaprefixo(X,Y,Z:char) : boolean;
var R:char;
begin
     Case X of
          '2':case Y of
                       '1':Case Z of
                              '4':R:='1';
                         else
                            R:='0';
                         end;
          {//Final do prefixo 214}
                       '2':Case Z of
                              '1','3':R:='1'
                         else
                              R:='0';
                         end;
          {//Final do prefixo 221,223}
                       '3':Case Z of
                              '1','5','7':R:='1'
                         else
                             R:='0';
                         end;
         { //Final do prefixo 231,235,237}
                       '4':Case Z of
                              '1':R:='1'
                         else
                             R:='0';
                         end;
                  else
                      R:='0';
          {//Final do prefixo 241}
            end;
          {//Final do prefixo beginning com 2}
          '3':Case y of
                   '2':Case z of
                            '0','2','4','5','6','7':R:='1'
                       else
                           R:='0';
                       end;
                   '3':Case z of
                            '4','6','8':R:='1'
                       else
                           R:='0';
                       end;
                   '4':Case z of
                            '4':R:='1'
                       else
                           R:='0';
                       end;
                   '5':Case z of
                            '4','5','8','9':R:='1'
                       else
                           R:='0';
                       end;
          end;
     else
         R:='0';
     end;
     validaprefixo:=(R='1');

end;

end.



