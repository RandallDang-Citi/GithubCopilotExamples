const express = require('express');
const cors = require('cors');
const app = express();

const getUser = require('./server/getUser');

app.set('port', 3000);

app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.get('/', (req, res, next) => {
  res.send('Welcome to Mock API!');
});

app.use('/getUser', getUser);

app.listen(app.get('port'), () => {
  console.log(`Mock API is running in http://localhost:${app.get('port')}/`);
});

module.exports = app;
