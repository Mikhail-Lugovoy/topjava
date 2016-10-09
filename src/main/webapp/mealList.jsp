<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<c:forEach var="meal" items="${meals}">
    <%--<div class="row">--%>
        <%--<div class="col-md-2 col"><h3>${meal.dateTime}</h3></div>--%>
        <%--<div class="col-md-2 col"><h3>${meal.description}</h3></div>--%>
        <%--<div class="col-md-2 col"><h3>${meal.calories}</h3></div>--%>
        <%--<div class="col-md-2 col"><h3>${meal.exceed}</h3></div>--%>
    <%--</div>--%>
    <tr>
        <td>
            <h3>${meal.dateTime}</h3>
        </td>
        <td>
            <h3>${meal.description}</h3>
        </td>
        <td>
            <h3>${meal.calories}</h3>
        </td>
        <td>
            <h3>${meal.exceed}</h3>
        </td>
    </tr>


</c:forEach>
</body>
</html>
