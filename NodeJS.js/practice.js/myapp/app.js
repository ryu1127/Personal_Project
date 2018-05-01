var express = require('express');
var app = express();
var bodyParser = require('body-parser');

app.use(express.static('public'));
app.use(bodyParser.urlencoded({extended: false}));

app.get('/', function(req,res){
	res.send('Hello index!');
})

app.get('/users',function(req,res){
	res.send('Hello Users!');
})
app.get('/route',function(req,res){
	res.send('Hello Router,<img src = "/DreamOn.png">');
})

app.get('/url',function(req,res){
	var topics = [
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

app.get('/dynamic',function(req,res){
	var lis = '';
	for(var i=0;i<5;i++){
		lis = lis + '<li>coding</li>';
	}
	var time = Date();
	var output = `
	 <!DOCTYPE html>
  <html>
    <head>
      <meta charset="utf-8">
      <title></title>
    </head>
    <body>
        Hello, Dynamic!
        <ul>
          ${lis}
        </ul>
        ${time}
    </body>
  </html>`;
  res.send(output);
});

app.get('/url/:name/:number',function(req,res){
	res.send(req.params.name+','+req.params.number);
});

app.post('/form_receiver',function(req,res){
	var title = req.body.title;
	var description = req.body.description;
	res.send(title + ', '+ description);
})

app.get('/form_receiver',function(req,res){
	var title = req.query.title;
	var description = req.query.description;
	res.send(title+', '+description);
})
app.listen(3000,function(){
	console.log('Server running at 3000 port!');
})