# Exercise 1: HTML DSL, POST and Sessions

This exercise teaches you how to:


* Use [kotlinx.html](https://github.com/Kotlin/kotlinx.html) to return HTML.
* Parse input from requests (both query fields via GET and body content using POST).
* Use the **session** object to store session information.


requesting user and password, and a `POST /login` route that adds the user name to the session in the case
`username == password` while redirecting to `/`, or displaying an error if user and password are not the same. Also
you should modify `GET /` to display `Hello $user` in the case there is an user in the session or `Hello World`
if no session or no user is specified in the session.

## Proposed steps for this exercise:

* Create a route that responds to a GET request to the path `/login`, returning an HTML form using **kotlinx.html**. The form should contain two input fields: user, password and a submit button with action POST `/login`.
* Create a route that responds to POST `/login` that validates the credentials (simple check such that user === password). If the credentials are correct, then it should add the user to the session page and redirect to `/` where it respond with plain text `Hello $username` where `$username` is the value stored in the session.
