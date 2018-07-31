@echo off

set VLC_PLUGIN_PATH=%cd%\jnas\vlcj\plugins
call ".\jre\bin\java" -jar -Djna.library.path=%cd%\jnas\vlcj -Dfile.encoding=utf-8 @jar_file_name@
