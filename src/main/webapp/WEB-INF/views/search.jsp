<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Search</title>
</head>
<body>
<form:form method="get" action="/users">
    <input name="firstName" id="firstName" type="text"/>
    <input type="submit">
    <input type="checkbox" id="matchExact" name="matchExact">
    <label for="matchExact">Match Exact</label>
</form:form>
<br>
<a href="/users">Go back to users list</a>
</body>
</html>