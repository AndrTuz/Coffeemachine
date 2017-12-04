<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 21.10.2017
  Time: 14:55
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
    <title><fmt:message key="usermain.title"/></title>
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
      background="https://previews.123rf.com/images/milkos/milkos1606/milkos160600339/58705484-White-wood-table-texture-and-background-White-painted-wood-texture-background-Rustic-shabby-chick-wo-Stock-Photo.jpg">
<%@include file="/jsp/jspf/userheader.jsp" %>
<br/>
<br/>
<br/>
<br/>
<form name="StartPageForm" method="post" action="controller">
    <input type="hidden" name="command" value="createorder"/>
    <div class="row">
        <div class="col-sm-4">
            <div align="center">
                <img src="https://www.usa.philips.com/c-dam/b2c/category-pages/Household/coffee/master/faq/cups/Productimg_Cappuccino.png">
                <h4><strong><c:out value="${drinks_list[1].name}"/></strong></h4>
                <input type="text" style="width: 30px; text-align: center; background-color: inherit" name="drink_2_count" value="0">
                <strong><font color="red"><fmt:message key="usermain.text.price"/> <c:out value="${drinks_list[1].cost}"/></font></strong>
            </div>
        </div>
        <div class="col-sm-4">
            <div align="center">
                <img src="https://www.usa.philips.com/c-dam/b2c/category-pages/Household/coffee/master/faq/cups/Productimg_Americano.png">
                <h4><strong><c:out value="${drinks_list[0].name}"/></strong></h4>
                <input type="text" style="width: 30px; text-align: center; background-color: inherit" name="drink_1_count" value="0">
                <strong><font color="red"><fmt:message key="usermain.text.price"/> <c:out value="${drinks_list[0].cost}"/></font></strong>
            </div>
        </div>
        <div class="col-sm-4">
            <div align="center">
                <img src="https://www.usa.philips.com/c-dam/b2c/category-pages/Household/coffee/master/faq/cups/Productimg_Espresso.png">
                <h4><strong><c:out value="${drinks_list[2].name}"/></strong></h4>
                <input type="text" style="width: 30px; text-align: center; background-color: inherit" name="drink_3_count" value="0">
                <strong><font color="red"><fmt:message key="usermain.text.price"/> <c:out value="${drinks_list[2].cost}"/></font></strong>
            </div>
        </div>
    </div>
    <br/>
    <br/>
    <br/>
    <div class="row">
        <div class="col-sm-4">
            <div align="center">
                <img src="http://pngimg.com/uploads/sugar/sugar_PNG21.png" width="170px" height="170px">
                <h4><strong><c:out value="${drinks_list[4].name}"/></strong></h4>
                <input type="text" style="width: 30px; text-align: center; background-color: inherit" name="drink_5_count" value="0">
                <strong><font color="red"><fmt:message key="usermain.text.price"/> <c:out value="${drinks_list[4].cost}"/></font></strong>
            </div>
        </div>
        <div class="col-sm-4">
            <div align="center">
                <img src="https://www.usa.philips.com/c-dam/b2c/category-pages/Household/coffee/master/faq/cups/Productimg_Flat.png">
                <h4><strong><c:out value="${drinks_list[3].name}"/></strong></h4>
                <input type="text" style="width: 30px; text-align: center; background-color: inherit" name="drink_4_count" value="0">
                <strong><font color="red"><fmt:message key="usermain.text.price"/> <c:out value="${drinks_list[3].cost}"/></font></strong>
            </div>
        </div>
        <div class="col-sm-4">
            <div align="center">
                <img src="http://www.2goodapples.com/wp-content/uploads/2015/02/lemon.png" width="170px" height="170px">
                <h4><strong><c:out value="${drinks_list[5].name}"/></strong></h4>
                <input type="text" style="width: 30px; text-align: center; background-color: inherit" name="drink_6_count" value="0">
                <strong><font color="red"><fmt:message key="usermain.text.price"/> <c:out value="${drinks_list[5].cost}"/></font></strong>
            </div>
        </div>
    </div>
    <div align="center">
        <br/>
        <p align="center"><input type="submit" class="btn btn-success" value="<fmt:message key="usermain.button.create"/>"></p>
    </div>
</form>
<p align="center"><font color="red"><c:out value="${message}"/></font></p>
</body>
</html>