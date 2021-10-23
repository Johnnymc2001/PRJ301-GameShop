<%-- 
    Document   : shopping
    Created on : Jun 17, 2021, 11:03:41 AM
    Author     : JohnnyMC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Shoppa</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%--<%! ArrayList<ShoppaDTO> avail = new ArrayList<ShoppaDTO>();%>
        <%
            ShoppaDAO dao = new ShoppaDAO();
            avail = dao.getAvailableGames();
            pageContext.setAttribute("AVAILABLE_LIST", avail);
        %>--%>

        <c:set var="availiableLists" value="${ShoppaDAO().getAvailableGames()}"/>
        <h1>Shoppa</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity Left</th>
                    <th>Price</th>
                    <th>Add</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="dto" items="${pageScope.availiableLists}" varStatus="counter">
                <form action="DispatchServlet">
                    <tr>
                        <td>${counter.count}</td>
                        <td>${dto.id}</td>
                        <td>
                            ${dto.name}
                            <input type="hidden" name="txtGamename" value="${dto.name}"/>
                        </td>
                        <td>${dto.quantity}</td>
                        <td>${dto.price}</td>
                        <td><input type="submit" value="Add Item to Cart" name="btnAction"/></td>
                    </tr>
                </form>
            </c:forEach>
            <form action="DispatchServlet">
                <tr>
                    <td colspan="5">
                        <input type="submit" value="View Cart" name="btnAction"/>       
                    </td>
                </tr>
            </form>

        </tbody>
    </table>
    <%--        <form action="DispatchServlet">
                Choose your item <select name="cboBox">
    <%
        for (ShoppaDTO dto : avail) {
    %>
    <option>
    <%=dto.getName()%>
</option>
    <%
        }
    %>
</select>
<input type="submit" value="Add Item to Cart" name="btnAction"/>
<input type="submit" value="View Cart" name="btnAction"/>
</form>--%>
</body>
</html>
