if not exist "c:\Serenity_Reports\" MKDIR c:\Serenity_Reports
@echo off
for /f "tokens=2 delims==" %%a in ('wmic OS Get localdatetime /value') do set "dt=%%a"
set "YY=%dt:~2,2%" & set "YYYY=%dt:~0,4%" & set "MM=%dt:~4,2%" & set "DD=%dt:~6,2%"
set "HH=%dt:~8,2%" & set "Min=%dt:~10,2%" & set "Sec=%dt:~12,2%"
set "datestamp=%YYYY%-%MM%-%DD%" & set "timestamp=%HH%h%Min%m%Sec%s"
set "fullstamp=%YYYY%-%MM%-%DD%_%HH%-%Min%-%Sec%"
cd \
xcopy /s /i "C:\Users\7201\Desktop\Karate Rest Api Testing\JhbGrads201905_ProjectDay4_Mlungisi\target\site" "C:Serenity_Reports\project4_%datestamp%_%timestamp%"
