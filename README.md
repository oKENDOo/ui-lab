# UI testing lab

Chrome: 156.0.8078.80 (64-bit). ChromeDriver: 156.0.8078.80.

## Part 1 - Selenium IDE

`selenium-ide/ui-lab.side` contains the web-form test and the QP-01 customer test. From that folder, make ChromeDriver available to the runner and run both tests:

```powershell
$env:Path = (Split-Path $env:CHROMEDRIVER) + ";" + $env:Path
npx.cmd --yes selenium-side-runner@4.0.13 ui-lab.side
```

`selenium-ide/QP01customerTest.java` is the Java JUnit export of the QP-01 customer test.

## Part 2 - Selenium WebDriver with Java

`selenium-java` contains JUnit/Selenium tests for Selenium Web Form, dynamic waits, QP-01, and its Page Object version. Run from that folder:

```powershell
mvn test -Dheadless=true
```

Screenshots and the saved Surefire report evidence successful QP-01 runs.

## Part 3 - Robot Framework

`robot` contains Web Form tests, QP-01, and reusable QuickPizza keywords. Activate the virtual environment and run:

```powershell
.venv\Scripts\activate
robot -d results/run1 -v BROWSER:headlesschrome pizza_scenario.robot
```

`robot/results/run1` and `robot/results/run2` contain successful full-run reports, logs, XML results, and screenshots.

## Part 4 - Cypress

`cypress` contains the configuration, web-form spec, QP-01 spec, and `adminLogin` custom command. Run from that folder:

```powershell
.\node_modules\.bin\cypress.cmd run --browser chrome
```
