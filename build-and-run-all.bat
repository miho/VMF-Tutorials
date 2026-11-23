@echo off
setlocal

for /d %%D in ("%~dp0VMF-Tutorial-*") do (
    call :tutorial "%%~fD"
    if errorlevel 1 exit /b 1
)
exit /b 0

:tutorial
echo.
echo Building and running %~nx1
pushd "%~1" || exit /b 1
if exist gradlew.bat (
    call gradlew.bat clean build run --console=plain
) else if exist pom.xml (
    call mvn -B -ntp clean verify
) else (
    echo No build found in "%~1" 1>&2
    popd
    exit /b 1
)
set "tutorial_status=%errorlevel%"
popd
exit /b %tutorial_status%
