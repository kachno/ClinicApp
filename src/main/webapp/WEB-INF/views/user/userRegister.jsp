<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Clinic App - Register user</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
	<form:form method="post" modelAttribute="user">
		<div>
			Użytkownik:
			<form:input path="login" />
			<form:errors path="login" />
		</div>
		<div>
			Hasło:
			<form:password path="password" />
			<form:errors path="password" />
		</div>
		<div>
			Imię:
			<form:input path="firstName" />
			<form:errors path="firstName" />
		</div>
		<div>
			Nazwisko:
			<form:input path="lastName" />
			<form:errors path="lastName" />
		</div>
		<div>
			<input type="submit" name="Submit" value="Zatwierdź" />

		</div>
	</form:form>
	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>