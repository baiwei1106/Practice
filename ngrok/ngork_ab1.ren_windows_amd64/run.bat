@echo off
:start
	set /p pass=�������������룺
	If not %pass%==start echo ������� & exit
	If %pass%==start ngrok.exe -config=ngrok.cfg start http