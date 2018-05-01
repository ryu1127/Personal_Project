var express = require('express');
var router = express.Router();

router.get('/',function(req,res){
	var topic = [
	'Javascript is...',
	'Nodejs is...',
	'Express is...'
	];

	var output = `
	<a href="/URL?id=0">JavaScript</a><br>
  	<a href="/URL?id=1">Nodejs</a><br>
  	<a href="/URL?id=2">Express</a><br><br>
  	${topics[req.query.id]}
  `
  res.send(output);
});

module.exports= router;