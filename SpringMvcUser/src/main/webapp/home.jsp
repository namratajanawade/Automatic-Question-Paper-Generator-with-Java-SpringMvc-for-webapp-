<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>My Website</title>
	<style>
		body {
			background-color: #f2f2f2;
			font-family: Arial, sans-serif;
		}
		.container {
			max-width: 800px;
			margin: 0 auto;
			padding: 20px;
		}
		h1 {
			font-size: 36px;
			color: #333;
			text-align: center;
			margin-top: 50px;
			margin-bottom: 50px;
		}
		.link-container {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-top: 50px;
		}
		.link {
			background-color: #333;
			color: #fff;
			font-size: 24px;
			padding: 20px;
			border-radius: 5px;
			text-decoration: none;
			transition: all 0.3s ease;
			box-shadow: 0 3px 6px rgba(0,0,0,0.1);
		}
		.link:hover {
			background-color: #fff;
			color: #333;
			box-shadow: 0 6px 12px rgba(0,0,0,0.15);
			transform: translateY(-3px);
		}
	</style>
</head>
<body>
	<div class="container">
		<h1>STAFF PORTAL</h1>
		<div class="link-container">
			<a href="login" class="link">Login</a>
			<a href="register" class="link">Register</a>
            <a href="index.jsp" class="link">Home</a>
		</div>

	</div>
</body>
</html>