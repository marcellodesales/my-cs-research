@echo off

SET JAVA_HOME=C:\j2sdk1.4.2_02
SET TEST_HOME=C:\test_auto
SET PTF_HOME=C:\phonetest

SET 

CLASSPATH=%PTF_HOME%\lib\aniteserver.jar;%PTF_HOME%\lib\asn13gpp.jar;%PTF_HOME%\lib\asn1rt.jar;%PTF_HOME%\lib\asn1rt1.jar;%PTF_HOME%\lib\atf.jar;%PTF_HOME%\lib\bridge2java.jar;%PTF_HOME%\lib\caimeserver.jar;%PTF_HOME%\lib\caimeserver1x.jar;%PTF_HOME%\lib\jgl3.1.0.jar;%PTF_HOME%\lib\phonetest.jar;%PTF_HOME%\lib\Serialio.jar;%PTF_HOME%\lib\testquest.jar;%PTF_HOME%\lib\thingkxml.jar;%PTF_HOME%\lib\vte.jar;

SET CLASSPATH=%CLASSPATH%;%TEST_HOME%\lib\testcase.jar;%TEST_HOME%\lib\taf.jar

PATH=%PTF_HOME%\lib;%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin\client

SET CONFIG_FILE=C:\test_auto\p2k\p041\triplets_refresh\TAF_PTF_Config.cfg

SET TEST_LIST=C:\test_auto\p2k\p041\triplets_refresh\Testcase_Exec_List.txt

java phonetest.TSM -l %TEST_LIST% -c %CONFIG_FILE%

cd ..

@echo on
