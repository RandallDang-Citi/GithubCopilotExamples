// create a API with Express to query the data from the database
// import the express module
const express = require("express");
// create a new express app
const app = express();


// create a method to get users
const getUsers = () => {
    return [{id: '1', name: 'John', role: 'admin'}, {id: '2', name: 'Juan', role: 'developer'}];
}


// create a GET route to query the user by name
// url path: /users?name=John or /users
app.get("/users", (req, res) => {
    // get the name from the query string
    const name = req.query.name;
    // get the users
    const users = getUsers();
    // filter the users by name
    const filteredUsers = users.filter((user) => user.name === name);
    // return all users if no name is provided
    if (!name) {
        res.send(users);
        return;
    }
    // return the filtered users
    res.send(filteredUsers);
    return;
});

// create a method to start the express server
const startServer = () => {
  // start listening on port 3000
  app.listen(3000, () => {
    // log a message
    console.log("Server is listening on port 3000");
  });
};

// start the express server
startServer();
