<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Error" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>
	
<body>

	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf"%>

		<tr >
			<td class="content">
			<%-- CONTENT --%>
				
				<h2 class="error">
					The following error occurred
				</h2>

				<c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
				<c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>

				<c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>
				
				<c:if test="${not empty code}">
					<h3>Error code: ${code}</h3>
				</c:if>			
				
				<c:if test="${not empty message}">
					<h3>Message: ${message}</h3>
				</c:if>
				
				<%-- if get this page using forward --%>
				<c:if test="${not empty errorMessage and empty exception and empty code}">
					<h3>Error message: ${errorMessage}</h3>
				</c:if>	
				
				<%-- this way we print exception stack trace --%>
				<c:if test="${not empty exception}">
					<hr/>
					<h3>Stack trace:</h3>
					<c:forEach var="stackTraceElement" items="${exception.stackTrace}">
						${stackTraceElement}
					</c:forEach>
				</c:if>	

			</td>
		</tr>

		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		
	</table>
</body>
</html>