<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tuz
  Date: 31.10.2017
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mtg" uri="/WEB-INF/tld/tag_library.tld" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin page</title>
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/mainstyle.css" rel="stylesheet">
</head>
<body>
<form role="form" class="form-inline pull-right">
    <div class="form-group">
        <c:out value="${user.login}"/>
        <a href="controller?command=logout"><input class = "btn btn-primary" type="button" value="Log out"></a>
    </div>
</form>
<br/>
<br/>
<br/>
<div class="row">
    <div class="col-sm-3"> </div>
    <div class="col-sm-6">
<p align="center"><mtg:admin-table/></p>
        <p align="right">
            <a href="controller?command=filldrinks"><input class = "btn btn-primary" type="button" value="Fill Drinks"></a>
        </p>
        <p align="center">${filleddrinksmessage}</p>
        <img src="img/02.png">
    </div>
    <div class="col-sm-3"> </div>
</div>
</body>
</html>
