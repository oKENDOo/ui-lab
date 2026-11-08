# UI lab results

Tool parts completed: Part 1 - Selenium IDE, Part 2 - Selenium WebDriver in Java, Part 3 - Robot Framework, Part 4 - Cypress

## Part 0

- Step 0.1, Chrome version: 156.0.8078.80 (Official Build) (64-bit) (cohort: Stable)
- Step 0.3, ChromeDriver version: 156.0.8078.80
- Step 0.4, locator of Pizza, Please!: `name=pizza-please` (used by passing Java and Robot QP-01 tests)
- Step 0.4, locator of Excluded tools: `css=select` (used by passing Java and Robot QP-01 tests)
- Step 0.4, locator of Must be vegetarian: `id=default-checkbox` (used by passing Java and Robot QP-01 tests)
- Step 0.4, locator of Custom Pizza Name: `css=input#pizza-name` (used by passing Java and Robot QP-01 tests)
- Step 0.5, manual run: `Lab Pizza 20261108-140501`, tool `Scissors`, 525 calories per slice. This is a manual UI result, recorded separately from the timestamped automated QP-01 run in Part 4.

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

- Step 2.4, the failure line for the wrong title: `FirstTest.webFormOpens:20 expected: <Web Form> but was: <Web form>`
- Step 2.15, QP01Test run 1, result and time: Pending
- Step 2.15, QP01Test run 2, result and time: Pending
- Step 2.20, full run 1, time and number of tests: Pending
- Step 2.20, full run 2, time and number of tests: Pending

## Part 3: Robot Framework

- robot --version: Robot Framework 7.5.1 (Python 3.15.0 on win32)
- Step 3.2, failure message for the wrong title: `Title should have been 'Web Form' but was 'Web form'.`
- Step 3.8, run 1, result, elapsed time and pizza name: Pending
- Step 3.8, run 2, result, elapsed time and pizza name: Pending

## Part 4: Cypress

- `node --version`: `v24.22.1`; `npm --version`: `11.19.1`; Cypress package/binary: `16.2.1`.
- Step 4.3, failure message for the wrong title: Pending
- Step 4.6, recorded Cypress QP-01 result: `Lab Pizza 20261108-135047`; returned tool `Pizza cutter`; 475 calories per slice. The test excluded `Knife`, so the returned tool is valid.
- Step 4.7, recorded Cypress admin-list line: `Lab Pizza 20261108-135047 (tool=Pizza cutter, ingredients_number=6) (newest)`.
- Step 4.8, run 1: Pass (user-confirmed local execution). Evidence is committed in `cypress/cypress/videos/` (one video for each spec) and `cypress/cypress/screenshots/pizza-scenario.cy.js/` (steps 4, 5, and 7). The available artifacts do not contain a reliable total duration, so none is claimed here.
- Step 4.8, run 2, result, duration and pizza name: Pending
- Step 4.9 (optional), browser and result: Not run

## Part B

1. Max Calories per Slice cannot use `#floating_filled` because it appears on three inputs. A unique id such as `max-calories-per-slice` would prevent tests selecting the wrong field.
2. Step 7 is a consistency oracle: it compares the tool in the recommendation with the tool in the admin list. It catches a fault where the admin page stores or displays a different tool.
3. A vegetarian check needs a visible label or API field. Cypress can assert the `vegetarian` response field; a UI-only test needs equivalent page metadata.
4. Java uses explicit `WebDriverWait`; Robot uses `Wait Until ...` keywords. Both passed QP-01; Robot's HTML log is particularly readable, while Java retains concise screenshots and Surefire output.
5. Robot Framework is preferred for Project 2: its scenario reads close to the written case, and its resource file centralizes locators/actions. Two full headless runs produced reports, logs, XML, and screenshots.
6. A changed calories locator requires changes inside QP01Test, but only `HomePage.MAX_CALORIES` for QP01PageObjectTest. Page objects reduce repair scope.
7. Robot keeps the calories locator in `${MAX CALORIES}` in `resources/quickpizza.resource`; the scenario test file does not need changing.
