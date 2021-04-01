<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="List clients" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr>
        <td class="content">

                <h1>Our clients</h1>

                    <table id="list_order_table">
                        <thead>
                        <tr>
                            <td>№</td>
                            <td>name/surname</td>
                            <td>email</td>
                            <td>password</td>
                            <td>phone_number</td>
                            <td>role_id</td>
                        </tr>
                        </thead>

                        <c:forEach var="client" items="${clients}">

                            <tr>
                                <td>${client.id}</td>
                                <td>${client.name} ${client.surname}</td>
                                <td>${client.email}</td>
                                <td>${client.password}</td>
                                <td>${client.phone_number}</td>
                                <td>${client.role_id}</td>
                            </tr>

                        </c:forEach>
                    </table>

        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

</table>
</body>
</html>
