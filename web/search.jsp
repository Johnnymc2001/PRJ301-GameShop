<%-- 
    Document   : search
    Created on : Jun 6, 2021, 11:15:12 AM
    Author     : JohnnyMC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Search Page</title>
    </head>
    <body>
        <%-- <form action="DispatchServlet"><input type="submit" value="Logout" name="btnAction" /></form>
        <%
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
        %>
        <jsp:useBean id="loginAttribute" class="johnny.javabean.LoginBean" scope="session"/>
       
        
        <font color="salmon">    
            Welcome, <jsp:getProperty name="loginAttribute" property="username"/><br/> 
            Welcome, <%=((LoginBean) session.getAttribute("loginAttribute")).getUsername()%>
            ${session.loginAttribute.getUserName}
        </font>
        <%
//            }
        %>
        <h1>Search Criteria</h1>
        <form action="DispatchServlet">
            <label for="txtSearchValue">Search</label>
            <input type="text" value="<%=request.getParameter("txtSearchValue") == null ? "" : request.getParameter("txtSearchValue")%>" name="txtSearchValue"/>
            <input type="submit" value="Search" name="btnAction"/>
        </form>
        <br/>


        <%
            String searchValue = request.getParameter("txtSearchValue");
            if (searchValue != null) { // Search value inputted
                List<RegisterationDTO> dtoList
                        = (List<RegisterationDTO>) request.getAttribute("SEARCH_RESULT");

                if (dtoList != null) { // Search value existed in database

        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Role</th>
                    <th>Delete</th>
                    <th>Update</th>

                </tr>
            </thead>
            <tbody>
                <%                    int count = 0;
                    for (RegisterationDTO dto : dtoList) {
                        String urlRewriting = "DispatchServlet"
                                + "?btnAction=Delete"
                                + "&username=" + dto.getUsername()
                                + "&lastSearchValue=" + request.getParameter("txtSearchValue");
                %>
            <form action="DispatchServlet">
                <tr>
                    <td>
                        <%=++count%>
                    </td>
                    <td>
                        <%=dto.getUsername()%>
                        <input type="hidden" name="hiddenTxtUsername" value="<%=dto.getUsername()%>" />
                    </td>
                    <td>
                        <input type="text" value="<%=dto.getPassword()%>" name="txtPassword"/>
                    </td>
                    <td>
                        <%=dto.getName()%>
                    </td>
                    <td>

                        <input type="checkbox" name="chkAdmin" value="ON" 
                               <%
                                   if (dto.isIsAdmin()) {
                               %> 
                               checked="checked"/>
                        <%
                            }
                        %>
                    </td>
                    <td>
                        <a href="<%=urlRewriting%>">Delete</a>
                    </td>
                    <td>
                        <input type="submit" value="Update" name="btnAction" />
                        <input type="hidden" name="lastSearchValue" 
                               value="<%= searchValue%>" />
                    </td>
                </tr>
            </form>

            <%
                }
            %>
        </tbody>
    </table>
    <%
    } else { // Search value not existed in database
    %>
    <h2> No result found </h2>
    <%
            }
        } else {
        }
    %>--%>
        <font color="red">
        <%--. thay thế cho get --%>
        Welcome ${sessionScope.NAME} <%--Thằng nào in username thằng đó không điểm --%>
        <form action="DispatchServlet"><input type="submit" value="Logout" name="btnAction"/></form>
        </font>

        <form action="DispatchServlet">
            <label for="txtSearchValue">Search</label>
            <%-- param = request.getParamter --%>
            <input type="text" value="${param.txtSearchValue}" name="txtSearchValue"/>
            <input type="submit" value="Search" name="btnAction"/>
        </form>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <%-- 
        empty khi:
        1. Một biến dạng chuỗi mà chuỗi = null
        2. Nếu biến là object mà object = null
        3. Object != null mà object.size = 0
        ---%>
        <c:if test="${not empty searchValue}"> 
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- Biến dto, mảng results, biến chạy count --%>
                        <c:forEach var="dto" items="${result}" varStatus="counter">
                        <form action="DispatchServlet">
                            <tr>
                                <td>
                                    <%--
                                    counter.count -- tính từ 1
                                    counter.index -- tính từ 0
                                    --%>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="hiddenTxtUsername" 
                                           value="${dto.username}" />
                                </td>
                                <td>
                                    <input type="text" name="txtPassword"
                                           value="${dto.password}"/>
                                </td>
                                <td>
                                    ${dto.name}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAdmin" value="ON"
                                           <c:if test="${dto.isAdmin == true}">
                                               checked="checked"
                                           </c:if>
                                </td>
                                <td>
                                    <c:url var="deleteLink" value="DispatchServlet">
                                        <c:param name="btnAction" value="Delete Account"/>
                                        <c:param name="username" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                    <!--<input type="submit" value="Delete Account" name="btnAction" />-->
                                </td>
                                <td>
                                    <input type="submit" value="Update Account" name="btnAction" />
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${searchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">
            <h2>
                No record is matched!!
            </h2>
        </c:if>
    </c:if>
</body>
</html>
