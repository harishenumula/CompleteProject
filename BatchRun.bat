set projectLocation=I:\Git Projects\CompleteProject\CompleteProject

cd %projectLocation%

set classpath=%projectLocation%\bin;%projectLocation%\lib\*

java org.testng.TestNG %projectLocation%\testng.xml

pause
