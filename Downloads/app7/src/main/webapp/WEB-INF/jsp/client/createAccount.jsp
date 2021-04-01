<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Create new account" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">
    <tr >
        <td class="content center">

            <form id="settings_form" action="controller" method="get">
                <input type="hidden" name="command" value="newClient" />

                <div>
                    <p>Name</p>
                    <input name="name">
                </div>

                <div>
                    <p>Surname</p>
                    <input name="surname">
                </div>

                <div>
                    <p>Email</p>
                    <input name="email">
                </div>

                <div>
                    <p>Password</p>
                    <input name="password">
                </div>

                <div>
                    <p>Phone number</p>
                    <input name="phone_number">
                </div>

                <input type="submit" value='submit'><br/>
            </form>

        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf"%>

</table>
</body>
</html>
