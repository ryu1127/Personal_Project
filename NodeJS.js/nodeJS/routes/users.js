var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

router.get('/key',function(req,res){
  fs.html("./index.html");
});

module.exports = router;
