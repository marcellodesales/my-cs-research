SET JDK_BIN_DIR=z:\jdk1.3.1\bin
SET SOURCE_PATH=C:\desenv\BibliWeb\src
SET THIS_CLASSPATH=C:\wwwserver\tomcat4.0.3N\common\lib\servlet.jar;C:\wwwserver\tomcat4.0.3N\webapps\bibliweb\Web-Inf\lib\cos.jar;C:\wwwserver\tomcat4.0.3N\webapps\bibliweb\Web-Inf\lib\jdbc7.1-1.2.jar;C:\wwwserver\tomcat4.0.3N\webapps\bibliweb\Web-Inf\lib\jdom.jar;C:\wwwserver\tomcat4.0.3N\webapps\bibliweb\Web-Inf\lib\xerces.jar;
SET DESTINATION_DIR=C:\desenv\BibliWeb\doc\

%JDK_BIN_DIR%\javadoc.exe -link http://java.sun.com/products/jdk/1.3/docs/api -link http://java.sun.com/products/servlet/2.2/javadoc -windowtitle "Bibliweb - TCI/NPD/UFAL -  Documenta&ccedil;&atilde;o" -sourcepath %SOURCE_PATH% -classpath %THIS_CLASSPATH% @pacotes.txt -d %DESTINATION_DIR%
