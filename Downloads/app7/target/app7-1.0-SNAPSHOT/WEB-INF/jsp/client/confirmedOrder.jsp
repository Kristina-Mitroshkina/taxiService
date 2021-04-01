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

                <form id="order_form" action="controller" method="get">
                    <input type="hidden" name="command" value="updateStatus" />
            <c:choose>
                <c:when test="${(confirmedOrderBean) == null}">No such car</c:when>

                <c:otherwise>
                    <input type="hidden" name="clientOrder_id" value="${confirmedOrderBean.clientOrder_id}">
                    <p>Departure_address: ${confirmedOrderBean.departure_address} </p>
                    <p>Destination_address: ${confirmedOrderBean.destination_address} </p>
                    <p>Passengers number: ${confirmedOrderBean.passengers_number} </p>
                    <p>Category: ${confirmedOrderBean.category_name} </p>
                    <p>Car description: ${confirmedOrderBean.brand} ${confirmedOrderBean.model} </p>
                    <p>Car number: ${confirmedOrderBean.car_number} </p>
                    <p>Driver: ${confirmedOrderBean.driver_name} ${confirmedOrderBean.driver_surname} </p>
                    <p>Cost: ${confirmedOrderBean.cost} </p>
                    <p>Discount cost: ${confirmedOrderBean.discount_cost} </p>
                    <input id="submit" type="submit" value="ok">
                </c:otherwise>
            </c:choose>

                </form>
        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>
