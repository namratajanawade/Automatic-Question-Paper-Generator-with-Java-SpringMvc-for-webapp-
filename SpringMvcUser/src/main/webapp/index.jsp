<!DOCTYPE html>
<html>
<head>
	<title>Welcome to Online Test Application</title>
	<style>
		body {
			background-color: #f5f5f5;
			font-family: Arial, Helvetica, sans-serif;
		}
		
		.container {
			position: absolute;
  			top: 50%;
  			left: 50%;
  			transform: translate(-50%, -50%);
  			padding: 50px 0;
			margin: 0 auto;
			width: 60%;
			text-align: center;
			background-color: #fff;
			border-radius: 10px;
			box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
		}
		
		h1 {
			color: #428bca;
			font-size: 36px;
			margin-bottom: 20px;
		}
		
		button {
			background-color: #428bca;
			color: #fff;
			padding: 12px 24px;
			border: none;
			border-radius: 5px;
			font-size: 18px;
			margin: 10px;
			cursor: pointer;
			transition: background-color 0.3s ease;
		}
		
		button:hover {
			background-color: #357ebd;
		}
		
		img {
			max-width: 100%;
			height: auto;
			margin: 20px 0;
		}
	</style>
</head>
<body>
	<div class="container">
		<h1>Online Test Application</h1>
		<br>
		<br>
		<br>
		<button onclick="location.href='home.jsp'">Staff Login</button>
		<button onclick="location.href='homestudent.jsp'">Student Login</button>
	</div>
</body>
</html>
