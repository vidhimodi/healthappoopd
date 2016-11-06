<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.*" import= "controller.*" %>
    <%
    Moderator cur = (Moderator)session.getAttribute("user");
    String qual = null;
    for(String q:cur.getQualification()){
    	qual= q+" ";
    }
    qual=qual.trim();
    %>
<input name="phone" type="tel" class="form-control input-lg"
	value="<%=cur.getEm_number()%>">
<input name="qualification" type="text" class="form-control input-lg"
	value="<%=qual%>">