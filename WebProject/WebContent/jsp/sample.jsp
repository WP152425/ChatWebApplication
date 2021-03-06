<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html> 
<head> 
<style> 
.footer { width: 100%; height: 50px; position: fixed; bottom: 0; } 
</style> 
</head> 
<body> 
<div class='j-message'> 
</div> 
<div class='j-footer'> 
<table> 
<tr> 
<td width='100%'> 
<input id='message-input' type='text'> 
</td> 
<td width='20%'> 
<button id='message-button' type='submit'> 전송 </button> 
</td> 
</tr> 
</table> 
</div> 
<script type="text/javascript" src="http://chat.socket.io/socket.io/socket.io.js"></script> 
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script> 
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script> 
<script> 
var serverURL = 'localhost:50000'; 
var name = 'jin'; 
var room = '100'; 
$(document).ready(function() { 
	var socket = io.connect(serverURL); 
	socket.on('connection', function(data) { 
		if(data.type == 'connected') { 
			socket.emit('connection', { type : 'join', name : name, room : 100 }); 
			} 
		}); 
	socket.on('system', function(data) { 
		writeMessage('system', 'system', data.message); 
		}); 
	socket.on('message', function(data) { 
		writeMessage('other', data.name, data.message); 
		}); 
	$('#message-button').click(function() { 
		var msg = $('#message-input').val(); 
		socket.emit('user', { name : name, message : msg }); 
		writeMessage('me', name, msg); }); 
	function writeMessage(type, name, message) { 
		var html = '<div>{MESSAGE}</div>'; 
		var printName = ''; 
		if(type == 'me') { 
			printName = name + ' : '; 
		} 
		html = html.replace('{MESSAGE}', printName + message); 
		$(html).appendTo('.j-message'); } 
	}); 
</script> 
</body> 
</html>