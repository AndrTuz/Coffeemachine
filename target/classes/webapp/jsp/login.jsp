<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 21.10.2017
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="resources.page"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="login.title"/></title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mainstyle.css" rel="stylesheet">
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link href="css/mainstyle.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #080808; background-size: 135%" background="http://www.coffeedaybeverages.com/img/home/home-banner2.jpg">
<%@include file="jspf/header.jspf"%>
<br/>
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
    <form name="LoginForm" action="controller" method="post">
        <input type="hidden" name="command" value="login"/>
        <input type="text" class="input-sm" name="login" placeholder="<fmt:message key="login.title"/>" value=""/>
        <br/>
        <br/>
        <input type="password" class="input-sm" name="password" placeholder="<fmt:message key="login.placeholder.password"/>" value=""/>
        <br/>
        <br/>
        <input type="submit" class="btn btn-success" value="<fmt:message key="login.button.logintext"/>"/>
        <br/>
        <font color="red">${errorLoginPassMessage}</font>
        <br/>
        <a href="controller?command=gotoregistration" class="btn btn-link" role="button"><fmt:message key="login.href.registration_new"/></a>
    </form>
</div>
</body>
</html>
