<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.*" import= "controller.*" %>
    <%
	Admin cur = (Admin)session.getAttribute("user");
	%>
<input name="phone" type="tel" class="form-control input-lg"
	value="<%=cur.getEm_num() %>">