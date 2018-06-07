<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/views/jspf/header.jspf"%>
	<table border="1">
		<c:forEach items="${patients}" var="patient">
			<tr>
				<td><strong> ${patient.firstName} ${patient.lastName}
				</strong></td>
				<td><a href="<c:url value='/patient/${patient.id}/edit'/>">EDIT</a></td>
				<td><a href="<c:url value='/patient/${patient.id}/del'/>">DEL</a></td>
				<td>

					<ul>
						<c:forEach items="${patient.procedureCode}" var="procedure">
							<li>${procedure.code9}- ${procedure.procedure} <a
								href="<c:url value='/patient/${patient.id}/procedure/${procedure.id}/del'/>">(X)</a>
							</li>
						</c:forEach>

						<%-- <c:forEach items="${patient.diseaseCode}" var="disease">
							<li>${disease.code10}- ${disease.disease} <a
								href="<c:url value='/patient/${patient.id}/disease/${disease.id}/del'/>">(X)</a>
							</li>
						</c:forEach> --%>
					</ul>
				</td>
				<td><a
					href="<c:url value='/patient/procedure/${patient.id}/assign'/>">Przydziel
						procedurę</a></td>
				<td><a
					href="<c:url value='/patient/disease/${patient.id}/assign'/>">Przydziel
						chorobę</a></td>
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="form">Dodaj nowego pacjenta</a>
	</div>
	<%@ include file="/WEB-INF/views/jspf/footer.jspf"%>
</body>
</html>