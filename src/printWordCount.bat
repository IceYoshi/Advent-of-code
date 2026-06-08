@echo off

:: Prints the number of lines of code per java file, as well as the total.
bash -c "find . -name '*.java' | xargs wc -l"

pause