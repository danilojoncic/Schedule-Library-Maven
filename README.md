# Schedule-Library-Maven
## Overview
A simple and generic schedule library, using Apache Maven build software, the following maven commands are necessary for any type of modification
- mvn clean install
- mvn package
- mvn test
### Features
- Compile-time specification module
- Run-time implementation module
- gui which includes both as well as many others libraries as maven dependencies
- Importing a schedule contained in a .json or .csv file
- Editing of individual rows
- Creation of new rows
- Deletion of not important columns
- Addition a calendar linked column for either representing starting and ending dates of an event or a simple date (means duplicates will be created for all the dates within the period)
- Filtering the contents of the schedule on any keyword containd in its rows
- Exporting of the now edited, mangled or even ruined schedule to either .json or .csv files
### Tests
- All the tests are also provided and can be found in the coresponding test directories
- All the test were written in the JUnit 5 testing framework, the test directiories include several unit and integration tests as well as all the test resources required
## Requirements
- JDK 17
- Maven
## How to run
### Build yourself
- Clone the repository
- Traverse into specification
- Run the following command in the terminal `mvn clean install
- Traverse into implementation
- Run the following command in the terminal `mvn clean install
- Traverse into gui and run it like any other java app
### Run the provided .jar
- Clone the repository
- Locate the provided "fat jar"
- Run the following command in the terminal `java -jar \path\to\filename.jar
## Issues
- As the gui was written to be minimalistic in the code and in its appearance, please use "Common Sense" when exploring the features
- Excepetion handling is missing for several suspicious actions
- Using the calendar button requires the imported schedule to have a collumn named "Dan" on the index field 4, so if the imported schedule does not have it, unfortunately this feature is not advised to be used as it will lead to several exception or the program freezing
- Please dont use for any serious schedule "enhancments"
## Thanks
Huge thanks to my friend Aleksa Aleksic that helped out with writting and planning this "mess"
## Images
![image](https://github.com/user-attachments/assets/33594ad4-9c8a-451b-be1c-5d7fff351ac2)
![image](https://github.com/user-attachments/assets/be4ff03b-8544-4826-bd01-57edd9c9e13f)
![image](https://github.com/user-attachments/assets/0d372c26-4670-42ca-abd8-9ac30edbf124)
![image](https://github.com/user-attachments/assets/857f9e8d-a970-41cc-9566-b7c0d1de2bb1)
![image](https://github.com/user-attachments/assets/6a1d89d7-e302-4409-9eb4-ef4ac4cd655f)
![image](https://github.com/user-attachments/assets/37ea551b-3a35-4c7c-87e5-5fcf6af0a315)
![image](https://github.com/user-attachments/assets/51270b87-a161-4cf8-aadd-63cb0baaa568)
![image](https://github.com/user-attachments/assets/66eb3b57-38ed-4bf9-b983-6e6b9269dbbd)






