# Exercise 0: Hello World

Given a [basic structure](/exercises/skeleton): `build.gradle` with all the required dependencies and a `Main.kt` file,
create a simple **embedded server** using **Netty** as backend and using **Routing** feature,
reply `Hello World` for **`GET /`** requests.

## Proposed steps for this exercise:

* Create the simplest ktor application that can run: an embedded Netty server listening to port 8080
* Add the Routing feature and configure a GET route for root “/” and response with a plain text `Hello World`.
* Extract your GET / route to an extension method
* Change that Hello World to html using Kotlin HTML DSL to generate: `<h1>Hello World</h1>`
