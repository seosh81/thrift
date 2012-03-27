var thrift = require('thrift');

var User = require('./gen-nodejs/UserService.js'),
    ttypes = require('./gen-nodejs/user_types.js');

var connection = thrift.createConnection('203.245.50.98', 9090),
    client = thrift.createClient(User, connection);

var user = new ttypes.User({id:"seosh81", name:"howard", password:"1234"});

connection.on('error', function(err) {
    console.error(err);
});

console.log('hi');
client.store(user, function(err, res) {
    console.log('in store');
    if (err) {
	console.error(err);
    } else {
	console.log("client stored:", user.name);
	client.get('seosh81', function(err, response) {
	    if (err) {
		console.error(err);
		connection.end();
	    } else {
		console.log("client get:", response.name);
		connection.end();
	    }
	});
    }
});
