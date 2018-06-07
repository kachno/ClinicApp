<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
</head>
<body>
<div><a href="../patient/form">Zarejestruj pacjenta</a></div>
	<h1>Lista pacjentów</h1>
	<c:forEach items="${patients}" var="patient">
${patient.firstName} ${patient.lastName} <a href="<c:url value='/patient/${patient.id}/edit'/>">EDIT</a> <a href="<c:url value='/patient/${patient.id}/del'/>">DEL</a><br><br>
	</c:forEach>
</body>
</html>