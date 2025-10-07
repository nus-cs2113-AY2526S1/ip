@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM create data directory for save/load if it doesn't exist (relative to text-ui-test)
if not exist .\data mkdir .\data

REM delete previous tasks.txt to ensure clean test (relative to text-ui-test)
if exist .\data\tasks.txt del .\data\tasks.txt

REM create an empty tasks.txt file for a clean test
type nul > .\data\tasks.txt

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\rudeus\*.java ..\src\main\java\rudeus\command\*.java ..\src\main\java\rudeus\task\*.java ..\src\main\java\rudeus\ui\*.java ..\src\main\java\rudeus\storage\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin rudeus.Rudeus < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
