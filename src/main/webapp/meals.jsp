<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<c:forEach items="${mealList}" var="meal" >
<h2>
    <c:out value="${meal.getDescription()}"></c:out>
 </h2>
</c:forEach>
</body>
</html>
