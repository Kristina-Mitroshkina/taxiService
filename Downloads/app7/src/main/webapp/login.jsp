<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Login" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

	<table id="main-container">

		<tr >
			<td class="content center">

				<form id="login_form" action="controller" method="post">

					<input type="hidden" name="command" value="email"/>

					<fieldset >
						<legend >
							Email
						</legend>
						<input name="email"/><br/>
					</fieldset><br/>
					<fieldset>
						<legend>
							Password
						</legend>
						<input type="password" name="password"/>
					</fieldset><br/>


					<input type="submit" value='submit'>
				</form>

			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>

	</table>
</body>
</html>