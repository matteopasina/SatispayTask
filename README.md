# Satispay Task

This repo contains a maven project to solve the exercise:  
"Satispay Signature implementation: Satispay API gateway uses the  "http signature"  authentication type to authorise its clients."

To run the project you need java 8 and maven 3.  

The code is divided in 4 packages:
* **authentication**: contains the logic to compute the authorization signature 
* **model**: contains the model of the request and the response
* **services**: contains the service to make and handle the REST calls
* **utils**: contains some utils classes like the constants

The code is tested with some unittest and connected to travis CI for continuous build.
