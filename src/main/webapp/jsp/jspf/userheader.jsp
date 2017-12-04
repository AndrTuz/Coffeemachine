<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top" style="background-color: black">
    <a class="navbar-brand" href="controller?command=logout"><font color="red">CoffeeMachine</font></a>
    <form class="navbar-form navbar-right">
        <div class="form-group">
            <font color="blue"><core:out value="${user.login}"/> <fmt:message key="userheader.balance"/> ${user.total_sum}</font>
            <a href="controller?command=setEnglish"><font color="#f0ffff">EN</font></a>
            <a href="controller?command=setRussian"><font color="#f0ffff">РУС</font></a>
            <a href="controller?command=gotorefillcard"><input class = "btn btn-info" type="button" value="<fmt:message key="userheader.refillcard"/>"></a>
            <a href="controller?command=logout"><input class = "btn btn-danger" type="button" value="<fmt:message key="userheader.logout"/>"></a>
        </div>
    </form>
</nav>
