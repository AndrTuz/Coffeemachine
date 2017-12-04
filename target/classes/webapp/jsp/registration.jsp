<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 29.10.2017
  Time: 16:17
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
    <title><fmt:message key="registration.title"/></title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mainstyle.css" rel="stylesheet">
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link href="css/mainstyle.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #080808; background-size: 100%" background="http://www.coffeedaybeverages.com/img/about-us/coffee-machin.jpg" >
<%@include file="jspf/header.jspf"%>
<br/>
<br/>
<br/>
<br/>
<br/>
<h3 align="center"><fmt:message key="registration.text.welcome"/></h3>
<form name="RegistrationForm" method="post" action="controller">
    <input type="hidden" name="command" value="registration"/>
    <div align="center">
        <fmt:message key="registration.text.login"/><br/>
        <input type="text" class="input-sm" name="registration_login" placeholder="<fmt:message key="registration.placeholder.login"/>" value=""/>
        <br/>
        <br/>
        <fmt:message key="registration.text.password"/><br/>
        <input type="password" class="input-sm" name="registration_password" placeholder="<fmt:message key="registration.placeholder.password"/>" value=""/>
        <br/>
        <br/>
        <fmt:message key="registration.text.refilcard"/><br/>
        <input type="text" class="input-sm" name="registration_cardpin" placeholder="<fmt:message key="registration.placeholder.pin"/>" value=""/>
        <br/>
        <fmt:message key="registration.text.role"/><br/>
        <input type="radio" id="user" name="registration_role" value="user" checked>
        <label for="user"><fmt:message key="registration.text.choose.user"/></label>
        <input type="radio" id="deliveryman" name="registration_role" value="deliveryman">
        <label for="deliveryman"><fmt:message key="registration.text.choose.deliveryman"/></label>
        <br/>
        <input type="submit" value="<fmt:message key="registration.title"/>"/>
        <br/>
        <font color="red">${errorRegistrationMessage}</font>
    </div>
</form>
</body>
</html>
