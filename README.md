# Currency-Converter-Unit-Test
Currency-Exchange project includes all the currencies in the world.   

This project was made to practice unit testing in java   

**PLEASE READ THE SETUP PROJECT AND NOTES VERY CAREFULLY BEFORE RUNNING THE PROJECT!!!**  

Technologies used:   
1.) Spring boot maven project (backend)   
2.) React.js (frotnend)   
3.) Junit 5  
4.) Mockitio mocking framework   
5.) Postman for api testing and html reports  
6.) Selenium IDE and Selenium webdriver for testing the frontend

How to setup project:  
&emsp; 1.) Download or clone respo  
&emsp; 2.) for backend: build project or run project   
&emsp; 3.) for frontend, run npm install in terminal when opening the frontend path in it
&emsp; 4.) **wHEN FIRST RUNNING THE SERVERS PLEASE CALL THE FOLLWOING API OR CLICK ON THE UPDATEdb

Notes: 
1.) Backend will run on localhost:8080, frontend will run on localhost:3000  
2.) Backend APIs are :  
&emsp;* http://localhost:8080/api/v1/currency/exchange?targetCurrency=<targetCurrency>&sourceCurrency=<SourceCurrency>&rateSource=<API or DB> --> returns double value  
&emsp;* http://localhost:8080/api/v1/currency/shorthand --> return a list of currencies in shorthand  
&emsp;* http://localhost:8080/api/v1/currency/updateDB --> Updates and fills the database with curriences and exhangerates  
