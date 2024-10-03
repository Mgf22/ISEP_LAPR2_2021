# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

### Reporting
- The system should allow the Center Coordinator to generate and view statistics, charts, reports and analyse data from other centers.

### Security
- All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits;
- Only nurses are allowed to access all user's health data;

### Notifications
- The application should send an SMS message when the vaccination event is scheduled and registered in the system;
- The system should be able to notify (e.g.: SMS or email) the user that his/her recovery period has ended.

## _Usability_
_Evaluates the user interface. It has several subcategories, among them: error prevention; interface aesthetics and design; help and documentation; consistency and standards._

### _Help and documentation_
- _The final product should include a user manual;_
- _The teams must use Javadoc to generate useful documentation for Java code._

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._


### Testability
- The development team must implement unit tests for all methods, except for methods that implement Input/Output operations;
- The JaCoCo plugin should be used to generate the coverage report.

### Adaptability
- _The application should be designed to easily support managing other future pandemic events;_
- _The application should be conceived having in mind that it can be further commercialized to other companies and/or organizations and/or healthcare systems._

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- All the images/figures produced during the software development process should be recorded in SVG format;
- _The application should use object serialization to ensure data persistence between two runs of the application;_
- _The application graphical interface is to be developed in JavaFX 11;_
- _The team must adopt best practices;_
- _The team must adopt recognized coding standards;_
- _The application must, at least, support English and Portuguese language._

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- _The application must be developed in Java;_
- _The application must be developed in IntelliJ IDE or NetBeans;_
- _The unit tests should be implemented using the JUnit 5 framework._