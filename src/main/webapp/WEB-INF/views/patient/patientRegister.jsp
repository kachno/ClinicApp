<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Clinic App - Register patient</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
	<form:form method="post" modelAttribute="patient">
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
			Adres:
			<form:input path="address" />
			<form:errors path="address" />
		</div>
		<div>
			Telefon:
			<form:input path="phone" />
			<form:errors path="phone" />
		</div>
		<div>
			PESEL:
			<form:input path="pesel" />
			<form:errors path="pesel" />
		</div>
		<div>
			<input type="submit" name="Submit" value="Zatwierdź" />

		</div>
	</form:form>
	<div><a href="/Clinic_Registration/patient/all">Zarządzanie pacjentami</a></div>
	 <%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>