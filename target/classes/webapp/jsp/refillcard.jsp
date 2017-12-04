<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 31.10.2017
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="resources.page"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="refillcard.title"/></title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mainstyle.css" rel="stylesheet">
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link href="css/mainstyle.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid" style="background-color: #66512c; background-size: 100%"
background="https://i.ytimg.com/vi/Pp8HhX1dnfc/maxresdefault.jpg">
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: black">
    <a class="navbar-brand" href="controller?command=logout"><font color="red">CoffeeMachine</font></a>
    <form class="navbar-form navbar-right">
        <div class="form-group">
            <font color="blue"><c:out value="${user.login}"/> <fmt:message key="userheader.balance"/> <c:out value="${user.total_sum}"/></font>
            <a href="controller?command=setEnglish"><font color="#f0ffff">EN</font></a>
            <a href="controller?command=setRussian"><font color="#f0ffff">РУС</font></a>
            <a href="controller?command=gotorefillcard"><input class = "btn btn-info" type="button" value="<fmt:message key="userheader.refillcard"/>"></a>
            <a href="controller?command=gotousermain"><input class = "btn btn-danger" type="button" value="<fmt:message key="refillcard.header.main"/>"></a>
        </div>
    </form>
</nav>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div align="center">
    <h2><font color="black"><fmt:message key="userheader.balance"/> <c:out value="${user.total_sum}"/></font></h2>
    <form name="refillcard" method="post" action="controller">
        <input type="hidden" name="command" value="refillcard"/>
        <label><fmt:message key="refillcard.text.sum"/> <input type="text" name="refill_sum" size="3"></label>
        <label><fmt:message key="refillcard.text.pin"/> <input type="text" name="pin" size="5"></label>
        <p><font color="red">${errorRefill}</font></p>
        <p align="center"><input type="submit" class="btn btn-success" value="<fmt:message key="refillcard.button.refill"/>"></p>
    </form>
</div>
</body>
</html>
