# Exercise 0: Hello World

This basic structure allows you to build a simple application with Ktor that should use an **embedded server** using **netty**. 
The example should have two endpoints (/plainRoute and /htmlRoute) that responds with `Hello World` in plain text and HTML respectively.


## Proposed steps for this exercise:

* Create the simplest Ktor application using an embedded Netty server listening on port 8080
* Using the **Routing** Feature to configure a GET route for the path “/plainTextRoute” that responds with a plain text `Hello World` using `call.respondText`.
* Add a new GET route for the path "htmlRoute"  that responds with `<h1>Hello World</h1>` using `call.respondText`, passing in the content type.   
