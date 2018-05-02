var express = require('express');
var router = express.Router();
var path = require('path');
var root = '/Volumes/internal/Google_Drive/nodeJS_Mac/public/html/';
var fs = require('fs');



/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

router.get('/index',function(req,res){
  // res.sendFile(root + 'index.html');
  res.render('index');
})

module.exports = router;
