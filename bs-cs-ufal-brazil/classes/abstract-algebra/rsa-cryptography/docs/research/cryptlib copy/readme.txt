***************************************************************************
TRC6
TMars
TCast components by Duff Neill.

These components are FREEWARE FOREVER!!!!!!!!!!!!!


Description:

These components encrypts any file. 

To use:

First drop one of the components (RC6 for example) onto your form. Then
where the code is needed:

RC6.Key := 'a random string'; {this should be REALLY random, not 
 				      just key board entered characters}
RC6.InputFile := 'c:\myfile.in';
RC6.OutputFile := 'c:\myfile.out';
RC6.EncipherFile; {if decrypting then use RC6.DecipherFile}

The more random the string the better. A hashed passphrase mixed with 
some random salt is a good idea.

None Bugs:
When a file is decrypted the very last 4 bytes or so
are never properly unscrambled.

License agreement:
******************

   THIS SOFTWARE AND THE ACCOMPANYING FILES ARE FREEWARE AND
   WITHOUT WARRANTIES AS TO PERFORMANCE OF MERCHANTABILITY OR ANY
   OTHER WARRANTIES WHETHER EXPRESSED OR IMPLIED. NO WARRANTY OF FITNESS 
   FOR A PARTICULAR PURPOSE IS OFFERED.

   Good data processing procedure dictates that any program be
   thoroughly tested with non-critical data before relying on it. The
   user must assume the entire risk of using the program. BECAUSE THIS
   PRODUCT IS GIVEN TO YOU AS "FREEWARE" THERE WILL BE NO LIABILITY
   OF THE AUTHOR. SHOULD THE USE OF THIS SOFTWARE RESULT IN ANY DAMAGES
   OF HARDWARE, SOFTWARE OR DATA THE AUTHOR CAN NOT BE HELD RESPONSIBLE
   FOR THIS.

Legal stuff:
These algorithms are all candidates for the Advanced Encryption Standard
(AES) (see http://www.nist.gov/aes/ for more ifno). 
That means if one of these is chosen then it is free. If it isn't
chosen then the designers may try to patent it. So Putting these in something 
like a shareware thingie might not be too smart. For more information on
the algorithms and their designers see:

http://www.reaserch.ibm.com/security/mars/ for the Mars cipher
http://www.entrust.com/ for Cast
and
http://theory.lcs.mit.edu/~rivest/ for RC6

***************************************************************************