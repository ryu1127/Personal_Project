var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');



//URL
var index = require('./routes/index');
var users = require('./routes/users');
var test = require('./routes/test');





var app = express();

// view engine setup
//여기 세가지 를 이용하여 views 안에 있는 폴더에서 선택하여 사용할 수 있게 되었다.
//여기서 오류나면 npm install ejs --save 를 통하여 ejs 저장해주면 오류 안남.
app.set('views', path.join(__dirname, 'views'));
app.engine('html',require('ejs').renderFile);
app.set('view engine', 'ejs');
//npm install supervisor -g 를 통하여 supervisor저장하면 저장시 바로바로 반영되게 만들 수 있다.



// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));



//URL Routing
app.use('/', index);
app.use('/users', users);
app.use('/test',test);





// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
