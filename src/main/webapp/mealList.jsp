<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {color: green;}
        .exceed {color: red;}
    </style>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>
<a href="meals?action=create">Add Meal</a>
<table>

    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
<c:forEach var="meal" items="${meals}">
<jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealWithExceed"/>
<tr class="${meal.exceed ? 'exceed' : 'normal'}">
            <td>
                <h3><javatime:format value="${meal.dateTime}" style="MS" pattern="yyyy.MM.dd HH:mm" /></h3>
            </td>
            <td>
                <h3>${meal.description}</h3>
            </td>
            <td>
                <h3>${meal.calories}</h3>
            </td>
    <td><a href="meals?action=update&id=${meal.id}">Update</a> </td>
    <td><a href="meals?action=delete&id=${meal.id}">Delete</a> </td>

        </tr>

</c:forEach>
</table>
</body>
</html>
