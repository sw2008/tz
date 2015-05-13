set MY_HOME=%~dp0
set CLASSPATH=%CLASSPATH%;%MY_HOME%smdj.jar;%MY_HOME%..\lib\*;
set JAVA_OPTS=-Xms128m -Xmx256m 
java %JAVA_OPTS% com.nb.smdj.NBSmdj
