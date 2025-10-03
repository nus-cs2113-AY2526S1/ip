@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile all Java files from src/main/java (including subfolders) into bin
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\kyro\base\*.java ..\src\main\java\kyro\exceptions\*.java ..\src\main\java\kyro\tasks\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program using fully qualified class name (package kyro)
java -classpath ..\bin kyro.base.Kyro < input.txt > ACTUAL.TXT

REM compare the output to the expected output, ignoring line ending differences
fc /w ACTUAL.TXT EXPECTED.TXT > NUL
IF ERRORLEVEL 1 (
    echo ********** TEST FAILED **********
    fc /w ACTUAL.TXT EXPECTED.TXT
) ELSE (
    echo ********** TEST PASSED **********
)
