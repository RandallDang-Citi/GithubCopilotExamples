const express = require('express');
const router = express.Router();

const userList = require('./user.json');

router.get('/allUser', (req, res, next) => {
  res.send(userList);
});

// get user by name
router.get('/users', (req, res, next) => {
  const { query: { name } } = req;
  if (!name) {
    res.status(200).send(userList);
  } else {
    const user = userList.find((user) => user.name === name);
    if (user) {
      res.status(200).send(user);
    } else {
      res.status(200).send([]);
    }
  }
});

module.exports = router;
