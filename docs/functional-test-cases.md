# Functional Test Cases – OrangeHRM UI & REST Countries API

## UI Test Cases – OrangeHRM

### Core Functional

| TC ID   | Title                   | Preconditions      | Steps                     | Expected Result            | Mapping |
| TC-UI-0 | Self-Healing Locator    | Use incorrect locator | AI corrects locator and test passes                 | AI-FEATURE 
| TC-UI-1 | Valid Login             | User on login page | Enter valid creds → Login | Redirect to dashboard      | AC-UI-1 |
| TC-UI-2 | Invalid Login           | User on login page | Enter wrong creds → Login | Error shown, no navigation | AC-UI-2 |
| TC-UI-3 | Blank Login             | User on login page | Click login without data  | Required validation shown  | AC-UI-2 |
| TC-UI-4 | Navigation Visibility   | Logged in          | Observe menu              | PIM & Leave visible        | AC-UI-3 |
| TC-UI-5 | Open PIM                | Logged in          | Click PIM                 | PIM page loads             | AC-UI-3 |
| TC-UI-6 | Add Employee Form Load  | In PIM             | Click Add Employee        | Form fields visible        | AC-UI-4 |
| TC-UI-7 | Add Employee Success    | In form            | Enter valid data → Save   | Success + employee created | AC-UI-5 |
| TC-UI-8 | Add Employee Validation | In form            | Click Save empty          | Validation messages shown  | AC-UI-6 |
| TC-UI-9 | Verify Employee in List | Employee created   | Search by ID              | Employee found             | AC-UI-5 |

---

## UI Edge Cases

| TC ID    | Title                       | Scenario                    | Expected Result                  |
| -------- | --------------------------- | --------------------------- | -------------------------------- |
| TC-UI-10 | Special Characters in Name  | Enter `@#$%` in name fields | Validation OR handled gracefully |
| TC-UI-11 | Extremely Long Name         | Enter 200+ chars            | Input restricted OR handled      |
| TC-UI-12 | Duplicate Employee          | Add same name twice         | System still allows (ID unique)  |
| TC-UI-13 | Page Refresh After Fill     | Fill form → Refresh         | Data reset                       |
| TC-UI-14 | Navigate Without Save       | Fill form → Go back         | No employee created              |
| TC-UI-15 | Invalid Search              | Search non-existent ID      | No results shown                 |
| TC-UI-16 | Session Timeout (Simulated) | Wait or reload              | Redirect to login                |
| TC-UI-17 | Required Field Highlight    | Submit empty form           | Fields highlighted + error shown |
| TC-UI-18 | Partial Data Entry          | Only first name entered     | Validation triggered             |
| TC-UI-19 | Rapid Click on Save         | Click multiple times        | Only one employee created        |

---

## API Test Cases – REST Countries

### Core Functional

| TC ID    | Title                | Endpoint                  | Expected              |
| -------- | -------------------- | ------------------------- | --------------------- |
| TC-API-1 | Get Country by Name  | /name/india               | 200 + valid data      |
| TC-API-2 | Full Name            | /name/india?fullText=true | Exact match           |
| TC-API-3 | Country by Code      | /alpha/IN                 | 200 + correct data    |
| TC-API-4 | All Countries Fields | /all?fields=name          | Only requested fields |
| TC-API-5 | Invalid Country      | /name/invalid             | 404                   |
| TC-API-6 | Missing Fields Param | /all                      | 400                   |

---

## API Edge Cases

| TC ID     | Title               | Scenario             | Expected                |
| --------- | ------------------- | -------------------- | ----------------------- |
| TC-API-7  | Case Sensitivity    | `/name/INDIA`        | Should still work (200) |
| TC-API-8  | Numeric Input       | `/name/123`          | 404                     |
| TC-API-9  | Empty Input         | `/name/`             | 400 or handled          |
| TC-API-10 | Special Characters  | `/name/@#$%`         | 404                     |
| TC-API-11 | Large Response      | `/all`               | Response handled        |
| TC-API-12 | Slow Response       | Simulated delay      | Should not break test   |
| TC-API-13 | Invalid Method      | POST on GET endpoint | 405                     |
| TC-API-14 | Invalid Field Param | `/all?fields=xyz`    | Partial/empty response  |

---

## Notes

* UI validation includes **data verification via Employee ID**
* Edge cases ensure robustness and real-world readiness
* API tests include **error handling and boundary scenarios**
