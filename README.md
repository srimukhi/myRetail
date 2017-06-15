# myRetail
Tools/Frameworks used:
•	JDK 1.8
•	Spring Tool Suite 3.8.4
•	Maven 3.x
•	MongoDB 3.4
•	JUnit
•	Mockito
•	Postman
•	Robomongo 1.0.0

Installation steps:

1.	Spring Tool Suite 3.8.4
-	Go to https://spring.io/
-	Scroll down until you see Spring Tool Suite, and select Learn more about STS
-	Now, you can select the tab on the side that says Download STS
-	A zip file is installed, extract it, and from there we can run the setup file(.exe)
-	Maven plug in comes with STS and we can add any extra dependencies from https://mvnrepository.com/

2.	MongoDB 3.4
-	Go to https://www.mongodb.com/ and select Download Enterprise 3.4
-	Select the appropriate OS and select Download
-	Now, create a folder named data under C:/ Drive. This data folder should contain another folder db
-	Having installed MongoDB, we now navigate to the MongoDB bin folder located at C:\Program Files\MongoDB\Server\3.4\bin
-	Now, open the Command prompt and navigate to this folder
-	Enter mongod, and now the connection is set up on port 27017
-	Now, we may open another command prompt and direct to the same location and enter mongo
-	We should make sure that Mongo runs on the background whenever we are making a connection to it via our application
-	We now end up with > where we can give our MongoDB commands
-	If we donot wish to use Command prompt, we can go for another MongoDB Management tool like Robomongo
3.	RoboMongo 1.0.0
-	Go To https://robomongo.org/
-	Select the download tab, select the OS and select .exe file
-	Open the installer and click next to continue by accepting the default settings
-	Finish the installation and open Robomongo tool. Here we should connect to Mongodb server port
-	For successful connection, Mongo should be up and running on the background.

4.	Postman
-	Go to Google chrome App Store, look for postman and install the plug in


Steps to run the application:

•	I have provided two runnable jar files, one for Database setup and one for the main Application that performs REST services
•	We should make sure that MongoDB runs on the background whenever we are making a connection to it via our application.
•	Now, run the DbSeeder-0.0.1—SNAPSHOT.jar in the command prompt in the following way,
  -	Navigate to the folder where the DbSeeder jar file is downloaded on your system and pass the command:
  java –jar DbSeeder-0.0.1-SNAPSHOT.jar
  -	By doing this, Spring Boot fires and the connection to the MongoDB opens and the project runs where the REST API services are d       developed.
  -	To verify, open the data store through Robomongo, and see if there is a data store named MyRetailDb. Also, check if the collection      price has values in it.
  -	Now close the connection by entering Ctrl+C in command prompt
•	Now, run the MyRetailApplication-0.0.1-SNAPSHOT jar in the prompt in the following way,
  -	Navigate to the folder where the MyRetailApplication jar file is downloaded and pass the command:
  java –jar MyRetailApplication-0.0.1-SNAPSHOT.jar
  -	By doing this,  Spring Boot fires and the connection to MongoDB opens.
  -	Now open Postman and pass the URL: For eg: localhost:8080/products/13860428, and select GET from the dropdown, now you will be able   to see the result in JSON format that has id, name and current_price object
  -	For PUT request, we provide the same URL used for making GET request : “localhost:8080/products/13860428” and select PUT from         dropdown.
  -	Go to Body->raw, select JSON and change the value of the price value.
  -	In order to verify the updated price in the local data store, we make a request in the data store and see that the price value is     changed.
  -	We can also verify it by making a GET request for the same id on Postman


