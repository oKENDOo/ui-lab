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
- Step 1.4, intentional `Received` (without `!`) failure: `Actual value 'Received!' did not match 'Received' during assertText:id=message:Received`; `Tests: 1 failed, 1 passed, 2 total`. The restored `Received!` assertion passed in both final project runs.
- Step 1.5, Submit target: `xpath=//button[@type='submit']`; the recorded `css=.btn` was fragile because a CSS class can be shared by multiple buttons.
- Step 1.6, changes after recording: used full URLs, replaced the Submit target, added waits/assertions, removed the redundant recorded option click, and completed the 29-command QP-01 customer test.
- Step 1.6, run 1 pizza name and duration: `Lab Pizza 20261108-205744`, 12.948 s; run 2: `Lab Pizza 20261108-210118`, 7.026 s.
- Step 1.6, the line Tests: of both runs: `Tests: 2 passed, 2 total`.
- Step 1.7, `driver.findElement(...).sendKeys("600");` needs a preceding `clear()` because QuickPizza pre-fills the calories box with 1000; otherwise it becomes 1000600. The export also treats the two `${pizzaName}` assertions as literal Java text, so they need rewriting before reuse.

## Part 2: Selenium WebDriver in Java

- Step 2.4, the failure line for the wrong title: `FirstTest.webFormOpens:20 expected: <Web Form> but was: <Web form>`
- Step 2.15, QP01Test run 1: PASS, 1 test, 11.68 s (Maven total 15.214 s).
- Step 2.15, QP01Test run 2: PASS, 1 test, 9.056 s (Maven total 12.306 s).
- Step 2.20, full run 1: PASS, 5 tests, 0 failures and 0 errors; aggregate Surefire test elapsed time 35.364 s.
- Step 2.20, full run 2: PASS, 5 tests, 0 failures and 0 errors; aggregate Surefire test elapsed time 36.036 s.

## Part 3: Robot Framework

- robot --version: Robot Framework 7.5.1 (Python 3.15.0 on win32)
- Step 3.2, failure message for the wrong title: `Title should have been 'Web Form' but was 'Web form'.`
- Step 3.8, run 1: PASS, 11.029601 s, `Lab Pizza 20261108-132725`.
- Step 3.8, run 2: PASS, 10.125512 s, `Lab Pizza 20261108-132745`.

## Part 4: Cypress

- `node --version`: `v24.22.1`; `npm --version`: `11.19.1`; Cypress package/binary: `16.2.1`.
- Step 4.3, failure message for the wrong title: Pending
- Step 4.6, recorded Cypress QP-01 result: `Lab Pizza 20261108-213840`; returned tool `Scissors`; 475 calories per slice. The test excluded `Knife`, so the returned tool is valid.
- Step 4.7, recorded Cypress admin-list line: `Lab Pizza 20261108-213840 (tool=Scissors, ingredients_number=7)`.
- Step 4.8, successful run 1: PASS, 2 specs and 2 tests, 12 s total (`pizza-scenario.cy.js` 10 s; `web-form.cy.js` 2 s). Evidence is committed in `cypress/cypress/videos/` and `cypress/cypress/screenshots/pizza-scenario.cy.js/`.
- Step 4.8, run 2, result, duration and pizza name: Pending
- Step 4.9 (optional), browser and result: Not run

## Part B

1. Max Calories per Slice cannot use `#floating_filled` because that id appears on three inputs. The tests therefore locate its label and select the related input. Giving this one input a unique id such as `max-calories-per-slice` would make the locator shorter and prevent a test from selecting the wrong number box.
2. Step 7 uses a consistency oracle because it compares the tool shown by two different parts of QuickPizza. The recommendation is created by the pizza API, while the admin list is a later stored/displayed representation. This would catch a fault where the admin page saved `Knife` or another incorrect tool even though the customer recommendation showed a different one.
3. A vegetarian check could assert the `vegetarian` field in the intercepted `/api/pizza` response. The customer page would also need a visible vegetarian label, ingredient metadata, or another stable UI value if the test must stay UI-only. Without one of those sources, QP-01 cannot prove that the recommended ingredients are vegetarian merely from the existing text.
4. Java waits through explicit `WebDriverWait` conditions, while Robot uses readable `Wait Until ...` keywords inside the resource file. The Java QP01Test passed in 11.68 s and 9.056 s, and the two Robot QP-01 runs passed in 11.029601 s and 10.125512 s. Robot's HTML log is easier to read step by step, while Java's Surefire reports and timestamped screenshots give concise implementation evidence.
5. Robot Framework is preferred for Project 2 because its scenario reads close to the written QP-01 test case. Its resource file centralizes locators and page actions, so a UI change has a smaller repair scope. The two completed headless runs also produced a report, log, XML result, and screenshots that are easy to review.
6. A changed calories locator requires edits inside QP01Test wherever that locator occurs. In QP01PageObjectTest, the repair is isolated to `HomePage.MAX_CALORIES`, while the scenario test remains unchanged. This is why page objects reduce the cost and risk of repairing locators after a page change.
7. Robot keeps the calories locator in `${MAX CALORIES}` in `resources/quickpizza.resource`. The test file calls `Fill In The Pizza Request` and does not repeat the locator. Changing the locator in the resource file therefore repairs every test using that keyword without editing the scenario file.
