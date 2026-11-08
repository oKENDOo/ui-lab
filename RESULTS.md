# UI lab results

Tool parts completed: Part 1 - Selenium IDE, Part 2 - Selenium WebDriver in Java, Part 3 - Robot Framework, Part 4 - Cypress

## Part 0

- Step 0.1, Chrome version: 156.0.8078.80 (Official Build) (64-bit) (cohort: Stable)
- Step 0.3, ChromeDriver version: 156.0.8078.80
- Step 0.4, locator of Pizza, Please!: `name=pizza-please`
- Step 0.4, locator of Excluded tools: `css=select`
- Step 0.4, locator of Must be vegetarian: `id=default-checkbox`
- Step 0.4, locator of Custom Pizza Name: `css=input#pizza-name`
- Step 0.5, manual run: `Lab Pizza 20261108-140501`, tool `Scissors`, 525 calories per slice.

| Step | Actual result | Pass or fail |
|------|---------------|--------------|
| 1 | Opened QuickPizza and saw `Looking to break out of your pizza routine?` | Pass |
| 2 | Opened `Advanced` and saw all four controls: Calories, Excluded tools, vegetarian, and custom name | Pass |
| 3 | Entered 600 calories, selected Knife, checked vegetarian, and set the custom name | Pass |
| 4 | Recommendation was `Lab Pizza 20261108-140501`; Knife was excluded; returned tool was `Scissors`; calories per slice was 525 (not above 600) | Pass |
| 5 | Selecting `Love it!` before login showed `Please log in first.` | Pass |
| 6 | Logged in at `/admin` with `admin` / `admin` and saw `Latest pizza recommendations` | Pass |
| 7 | Found `Lab Pizza 20261108-140501` in the admin list, with the same tool as step 4 (`Scissors`) | Pass |

## Part 1: Selenium IDE

- Firefox version and Node.js version: Firefox 159.0.1; Node.js v24.22.1
- Step 1.4, intentional `Received` (without `!`) failure: Not recorded separately; the corrected `Received!` assertion passed in both final project runs.
- Step 1.5, Submit target: `xpath=//button[@type='submit']`; the recorded `css=.btn` was fragile because a CSS class can be shared by multiple buttons.
- Step 1.6, changes after recording: used full URLs, replaced the Submit target, added waits/assertions, removed the redundant recorded option click, and completed the 29-command QP-01 customer test.
- Step 1.6, run 1 pizza name and duration: `Lab Pizza 20261108-205744`, 12.948 s; run 2: `Lab Pizza 20261108-210118`, 7.026 s.
- Step 1.6, the line Tests: of both runs: `Tests: 2 passed, 2 total`.
- Step 1.7, `driver.findElement(...).sendKeys("600");` needs a preceding `clear()` because QuickPizza pre-fills the calories box with 1000; otherwise it becomes 1000600. The export also treats the two `${pizzaName}` assertions as literal Java text, so they need rewriting before reuse.

## Part 2: Selenium WebDriver in Java

- Step 2.4, the failure line for the wrong title: Pending
- Step 2.15, QP01Test run 1, result and time: Pending
- Step 2.15, QP01Test run 2, result and time: Pending
- Step 2.20, full run 1, time and number of tests: Pending
- Step 2.20, full run 2, time and number of tests: Pending

## Part 3: Robot Framework

- robot --version: Pending
- Step 3.2, failure message for the wrong title: Pending
- Step 3.8, run 1, result, elapsed time and pizza name: Pending
- Step 3.8, run 2, result, elapsed time and pizza name: Pending

## Part 4: Cypress

- node --version, npm --version and npx cypress version: Pending
- Step 4.3, failure message for the wrong title: Pending
- Step 4.6, tool and calories in the answer of POST /api/pizza: Pending
- Step 4.7, the line of the admin list that shows your pizza: Pending
- Step 4.8, run 1, result, duration and pizza name: Pending
- Step 4.8, run 2, result, duration and pizza name: Pending
- Step 4.9 (optional), browser and result: Not run

## Part B

1. Pending completion of test runs.
2. Pending completion of test runs.
3. Pending completion of test runs.
4. Pending completion of test runs.
5. Pending completion of test runs.
6. Pending completion of test runs.
7. Pending completion of test runs.
