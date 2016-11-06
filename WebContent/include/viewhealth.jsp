<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.* , java.sql.*" import= "controller.*" %>
<%
	Member cur = (Member) session.getAttribute("user");
	String user = request.getParameter("user");
	HealthController hc = (HealthController)session.getAttribute("hc");
	DatumController dc = hc.getDatumtable();
	String name[]= new String[]{"Run           ","Calories burnt","Blood Pressure"};
	if(user==null||user.equalsIgnoreCase("null")){
		
	}
	else{
		if(hc!=null){
			System.out.println(user);
			Member t = hc.getRandUser(user);
			ResultSet rsdatum = hc.getDb().getDatum(user);
			dc =hc.getDatum(rsdatum);
		}
	}
%>
<h2>
	View Health Data
</h2>
<div class="col-sm-6">
<pre>
<h5>

<%
ArrayList<Datum> d1 = dc.getDatum();
for(Datum d:d1){
	out.println("    "+name[d.getPropertyID()-1]+"   	: "+d.getValue() +"    on :"+d.getWhenSaved());
}
%>
    </h5>
    </pre>
    </div>