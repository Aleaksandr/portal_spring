<%@ page import="com.portal.util.Attributes" %>
<%@ page import="com.portal.commands.AddNewsCommand" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Add News</title>
    <link type="text/css" rel="stylesheet" href="resources/css/adnews.css">

</head>

<body>
<div class="addnews">
    <h1>Add news</h1><br>

    <form action="FrontController" method="post">
        <input type="hidden" name="<%=Attributes.COMMAND%>" value="<%=AddNewsCommand.NAME%>">
        <input type="text" name="title" placeholder="title">
        <input type="text" name="title4menu" placeholder="title for menu">
        <textarea rows="20" cols="87" name="item"></textarea>
        <input type="submit" name="addnews" value="Add News">
    </form>

</div>

</body>

</html>