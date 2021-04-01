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
            <h1>Car park</h1>
            <c:choose>
                <c:when test="${fn:length(carBeanList) == 0}">No cars in the car park </c:when>

                <c:otherwise>
                    <table id="list_order_table">
                        <thead>
                        <tr>
                            <td>Id</td>
                            <td>Description</td>
                            <td>Car number</td>
                            <td>Max capacity</td>
                            <td>Category</td>
                            <td>Driver</td>
                            <td>State</td>
                        </tr>
                        </thead>

                        <c:forEach var="bean" items="${carBeanList}">

                            <tr>
                                <td>${bean.car_id}</td>
                                <td>${bean.brand} ${bean.model}</td>
                                <td>${bean.car_number}</td>
                                <td>${bean.max_capacity}</td>
                                <td>${bean.category_name}</td>
                                <td>${bean.driver_name} ${bean.driver_surname}</td>
                                <td>${bean.state_name}</td>
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