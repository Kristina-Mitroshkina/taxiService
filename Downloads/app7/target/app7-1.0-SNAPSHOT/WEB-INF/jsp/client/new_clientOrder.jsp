<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="New order" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">

    <%@ include file="/WEB-INF/jspf/header.jspf" %>

    <tr >
        <td class="content center">

                <form id="order_form" action="controller" method="get">
                    <input type="hidden" name="command" value="newClientOrder" />

                    <div>
                        <p>Welcome,</p>
                        <input type="hidden" name="user_id" value="${user.id}">
                        <c:out value="${user.email}"/>
                    </div>

                    <div>
                        <p>Departure address</p>
                        <select name="departure_address" >
                            <option value="1">Pushkinskaya</option>
                            <option value="2">Ivanova</option>
                            <option value="3">Bakulina</option>
                            <option value="4">Klochkovskaya</option>
                            <option value="5">23 Avgusta</option>
                            <option value="6">Pobedy av.</option>
                            <option value="7">Shevchenka</option>
                            <option value="8">Geroev Truda</option>
                            <option value="9">Molochnaya</option>
                            <option value="10">Gagarina av.</option>
                            <option value="11">Academica Pavlova</option>
                            <option value="12">Sumskaya</option>
                        </select>
                    </div>

                    <div>
                        <p>Destination address</p>
                        <select name="destination_address" >
                            <option value="1">Pushkinskaya</option>
                            <option value="2">Ivanova</option>
                            <option value="3">Bakulina</option>
                            <option value="4">Klochkovskaya</option>
                            <option value="5">23 Avgusta</option>
                            <option value="6">Pobedy av.</option>
                            <option value="7">Shevchenka</option>
                            <option value="8">Geroev Truda</option>
                            <option value="9">Molochnaya</option>
                            <option value="10">Gagarina av.</option>
                            <option value="11">Academica Pavlova</option>
                            <option value="12">Sumskaya</option>
                        </select>
                    </div>

                    <div>
                        <p>Passengers number</p>
                        <select name="passengers_number" >
                            <option value="4">1-4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                        </select>
                    </div>

                    <div>
                        <p>Car category</p>
                        <select name="car_category" >
                            <option value="0">Economy</option>
                            <option value="1">Comfort</option>
                            <option value="2">Business</option>
                            <option value="3">Premium</option>
                        </select>
                    </div>

                    <input id = "submit" type="submit" value='submit'><br/>
                </form>


        </td>
    </tr>

    <%@ include file="/WEB-INF/jspf/footer.jspf"%>

</table>
</body>
</html>