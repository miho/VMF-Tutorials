@echo off

REM # get script location and navigate to folder
SET DIR=%~dp0
REM echo %DIR:~0,-1%
cd %DIR%

FOR /D /r %%d in ("VMF-Tutorial*") DO (
    echo "----------------------------------------"
    echo "> compile & run project %%~nxd"
    echo "----------------------------------------"
    cd %DIR%
    cd %%d && gradlew clean run --stacktrace
)