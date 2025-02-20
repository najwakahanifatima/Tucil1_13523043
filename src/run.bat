@echo off
setlocal

:: Compile all Java files in the current directory
javac *.java
if %errorlevel% neq 0 exit /b

:: Run the main class ()'Main' is the class name)
java FileProcessing

:: Delete all generated .class files
del /Q *.class

endlocal
