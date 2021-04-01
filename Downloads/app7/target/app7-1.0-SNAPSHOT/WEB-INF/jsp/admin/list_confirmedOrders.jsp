<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List orders" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">
            <h1>All orders</h1>
                <a href="controller?command=listOrders">
                    Source table
                </a><br>
                <a href="controller?command=listSortedByDateOrders">
                    Sort orders by date (DESC)
                </a><br>
                <a href="controller?command=listSortedByCostOrders">
                    Sort orders by cost (DESC)
                </a>
                <form id="settings_form" action="controller" method="get">
                    <input type="hidden" name="command" value="listFilteredByClientsOrders" />
                    User_id
                    <input name="user_id">
                    <input type="submit" value='submit'>
                </form>
                <form id="settings_form" action="controller" method="get">
                    <input type="hidden" name="command" value="listFilteredByDatesOrders" />
                    Date
                    <input type="date" name="order_date">
                    <input type="submit" value='submit'>
                </form>

            <c:choose>
                <c:when test="${fn:length(confirmedOrderBeanList) == 0}">No such orders</c:when>

                <c:otherwise>
                    <table id="list_order_table">
                        <thead>
                        <tr>
                            <td>Id</td>
                            <td>User_id</td>
                            <td>Departure_address</td>
                            <td>Destination_address</td>
                            <td>Passengers number</td>
                            <td>Car_id</td>
                            <td>Cost</td>
                            <td>Discount cost</td>
                            <td>Order date</td>
                            <td>Status</td>
                        </tr>
                        </thead>

                        <c:forEach var="bean" items="${confirmedOrderBeanList}">

                            <tr>
                                <td>${bean.confirmedOrder_id}</td>
                                <td>${bean.user_id}</td>
                                <td>${bean.departure_address}</td>
                                <td>${bean.destination_address}</td>
                                <td>${bean.passengers_number}</td>
                                <td>${bean.car_id}</td>
                                <td>${bean.cost}</td>
                                <td>${bean.discount_cost}</td>
                                <td>${bean.order_date}</td>
                                <td>${bean.status}</td>
                            </tr>

                        </c:forEach>
                    </table>
                </c:otherwise>
            </c:choose>

        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>