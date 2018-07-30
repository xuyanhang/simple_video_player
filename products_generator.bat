cd /d %~dp0build\antscripts
start /wait cmd /k "ant & exit"
cd ..\..\
if not exist products md products
move build\target\*.zip products
rd /q /s build\source
rd /q /s build\target
