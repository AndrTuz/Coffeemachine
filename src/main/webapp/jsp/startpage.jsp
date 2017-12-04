<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 30.10.2017
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="resources.page"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="start.title"/></title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mainstyle.css" rel="stylesheet">
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link href="css/mainstyle.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #080808">
<%@include file="jspf/header.jspf"%>
<br>
<div class="container" >
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <div class="carousel-inner">
            <div class="item active">
                <img src="https://laboratoriocreativo.ro/wp-content/uploads/2017/04/cafea-cu-dichis-coffee-02-1200x700.png" alt="Los Angeles" style="width:100%">
                <div class="carousel-caption"><h1>Americano</h1></div>
            </div>

            <div class="item">
                <img src="http://lifestylepubs.wordpress.s3-us-west-2.amazonaws.com/app/uploads/prod/2016/10/ccc_67-1200x700.jpg" alt="Chicago" style="width:100%;">
                <div class="carousel-caption"><h1>Latte</h1></div>
            </div>

            <div class="item">
                <img src="http://lifestylepubs.wordpress.s3-us-west-2.amazonaws.com/app/uploads/prod/2017/10/strengthening-communities-with-coffee-1200x700.jpg" alt="New york" style="width:100%;">
            </div>
        </div>

        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>
</body>
</html>
