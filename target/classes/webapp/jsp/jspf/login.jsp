<%-- 
    Document   : login
    Created on : 02.12.2013, 8:45:50
    Author     : Nadezhda
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="com.laputskaya.races.resourse.page"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="login.title"/></title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="../../../../../Desktop/EpamProject/web/css/bootstrap.css" rel="stylesheet">
        <script src="js/jquery-1.8.3.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <link href="css/mainstyle.css" rel="stylesheet">  
    </head>
    <body>
         <%@include file="jspf/header.jspf" %>   
         <div class="container-fluid row-fluid span9 hero-unit">
                       <h2><fmt:message key="login.text"/></h2>  
                               <form action="MServlet" method="POST">
                                    <div id="divCoefficients" class="coefficients">
                                        <table>
                                         <tr><td> <fmt:message key="login.name"/></td><td> <input type="text" name="login" value=""/></td></tr>
                                         <tr><td> <fmt:message key="login.password"/> </td><td> <input type="password" name="password" value=""/></td></tr>
                                        </table>
                                    </div>         
                                    <button type="submit" class="solve btn"  value="login" name="command" > <fmt:message key="login.button.login"/></button>
                               </form>
             <c:out value="${resultLoginMessage}
         </div>
         <%@include file="../../../../../Desktop/EpamProject/web/jsp/jspf/footer.jspf" %>
    </body>
</html>
