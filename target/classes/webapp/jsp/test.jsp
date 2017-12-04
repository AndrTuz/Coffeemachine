<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 01.11.2017
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mainstyle.css" rel="stylesheet">
</head>
<body>
<%@include file="/jsp/jspf/userheader.jsp" %>
<br/>
<br/>
<br/>
<form name="StartPageForm" method="post" action="controller">
    <div class="row">
        <div class="col-sm-4"> </div>
        <div class="col-sm-2">
            <h4 align="center"><strong>${drinkArray[2]}</strong></h4>
            <b/>
            <p align="center">
                <label>Count: <input type="text" name="drink_1_count" size="1"></label>
                <label>Add <input type="checkbox" name="drink_1_confirm"></label>
            </p>
        </div>
        <div class="col-sm-2">
            <h4 align="center"><strong>${drinkArray[1]}</strong></h4>
            <b/>
            <p align="center">
                <label>Count: <input type="text" name="drink_2_count" size="1"></label>
                <label>Add <input type="checkbox" name="drink_2_confirm"></label>
            </p>
        </div>
        <div class="col-sm-4"> </div>
    </div>
    <br/>
    <br/>
    <br/>
    <div class="row">
        <div class="col-sm-4"> </div>
        <div class="col-sm-2">
            <h4 align="center"><strong>${drink3}</strong></h4>
            <p align="center">
                <label>Count: <input type="text" name="drink_3_count" size="1"></label>
                <label>Add <input type="checkbox" name="drink_3_confirm"></label>
            </p>
        </div>
        <div class="col-sm-2">
            <h4 align="center"><strong>LATTE</strong></h4>
            <p align="center">
                <label>Count: <input type="text" name="drink_4_count" size="1"></label>
                <label>Add <input type="checkbox" name="drink_4_confirm"></label>
            </p>
        </div>
        <div class="col-sm-4"></div>
    </div>
    <br/>
    <br/>
    <br/>
    <div class="row">
        <div class="col-sm-4"> </div>
        <div class="col-sm-2">
            <h4 align="center"><strong>SUGAR</strong></h4>
            <p align="center">
                <label>Count: <input type="text" name="drink_5_count" size="1"></label>
                <label>Add <input type="checkbox" name="drink_5_confirm"></label>
            </p>
        </div>
        <div class="col-sm-2">
            <h4 align="center"><strong>LEMON</strong></h4>
            <p align="center">
                <label>Count: <input type="text" name="drink_6_count" size="1"></label>
                <label>Add <input type="checkbox" name="drink_6_confirm"></label>
            </p>
            <br/>
            <p align="center"><input type="submit" value="Create order"></p>
        </div>
        <div class="col-sm-4"></div>
    </div>
</form>

</body>
</html>
