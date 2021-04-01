<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Settings" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>

		<tr>
			<td class="content">

				<form id="settings_form" action="controller" method="get">
					<input type="hidden" name="command" value="updateSettings" />

					<div>
						<p>
							<fmt:message key="settings_jsp.label.localization"/>
						</p>
						<select name="localeToSet">
							<c:choose>
								<c:when test="${not empty defaultLocale}">
									<option value="">${defaultLocale}[Default]</option>
								</c:when>
								<c:otherwise>
									<option value=""/>
								</c:otherwise>
							</c:choose>
														
							<c:forEach var="localeName" items="${locales}">
								<option value="${localeName}">${localeName}</option>							
							</c:forEach>
						</select>
					</div>
					
					<div>
						<p>
							<fmt:message key="settings_jsp.label.first_name"/>
						</p>
						<input name="firstName">
					</div>
					
					<div>
						<p>
							<fmt:message key="settings_jsp.label.last_name"/>
						</p>
						<input name="lastName">
					</div>
					
					<input type="submit" value='<fmt:message key="settings_jsp.button.update"/>'><br/>
				</form> 

			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf" %>
		
	</table>
</body>
</html>