# Currency-Converter-Unit-Test
Currency-Exchange project includes all the currencies in the world.

This project was made to practice unit testing in java

Technologies used:   
1.) Spring boot maven project (backend)   
2.) React.js (frotnend)   
3.) Junit 5  
4.) Mockitio mocking framework   
5.) Postman for api testing and html reports  
6.) Selenium IDE and Selenium webdriver for testing the frontend

How to setup project: 
1.) Download or clone respo  
2.) for backend: build project or run project   
3.) for frontend, run npm install in terminal when opening the frontend path in it   


Notes: 
1.) Backend will run on localhost:8080, frontend will run on localhost:3000  
2.) Backend APIs are :  
    * http://localhost:8080/api/v1/currency/exchange?targetCurrency=<targetCurrency>&sourceCurrency=<SourceCurrency>&rateSource=<API or DB> --> returns double value  
    * http://localhost:8080/api/v1/currency/shorthand --> return a list of currencies in shorthand
    * http://localhost:8080/api/v1/currency/updateDB --> Updates and fills the database with curriences and exhangerates
