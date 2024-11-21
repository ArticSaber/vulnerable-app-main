This application is written in Java and uses Spring Boot. It requires Java 11 or higher.
It is a very small application and was created to demo some application security testing tools.
The application consists of a register, login and post page.

The endpoints are:
+ /           (GET)
+ /login      (GET/POST)
+ /register   (GET/POST)
+ /logout     (GET)
+ /posts      (GET)
+ /post       (POST)
+ /post/{id}  (GET)

The repository contains a packaged version of the application as the file vulnapp-0.0.1-SNAPSHOT.jar.
You can run the application locally with the following command: 
<p><code>java -jar vulnapp-0.0.1-SNAPSHOT.jar</code></p>