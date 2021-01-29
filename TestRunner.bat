@echo off
echo MaxSoft GReporter .............

call mvn clean install -DskipTests
call mvn test-compile gauge:execute -DspecsDir="specs" -Denv="qa"

call mvn clean -DemailConfigEnv=qa install exec:java
echo Exit Code = %ERRORLEVEL%
if not "%ERRORLEVEL%" == "0" exit /b