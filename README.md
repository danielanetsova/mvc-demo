# Name

MVC Project: Next Level Technologies

## Description

Application is able to import hard-formatted data easily and support functionality for also exporting the imported data.

## Tasks
### _Model Definition_
There are four models that the **nlt** database application should contain in its functionality - Company, Project, Employee
and User. Design them in the most appropriate way, considering the following data constraints:
1) Company
	- id – integer number, primary identification field.
	- name – a string (required).
2) Project
	- id – integer number, primary identification field
	- name – a string (required)
	- description – a very long text description (required)
	- is finished – a Boolean
	- payment – Big Decimal (required)
	- start date – a date
	- company – a Company entity (required)
3) Employee
	- id – integer number, primary identification field
	- first name – a string (required)
	- last name – a string (required)
	- age – Integer(required)
	- project – a Project entity(required)
4) User
	- id – integer number, primary identification field
	- username – a string (required, unique)
	- password – a string (required)
	- email – a string (required, unique)

### _Data Import_
Use the provided files from _src/main/resources/files/xmls_ to populate the database with data. Import all the information from those files into the
database. Validation should be done.

### _Data Export_

* _Query 1 – Finished Projects_
 
Export all projects which have been finished in the specified format:

Project Name: .....<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Description: .....<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{project's payment}<br />

- _Query 2 – Employees with Age Above 25_

Export the employees with age above 25 in the specified format:

Name: {employee's full name}<br />
&nbsp;&nbsp;&nbsp;&nbsp;Age: .....<br />
&nbsp;&nbsp;&nbsp;&nbsp;Project Name: .....<br />
