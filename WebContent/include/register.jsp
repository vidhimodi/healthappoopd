<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	String work = (String) request.getParameter("utype");
	System.out.print(work);
	if (work == null || work.equalsIgnoreCase("null")
			|| work.equalsIgnoreCase("utype")) {
		work = "./register_utype.jsp";
	} else {
		work = "./register_member.jsp";
	}
%>
<jsp:include page="<%=work %>"></jsp:include>