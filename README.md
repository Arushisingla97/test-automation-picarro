# 🚀 AI-Powered Test Automation Framework (UI + API)

## 📌 Overview

This project is a **hybrid automation framework** combining:

* ✅ Selenium WebDriver (UI Automation)
* ✅ Rest Assured (API Automation)
* ✅ TestNG (Test Execution)
* ✅ Extent Reports (Reporting)
* ✅ AI-based Self-Healing Locators (Ollama / LLM)

### 🎯 Goals

* 🔥 Resilient (self-healing locators)
* ⚡ Scalable (modular design)
* 🧠 Intelligent (AI-assisted recovery)

---

# 🧱 Framework Architecture

```text
ui-automation/
│
├── base/              → BaseTest (Driver setup)
├── pages/             → Page Objects (Login, Dashboard, PIM)
├── tests/             → UI test cases
├── utils/             → SmartDriver, Listeners
├── ai/                → AILocatorHealer, DOMFetcher
│
api/
├── clients/           → API clients
├── tests/             → API test cases
├── validators/        → Schema validation
│
common/
├── utils/             → ExtentReports, TestListener
│
docs/                  → Test cases, test matrix, API docs
│
test-data/             → Test data
```

---

# 📄 Documentation (docs/ folder)

The `docs/` folder contains:

* 🧪 Test Case Document (UI + API coverage)
* 📊 Test Matrix (feature vs test coverage)
* 📘 API Test Documentation
* 🧠 Edge cases & scenarios

👉 This helps ensure **traceability and coverage clarity**.

---

# 🤖 AI Self-Healing Locator

## 💡 Problem

UI tests break when locators change.

## ✅ Solution

AI automatically fixes broken locators using DOM analysis.

### 🔄 Flow

1. Locator fails
2. DOM is captured
3. AI generates new locator
4. Framework retries

### 🧠 Example

```text
Broken: name=wrong_username  
AI Fix: id=username  
```

### ✨ Features

* Prevents reuse of broken locators
* Supports: id, name, xpath, css
* Safe fallback mechanism
* Handles AI inconsistencies gracefully

---

# 🌐 UI Automation Coverage

## ✅ Login

* Valid login → Dashboard
* Invalid login → Error message
* Blank login → Required field validation

## ✅ Navigation

* Dashboard visibility
* Navigation to PIM / Leave modules

## ✅ Add Employee Flow

* Add employee with valid data
* Verify employee via ID
* Required field validation
* Form UI validation

## 🧪 Edge Cases

* Empty inputs
* Invalid credentials
* Duplicate entries
* Missing required fields

---

# 🔌 API Automation Coverage

## ✅ Countries API

* Get all countries
* Status code validation
* Schema validation
* Field-level validation

## ✅ Additional APIs

* Currency API
* Region API

## 🔍 Validations

* Status codes
* Response structure
* JSON schema

---

# 🧪 Tech Stack

| Layer    | Tool           |
| -------- | -------------- |
| UI       | Selenium       |
| API      | Rest Assured   |
| Runner   | TestNG         |
| Reports  | Extent Reports |
| AI       | Ollama         |
| Build    | Maven          |
| Language | Java           |

---

# 📊 Reporting

📍 Report location:

```text
test-output/ExtentReport.html
```

Includes:

* Pass / Fail status
* Logs per step
* Screenshots on failure

---

# 🖥️ How to Run (Quick Start)

## 📦 Prerequisites

* Java 11+
* Maven
* Chrome browser
* Ollama installed

---

## 🚀 Run Everything (Recommended)

### Step 1: Start AI

```bash
ollama run llama3
```

👉 Keep this running

---

### Step 2: Run Tests

```bash
mvn clean test
```

---

### Step 3: View Report

```bash
open test-output/ExtentReport.html   # Mac
```

OR open manually.

---

# 🎯 Run Specific Tests

## ▶ UI Tests

```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## ▶ Single Test Class

```bash
mvn test -Dtest=LoginTest
```

---

## ▶ Single Test Method

```bash
mvn test -Dtest=LoginTest#verifyValidLogin
```

---

## ▶ API Tests Only

```bash
mvn test -Dtest=CountriesApiTest
```

---

# 🖥️ Headless Mode

### Option 1: testng.xml

```xml
<parameter name="headless" value="true"/>
```

### Option 2: CLI

```bash
mvn test -Dheadless=true
```

---

# ⚠️ Important Notes

* AI healing works only if Ollama is running
* First execution may be slower
* Ensure application under test is accessible

---

# ⚙️ CI/CD

Supports GitHub Actions:

* Run tests on commit
* Generate reports automatically
* Headless execution support

---

# 💡 Highlights

* 🔥 AI-powered self-healing locators
* 🧩 Modular POM architecture
* ⚡ UI + API combined framework
* 📊 Rich reporting with Extent
* 🧠 Intelligent failure handling

---

# 🚀 Future Improvements

* AI-based test generation
* Locator caching
* Parallel execution
* Docker support
* Cloud execution (BrowserStack / SauceLabs)

---

# 👩‍💻 Author

**Arushi Singla**

---

# 🏁 Final Note

This framework demonstrates:

👉 Stability (SmartDriver)
👉 Intelligence (AI healing)
👉 Scalability (modular design)

---

