# Exercise 1: HTML DSL, POST and Sessions

Using [Hello World](/exercises/exercise0-hello-world.md) as base, create a `GET /login` route with an HTML form
requesting user and password, and a `POST /login` route that adds the user name to the session in the case
`user == password` while redirecting to `/`, or displaying an error if user and password are not the same. Also
you should modify `GET /` to display `Hello $user` in the case there is an user in the session or `Hello World`
if no session or no user is specified in the session.

## Proposed steps for this exercise:

* Create a route GET /login that will return an HTML form: user + password + submit pointing to POST /login constructed using Kotlin HTML DSL. `<form action=”/login” method=”post”><input type=”text” name=”user” /><input type=”password” name=”password” /><input type=”submit” /></form>`
* Create a POST /login route that displays the username and password with each character replaced by a * (optionally).
* Install Sessions feature using data class MySession(val user: String) as the session class
* Change POST /login to create a session with the user name on it if the password is the same as the user and change it to redirect to “/”
* Change GET / to create optionally get the session MySession, if not available keep returning Hello World, and if it is available, changing it to Hello $user. Plus: escape potentially
