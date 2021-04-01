<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Menu" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">

            <form id="make_order" action="controller">

                <h1>Hello, client!</h1>

                <table id="list_orders_table">
                    <thead>
                    <tr>
                        <td>Id</td>
                        <td>user_id</td>
                        <td>departure_address</td>
                        <td>destination_address</td>
                        <td>passengers_number</td>
                        <td>car_category</td>
                        <td>order_date</td>
                    </tr>
                    </thead>

                    <c:forEach var="order" items="${clientOrders}">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.user_id}</td>
                            <td>${order.departure_address}</td>
                            <td>${order.destination_address}</td>
                            <td>${order.passengers_number}</td>
                            <td>${order.car_category}</td>
                            <td>${order.date}</td>
                        </tr>
                    </c:forEach>
                </table>

            </form>

        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>