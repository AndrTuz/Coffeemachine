<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 04.11.2017
  Time: 14:28
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
    <title><fmt:message key="deliveryman.title"/></title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mainstyle.css" rel="stylesheet">
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link href="css/mainstyle.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid" style="background-color: #c0a16b; background-size: 100%">
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: black">
    <a class="navbar-brand" href="controller?command=logout"><font color="red">CoffeeMachine</font></a>
    <form class="navbar-form navbar-right">
        <div class="form-group">
            <font color="blue"><c:out value="${user.login}"/></font>
            <a href="controller?command=setEnglish"><font color="#f0ffff">EN</font></a>
            <a href="controller?command=setRussian"><font color="#f0ffff">РУС</font></a>
            <a href="controller?command=logout"><input class = "btn btn-danger" type="button" value="<fmt:message key="userheader.logout"/>"></a>
        </div>
    </form>
</nav>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<div class="row">
    <div class="col-sm-3"> </div>
    <div class="col-sm-6">
        <table class="table" style="font-size: large">
            <caption style="font-size: large;"><strong class="h1"><font color="black">Orders:</font></strong></caption>
            <thead>
            <tr class="active">
                <th style="background-color: inherit">№</th>
                <th style="background-color: inherit">Owner</th>
                <th style="background-color: inherit">Room</th>
                <th style="background-color: inherit">Drink</th>
                <th style="background-color: inherit">Count</th>
                <th style="background-color: inherit">Cost</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="elem" items="${order_list}">
                <tr>
                    <td class="text-center"><c:out value="${elem.id_order}"/></td>
                    <td class="text-center"><c:out value="${elem.user.login}"/></td>
                    <td class="text-center"><c:out value="${elem.delivery_room}"/></td>
                    <td class="text-center"><c:out value="${elem.drinks[0].name}"/></td>
                    <td class="text-center"><c:out value="${elem.drinks[0].countInOrder}"/></td>
                    <td class="text-center"><c:out value="${elem.total_sum}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-sm-3"> </div>
</div>

</body>
</html>
