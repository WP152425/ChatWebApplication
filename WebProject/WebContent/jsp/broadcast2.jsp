<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />
    
<!DOCTYPE html>
<html>

<head>
	<title>Chatting Room</title>
	<meta http-equiv="Content-Type" content = "text/html; charset=UTF-8">
	<meta name = "viewprot" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${ contextPath }/css/bootstrap.css">
	<link rel="stylesheet" href="${ contextPath }/css/socket.css">
	<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<%
    	String category = (String)session.getAttribute("category");
		String chatName = (String)session.getAttribute("chatName");
   	 %>
	<script type="text/javascript">
		var lastID = 0;  
		function submitFunction(){
			var chatName = "${ chatName }";
			var chatContent = $('#chatContent').val();
			var chatCategory = "${ category }";
			$.ajax({
				type : "POST",
				url : "${ contextPath }/ChatSubmit.do",
				data : {
					chatName : encodeURIComponent(chatName),
					chatContent : encodeURIComponent(chatContent),
					chatCategory : chatCategory
				},
				success : function(result){
					if (result == 1){
						autoClosingAlert('successMessage', 2000);
					} else if (result == 0){
						autoClosingAlert('dangerMessage', 2000);
					} else {
						autoClosingAlert('warningMessage', 2000);
					}
				}
			});
			$('#chatContent').val('');
		}
		function autoClosingAlert(selector, delay) {
			var alert = document.getElementById(selector); 
			alert.style.display = 'block';
			window.setTimeout(function(){alert.style.display = 'none'}, delay);
		}
		function chatListFunction(type){
			var chatCategory = "${ category }";
			$.ajax({
				type : "POST",
				url : "${ contextPath }/ChatList.do",
				data : {
					listType : type,
					chatCategory : chatCategory
				},
				success : function(data){
					if (data == "") return;
					var parsed = JSON.parse(data);
					var result = parsed.result;
					for (var i = 0; i < result.length; i++) {
						addChat(result[i][0].value, result[i][1].value, result[i][2].value);
					}
					lastID = Number(parsed.last);
					
				}
			});
		}
		function addChat(chatName, chatContent, chatTime){
			var text = '<div class="row">' +
			'<div class="col-lg-12">' +
			'<div class="media">' +
			'<a class="pull-left" href="#">' +
			'<img class="media-object img-circle" src="${ contextPath }/image/anony.jpg">' +
			'</a>' +
			'<div class="media-body">' +
			'<h4 class="media-heading">' + chatName +
			'<span class="small pull-right">' + chatTime + '</span>' +	
			'</h4>' +
			'</div>' +
			'<p>' + chatContent + '</p>' +
			'</div>' +
			'</div>' +
			'</div>' +
			'</div>' +
			'<hr>';
			var inChatList = document.getElementById("chatList").innerHTML;
			var list = inChatList.concat(text);
			document.getElementById("chatList").innerHTML = list;
			$('#chatList').scrollTop($('#chatList')[0].scrollHeight);
		}
		
		function getInfiniteChat(){
			setInterval(function() {
				chatListFunction(lastID);
			}, 1000);
		}
		
	</script>
</head>
 
<body>
	<div class="container">
		<div class="container bootstrap snippet">
			<div class="row">
				<div class="col-xs-12">
					<div class="portlet portlet-default">
						<div class="portlet-heading" style="line-height : 13px">
							<div class="portlet-title">
								<h4><i class="fa fa-circle text-green" id = "category">${ category } Chatting Room</i></h4>
							</div>
							<br>
								<span class="big pull-right">${ user.name }님</span>
							<div class="clearfix"></div>
						</div>
						<div id="chat" class="panel-collapse collapse in">
							<div id = "chatList" class="portlet-body chat-widget" style="overflow-y: auto; width : auto; height : 600px;"></div>
							<div class="portlet-footer">
								<div class="row" style="height:90px">
									<div class="form-group col-xs-10">
										<textarea style="height:80px;" id="chatContent" class="form-control" placeholder="메시지를 입력하세요." maxlength=100></textarea>
									</div>
									<div class="form-group col-xs-2">
										<button type="button" class="btn btn-default pull-right" onclick="submitFunction();">전송</button>
										<div class="clearfix"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="alert alert-success" id="successMessage" style="display : none;">
			<strong>메시지 전송에 성공하였습니다.</strong>
		</div>
		<div class="alert alert-danger" id="dangerMessage" style="display : none;">
			<strong>이름과 내용을 모두 입력해주세요.</strong>
		</div>
		<div class="alert alert-warning" id="warningMessage" style="display : none;">
			<strong>데이터베이스 오류가 발생했습니다.</strong>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function () {
			chatListFunction('ten');
			getInfiniteChat();
		});
	</script>
</body>
</html>