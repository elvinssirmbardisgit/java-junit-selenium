# Copilot Instructions for Java JUnit Project

## Project Overview

Basic Maven-based Java project demonstrating core testing patterns with JUnit 4. Single module with production code in `com.example` package and corresponding tests following 1:1 mapping convention.

## Project Structure & Architecture

- `src/main/java/com/example/` - Production code (e.g., `Calculator.java`, `SearchPage.java`)
- `src/test/java/com/example/` - Test code (unit and web tests)
- `pom.xml` - Maven config: Java 11, JUnit 4.13.2, Selenium 4.15.0, maven-compiler-plugin, maven-surefire-plugin

**Key Pattern:** Test classes mirror source structure with `*Test` suffix. Example: `Calculator.java` ↔ `CalculatorTest.java`.

**Dependencies:**
````instructions
# Copilot Instructions — Java JUnit Selenium

Concise, project-specific guidance for AI coding agents working on this repo.

## Big picture
- Single-module Maven Java project (Java 11). Production code: `src/main/java/com/example/`.
- Tests mirror production classes under `src/test/java/com/example/` (1:1 mapping, `*Test` suffix).
- Two main test categories: fast unit tests (Calculator, SearchPage) and one Selenium-backed web test (`SeleniumWebTest`) that requires ChromeDriver.

## Key files to inspect
- `pom.xml` — build config, Java version, test deps (JUnit4, Selenium).
- `src/main/java/com/example/Calculator.java` — example of public API + Javadoc + input validation (`IllegalArgumentException` on bad args).
- `src/main/java/com/example/SearchPage.java` — page-object-style helper used by tests.
- `src/test/java/com/example/*Test.java` — canonical test patterns and naming.
- `target/surefire-reports/` — test outputs (`.txt` and `.xml`) for CI and debugging.

## Testing & workflow commands (PowerShell)
- Compile: `mvn clean compile`
- Run all tests: `mvn test`
- Run a single test class: `mvn test -Dtest=CalculatorTest`
- Run a single test method: `mvn test -Dtest=SearchPageTest#testSomeMethod`
- Exclude the Selenium test: `mvn test -Dtest=!SeleniumWebTest` (useful locally)
- Package: `mvn clean package`

When changing code, run `mvn test` to validate both unit and Selenium tests (if Selenium is required for your change).

## Selenium specifics (environment)
- ChromeDriver must match local Chrome version. Either put `chromedriver.exe` on the `PATH` or run tests with a system property:
  - `mvn test -Dwebdriver.chrome.driver="C:\path\to\chromedriver.exe"`
- Tests assume internet access when `SeleniumWebTest` runs.

## Project-specific conventions
- Testing framework: JUnit 4 (note `@Before`, `@After`, `@Test(expected=...)` patterns). Do not convert to JUnit 5 without project-wide changes.
- Test method names use `test*` style in existing classes (follow when adding new tests for consistency).
- Use static imports from `org.junit.Assert.*` in tests for assertions.
- Public methods must include Javadoc with `@param` and `@return` tags (see `Calculator.java`).

## Where to make changes safely
- Prefer updating tests that describe desired behavior; only modify production code when behavior must change.
- Keep package structure `com.example` intact — tests depend on the package layout.
- If adding dependencies, update `pom.xml` and run `mvn clean test` locally.

## Debugging tips
- Test failures: inspect `target/surefire-reports/TEST-*.xml` and corresponding `.txt` files.
- Compile errors: `mvn compile` shows errors; IDEs (IntelliJ/VS Code Java) can run individual tests/debug sessions.
- For Selenium debugging, run the Selenium test alone and set `-Dwebdriver.chrome.driver` if not on PATH.

## Useful examples from the codebase
- `Calculator.divide(int a, int b)` throws `IllegalArgumentException` for `b == 0` — follow this pattern for input validation.
- `SearchPage` acts as a lightweight page object — prefer encapsulating Selenium interactions into this class rather than scattering finds across tests.

## Non-goals / assumptions
- No CI-specific instructions are included (none discovered in repo). If you add CI, ensure `chromedriver` availability for Selenium jobs.

If anything here is unclear or you'd like more detail (CI notes, contributor rules, or automated formatting commands), tell me which section to expand.
````
```
