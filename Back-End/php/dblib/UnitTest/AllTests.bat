cls
set IgnoreCache=1
set UnitTest=
phpunit --verbose --log-junit resultsDB.xml .\
set UnitTest=1
phpunit --verbose --log-junit resultsXML.xml .\