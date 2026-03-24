# Coverage Matrix

---

## UI Coverage

| Requirement | Acceptance Criteria | Automated Test                              |
| ----------- | ------------------- | ------------------------------------------- |
| FR-UI-1     | AC-UI-1             | LoginTest.verifyValidLogin                  |
| FR-UI-2     | AC-UI-2             | LoginTest.verifyInvalidLogin                |
| FR-UI-2     | AC-UI-2             | LoginTest.verifyBlankLogin                  |
| FR-UI-3     | AC-UI-3             | LoginTest.verifyNavigationAfterLogin        |
| FR-UI-4     | AC-UI-4             | AddEmployeeTest.verifyAddEmployeeFormFields |
| FR-UI-5     | AC-UI-5             | AddEmployeeTest.verifyAddEmployee           |
| FR-UI-5     | AC-UI-5             | AddEmployeeTest.verifyEmployeeAddedInList   |
| FR-UI-6     | AC-UI-6             | AddEmployeeTest.verifyRequiredFieldError    |

---

## UI Edge Case Coverage

| Scenario                 | Test                                        |
| ------------------------ | ------------------------------------------- |
| Special characters input | AddEmployeeTest.verifySpecialCharacterInput |
| Long input               | AddEmployeeTest.verifyLongNameInput         |
| Duplicate employee       | AddEmployeeTest.verifyDuplicateEmployee     |
| Empty search             | AddEmployeeTest.verifyInvalidSearch         |
| Partial form             | AddEmployeeTest.verifyPartialInput          |
| Rapid submit             | AddEmployeeTest.verifyMultipleClicks        |

---

## API Coverage

| Requirement         | Acceptance Criteria | Automated Test                  |
| ------------------- | ------------------- | ------------------------------- |
| FR-API-1 → FR-API-9 | AC-API-1 → AC-API-9 | RestCountriesTest (all methods) |

---

## API Edge Coverage

| Scenario         | Test                        |
| ---------------- | --------------------------- |
| Case sensitivity | verifyCaseInsensitiveSearch |
| Invalid input    | verifyInvalidCountry        |
| Empty request    | verifyEmptyInput            |
| Invalid method   | verifyInvalidMethod         |
| Invalid params   | verifyInvalidFields         |

---

##  Summary

* Functional + edge cases both covered
* UI + API complete coverage
* Strong validation strategy
* AI locator healing ensures resilience
