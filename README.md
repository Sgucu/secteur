#*******************************This code is just for practise it uses passwords and sql code that is developed for test purpose only******************************* 

So you have one end point called /upload/{destination} 

Destination parameter is whether you use "Oracle" or "Postgres" database. When you pass one it will save data to Oracle and when you pass other to Postgres.


![image](https://user-images.githubusercontent.com/13905523/155586498-58f14caf-1f28-421a-8fca-de791e6bc482.png)


So here I use postman to test, It’s important to be POST method of course, and write Oracle and select a file. 
And I set some columns you didn't ask but I taught it will help you later. (You can delete if not needed) 
The tables has its ID, Destination (Ora. Or Pos.), name of the file, its type (you can upload anything not just SQL) and I added some comment its always useful for stuff like this, and finally the field to store the file, attachmentData. 

As you can see I named table secteur_application_data_new, you can just change the name. If your database is set up to allow that, just by calling post method, it will create the table and all the columns. 

***Last thing  application.properties file where you should set your databases and credentials. 

![image](https://user-images.githubusercontent.com/13905523/155586807-ca3d2f5a-c08b-4552-b0a1-8e0a079226c7.png)


