<%-- 
    Document   : shopping
    Created on : Jun 15, 2021, 2:01:03 PM
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
        <h1>Your Cart</h1>
        <!--1. Khách hàng tới chỗ lấy giỏ-->
        <c:if test="${not empty sessionScope}">
            <!--2. Khách hàng láy giỏ-->
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <!--3. Khách hàng lấy tất cả đồ-->
                <c:set var="items" value="${cart.getItems()}"/>
                <c:if test="${not empty items}">
                    <form action="DispatchServlet">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Remove</th>
                                    <th>...</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="entry" items="${items}" varStatus="counter">
                                    <tr>
                                        <td>${counter.count}</td>
                                        <td>${entry.value.id}</td>
                                        <td>${entry.key}</td>
                                        <td>${entry.value.quantity}</td>
                                        <td>${entry.value.price}</td>
                                        <td><input type="checkbox" name="cboBox" value="${entry.key}" /></td>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td colspan="3"><a href="shopping.jsp">Back to Selection</a></td>
                                    <td><input type="submit" value="Remove Selected Items" name="btnAction"></td>
                                    <td><input type="submit" value="Checkout" name="btnAction"></td>
                                </tr>
                        </table>
                    </form>
                </c:if>
                <c:if test="${empty items}">
                    <h1>Cart is Empty!</h1>
                    <a href="shopping.jsp">Back to Selection</a>
                </c:if>

            </c:if>
            <c:if test="${empty cart}">
                <h1>Cart is Empty!</h1>
                <a href="shopping.jsp">Back to Selection</a>
            </c:if>
        </c:if>
        <c:if test="${empty sessionScope}">
            <h1>Cart is Empty! [Session]</h1>
            <a href="shopping.jsp">Back to Selection</a>
        </c:if>   
        <%--<%
            //1. Khách hàng tới chỗ lấy giỏ
            if (session != null) {
                //2. Khách hàng láy giỏ
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3. Khách hàng lấy tất cả đồ
                    Map<String, ShoppaDTO> items = cart.getItems();

                    if (items != null) {

                        //4. Xuất item
        %> 
        <form action="DispatchServlet">
            <table border="1">

                <thead>
                    <tr>
                        <th>No.</th>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Remove</th>
                        <th>...</th>
                    </tr>
                </thead>
                <tbody>
                    <%                    int i = 0;
                        for (String name : items.keySet()) {
                            ShoppaDTO item = items.get(name);
                    %>
                    <tr>
                        <td><%=++i%></td>
                        <td><%=item.getId()%></td>
                        <td><%=name%></td>
                        <td><%=item.getQuantity()%></td>
                        <td><%=item.getPrice()%></td>
                        <td><input type="checkbox" name="cboBox" value="<%=name%>" /></td>
                    </tr>

                    <%
                        }
                    %> 
                    <tr>
                        <td colspan="3"><a href="shopping.jsp">Back to Selection</a></td>
                        <td><input type="submit" value="Remove Selected Items" name="btnAction"></td>
                        <td><input type="submit" value="Checkout" name="btnAction"></td>
                    </tr>
                    <%
                                    return;
                                }
                            }
                        }%>
                </tbody>
            </table>
        </form>

        <h1>Cart is Empty!</h1>
        <a href="shopping.jsp">Back to Selection</a>
    </body> --%>
</html>
