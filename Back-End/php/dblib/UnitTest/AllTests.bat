set IgnoreCache=1
set UnitTest=
call php C:/php-7.2/phpunit-7.2.7.phar --verbose --log-junit resultsDB.xml .\
pause
set UnitTest=1
call php C:/php-7.2/phpunit-7.2.7.phar --verbose --log-junit resultsXML.xml .\
pause


