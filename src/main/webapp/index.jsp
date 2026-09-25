<jsp:directive.page contentType="text/html; charset=UTF-8" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>Home - Facebook CRUD</title>
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-icons.css">
	</head>

	<body>

		<h1>Futura Home</h1>

		<div class="container">
			<div class="col">
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/users">Usuarios</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/posts">Posts</a>
					</li>
				</ul>
			</div>
		</div>
	</body>

	</html>