var express = require('express');
var router = express.Router();
var fs = require('fs');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});
router.get('/key',function(req,res,next){
  fs.readFile('/public/html/index.html',function(err,data){
    res.writeHead(200,{'Content-Type':'text/html'});
    res.end(data);
  })
});



module.exports = router;
