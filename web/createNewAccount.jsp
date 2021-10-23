<%-- 
    Document   : createNewAccount
    Created on : Jun 29, 2021, 9:17:16 AM
    Author     : JohnnyMC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Sign Up</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchServlet" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERROR}"/>
            <!--                                                                                -->
            Username* : <input type="text" name="txtUsername" value ="${param.txtUsername}"/> (6 - 20 Characters) 
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font>
            </c:if>
            <br/>
            <!--                                                                                -->

            Password* : <input type="password" name="txtPassword" value =""/> (6 - 30 Characters)
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font>
            </c:if>
            <br/>
            <!--                                                                                -->

            Confirm Password* : <input type="password" name="txtConfirm" value =""/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}
                </font>
            </c:if>
            <br/>
            <!--                                                                                -->

            Full Name* : <input type="text" name="txtName" value="${param.txtName}"/> (2 - 50 Characters) 
            <c:if test="${not empty errors.nameLengthError}">
                <font color="red">
                ${errors.nameLengthError}
                </font>
            </c:if>
            <br/>
            <input type="submit" value="Create New Account" name="btnAction" /> 
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>

