# Selenium Test Automation Project
This project is a Behavior-Driven Development (BDD) automation framework for testing Amazon's search functionality using Selenium, TestNG, and Cucumber. 
The project is structured for easy maintenance and scalability, following industry-standard practices with a focus on readability, reusability, and modularity.

## Project Structure
<img width="673" alt="Screenshot 2024-09-12 at 21 11 41" src="https://github.com/user-attachments/assets/26d4df85-bc58-4c98-9d62-41317478e80b">

## Setup and Installation

### Prerequisites

- Java Development Kit (JDK) 8 or above.
- IDE (e.g., Eclipse)
- Apache Maven (for managing dependencies).
- WebDriver executable (e.g., ChromeDriver).

### Steps to Set Up

1. **Clone the Repository**
   git clone <repository-url>
   cd <repository-directory>
2. **Install Dependencies**
   mvn install
4. **Download WebDriver** - this is not required as I have added webdrivermanager dependency in pom.xml file
   Place the WebDriver executable in the path/to/chromedriver directory, or update the path in BaseTest.java:
   System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

### Running the Tests
**Run As > Cucumber Feature**
Open feature file search_amazon.feature from features folder
Right click on it and select Run As > Cucumber Feature

**Using TestNGRunner**
Open TestNGRunner.java file from runners package
Right click on it and select Run As > TestNG Test

**Run As >TestNG Test**
Open AmazonLGSoundbarSearchTest.java file from runners package
Right click on it and select Run As > TestNG Test

### Page Object Model (POM)
The framework uses the Page Object Model (POM) design pattern to enhance test maintainability and readability. The pages/ directory contains page object classes, and each page object class is responsible for interacting with a specific page.

### Easy steps to run the project locally - 
1. Clone the repository
2. Open the project in IDE as the existing Maven project
3. Right-click on the project and Run As > Maven clean, then Run As > Maven install - to install all dependencies
5. The TestNGrunner.java file is kept in the runners package
6. Pom.XML file contains all required dependencies 

### Troubleshooting
**WebDriver Issues:** Ensure that the path to the WebDriver executable is correct and that the correct version of the WebDriver is used for your browser.
**Dependencies:** Ensure that all dependencies are correctly added to your project by running mvn install.



