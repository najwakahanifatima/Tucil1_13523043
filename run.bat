@echo off
setlocal

:: Compile all Java files in the current directory
javac -d bin src/*.java
if %errorlevel% neq 0 exit /b

:: Run the main class ()'Main' is the class name)
java -cp bin Main  

endlocal