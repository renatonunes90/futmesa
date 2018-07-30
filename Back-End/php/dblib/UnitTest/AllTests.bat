set IgnoreCache=1
set UnitTest=
call phpunit --verbose --log-junit resultsDB.xml .\
pause
set UnitTest=1
call phpunit --verbose --log-junit resultsXML.xml .\
pause


