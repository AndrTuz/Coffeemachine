<%-- 
    Document   : registration
    Created on : 07.12.2013, 15:24:19
    Author     : Nadezhda
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="com.laputskaya.races.resourse.page"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="registration.title"/></title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/mainstyle.css" rel="stylesheet">  
    </head>
    <body>
            <%@include file="jspf/header.jspf" %>
            <br>
             <div class="container-fluid row-fluid span9 hero-unit">
                         <h2><fmt:message key="registration.text"/></h2>  
                         <form action="MServlet" method="POST">
                             <div id="divCoefficients" class="coefficients">
                                 <table>
                                  <tr><td> <fmt:message key="registration.name"/> </td><td> <input type="text" name="login" value=""/></td></tr>
                                  <tr><td> <fmt:message key="registration.password"/></td><td> <input type="password" name="password" value=""/></td></tr>
                                  <tr><td> <fmt:message key="registration.account"/> </td><td> <input type="number" name="account" value="0"/></td></tr>
                                </table> 
                             </div>
                             <button type="submit" value="register" name="command" ><fmt:message key="registration.button.registration"/></button>
                        </form>
                         ${errorRegisterMessage}
            </div>
            <%@include file="../../../../../Desktop/EpamProject/web/jsp/jspf/footer.jspf" %>
    </body>
</html>
