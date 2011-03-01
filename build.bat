setlocal
set ANT_HOME=D:\tools\ant\apache-ant-1.8.2
set JAVA_HOME=C:\jdk1.6.0
set path=%JAVA_HOME%\bin;%ANT_HOME%\bin;%path%

ant %1 %2 %3 %4 %5 %6 %7 %8 %9
endlocal
