# MaxSoft GReporter

## Introduction
In testing perspective, the test execution report is a vital document where the stakeholders can get an idea of the project health.

Gauge is proving a detailed test execution report with all the error messages and the screenshots incorporated to the failing steps.

But anyhow, QA should manually send the test execution report to the stakeholders by mentioning the number of scenarios executed, passed count, failed count and skipped count. Also in a regression cycle, QA has to send those counts as module wise figures. Further, QA needs to embed pie charts and bar charts to that report to represents the test execution data in a high readable manner.

MaxSoft GReporter is an automated solution for this issue. It acts as a Java plugin for gauge to send the test execution summary to a defined audience.

## Technologies/Frameworks used
- Java
- Gauge Framework
- JFreeChart
- xChart
- JSON Simple
- Jayway JSONPath
- SMTP
- MailAPI
- TestNG
- Apache Maven

## Supported Platforms
- Windows
- Linux
- Mac OS

## Supported Languages
- Java

## Advantages
- Automated emails for test execution summary with graphical representations.

## Pre Requisites
1. Java
2. Maven

## How to Install Gauge Core
**On Windows**
1. Install Chocolatey by executing the following command.

` @"%SystemRoot%\System32\WindowsPowerShell\v1.0\powershell.exe" -NoProfile -InputFormat None -ExecutionPolicy Bypass -Command "iex ((New-Object System.Net.WebClient).DownloadString(‘https://chocolatey.org/install.ps1'))" && SET "PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin"`

2. Install Gauge by executing the following command.

`choco install gauge`

**On MacOS**
1. Update Homebrew.

`brew update`

2. Install Gauge using Homebrew.

`brew install gauge`

**On Linux**
1. First, add Gauge’s GPG key with this command.

`sudo apt-key adv --keyserver hkp://pool.sks-keyservers.net --recv-keys 023EDB0B`

2. Then add Gauge to the repository list using this command.

`echo deb https://dl.bintray.com/gauge/gauge-deb nightly main | sudo tee -a /etc/apt/sources.list`

3. Finally, install Gauge using these commands.

`sudo apt-get update`
`sudo apt-get install gauge`

## How to Install Gauge Plugins
1. Open Command Prompt and execute following commands.

`gauge install java`

`gauge install html-report`

`gauge install json-report`

`gauge install xml-report`

`gauge install spectacle`

`gauge install flash`

2. You can check the installation using the following command.

`gauge -v`

	If the installation is success, it will output like this:

```markdown
    Gauge version: <version number>
    Plugins
    -------
    flash (<version number>)
    html-report (<version number>)
    java (<version number>)
    json-report (<version number>)
    spectacle (<version number>)
    xml-report (<version number>)
```

## MaxSoft GReporter Tutorials
- [MaxSoft GReporter](https://medium.com/greporter/maxsoft-email-client-for-gauge-8ae8af8ad32f)

## How to use MaxSoft GReporter?
1. Clone this gauge project into your computer
2. Create your gauge tests (i.e: [Gauge Documentation](https://docs.gauge.org/latest/index.html "Gauge Documentation"))
3. Open **"email.properties"** file (`<rootDir>\env\email\email.properties`) from notepad
4. Change the properties and Save it
5. Open **"piechart.properties"** file (`<rootDir>\env\chart\piechart.properties`) from notepad
6. Change the properties and Save it
7. Open **"barchart.properties"** file (`<rootDir>\env\chart\barchart.properties`) from notepad
8. Change the properties and Save it
9. Double-click on **"TestRunner.bat"** which is in the root directory
10. After the execution is completed, GReporter will push an email to the given audience including the test execution summary
![GReporter v1.0.3](https://i.imgur.com/xEovMLC.png)

## How to build MaxSoft GReporter JAR File?
1. Open the command prompt
2. Navigate to the directory of the project (`cd <project dir>`)
3. Execute this command
`mvn clean install -DskipTests`

## License
<img src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/License_icon-mit-2.svg/2000px-License_icon-mit-2.svg.png" alt="MIT License" width="100" height="100"/> [MaxSoft GReporter](https://medium.com/greporter) is released under [MIT License](https://opensource.org/licenses/MIT)

## Copyright
Copyright 2022 MaxSoft.
