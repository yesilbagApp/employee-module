
## Getting Started

### Prerequisites

The microservice runs with Java 1.8. It requires SQL service and Lombok plugin.

### Installation
## Table of Contents

* [About the Microservice](#about-the-microservice)
* [Getting Started](#getting-started)
    * [Prerequisites](#prerequisites)
    * [Installation](#installation)
    * [Configurations](#configurations)
        * [Application](#application-configurations)
        * [Database](#database-configurations)
    * [Debug](#debug)

* [Outbound Services](#outbound-services)
* [Usage](#usage)
* [Roadmap](#roadmap)
* [Latest's Clients](#latests-clients)
* [API Resources](#api-resources)

<!-- ABOUT THE MICROSERVICE -->
## About the Microservice

Employee microservice is the main service to create day off request. All employees related transactions can be performed through this service.
It provides apis to gather information about employees.

* MySQL must be installed and created "employee" schema
* JAVA8 SDK must be installed. Oracle account is required for this setup. You can download the SDK by opening an Oracle account with your work mail. https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* After installing Java, the necessary definitions should be made by following the https://javatutorial.net/set-java-home-windows-10 document.
* IDE must be installed for Java development. (We recommend "Version: 2019-06" version for Eclipse, 2019.2 version for intellij idea). Download link: https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC


### Debug

The application can be downloaded to local repository by the following command after configuring SSH settings :

  ```
  git clone https://github.com/yesilbagApp/employee-module
  git checkout master
 
  ```

## API Resources

| Code | Method Description | Endpoint |
| ------ | -------- | ------ |
|   APIAPPROVEDAYOFFREQUESTPOST  |  Approved Day Off Request By Day Off Request Id    | /api/employee/approve/dayOffRequest/{dayOffRequestId} |
|	APIDAYOFFREQUESTPOST	|	Create Day Off Request | /api/employee/dayOffRequest |
|	APIDELETEEMPLOYEEDELETE	|	Delete Employee By Employee Id	|	/api/employee/delete/{employeeById}	|
|	APIGETALLEMPLOYEESGET	|	Get All Employees	|	/api/employee/getAllEmployees	|
|	APIGETALLPENDINGREQUESTTOMANAGERGET	|	Get All Pending Request To Manager By Manager Id	|	/api/employee/getAllPendingRequestToManager/{managerId}	|
|	APIGETALLREQUESTTOMANAGERGET	|	Get All Request To Manager By Manager Id	|	/api/employee/getAllRequestToManager/{managerId}	|
|	APIGETEMPLOYEEBYIDGET	|	Get Employee By Employee Id	|	/api/employee/getEmployeeById/{employeeId}	|
|	APIGETEMPLOYEEBYNAMEGET	|	Get Employee By Employee Name	|	/api/employee/getEmployeeByName/{name}	|
|	APIREJECTEDDAYOFFREQUESTPOST	|	Rejected Day Off Request By Day Off Request Id	|	/api/employee/rejected/dayOffRequest/{dayOffRequestId}	|

