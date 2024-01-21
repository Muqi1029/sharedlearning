const express = require("express");

const app = express();

app.use(express.static("../dist"));
app.listen(80, (err) => {
  if (err) {
    console.log(err);
  } else {
    console.log("started successfully!");
  }
});
