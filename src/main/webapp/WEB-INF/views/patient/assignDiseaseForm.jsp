<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />


<title>Clinic</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
	<P>Pacjent: ${patient.firstName} ${patient.lastName}</P>

	<form method="post">

		<select name="diseaseId">
			<c:forEach items="${diseases}" var="disease">
				<option value="${disease.id}">Kod: ${disease.code10} -
					${disease.disease}</option>
			</c:forEach>
		</select> <input type="submit" />
		</div>
	</form>

	<div>
		<a href="/Clinic_Registration/patient/all">Zarządzanie pacjentami</a>
	</div>
	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>