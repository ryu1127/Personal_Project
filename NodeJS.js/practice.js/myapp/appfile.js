var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var fs = require('fs');
var multer = require('multer');
var upload = multer({dest: 'uploads/'});

app.locals.pretty = true;

app.set('views','./views_file');
app.set('view engine', 'jade');

app.use(bodyParser.urlencoded({extended: false}));

app.get('/topic/new',function(req,res){
	fs.readdir('data',function(err,files){
		if(err){
			console.log(err);
			res.status(500).send('Internal Server Error');
		}
	res.render('new',{topics:files});
});
});

app.get(['/topic','/topic/:id'],function(req,res){
	fs.readdir('data',function(err,files){
		if(err){
			console.log(err);
			res.status(500).send('Internal Server Error');
		}
		var id = req.params.id;
		if(id){
			//id값이 있을때
			fs.readFile('data/'+id,'utf8',function(err,data){
				if(err){
					console.log(err);
					res.status(500).send('Internal Server Error');
				}
				res.render('view',{topics:files, title:id, description:data});
			});
		}
		else{
			//id값이 없을때
			res.render('view',{topics:files,title:'Welcome',description : ' Hello Javascript for Server.'});
		}
	})
});
app.post('/topic',function(req,res){
	var title = req.body.title;
	var description = req.body.description;
	fs.writeFile('data/'+title,description,function(err){
		if(err){
			console.log(err);
			res.status(500).send('Internal Server Error');
		}
		res.redirect('/topic/'+title);
		//사용자를 그 경로로 보내버리는 것
	})

})

app.post('/upload',upload.single('avatar'),function(req,res){
	res.send('Uploaded : '+req.file.filename);
	console.log(req.file);
})
;
app.listen(3000,function(){
	console.log('Connected at 3000 port!');
});
