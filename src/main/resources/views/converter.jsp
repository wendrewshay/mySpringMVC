<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>HttpMessageConverter Demo</title>
</head>
<body>
	<div id="resp"></div>
	<input type="button" onclick="req();" value="请求"/>
	<script type="text/javascript" src="assets/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		function req() {
			$.ajax({
				url:"convert",
				data:"1-xiawenqiang",
				type:"POST",
				contentType:"application/x-wisely",
				success:function(data) {
					$("#resp").html(data);
				}
			});
		}
	</script>
</body>
</html>