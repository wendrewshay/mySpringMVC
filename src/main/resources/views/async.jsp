<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>servlet async support</title>
</head>
<body>
	<div id="msgFromPush"></div>
	<script type="text/javascript" src="assets/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		deferred();
		function deferred() {
			$.get('defer',function(data){
				console.log(data);
				deferred();
			})
		}
	</script>
</body>
</html>