@echo off
echo Test ISBN Amanuensis

C:
cd \cmp\ISBN

echo JDK 1.2 application no jar
set path=D:\jdk1.3\bin
SET JAVA_HOME=D:\jdk1.3\java
set classpath=..\..\
Rem note lack of .class extension
java.exe cmp.ISBN.ISBN
pause

echo Symantec Visual Cafe 2.5a application no jar
set path=D:\vcp\bin;d:\vcp\java\bin
SET JAVA_HOME=D:\vcp\java
REM ignores SET classpath, so must pass classpath on the command line.
Rem note lack of .class extension
java.exe -classpath ..\..\;D:\vcp\java\lib\CLASSES.ZIP cmp.ISBN.ISBN
pause

echo JDK 1.2 application with jar
set path=D:\jdk1.3\bin
SET JAVA_HOME=D:\jdk1.3\JAVA
SET classpath=isbn.jar
Rem note lack of .class extension
java.exe  cmp.ISBN.ISBN
pause

echo Symantec Visual Cafe 2.5a application with jar
set path=D:\vcp\bin;d:\vcp\java\bin
SET JAVA_HOME=D:\vcp\JAVA
REM ignores SET classpath, so must pass classpath on the command line.
Rem note lack of .class extension
java.exe  -classpath isbn.jar;D:\vcp\JAVA\LIB\CLASSES.ZIP cmp.ISBN.ISBN
pause


echo JDK 1.2 appletviewer both with and without jar (watch overlap)
set path=D:\jdk1.3\bin
SET JAVA_HOME=D:\jdk1.3\java
set classpath=.
Appletviewer.exe ISBNTest.html
pause

echo Symantec Visual Cafe 2.5a appletviewer both with and without jar (watch overlap)
set path=D:\vcp\bin;d:\vcp\java\bin
SET JAVA_HOME=D:\vcp\JAVA
REM ignores SET classpath, so must pass classpath on the command line.
REM Note it takes two -J commands to pass the classpath through Appletviewer
REM Note lack of space after -J.
Appletviewer.exe -J-classpath -J.;D:\vcp\java\lib\CLASSES.ZIP ISBNTest.html
pause

echo Netscape Applet both with and without jar
set classpath=.
start /wait "D:\program files\Netscape\Programs\Netscape.exe" ISBNTest.html
pause

echo Internet Explorer both with and without jar
echo If Explorer sulks because you gave the *.html extension to Netscape
echo and it brings up Netscape instead, you will have to run this
echo test manually.
set classpath=.
iexplore.exe D:\CMP\ISBN\ISBNTest.html
pause

Echo test complete
REM -30-
