

FOR /F %%i IN ('dir /a:d /t:c /o-d /b') DO (
    SET a=%%i
    GOTO :found_mid
)

echo No subfolder found
goto :eof

:found_mid
cd %a%

FOR /F %%i IN ('dir /a:d /t:c /o-d /b') DO (
    SET a=%%i
    GOTO :found_last
)

:found_last
echo Most recent subfolder: %a%
set last_subforlder=%a%

powershell Compress-Archive -LiteralPath '%a%' -DestinationPath "%a%.zip"

move %a%.zip ../Reporte.zip