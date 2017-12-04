<%-- 
    Document   : races
    Created on : 03.01.2014, 22:25:43
    Author     : Nadezhda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tl" uri="/WEB-INF/tld/tag_library.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${lang}"/>
<fmt:setBundle basename="com.laputskaya.races.resourse.page"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="races.title"/></title>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/mainstyle.css" rel="stylesheet">  
    </head>
    <body>
                 <%@include file="jspf/userheader.jspf" %>
                 <br>
                 <div class="container-fluid row-fluid span9 hero-unit">
                         <h2><fmt:message key="races.text"/></h2>  
                         <tl:race-table races="${races}"> </tl:race-table>
                 </div> 
            <%@include file="jspf/footer.jspf" %>
    </body>
</html>
