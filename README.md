# Currency-Converter-Unit-Test
done by: Hanna Hinn 1190336, Ghazi Tarazi 1180391, Malek Aqel 1182405.  
The currency-Exchange project includes all the currencies in the world.   

This project was made to practice unit testing in Java   

 """ **PLEASE READ THE SETUP PROJECT AND NOTES VERY CAREFULLY BEFORE RUNNING THE PROJECT!!!**  

Technologies used:   
1.) Spring boot maven project (backend)   
2.) React.js (frontend)   
3.) Junit 5  
4.) Mockitio mocking framework   
5.) Postman for API testing and HTML reports  
6.) Selenium IDE and Selenium web driver for testing the frontend

How to set up the project:  
&emsp; 1.) Download or clone repo    
&emsp; 2.) for backend: run build project  
&emsp; 3.) For frontend, run npm install in the terminal when opening the frontend path in it     





  
Notes:   
1.) The backend will run on localhost:8080, and the front will run on localhost:3000  
2.) Backend APIs are :  
&emsp;* http://localhost:8080/api/v1/currency/exchange?targetCurrency={targetCurrency}&sourceCurrency={SourceCurrency}&rateSource={API or DB} --> returns double value  
&emsp;* http://localhost:8080/api/v1/currency/shorthand --> return a list of currencies in shorthand  
&emsp;* http://localhost:8080/api/v1/currency/updateDB --> Updates and fills the database with currencies and exchange rates

