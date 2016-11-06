<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "model.*" import= "controller.*"%>

<%
	Member cur = (Member) session.getAttribute("user");
	String user = request.getParameter("user");
	HealthController hc = (HealthController)session.getAttribute("hc");
	if(user==null||user.equalsIgnoreCase("null")){
		
	}
	else{
		if(hc!=null){
			System.out.println(user);
			Member t = hc.getRandUser(user);
			if(t!=null){
				cur=t;
			}
		}
	}
%>

<div class="col-sm-6">
<h2>
	Welcome <%=cur.getUsername()%>
</h2>
<pre>
<h5>
    User name  			: <%=cur.getUsername()%><br>
    Email:    			: <%=cur.getPrimary_email()%><br>
    FirstName 			: <%=cur.getFirstname()%><br>
    LastName  			: <%=cur.getLastname()%><br>
    Sec. Email			: <%=cur.getSecondary_email()%><br>
    Governing district       	: <%=cur.getGoverningdistrict()%><br>
    Major municipality          : <%=cur.getMajormunicipality()%><br>
    Postal area 		: <%=cur.getPostalarea()%><br>
    StreetName			: <%=cur.getStreetname()%><br>
    Street number 		: <%=cur.getStreetnumber()%><br>
    PhototUrl			: <%
	for (String p : cur.getUrl()) {
		out.print(p + ",");
	}
%>
    
<%
    	if (cur instanceof Admin) {
    		out.println("Emergency Contact number  :"
    				+ ((Admin) cur).getEm_num());
    	} else if (cur instanceof Moderator) {
    		out.println(((Moderator) cur).getEm_number());
    		for (String q : ((Moderator) cur).getQualification()) {
    			out.print(q + ",");
    		}
    	} else {
    		out.print("    Karma                       :" + ((User) cur).getKarma());
    	}
    %>
    </h5>
    </pre>
    </div>