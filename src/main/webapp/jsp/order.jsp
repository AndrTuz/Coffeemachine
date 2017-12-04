<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 05.11.2017
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mtg" uri="/WEB-INF/tld/tag_library.tld" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="resources.page"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="order.title"/></title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mainstyle.css" rel="stylesheet">
    <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <link href="css/mainstyle.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="container-fluid" style="background-size: 100%" background="http://bgfons.com/upload/coffe_chocolate_texture1504.jpg">
<%@include file="/jsp/jspf/userheader.jsp" %>
<br/>
<br/>
<br/>
<div class="row">
    <div class="col-sm-3"> </div>
    <div class="col-sm-6">
        <table class="table" style="font-size: large">
            <caption style="font-size: large;"><strong class="h1"><font color="black"><fmt:message key="order.text.capture"/></font></strong></caption>
            <thead>
            <tr class="active">
                <th style="background-color: inherit"><fmt:message key="order.table.head.name"/></th>
                <th style="background-color: inherit" class="text-center"><fmt:message key="order.table.head.price"/></th>
                <th style="background-color: inherit" class="text-center"><fmt:message key="order.table.head.count"/></th>
                <th style="background-color: inherit" class="text-center"><fmt:message key="order.table.head.total"/></th>
                <th style="background-color: inherit"><fmt:message key="order.table.head.additionally"/></th>
                <th style="background-color: inherit"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="elem" items="${user_order.drinks}">
                <c:if test="${(elem.availiable_sum - elem.countInOrder)<0}">
                    <c:set var="color" value="red" scope="page"/>
                </c:if>
                <tr bgcolor="${color}">
                    <td><c:out value="${elem.name}"/></td>
                    <td class="text-center"><c:out value="${elem.cost}"/></td>
                    <td class="text-center"><c:out value="${elem.countInOrder}"/></td>
                    <td class="text-center"><c:out value="${elem.countInOrder*elem.cost}"/></td>
                    <td class="text-center"><c:if test="${(elem.availiable_sum - elem.countInOrder)<0}"><fmt:message key="order.table.text.availiable"/> ${elem.availiable_sum}</c:if></td>
                    <td class="text-center"><mtg:remove-button id_drink="${elem.id_drink}"></mtg:remove-button></td>
                </tr>
                <c:set var="color" value="" scope="page"/>
            </c:forEach>
            </tbody>
        </table>
        <form name="OrderConfirm" action="controller" method="post">
            <input class="hidden" name="command" value="confirmorder">
            <label class="left"><fmt:message key="order.input.text.room"/>
                <input type="text" style="width: 65px; background-color: tan" name="room" placeholder="<fmt:message key="order.input.placeholder.room"/>" value=""/>
            </label>
            <p align="Right" class="h1"><strong><fmt:message key="order.text.total"/> ${user_order.total_sum}</strong></p>
            <p align="center"><input type="submit" class="btn btn-success" value="<fmt:message key="order.input.submit.btn"/>"/></p>
        </form>
        <p align="center"><font color="red">${errorConfirmOrder}</font></p>
    </div>
    <div class="col-sm-3"> </div>
</div>
</body>
</html>
