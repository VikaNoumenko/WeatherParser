<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>??????</title>
</head>
<body>

<br>
<c:set var="city" scope="session" value="${wb.city}"/>
<c:set var="error" scope="session" value="${exception.error}"/>
<c:if test="${not empty city}">
    <table border="1">
        <tr>
            <td>?????</td>
            <td> ${wb.city}</td>
        </tr>

        <tr>
            <td>???????????</td>
            <td>${wb.temperature}&deg;C</td>
        </tr>


    </table>
</c:if>
</body>
</html>