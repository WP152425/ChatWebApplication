<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.dimigo.vo.UserVO" %> 
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${ pageContext.request.contextPath }" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Category</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
<link rel="stylesheet" href="${ contextPath }/css/footer.css">
<style>
div.container {
  padding-top: 30px;
  padding-bottom: 20px;
}
img.card-img-top {
	width : 100px;
	height : 100px;
	margin-left = auto;
	margin-right = auto;
}
</style>

    <%
    	UserVO user = (UserVO)session.getAttribute("user");
    %>

<script>

function menu_over(e) {
	e.setAttribute("class", "nav-item active");
}
function menu_out(e) {
	e.setAttribute("class", "nav-item");
}
 
function categoryFunction(category){
	document.getElementById("category").value = category;
	document.getElementById("name").value = "${ user.name }";
	
}
</script>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="${ contextPath }/jsp/home.jsp">Home</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  	<%@ include file="menu.jsp" %>
  	
    <%-- 세션이 없는 경우 --%>
    <c:if test="${ user == null }">
    	<a class="text-bold text-white" style="text-decoration: none" href="${ contextPath }/jsp/login.jsp">Sign in</a>
    	<span class="text-bold text-white">&nbsp; or &nbsp;</span>
    	<a class="text-bold text-white" style="text-decoration: none" href="${ contextPath }/jsp/signup.jsp">Sign up</a>
    </c:if>
    <c:if test = "${ user != null }">
    <%-- 세션이 있는 경우 --%>
	    <ul class="navbar-nav flex-row ml-md-auto d-none d-md-flex">
	    <li class="nav-item dropdown">
	      <a class="nav-item nav-link dropdown-toggle mr-md-2" href="#" id="bd-versions" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    	${ user.name }님
	      </a>
	      <div class="dropdown-menu dropdown-menu-right" aria-labelledby="bd-versions">
	      	<form action="${ contextPath }/logout.do" method="post">
	      		<button type="submit" class="dropdown-item">Sign out</button>
	      	</form>
	       	<div class="dropdown-divider"></div>
	      	<form action="${ contextPath }/jsp/info.jsp" method="post">
	        <button type="submit" class="dropdown-item">내 정보</button>
	        </form>
	      </div>
	    </li>
	    </ul>
	</c:if>
  </div>
</nav>
<div class="container">
	<form action = "${ contextPath }/ChatCategory.do" method = "post">
	<div class = "row">
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/sport.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "sportcategory">Sport</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('Sport');" type="submit">Join</button>
				</div>
			</div>
		</div>
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/icons8-communicate-50.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "languagecategory">Language</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('Language');" type="submit">Join</button>
				</div>
			</div>
		</div>
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/icons8-compact-camera-50.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "photocategory">Photo</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('Photo');" type="submit">Join</button>
				</div>
			</div>
		</div>
		<hr>
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/icons8-computer-50.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "itcategory">IT</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('IT');" type="submit">Join</button>
				</div>
			</div>
		</div>
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/icons8-game-controller-50.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "gamecategory">Game</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('Game');" type="submit">Join</button>
				</div>
			</div>
		</div>
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/icons8-movie-50.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "moviecategory">Movie</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('Movie');" type="submit">Join</button>
				</div>
			</div>
		</div>
		<hr>
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/icons8-musical-notes-50.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "musiccategory">Music</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('Music');" type="submit">Join</button>
				</div>
			</div>
		</div>
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/icons8-restaurant-50.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "foodcategory">Food</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('Food');" type="submit">Join</button>
				</div>
			</div>
		</div>
		<div class = "col-sm-4">
			<div class="card" style="width: 20rem;">
			  	<img class="card-img-top" src="${ contextPath }/image/icons8-study-50.png" alt="Card image cap">
				<div class="card-body">
					 <h4 class="card-title" id = "studycategory">Study</h4>
					 <button class="btn btn-default pull-right" onclick="categoryFunction('Study');" type="submit">Join</button>
				</div>
			</div>
		</div>
	</div>	
	<input type="hidden" name = "category" id="category" value = "" />
	<input type="hidden" name = "name" id = "name" value = ""/>
	</form>
</div>

<%@ include file="footer.jsp" %>

<!-- Bootstrap js -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

</body>
</html>