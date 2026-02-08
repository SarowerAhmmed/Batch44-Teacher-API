Feature: check create data
Scenario Outline: as a user, i can create new data in API CURD function test
Given setup post UPL & file json file
When send request and get response for post
Then valide API basic valition
And validate json key "<Key>"
And validate json value "<Value>"
Examples:

|Key					|Value|
|employeeId 			|EMP001|
|name					|David|
|salary					|8000|
|skills					|Selenium|
|data					|John|
|projects				|Banking App|
|projects				|101|
