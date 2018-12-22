@echo off
:start
	set /p pass=ÇëÊäÈëÆô¶¯ÃÜÂë£º
	If not %pass%==start echo ÃÜÂë´íÎó & exit
	If %pass%==start ngrok.exe -config=ngrok.cfg start http