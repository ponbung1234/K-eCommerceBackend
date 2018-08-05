<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
for(int i = 0 ; i < 100; i++){
	out.print("Payment in progress");
	if(i%3==0)
		out.print("Payment in progress.");
	if(i%3==1)
		out.print("Payment in progress..");
	if(i%3==2)
		out.print("Payment in progress...");
	Thread.sleep(500);
}
%>
	
</body>
</html>