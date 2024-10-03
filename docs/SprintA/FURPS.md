# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._



### User Scenarios

- The application should allow the SNS user to schedule his/her vaccination
- The application should check the vaccination center capacity for that day/time and, if possible, confirm that the vaccination is scheduled and inform the user that (s)he should be at the selected vaccination center at the scheduled date.
- The receptionist should be able to register the arrival of the user.
- The application should allow the receptionist to check the list of users that are present in the vaccination center.
- The system should allow the nurse to check user info and health conditions.
- The system should allow the nurse to register that the vaccine has been given as well as some information about them.
- The system should allow the nurse to register any adverse reactions during the recovery period of each user.
- The system should allow the Administrator to register centers, SNS users, center coordinators, receptioninsts and nurses to the vaccination process.

### Reporting
- The system should allow the Center Coordinator to generate and view statistics, charts, reports and analyse data from other centers.

### Security
- All those who wish to use the application must be authenticated with a password holding seven alphanumeric characters, including three capital letters and two digits;
- Only nurses are allowed to access all user's health data;

### Notifications
- The application should send an SMS message when the vaccination event is scheduled and registered in the system.
- The system should be able to notify (e.g.: SMS or email) the user that his/her recovery period has ended.

## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._



### Testability
- The development team must implement unit tests for all methods, except for methods that implement Input/Output operations;
- The unit tests should be implemented using the JUnit 5 framework;
- The JaCoCo plugin should be used to generate the coverage report.

### Adaptability
- The application should be designed to easily support managing other future pandemic events
- The application should be conceived having in mind that it can be further commercialized to other companies and/or organizations and/or healthcare systems

## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

- All the images/figures produced during the software development process should be recorded in SVG format;
- The application must be developed in Java;
- The application must be developed in IntelliJ IDE or NetBeans;
- The teams must use Javadoc to generate useful documentation for Java code.
- The application should use object serialization to ensure data persistence between two runs of the application.
- The application graphical interface is to be developed in JavaFX 11

### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

- The team must adopt best practices;
- The team must adopt recognized coding standards;
- The application must, at least, support English and Portuguese language