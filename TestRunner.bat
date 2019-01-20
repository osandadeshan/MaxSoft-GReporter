@echo off
echo MaxSoft GReporter .............

call mvn clean install -DskipTests
call mvn gauge:execute -DspecsDir="specs"

call mvn clean -DemailConfigEnv=dev install exec:java
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b